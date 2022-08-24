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

	public List<PaymentDTO> getPaymentList(int u_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		//query= SELECT_PAYLIST_BY_UNO;
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
			while(rs.next()) {
				dto = new PaymentDTO();
				dto.setP_no(rs.getInt(1));
				dto.setItem_no(rs.getInt(2));
				dto.setP_checkin(rs.getString(3));
				dto.setP_checkout(rs.getString(4));
				dto.setP_night(rs.getInt(5));
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
		} catch( SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn, rs);
		}
		return list;
	}
	

	public void insertPayment(PaymentDTO dto) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query= "insert into payment(u_no, item_no, room_no, p_name,p_phone,"
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
			pstmt.setInt(8, dto.getP_night());
			pstmt.setString(9, dto.getP_fee());
			pstmt.setString(10, dto.getP_totalFee());
			pstmt.setInt(11, dto.getP_point());
			
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
		query="";
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
			
		}catch( SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn);
		}
		
	}
	public int getTotalPoint(int u_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query="select sum(p_point) from payment where u_no=?";
		int totalPoint = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, u_no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				totalPoint= rs.getInt("sum(p_point)");
			}
			
		}catch( SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn,rs);
		}
		return totalPoint;
	}
	

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
