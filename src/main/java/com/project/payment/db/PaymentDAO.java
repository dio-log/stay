package com.project.payment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.simple.JSONObject;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;

public class PaymentDAO {
	private static PaymentDAO dao = new PaymentDAO();
	private final static String DATASOURCE_NAME = "jdbc/testdb";
	private DataSource dataSource = null;
	private String query = null;
	private final String SELECT_PAYLIST_BY_UNO = "SELECT * FROM payment WHERE u_no";

	private PaymentDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/" + DATASOURCE_NAME);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static PaymentDAO getIns() {
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

	public List<PaymentDTO> getPaymentList(int u_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		// query= SELECT_PAYLIST_BY_UNO;
		query = "select a.p_no, a.item_no, a.p_checkin, a.p_checkout,"
				+ "a.p_night, a.p_fee, a.p_totalfee, a.p_point, a.p_status,a.p_wtime, b.item_name, c.room_name"
				+ " b.item_imgpath from payment a left join item b on a.item_no = b.item_no "
				+ "left join room_list c on a.room_no=c.room_no and a.item_no=c.item_no";
		ResultSet rs = null;
		PaymentDTO dto = null;
		List<PaymentDTO> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, u_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new PaymentDTO();
				dto.setP_no(rs.getInt(1));
				dto.setItem_no(rs.getInt(2));
				dto.setP_checkin(rs.getString(3));
				dto.setP_checkout(rs.getString(4));
				dto.setP_night(rs.getString(5));
				dto.setP_fee(rs.getString(6));
				dto.setP_totalFee(rs.getString(7));
				dto.setP_point(Integer.parseInt(rs.getString(8)));
				dto.setP_status(rs.getString(9));
				dto.setP_wtime(rs.getString(10));
				dto.setP_item_name(rs.getString(11));
				dto.setP_room_name(rs.getString(12));
				dto.setP_item_imgpath(rs.getString(13));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		return list;
	}

	public void insertPayment(PaymentDTO dto) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query = "insert into payment(u_no, item_no, room_no, p_name,p_phone,"
				+ " p_checkin, p_checkout, p_night,p_fee, p_totalfee,p_point) values(?,?,?,?,?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dto.getU_no());
			pstmt.setInt(2, dto.getItem_no());
			pstmt.setInt(3, dto.getRoom_no());
			pstmt.setString(4, dto.getP_name());
			pstmt.setString(5, dto.getP_phone());
			pstmt.setString(6, dto.getP_checkin());
			pstmt.setString(7, dto.getP_checkout());
			pstmt.setString(8, dto.getP_night());
			pstmt.setString(9, dto.getP_fee());
			pstmt.setString(10, dto.getP_totalFee());
			pstmt.setInt(11, dto.getP_point());

			int result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}

	}

	public void payFees(PaymentDTO dto) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query = "";
		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, dto.getRoom_no());
			pstmt.setString(2, dto.getP_checkin());
			pstmt.setString(3, dto.getP_checkout());
			pstmt.setString(4, dto.getP_fee());
			pstmt.setInt(5, dto.getP_point());
			pstmt.setString(6, "y");
			pstmt.setInt(7, dto.getP_no());
			pstmt.setString(8, dto.getP_item_name());
			pstmt.setString(9, dto.getP_phone());

			int result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}

	}

	public int getTotalPoint(int u_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = "select sum(p_point) from payment where u_no=?";
		int totalPoint = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, u_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				totalPoint = rs.getInt("sum(p_point)");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		return totalPoint;
	}

	public void deleteCart(int p_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query = "delete from payment where p_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, p_no);
			int result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}

	public List<Integer> checkDate(int checkinInput, int checkoutInput) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		List<Integer> noList = new ArrayList<>();
		query = "select room_no, p_checkin,p_checkout from payment where p_status='y'";
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String checkin = rs.getString("p_checkin");
				String checkout = rs.getString("p_checkout");
				int room_no = rs.getInt("room_no");
				long dateCheckin = dateFormat.parse(checkin).getTime();
				long dateCheckout = dateFormat.parse(checkout).getTime();
				if (dateCheckin < checkinInput && checkinInput < dateCheckout
						|| dateCheckin < checkoutInput && checkoutInput < dateCheckout) {

				} else { // 날짜가 겹치지 않는것만 넣음
					noList.add(room_no);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		finally {
			close(pstmt, conn, rs);
		}
		return noList;
	}

	public JSONObject getPayment(int u_no, int item_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		String room_name = null;
		JSONObject obj = null;
		query = "select a.room_name,a.room_no,a.item_no from room_list a left join payment b on a.room_no = b.room_no"
				+ " where b.u_no=? and b.item_no=? order by b.p_wtime desc limit 1";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, u_no);
			pstmt.setInt(2, item_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				obj = new JSONObject();
				obj.put("re_room_name", rs.getString("a.room_name"));
				obj.put("item_no", rs.getInt("a.item_no"));
				obj.put("room_no", rs.getInt("a.room_no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			close(pstmt, conn, rs);
		}
		return obj;
	}

	// 리뷰 작성전 결제 여부 체크, 확인해서 결제 넘버, 룸네임,룸넘버 반환
	// 이후 해당 결제 넘버로 리뷰테이블 조회해서 없으면 가능, 있으면 불가
	public JSONObject hasPayment(int u_no, int item_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		JSONObject obj = new JSONObject();
		query = "select a.p_no, a.room_no, b.room_name from payment a "
				+ "left join room_list b on a.room_no=b.room_no inner join review c "
				+ "on a.p_no != c.p_no where a.p_status ='n' and a.u_no=3 and a.item_no=3"
				+ " group by a.p_no";
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setInt(1, u_no);
			pstmt.setInt(2, item_no);
			rs= pstmt.executeQuery();
			while(rs.next()) {
				obj.put("p_no", rs.getInt("a.p_no"));
				obj.put("room_no", rs.getInt("a.room_no"));
				obj.put("room_name", rs.getString("b.room_name"));
			}
			} catch( SQLException e) {
				e.printStackTrace();
			} finally {
				close(pstmt, conn,rs);
			}
			
		return obj;

}}