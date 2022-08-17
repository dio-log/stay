package com.project.payment.db;

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

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;

public class PaymentDAO {
	private static PaymentDAO dao = new PaymentDAO();
	private final static String DATASOURCE_NAME = "jdbc/testdb";
	private DataSource dataSource = null;
	private String query=null;
	private final String SELECT_PAYLIST_BY_UNO =  "SELECT * FROM payment WHERE u_no";


	private PaymentDAO(){
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/"+DATASOURCE_NAME);
		}catch(NamingException e) {
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

	public List<PaymentDTO> getCartList(int u_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query= SELECT_PAYLIST_BY_UNO;
		ResultSet rs = null;
		PaymentDTO dto = null;
		List<PaymentDTO> list = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, u_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto = new PaymentDTO();
				dto.setP_no(rs.getInt("p_no"));
				dto.setItem_no(rs.getInt("item_no"));
				dto.setRoom_no(rs.getInt("room_no"));
				dto.setP_checkin(rs.getString("p_checkin"));
				dto.setP_checkout(rs.getString("p_checkout"));
				dto.setP_pay(rs.getInt("p_pay"));
				dto.setP_point(rs.getInt("p_point"));
				dto.setP_wtime(rs.getString("p_wtime"));
				list.add(dto);
			}
		} catch( SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn, rs);
		}
		return list;
	}
	
	//바로결제시 이 메서드 호출후 업데이트로 결제넘어가면됨
	public void insertCart(PaymentDTO dto) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query= "insert into payment(u_no, item_no, room_no, p_checkin, p_checkout, p_pay,p_point) values(?,?,?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dto.getU_no());
			pstmt.setInt(2, dto.getItem_no());
			pstmt.setInt(3, dto.getRoom_no());
			pstmt.setString(4, dto.getP_checkin());
			pstmt.setString(5, dto.getP_checkout());
			pstmt.setInt(6, dto.getP_pay());
			pstmt.setInt(7, dto.getP_point());
			int result = pstmt.executeUpdate();
			
		}catch( SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
		
	}
	
	public void payFees(PaymentDTO  dto) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query= "update payment room_no=?, p_checkin=?, p_checkout=?, p_pay=?,p_point=?, p_status=?,p_name=?,p_phone=? WHERE p_no=?" ;
		try {
			pstmt = conn.prepareStatement(query);
		
			pstmt.setInt(1, dto.getRoom_no());
			pstmt.setString(2, dto.getP_checkin());
			pstmt.setString(3, dto.getP_checkout());
			pstmt.setInt(4, dto.getP_pay());
			pstmt.setInt(5, dto.getP_point());
			pstmt.setString(6, "y");
			pstmt.setInt(7, dto.getP_no());
			pstmt.setString(8, dto.getP_name());
			pstmt.setString(9, dto.getP_phone());
			
			int result = pstmt.executeUpdate();
			
		}catch( SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
		
	}
	
//	public int getP_no(PaymentDTO  dto) {
//		PreparedStatement pstmt = null;
//		Connection conn = getConn();
//		ResultSet rs = null;
//		query= "select p_no from payment where u_no=? and item_no=? and room_no=?";
//		int p_no = 0;
//		try {
//			pstmt = conn.prepareStatement(query);
//			pstmt.setInt(1, dto.getU_no());
//			pstmt.setInt(1, dto.getItem_no());
//			pstmt.setInt(1, dto.getRoom_no());
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				p_no = rs.getInt("p_no");
//			}
//			
//		}catch( SQLException e) {
//			e.printStackTrace();
//		}finally {
//			close(pstmt, conn,rs);
//		}
//		return p_no;
//	}

	public void deleteCart(int p_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query= "delete from payment where p_no=?" ;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, p_no);
			int result = pstmt.executeUpdate();
			
		}catch( SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
	}

}
