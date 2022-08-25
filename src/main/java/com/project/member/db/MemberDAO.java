package com.project.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO {
	private static MemberDAO dao = new MemberDAO();
	private DataSource dataSource = null;
	private final static String DATASOURCE_NAME = "jdbc/testdb";
	private String query =null;
	private int result=0;
	private final String TABLE_NAME = "member";
	private final String INSERT_MEMBER = "INSERT INTO "+TABLE_NAME+"(u_name,u_id,u_nick,u_pw,u_email,u_phone,"
			+ "u_gender) VALUES(?,?,?,?,?,?,?)";
	private final String UPDATE_MEMBER = "UPDATE "+TABLE_NAME+" SET u_nick=?, u_pw=?, u_email=?,u_phone=? WHERE no=?";
	private final String SELECT_NO_NICK_BY_ID = "SELECT u_no, u_nick,u_div FROM "+TABLE_NAME+" WHERE u_id=?";
	private final String SELECT_PW = "SELECT u_pw FROM "+TABLE_NAME+" WHERE u_id=?";
	private final String SELECT_MEMBER = "SELECT * FROM "+TABLE_NAME+" WHERE u_no=?";
	private MemberDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/"+DATASOURCE_NAME);
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	public static MemberDAO getIns() {
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
	public void insertMember(MemberDTO dto) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query = INSERT_MEMBER;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getU_name());
			pstmt.setString(2, dto.getU_id());
			pstmt.setString(3, dto.getU_nick());
			pstmt.setString(4, dto.getU_pw());
			pstmt.setString(5, dto.getU_email());
			pstmt.setString(6, dto.getU_phone());
			pstmt.setString(7, dto.getU_gender());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
	}
	
	public MemberDTO getNoAndNick(String u_id) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = SELECT_NO_NICK_BY_ID;
		// "SELECT u_no,u_nick FROM "+TABLE_NAME+" WHERE u_id=?";
		MemberDTO dto = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, u_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
					dto = new MemberDTO();
					dto.setU_no(rs.getInt("u_no"));
					dto.setU_nick(rs.getString("u_nick"));
					dto.setU_div(rs.getInt("u_div"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn, rs);
		}
		return dto;
					
	}
	
	public boolean hasId(String u_id) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = "SELECT u_id FROM MEMBER WHERE u_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, u_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn, rs);
		}
		return false;
	}
	public boolean hasNick(String u_nick) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = "SELECT u_nick FROM MEMBER WHERE u_nick=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, u_nick);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn, rs);
		}
		return false;
	}
	
	public boolean hasMember(String u_id,String u_pw) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = SELECT_PW;
			//"SELECT u_pw FROM member WHERE u_id=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, u_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				if(u_pw.equals(rs.getString("u_pw"))) return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn, rs);
		}
		return false;
	}
	
	public MemberDTO getMemberInfo(int u_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		MemberDTO dto = null;
		query = SELECT_MEMBER;
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, u_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new MemberDTO();
				dto.setU_name(rs.getString("u_name"));
				dto.setU_id(rs.getString("u_id"));
				dto.setU_email(rs.getString("u_email"));
				dto.setU_phone(rs.getString("u_phone"));
				dto.setU_gender(rs.getString("u_gender"));	
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn, rs);
		}
		return dto;
	}
	
	public void updateMember(MemberDTO dto) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query = UPDATE_MEMBER;
		//"UPDATE "+TABLE_NAME+" SET u_nick=?, u_pw=?, u_email=?,u_phone=? WHERE no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getU_nick());
			pstmt.setString(2, dto.getU_pw());
			pstmt.setString(3, dto.getU_email());
			pstmt.setString(4, dto.getU_phone());
			pstmt.setInt(5, dto.getU_no());
			pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
	}
	public boolean checkPw(int u_no, String u_pw) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = "select u_pw from member where u_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, u_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				if(rs.getString("u_pw").equals(u_pw)) return true;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn,rs);
		}
		return false;
	}
	
	public int getPoint(int u_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		int u_point = 0;
		query = "select u_point from member where u_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, u_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				u_point = rs.getInt("u_point");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn,rs);
		}
		return u_point;
	}
}
