package com.project.reveiw.db;

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
		query = "select max(re_no) from review";
		
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt("max(re_no)");
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
		int maxReNo = getTotalR_no();
		query = "insert into review(re_no,u_no,item_no,room_no,re_grade,re_title,re_content,re_imgpath) values(?,?,?,?,?,?,?,?)";
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1, maxReNo+1);
			pstmt.setInt(2, dto.getU_no());
			pstmt.setInt(3, dto.getItem_no());
			pstmt.setInt(4, dto.getRoom_no());
			pstmt.setString(5, dto.getRe_grade());
			pstmt.setString(6, dto.getRe_title());
			pstmt.setString(7, dto.getRe_content());
			pstmt.setString(8, dto.getRe_imgpath());
			
			pstmt.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
	}
	//리뷰수정
	public void updateReview(ReviewDTO dto) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query = "update review set re_title=?, re_content=? where re_no=? and re_indent=?";
		try {
			pstmt =conn.prepareStatement(query);
			
			pstmt.setString(1, dto.getRe_title());
			pstmt.setString(2, dto.getRe_content());
			pstmt.setInt(3,dto.getRe_no());
			pstmt.setInt(4,dto.getRe_indent());
			
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
		query = "insert into review(re_no,u_no,item_no,room_no,re_content,re_indent) values(?,?,?,?,?,?)";
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1,dto.getRe_no());
			pstmt.setInt(2, dto.getU_no());
			pstmt.setInt(3,dto.getItem_no());
			pstmt.setInt(4,dto.getRoom_no());
			pstmt.setString(5, dto.getRe_content());
			pstmt.setInt(6, 1);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
		updateReAnswerSts(dto.getRe_no());
		
	}
	public void updateReAnswerSts(int re_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query = "update from review set re_answer_sts='y' where re_no=? and re_indent=0";
		try {
			pstmt =conn.prepareStatement(query);
			pstmt.setInt(1,re_no);
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
	}
	
	public List<ReviewDTO> getDtoList(int item_no){
		 List<ReviewDTO> list= new  ArrayList();
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query ="";
		try {
			ReviewDTO dto = new ReviewDTO();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn,rs);
		}
	}

	
	
}
