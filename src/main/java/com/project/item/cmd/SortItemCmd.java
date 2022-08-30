package com.project.item.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.project.item.db.ItemsDAO;
import com.project.item.db.ItemsDTO;

public class SortItemCmd implements BasicCmd {

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String sortBy = req.getParameter("sortBy");
		String searchWord = req.getParameter("searchWord");
		String item_div = req.getParameter("item_div");
		String room_theme = req.getParameter("room_theme");
		String room_extraopt = req.getParameter("room_extraopt");
		int preIdx = Integer.parseInt(req.getParameter("preIdx"));
		int minPrice = 0 ;
		int maxPrice = 999999999;
		String min = req.getParameter("minPrice");
		String max = req.getParameter("maxPrice");
		if(!(min.equals("")||min==null)) {
			minPrice = Integer.parseInt(min);
		}
		if(!(max.equals("")||max==null)) {
			maxPrice = Integer.parseInt(max);
		}
		PrintWriter pw = resp.getWriter();

		ItemsDAO dao = null;
		JSONArray jsonArr = null;
		dao = ItemsDAO.getIns();
		jsonArr = dao.getSortedItemDtoListByPrice(sortBy, searchWord, item_div, room_theme, room_extraopt, preIdx,minPrice,maxPrice);
		
		pw.print(jsonArr);
	}

}
