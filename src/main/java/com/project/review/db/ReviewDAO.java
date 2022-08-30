package com.project.review.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ReviewDAO {
	private static ReviewDAO dao = new ReviewDAO();
	private DataSource dataSource = null;
	private final static String DATASOURCE_NAME = "jdbc/testdb";
	private String query = null;

	private ReviewDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/" + DATASOURCE_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static ReviewDAO getIns() {
		return dao;
	}

	public Connection getConn() {
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void close(PreparedStatement pstmt, Connection conn) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("콘객체 닫기 실패");
		}
	}

	public void close(PreparedStatement pstmt, Connection conn, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("콘객체 닫기 실패");
		}
	}

	public int getTotalR_no() {
		int result = 0;
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = "select max(re_no) from review";

		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("max(re_no)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}

		return result;
	}

	public void insertReview(ReviewDTO dto) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		int maxReNo = getTotalR_no();
		if (dto.getRe_grade() == "" || dto.getRe_grade() == null) {
			dto.setRe_grade("0");
		}
		int result = 0;
		query = "insert into review(re_no,u_no,item_no,room_no,re_grade,re_title,re_content,p_no) values(?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, maxReNo + 1);
			pstmt.setInt(2, dto.getU_no());
			pstmt.setInt(3, dto.getItem_no());
			pstmt.setInt(4, dto.getRoom_no());
			pstmt.setString(5, dto.getRe_grade());
			pstmt.setString(6, dto.getRe_title());
			pstmt.setString(7, dto.getRe_content());
			pstmt.setInt(8, dto.getP_no());
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
		if (result > 0) {
			updateItemGrade(dto.getItem_no());
			System.out.println("아이템그레이드");
		}
	}

	// 리뷰수정
	public void updateReview(ReviewDTO dto) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query = "update review set re_title=?, re_content=? where re_no=? and re_indent=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getRe_title());
			pstmt.setString(2, dto.getRe_content());
			pstmt.setInt(3, dto.getRe_no());
			pstmt.setInt(4, dto.getRe_indent());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}

	public void insertAnswer(ReviewDTO dto) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query = "insert into review(re_no,u_no,item_no,re_content,re_indent) values(?,?,?,?,?)";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dto.getRe_no());
			pstmt.setInt(2, dto.getU_no());
			pstmt.setInt(3, dto.getItem_no());
			pstmt.setString(4, dto.getRe_content());
			pstmt.setInt(5, 1);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
		if (result > 0) {
			updateReAnswerSts(dto.getRe_no());
		}

	}

	public void updateReAnswerSts(int re_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query = "update review set re_answer_sts='y' where re_no=? and re_indent=0";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, re_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}

	public List<ReviewDTO> getReviewDtoList(int item_no) {
		List<ReviewDTO> dtoList = new ArrayList();
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;

		query = "select a.re_no, a.re_grade, a.re_title, a.re_content, a.re_wtime, b.room_name,c.u_nick,a.re_indent"
				+ " from review a left join room_list b on a.room_no = b.room_no left join member"
				+ " c on a.u_no=c.u_no where a.item_no=? group by a.re_wtime order by a.re_no, a.re_indent";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				String grade = rs.getString(2);

				double re_grade = Double.parseDouble(grade);

				if (re_grade * 10 % 10 < 3) {
					re_grade = Math.floor(re_grade);
				} else if (re_grade * 10 % 10 >= 3 && re_grade * 10 % 10 < 8) {
					re_grade = Math.floor(re_grade) + 0.5;
				} else if (re_grade * 10 % 10 >= 8) {
					re_grade = Math.ceil(re_grade);
				}

				dto.setRe_no(rs.getInt(1));
				dto.setRe_grade("" + re_grade * 10);
				dto.setRe_title(rs.getString(3));
				dto.setRe_content(rs.getString(4));
				dto.setRe_wtime(rs.getString(5));
				dto.setRe_room_name(rs.getString(6));
				dto.setRe_u_nick(rs.getString(7));
				dto.setRe_indent(rs.getInt(8));

				dtoList.add(dto);
				System.out.println("컨텐츠   " + rs.getString(4));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		return dtoList;
	}

	public int getMaxAnswer(int item_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		int maxAnswer = 0;
		query = "select count(re_no) from review where item_no=? and re_indent=1";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				maxAnswer = rs.getInt("count(re_no)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		return maxAnswer;
	}

	public int getMaxReview(int item_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		int maxReview = 0;
		query = "select count(re_no) from review where item_no=? and re_indent=0";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				maxReview = rs.getInt("count(re_no)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		return maxReview;
	}

	public List<ReviewDTO> getPerReviewDtoList(int u_no) {
		List<ReviewDTO> dtoList = new ArrayList<>();
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		// 아이템 네임, 룸네임
		query = "select a.item_no, a.re_grade, a.re_title, a.re_wtime, b.item_name from review a"
				+ " left join item b on a.item_no=b.item_no where a.u_no=? order by re_no desc";
		// query="select a.item_no, a.re_grade, a.re_title, a.re_wtime, b.item_name from
		// review a left join item b on a.item_no=b.item_no where a.u_no=? order by
		// re_no desc";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, u_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 아이템이름, 타이틀, 평점 작성시간
				ReviewDTO dto = new ReviewDTO();
				dto.setItem_no(rs.getInt(1));
				dto.setRe_grade(rs.getString(2));
				dto.setRe_title(rs.getString(3));
				dto.setRe_wtime(rs.getString(4));
				dto.setRe_item_name(rs.getString(5));
				dtoList.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		return dtoList;
	}

	private void updateItemGrade(int item_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query = "update item set item_grade = (select round(avg(re_grade),1) from review where item_no=? and re_indent=0) where item_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_no);
			pstmt.setInt(2, item_no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}

	}

	public boolean hasWriteReview(int p_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = "select p_no from review where p_no=?";
		boolean flag = false;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, p_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		return flag;
	}
}
