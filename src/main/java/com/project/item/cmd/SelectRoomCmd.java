package com.project.item.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.project.item.db.ItemsDAO;
import com.project.item.db.RoomDTO;

public class SelectRoomCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ItemsDAO dao = ItemsDAO.getIns();
		PrintWriter pw = resp.getWriter();
		JSONObject obj = new JSONObject();
		String[] noStrArr = req.getParameter("dataSet").split("&");
		
		int item_no = Integer.parseInt(noStrArr[0]);
		int room_no = Integer.parseInt(noStrArr[1]);
		
		RoomDTO roomDto = dao.selectRoom(item_no ,room_no);
		obj.put("room_name", roomDto.getRoom_name());
		obj.put("basic_men", roomDto.getBasic_men());
		obj.put("max_men", roomDto.getMax_men());
		obj.put("room_bed", roomDto.getRoom_bed());
		obj.put("room_bed_cnt", roomDto.getRoom_bed_cnt());
		obj.put("room_view", roomDto.getRoom_view());
		obj.put("room_size", roomDto.getRoom_size());
		obj.put("room_theme", roomDto.getRoom_theme());
		obj.put("room_extraopt", roomDto.getRoom_extraopt());
		obj.put("room_img_path", roomDto.getRoom_img_path());
		pw.print(obj);
		
	}

}
