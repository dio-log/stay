package com.project.item.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.mysql.cj.xdevapi.JsonArray;
import com.project.item.db.ItemsDAO;
import com.project.item.db.ItemsDTO;

//ajax로 more 쓸떄
public class SelectMoreItemListCmd implements BasicCmd {

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ItemsDAO dao = ItemsDAO.getIns();
		String searchWord = req.getParameter("searchWord");
		System.out.println("서치워드"+searchWord);
		JSONArray jsonArr = dao.getMoreItemDtoList(searchWord);
		System.out.println("서치워드"+searchWord);
		JSONObject obj = new JSONObject();
		obj.put("searchWord", searchWord);
		jsonArr.add(obj);
		PrintWriter pw = resp.getWriter();
		pw.print(jsonArr);
		
	
	
		
	}
	

}
