package com.project.item.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.project.item.db.ItemsDAO;
import com.project.item.db.RoomDTO;
import com.project.mdClass.MultipartCtl;

public class InsertRoomCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RoomDTO dto = new RoomDTO();
		ItemsDAO dao = ItemsDAO.getIns();
		HttpSession session = req.getSession();
		int item_no=0;
		int room_no = 0;
		MultipartCtl mpt;
		PrintWriter pw = resp.getWriter();
		
		try {
			
			mpt = new MultipartCtl(req, resp);
			
			Map<String, String> map =  mpt.saveFiles();
			int no_fk=(Integer)session.getAttribute("no");
			dto.setNo_fk(no_fk);
			dto.setRoom_name(map.get("room_name"));
			dto.setRoom_price(map.get("room_price"));
			dto.setRoom_part_price(map.get("room_part_price"));
			dto.setRoom_part_time(Integer.parseInt(map.get("room_part_time")));
			dto.setMin_men(Integer.parseInt(map.get("min_men")));
			dto.setMax_men(Integer.parseInt(map.get("max_men")));
			dto.setRoom_bed(map.get("room_bed"));
			dto.setBedcnt(Integer.parseInt(map.get("bedcnt")));
			dto.setRoom_size(Integer.parseInt(map.get("room_size")));
			dto.setRoom_view(map.get("room_view"));
			dto.setRoom_theme(map.get("room_theme"));
			dto.setRoom_extraopt(map.get("room_extraopt"));
			dto.setRoom_img_path(map.get("fullFileName"));

			
			dao.insertRoom(dto);
//			item_no = dao.getItemNoByNo(no_fk);
//			room_no = dao.getRoomNoByItemNo(no_fk, item_no);
			JSONObject obj = new JSONObject();
//			obj.put("item_no", item_no);
//			obj.put("room_no", room_no);
			obj.put("item_no", 1);
			obj.put("room_no", 2);
			obj.put("roomFirstImg", map.get("roomFirstImg"));
			obj.put("room_price", map.get("room_price"));
			obj.put("room_part_price", map.get("room_part_price"));
			obj.put("room_name", map.get("room_name"));
			System.out.println("룸네임"+map.get("roomFirstImg"));
				
			pw.write(obj.toString());
			 
		} catch (FileUploadException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch( Exception e) {
			e.printStackTrace();
		}
		
	}

}
