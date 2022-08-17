package com.project.item.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.project.item.db.ItemsDAO;
import com.project.item.db.RoomDTO;

public class SelectRoomCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ItemsDAO dao = ItemsDAO.getIns();
		PrintWriter pw = resp.getWriter();
		JSONObject obj = new JSONObject();
		RoomDTO dto = dao.selectRoom(Integer.parseInt(req.getParameter("room_no")),Integer.parseInt(req.getParameter("item_no")));
		
		obj.put("roomDTO",dto);
		pw.write(obj.toJSONString());
	}

}
