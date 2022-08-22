package com.project.item.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.mysql.cj.xdevapi.JsonArray;
import com.project.item.db.ItemsDAO;
import com.project.item.db.ItemsDTO;


public class SelectItemListCmd implements BasicCmd {

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ItemsDAO dao = ItemsDAO.getIns();
		String searchWord = req.getParameter("searchWord");
		JSONArray jsonArr = dao.getDtoList(searchWord);
		PrintWriter pw = resp.getWriter();
		pw.print(jsonArr);
		
	}
	

}
