package com.project.item.db;

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

import org.apache.commons.io.filefilter.RegexFileFilter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.project.review.db.ReviewDAO;

public class ItemsDAO {
	private static ItemsDAO dao = new ItemsDAO();
	private DataSource dataSource = null;
	private final static String DATASOURCE_NAME = "jdbc/testdb";
//	private final String TABLE_NAME = "board";
	private final String SELECT_ITEM_BY_NO = "SELECT * FROM item WHERE u_no = ?";
	private final String SELECT_ALLROOM_BY_ITEMNO = "SELECT * FROM room_list WHERE item_no = ?";
	private final String SELECT_ROOM = "SELECT * FROM room_list WHERE room_no = ? and item_no=?";

	private final String INSERT_ROOM = "INSERT INTO room_list(u_no,item_no,room_no,room_name,room_price,"
			+ "room_part_price, room_part_time,room_bed,room_bed_cnt,room_size,room_view,"
			+ "room_theme,room_extraopt,basic_men,max_men,room_img_path) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String INSERT_ITEM = "INSERT INTO item_list VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private final String DELETE_ROOM_BY_ROOMNO = "DELETE FROM room_list WHERE room_no = ?";
	private final String DELETE_ITEM_BY_ITEMNO = "DELETE FROM item_list WHERE item_no = ?";

	private final String UPDATE_ROOM = "UPDATE room_list set room_name=?, room_price=?,"
			+ "room_part_price=?, room_part_time=?,room_bed=?,bedcnt=?,room_size=?,room_view=?,"
			+ "room_theme=?,room_extraopt=?,min_men=?,max_men=?,room_img_path=? WHERE room_no=? and item_no = ?";

	private final String SELECT_COUNT_ITEMNO_BY_NO = "SELECT count(item_no) FROM item_list WHERE no=?";

	private String query = null;
	private int result = 0;

	private ItemsDAO() {
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/" + DATASOURCE_NAME);
		} catch (NamingException e) {
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

	public int getItemNoByNo(int no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = SELECT_COUNT_ITEMNO_BY_NO;
		result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("count(item_no)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		return result;
	}

	public int getRoomNoByItemNo(int u_no, int itemNo) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = "select count(room_no) from room_list where item_no=? and u_no=?";
		result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, itemNo);
			pstmt.setInt(2, u_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("count(room_no)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		return result;
	}

	public void insertRoom(RoomDTO dto) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();

		int room_no = 0;

		// "INSERT INTO room_list VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		room_no = getRoomNoByItemNo(dto.getU_no(), dto.getItem_no());

		query = INSERT_ROOM;

		try {
			pstmt = conn.prepareStatement(query);

			pstmt.setInt(1, dto.getU_no());
			pstmt.setInt(2, dto.getItem_no());
			pstmt.setInt(3, ++room_no);
			pstmt.setString(4, dto.getRoom_name());
			pstmt.setInt(5, Integer.parseInt(dto.getRoom_price()));
			pstmt.setString(6, dto.getRoom_part_price());
			pstmt.setInt(7, dto.getRoom_part_time());
			pstmt.setString(8, dto.getRoom_bed());
			pstmt.setInt(9, dto.getRoom_bed_cnt());
			pstmt.setString(10, dto.getRoom_size());
			pstmt.setString(11, dto.getRoom_view());
			pstmt.setString(12, dto.getRoom_theme());
			pstmt.setString(13, dto.getRoom_extraopt());
			pstmt.setInt(14, dto.getBasic_men());
			pstmt.setInt(15, dto.getMax_men());
			pstmt.setString(16, dto.getRoom_img_path());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}

	public void updateRoom(RoomDTO dto) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();

		query = UPDATE_ROOM;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getRoom_name());
			pstmt.setInt(2, Integer.parseInt(dto.getRoom_price()));
			pstmt.setString(3, dto.getRoom_part_price());
			pstmt.setInt(4, dto.getRoom_part_time());
			pstmt.setString(5, dto.getRoom_bed());
			pstmt.setInt(6, dto.getRoom_bed_cnt());
			pstmt.setString(7, dto.getRoom_size());
			pstmt.setString(8, dto.getRoom_view());
			pstmt.setString(9, dto.getRoom_theme());
			pstmt.setString(10, dto.getRoom_extraopt());
			pstmt.setInt(11, dto.getBasic_men());
			pstmt.setInt(12, dto.getMax_men());
			pstmt.setString(13, dto.getRoom_img_path());
			pstmt.setInt(14, dto.getRoom_no());
			pstmt.setInt(15, dto.getItem_no());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}

	public void deleteRoom(int room_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query = DELETE_ROOM_BY_ROOMNO;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, room_no);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}
	}

	public RoomDTO selectRoom(int item_no, int room_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = SELECT_ROOM;
		RoomDTO dto = new RoomDTO();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, room_no);
			pstmt.setInt(2, item_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto.setRoom_name(rs.getString("room_name"));
				dto.setRoom_price("" + rs.getInt("room_price"));
				dto.setRoom_part_price(rs.getString("room_part_price"));
				dto.setRoom_part_time(rs.getInt("room_part_time"));
				dto.setBasic_men(rs.getInt("basic_men"));
				dto.setMax_men(rs.getInt("max_men"));
				dto.setRoom_bed(rs.getString("room_bed"));
				dto.setRoom_bed_cnt(rs.getInt("room_bed_cnt"));
				dto.setRoom_view(rs.getString("room_view"));
				dto.setRoom_size(rs.getString("room_size"));
				dto.setRoom_theme(rs.getString("room_theme"));
				dto.setRoom_extraopt(rs.getString("room_extraopt"));
				dto.setRoom_img_path(rs.getString("room_img_path"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		return dto;
	}

	public int getTotalItemNo() {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = "select max(item_no) from item";
		int totalItemNO = 0;
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				totalItemNO = rs.getInt("max(item_no)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		return totalItemNO;
	}

	private void modifyItemNo(int u_no, int item_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		query = "update room_list set item_no=?,room_sts='y' where item_no=0 and u_no =?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_no);
			pstmt.setInt(2, u_no);
			result = pstmt.executeUpdate();
			if (result > 0) {
				System.out.println("변동있음");
			} else {

				System.out.println("변동없음");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}

	}

	public void insertItem(ItemsDTO dto) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		// "UPDATE room_list set room_name=?, room_price=?,"
		// + "room_part_price=?,
		// room_part_time=?,room_bed=?,bedcnt=?,room_size=?,room_view=?,"
		// + "room_theme=?,room_extraopt=?,min_men=?,max_men=?,room_img_path=? WHERE
		// room_no=?";
		int totalItemNO = getTotalItemNo();
		query = "INSERT INTO item values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, dto.getU_no());
			pstmt.setInt(2, totalItemNO + 1);
			pstmt.setString(3, dto.getItem_name());
			pstmt.setString(4, dto.getItem_div());
			pstmt.setString(5, dto.getItem_addr());
			pstmt.setString(6, dto.getItem_addr_detail());
			pstmt.setString(7, dto.getItem_addr_extra());
			pstmt.setString(8, dto.getItem_event());
			pstmt.setString(9, dto.getItem_imgpath());
			pstmt.setString(10, dto.getItem_arround());
			pstmt.setString(11, dto.getItem_basic());
			pstmt.setString(12, dto.getItem_notice());
			pstmt.setString(13, dto.getItem_parking());
			pstmt.setString(14, dto.getItem_addpeople());
			pstmt.setString(15, dto.getItem_cancel_refund());
			pstmt.setString(16, "0");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn);
		}

		modifyItemNo(dto.getU_no(), totalItemNO + 1);

	}

	public JSONArray getMoreItemDtoList(String searchWord) {
		JSONArray jsonArr = new JSONArray();

		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = "select a.item_no,a.item_name,a.item_grade,a.item_imgpath,count(b.re_no), min(c.room_price) from item a left join review b on a.item_no=b.item_no left join room_list c on a.item_no=c.item_no where item_addr like '%"
				+ searchWord + "%' order by item_grade";
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("item_no", rs.getInt(1));
				obj.put("item_name", rs.getString(2));
				obj.put("item_grade", rs.getString(3));
				obj.put("item_imgpath", rs.getString(4));
				obj.put("item_reviewCnt", rs.getInt(5));
				obj.put("min_room_price", rs.getInt(6));

				jsonArr.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		return jsonArr;
	}

	public List<ItemsDTO> getItemDtoList(String searchWord) {
		List<ItemsDTO> itemDto = new ArrayList<>();
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		ReviewDAO reviewDao = ReviewDAO.getIns();
		query = "select a.item_no,a.item_name,a.item_grade,a.item_imgpath,a.item_div,(select count(b.re_no) from review b where b.item_no=a.item_no and b.re_indent=0) as cnt from item a where item_addr like '%" + searchWord
				+ "%' order by item_grade";
	
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ItemsDTO dto = new ItemsDTO();
				dto.setItem_no(rs.getInt(1));
				dto.setItem_name(rs.getString(2));
				dto.setItem_grade(rs.getString(3));
				dto.setItem_imgpath(rs.getString(4));
				dto.setItem_div(rs.getString(5));
				dto.setItem_reviewCnt(rs.getInt(6));
				dto.setItem_room_price(getRoomPrice(rs.getInt(1)));
				itemDto.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		return itemDto;
	}

	public String getRoomPrice(int item_no) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = "select room_price from room_list where item_no=? and room_no=1 ";
		String roomPrice = null;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				roomPrice = "" + rs.getInt("room_price");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		return roomPrice;
	}

	public ItemsDTO getItemDto(int item_no) {
		ItemsDTO dto = new ItemsDTO();
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = "select * from item where item_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto.setItem_name(rs.getString("item_name"));
				dto.setItem_div(rs.getString("item_div"));
				dto.setItem_addr(rs.getString("item_addr"));
				dto.setItem_addr_detail(rs.getString("item_addr_detail"));
				dto.setItem_addr_extra(rs.getString("item_addr_extra"));
				dto.setItem_event(rs.getString("item_event"));
				dto.setItem_imgpath(rs.getString("item_imgpath"));
				dto.setItem_arround(rs.getString("item_arround"));
				dto.setItem_basic(rs.getString("item_basic"));
				dto.setItem_notice(rs.getString("item_notice"));
				dto.setItem_parking(rs.getString("item_parking"));
				dto.setItem_addpeople(rs.getString("item_addpeople"));
				dto.setItem_cancel_refund(rs.getString("item_cancel_refund"));
				dto.setItem_grade(rs.getString("item_grade"));
				dto.setU_no(rs.getInt("u_no"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		return dto;
	}

	public List<RoomDTO> getRoomDtoList(int item_no) {
		List<RoomDTO> dtoList = new ArrayList<>();
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = "select * from room_list where item_no=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, item_no);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RoomDTO dto = new RoomDTO();
				dto.setRoom_no(rs.getInt("room_no"));
				dto.setRoom_name(rs.getString("room_name"));
				dto.setRoom_price("" + rs.getInt("room_price"));
				dto.setRoom_part_price(rs.getString("room_part_price"));
				dto.setRoom_part_time(rs.getInt("room_part_time"));
				dto.setBasic_men(rs.getInt("basic_men"));
				dto.setMax_men(rs.getInt("max_men"));
				dto.setRoom_bed(rs.getString("room_bed"));
				dto.setRoom_bed_cnt(rs.getInt("room_bed_cnt"));
				dto.setRoom_view(rs.getString("room_view"));
				dto.setRoom_size(rs.getString("room_size"));
				dto.setRoom_theme(rs.getString("room_theme"));
				dto.setRoom_extraopt(rs.getString("room_extraopt"));
				dto.setRoom_img_path(rs.getString("room_img_path"));
				dtoList.add(dto);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		return dtoList;
	}

	public JSONArray getSortedItemDtoListByPrice(String sortBy,String searchWord, String item_div,String room_theme, String room_extraopt, int preIdx,int minPrice,int maxPrice) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		JSONArray jsonArr = new JSONArray();
		
		if(sortBy.equals("byPrice")) {
			query = "select a.item_no, a.item_name,a.item_grade,a.item_imgpath,(select min(b.room_price) from room_list b where b.item_no=a.item_no and b.room_theme REGEXP '("+room_theme+")' and b.room_extraopt like '%"+room_extraopt+"%' ) as m,(select count(d.re_no) from review d where d.item_no=a.item_no and d.re_indent=0) as cnt, a.item_div from item a"
					+ " WHERE a.item_div REGEXP '("+item_div+")' "
							+ " and (a.item_addr like '%"+searchWord+"%'  or a.item_name like '%"+searchWord+"%')  group by a.item_no having "+minPrice+"<=m and m<="+maxPrice+" order by m limit ?,?";
		}else if(sortBy.equals("byGrade")) {
			query = "select a.item_no, a.item_name,a.item_grade,a.item_imgpath,(select min(b.room_price) from room_list b"
					+ " where b.item_no=a.item_no and b.room_theme REGEXP '("+room_theme+")' and b.room_extraopt like '%"+room_extraopt+"%') as m,"
							+ "(select count(d.re_no) from review d where d.item_no=a.item_no and d.re_indent=0) as cnt, a.item_div from item a"
					+ " WHERE a.item_div REGEXP '("+item_div+")' "
							+ " and (a.item_addr like '%"+searchWord+"%' or a.item_name like '%"+searchWord+"%') group by a.item_no having "+minPrice+"<=m and m<="+maxPrice+" order by a.item_grade desc limit ?,?";
		}else if(sortBy.equals("byReview")) {
			query = "select a.item_no, a.item_name,a.item_grade,a.item_imgpath,(select min(b.room_price) from room_list b"
					+ " where b.item_no=a.item_no and b.room_theme REGEXP '("+room_theme+")' and b.room_extraopt like '%"+room_extraopt+"%' ) as m,"
							+ "(select count(d.re_no) from review d where d.item_no=a.item_no and d.re_indent=0) as cnt, a.item_div from item a"
					+ " WHERE a.item_div REGEXP '("+item_div+")' "
							+ " and (a.item_addr like '%"+searchWord+"%' or a.item_name like '%"+searchWord+"%') group by a.item_no having "+minPrice+"<=m and m<="+maxPrice+" order by cnt desc limit ?,?";
		
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, preIdx);
			pstmt.setInt(2, (preIdx+1)*5);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				JSONObject obj = new JSONObject();
				obj.put("item_no", rs.getInt(1));
				obj.put("item_name", rs.getString(2));
				obj.put("item_grade", rs.getString(3));
				obj.put("item_imgpath", rs.getString(4));
				obj.put("minPrice", rs.getInt(5));
				obj.put("maxReview", rs.getInt(6));
				obj.put("item_div", rs.getString(7));
				jsonArr.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		
		return jsonArr;
	}
	
	public JSONObject getMaxItem(String sortBy,String searchWord, String item_div,String room_theme, String room_extraopt, int preIdx, int minPrice, int maxPrice) {
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		//item_div=	item_div.replaceAll(java.util.regex.Matcher.quoteReplacement(","), "|");
		if(sortBy.equals("byPrice")) {
			query = "select count(*), max(max), min(min) from (select a.item_no, a.item_name,a.item_grade,a.item_imgpath,(select min(b.room_price) from room_list b where b.item_no=a.item_no and b.room_theme REGEXP '("+room_theme+")' and b.room_extraopt like '%"+room_extraopt+"%' ) as min,"
					+ "(select count(d.re_no) from review d where d.item_no=a.item_no and d.re_indent=0) as cnt, a.item_div,"
					+ "(select max(b.room_price) from room_list b where b.item_no=a.item_no and b.room_theme REGEXP '("+room_theme+")' and b.room_extraopt like '%"+room_extraopt+"%' ) as max from item a"
					+ " WHERE a.item_div REGEXP '("+item_div+")' "
							+ " and (a.item_addr like '%"+searchWord+"%' or a.item_name like '%"+searchWord+"%') group by a.item_no having "+minPrice+"<=min and min<="+maxPrice+" order by min) as ab";
		}else if(sortBy.equals("byGrade")) {
			query = "select count(*), max(max), min(min) from (select a.item_no, a.item_name,a.item_grade,a.item_imgpath,(select min(b.room_price) from room_list b where b.item_no=a.item_no and b.room_theme REGEXP '("+room_theme+")' and b.room_extraopt like '%"+room_extraopt+"%' ) as min,"
					+ "(select count(d.re_no) from review d where d.item_no=a.item_no and d.re_indent=0) as cnt, a.item_div,"
					+ "(select max(b.room_price) from room_list b where b.item_no=a.item_no and b.room_theme REGEXP '("+room_theme+")' and b.room_extraopt like '%"+room_extraopt+"%' ) as max from item a"
					+ " WHERE a.item_div REGEXP '("+item_div+")' "
							+ " and (a.item_addr like '%"+searchWord+"%' or a.item_name like '%"+searchWord+"%') group by a.item_no having "+minPrice+"<=min and min<="+maxPrice+" order by a.item_grade desc) as ab";
			
		}else if(sortBy.equals("byReview")) {
			query = "select count(*), max(max), min(min) from (select a.item_no, a.item_name,a.item_grade,a.item_imgpath,(select min(b.room_price) from room_list b where b.item_no=a.item_no and b.room_theme REGEXP '("+room_theme+")' and b.room_extraopt like '%"+room_extraopt+"%' ) as min,"
					+ "(select count(d.re_no) from review d where d.item_no=a.item_no and d.re_indent=0) as cnt, a.item_div,"
					+ "(select max(b.room_price) from room_list b where b.item_no=a.item_no and b.room_theme REGEXP '("+room_theme+")' and b.room_extraopt like '%"+room_extraopt+"%' ) as max from item a"
					+ " WHERE a.item_div REGEXP '("+item_div+")' "
							+ " and (a.item_addr like '%"+searchWord+"%' or a.item_name like '%"+searchWord+"%') group by a.item_no having "+minPrice+"<=min and min<="+maxPrice+" order by cnt desc) as ab";
		}
		JSONObject obj = null;
		try {
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				obj = new JSONObject();
				obj.put("totalItem", rs.getInt("count(*)"));
				obj.put("maxPrice", rs.getInt("max(max)"));
				obj.put("minPrice", rs.getInt("min(min)"));
				System.out.println(obj.toJSONString());
			}
					
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		return obj;
	}
	
	public List<ItemsDTO> getAllItemName(int u_no){
		List<ItemsDTO> dtoList = new ArrayList<>();
		PreparedStatement pstmt = null;
		Connection conn = getConn();
		ResultSet rs = null;
		query = "select item_name,item_no from item where u_no=?";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, u_no);
			rs =pstmt.executeQuery();
			while(rs.next()) {
				ItemsDTO dto = new ItemsDTO();
				dto.setItem_name(rs.getString("item_name"));
				dto.setItem_no(rs.getInt("item_no"));
				dtoList.add(dto);
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt, conn, rs);
		}
		return dtoList;
	}
	

}