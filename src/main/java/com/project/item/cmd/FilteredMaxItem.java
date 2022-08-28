package com.project.item.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.project.item.db.ItemsDAO;

public class FilteredMaxItem implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String sortBy = req.getParameter("sortBy");
		String searchWord = req.getParameter("searchWord");
		String item_div = req.getParameter("item_div");
		String room_theme = req.getParameter("room_theme");
		String room_extraopt = req.getParameter("room_extraopt");
		int preIdx = Integer.parseInt(req.getParameter("preIdx"));
		PrintWriter pw = resp.getWriter();
		
		ItemsDAO dao = null;
		dao = ItemsDAO.getIns();
		int maxItem = dao.getMaxItem(sortBy, searchWord, item_div, room_theme, room_extraopt, preIdx);
		pw.print(maxItem);
	}

}
