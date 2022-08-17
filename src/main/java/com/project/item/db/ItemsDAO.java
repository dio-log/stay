package com.project.item.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ItemsDAO {
	private static ItemsDAO dao = new ItemsDAO();
	private DataSource dataSource =null;
	private final static String DATASOURCE_NAME = "jdbc/testdb";
//	private final String TABLE_NAME = "board";
	private final String SELECT_ITEM_BY_NO = "SELECT * FROM item_list WHERE no_fk = ?";
	private final String SELECT_ALLROOM_BY_ITEMNO = "SELECT * FROM room_list WHERE item_no_fk = ?";
	private final String SELECT_ROOM = "SELECT * FROM room_list WHERE room_no = ? and item_no=?";
	
	private final String INSERT_ROOM= "INSERT INTO room_list(no_fk,item_no,room_no,room_name,room_price,"
			+ "room_part_price, room_part_time,room_bed,bedcnt,room_size,room_view,"
			+ "room_theme,room_extraopt,min_men,max_men,room_img_path) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String INSERT_ITEM = "INSERT INTO item_list VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private final String DELETE_ROOM_BY_ROOMNO="DELETE FROM room_list WHERE room_no = ?";
	private final String DELETE_ITEM_BY_ITEMNO="DELETE FROM item_list WHERE item_no = ?";
	
	private final String UPDATE_ROOM="UPDATE room_list set room_name=?, room_price=?,"
			+ "room_part_price=?, room_part_time=?,room_bed=?,bedcnt=?,room_size=?,room_view=?,"
			+ "room_theme=?,room_extraopt=?,min_men=?,max_men=?,room_img_path=? WHERE room_no=? and item_no = ?";
	
	private final String SELECT_COUNT_ITEMNO_BY_NO = "SELECT count(item_no) FROM item_list WHERE no=?";
	
	
	private String query =null;
	private int result=0;
	
	private ItemsDAO(){
		try {
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/"+DATASOURCE_NAME);
		}catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public static ItemsDAO getIns() {
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
	
	public int getItemNoByNo(int no) {
		PreparedStatement pstmt =null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = SELECT_COUNT_ITEMNO_BY_NO;
		result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt("count(item_no)");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn, rs);
		}
		return result;
	}
	public int getRoomNoByItemNo(int no_fk,int itemNo) {
		PreparedStatement pstmt =null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = "select count(room_no) from room_list where item_no=? and no_fk=?";
		result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, itemNo);
			pstmt.setInt(2, no_fk);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt("count(room_no)");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt, conn, rs);
		}
		return result;
	}
	
	
	public void insertRoom(RoomDTO dto) {
		PreparedStatement pstmt =null;
		Connection conn = getConn();
//		query = "INSERT INTO room_list VALUES("+dto.getNo_fk()+","+dto.getItem_no()+","+99+","+dto.getRoom_name()+
//				","+dto.getRoom_price()+","+dto.getRoom_part_price()+","+dto.getRoom_part_time()+","+dto.getRoom_bed()+","+dto.getBedcnt()+
//				","+dto.getRoom_size()+","+dto.getRoom_view()+","+dto.getRoom_theme()+","+dto.getRoom_extraopt()+","+dto.getMin_men()+","+
//				dto.getMax_men()+","+dto.getRoom_img_path()+")";
		
		int room_no=0;
		
		// "INSERT INTO room_list VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		room_no = getRoomNoByItemNo(dto.getNo_fk(), dto.getItem_no());
		
		query = INSERT_ROOM;
	
		try {
			pstmt = conn.prepareStatement(query);
		
			pstmt.setInt(1, dto.getNo_fk());
			pstmt.setInt(2, dto.getItem_no());
			pstmt.setInt(3, ++room_no);
			pstmt.setString(4, dto.getRoom_name());
			pstmt.setString(5, dto.getRoom_price());
			pstmt.setString(6, dto.getRoom_part_price());
			pstmt.setInt(7, dto.getRoom_part_time());
			pstmt.setString(8, dto.getRoom_bed());
			pstmt.setInt(9, dto.getBedcnt());
			pstmt.setInt(10, dto.getRoom_size());
			pstmt.setString(11, dto.getRoom_view());
			pstmt.setString(12, dto.getRoom_theme());
			pstmt.setString(13, dto.getRoom_extraopt());
			pstmt.setInt(14, dto.getMin_men());
			pstmt.setInt(15, dto.getMax_men());
			pstmt.setString(16, dto.getRoom_img_path());
			
			pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstmt,conn);
		}
	}
	
	public void updateRoom(RoomDTO dto) {
		PreparedStatement pstmt =null;
		Connection conn = getConn();
		//"UPDATE room_list set room_name=?, room_price=?,"
		//+ "room_part_price=?, room_part_time=?,room_bed=?,bedcnt=?,room_size=?,room_view=?,"
		//+ "room_theme=?,room_extraopt=?,min_men=?,max_men=?,room_img_path=? WHERE room_no=?";
		query = UPDATE_ROOM;
		
		try {
			pstmt= conn.prepareStatement(query);
			pstmt.setString(1,dto.getRoom_name());
			pstmt.setString(2,dto.getRoom_price());
			pstmt.setString(3,dto.getRoom_part_price());
			pstmt.setInt(4,dto.getRoom_part_time());
			pstmt.setString(5,dto.getRoom_bed());
			pstmt.setInt(6,dto.getBedcnt());
			pstmt.setInt(7,dto.getRoom_size());
			pstmt.setString(8,dto.getRoom_view());
			pstmt.setString(9,dto.getRoom_theme());
			pstmt.setString(10,dto.getRoom_extraopt());
			pstmt.setInt(11,dto.getMin_men());
			pstmt.setInt(12,dto.getMax_men());
			pstmt.setString(13,dto.getRoom_img_path());
			pstmt.setInt(14,dto.getRoom_no());
			pstmt.setInt(15,dto.getItem_no());
			
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteRoom(int room_no) {
		PreparedStatement pstmt =null;
		Connection conn = getConn();
		query = DELETE_ROOM_BY_ROOMNO;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, room_no);
			result = pstmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public RoomDTO selectRoom(int room_no,int item_no) {
		PreparedStatement pstmt =null;
		Connection conn = getConn();
		ResultSet rs =null;
		query = SELECT_ROOM;
		RoomDTO dto =  new RoomDTO();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, room_no);
			pstmt.setInt(2, item_no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				dto.setRoom_name(rs.getString("room_name"));
				dto.setRoom_price(rs.getString("room_price"));
				dto.setRoom_part_price(rs.getString("room_part_price"));
				dto.setRoom_part_time(rs.getInt("room_part_time"));
				dto.setMin_men(rs.getInt("min_men"));
				dto.setMax_men(rs.getInt("max_men"));
				dto.setRoom_bed(rs.getString("room_bed"));
				dto.setBedcnt(rs.getInt("bedcnt"));
				dto.setRoom_view(rs.getString("room_view"));
				dto.setRoom_size(rs.getInt("room_price"));
				dto.setRoom_theme(rs.getString("room_theme"));
				dto.setRoom_extraopt(rs.getString("room_extraopt"));
				dto.setRoom_img_path(rs.getString("room_img_path"));
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return dto;
	}
}
