package com.project.reveiw.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
			dataSource = (DataSource) context.lookup("java:comp/env/"+DATASOURCE_NAME);
		}catch (NamingException e) {
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
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void close(PreparedStatement pstmt, Connection conn) {
		try {
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("콘객체 닫기 실패");
		}
	}
	public void close(PreparedStatement pstmt, Connection conn,ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("콘객체 닫기 실패");
		}
	}
	
	public int getTotalR_no() {
		int result=0;
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = "select count(r_no) from review";
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt("count(r_no)");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn, rs);
		}
		
		return result;
	}
	
	public void insertReview(ReviewDTO dto) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		int totalR_no = getTotalR_no();
		query = "insert into review(r_no,u_no,item_no,room_no,r_grade,r_content,r_imgpath) values(?,?,?,?,?,?,?)";
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1, totalR_no);
			pstmt.setInt(2, dto.getU_no());
			pstmt.setInt(3, dto.getItem_no());
			pstmt.setInt(4, dto.getRoom_no());
			pstmt.setInt(5, dto.getR_grade());
			pstmt.setString(6, dto.getR_content());
			pstmt.setString(7, dto.getR_imgpath());
			
			pstmt.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
	}
	
	public void updateReview(ReviewDTO dto) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query = "update review set r_content=? where r_no=? and r_indent=?";
		try {
			pstmt =conn.prepareStatement(query);
			
			pstmt.setString(1, dto.getR_content());
			pstmt.setInt(2,dto.getR_no());
			pstmt.setInt(3,dto.getR_indent());
			
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
	}
	
	public void insertAnswer(ReviewDTO dto) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		setAnswerColumn(dto.getR_no(), dto.getR_indent());
		query = "insert into review(r_no,u_no,item_no,room_no,r_content,r_indent) values(?,?,?,?,?,?)";
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1,dto.getR_no());
			pstmt.setInt(2, dto.getU_no());
			pstmt.setInt(3,dto.getItem_no());
			pstmt.setInt(4,dto.getRoom_no());
			pstmt.setString(5, dto.getR_content());
			pstmt.setInt(6, dto.getR_indent()+1);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
		
	}
	
	public void setAnswerColumn(int r_no, int r_indent) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query = "update review set r_answer=? where r_no=? and r_indent=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1,"y");
			pstmt.setInt(2, r_no);
			pstmt.setInt(3, r_indent);
			pstmt.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
	}
	
	
}
