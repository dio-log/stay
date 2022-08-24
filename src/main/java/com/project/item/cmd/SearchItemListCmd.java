package com.project.item.cmd;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.item.db.ItemsDAO;
import com.project.item.db.ItemsDTO;
import com.project.reveiw.db.ReviewDAO;

public class SearchItemListCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ItemsDAO dao = ItemsDAO.getIns();
		String searchWord = req.getParameter("searchWord");
		List<ItemsDTO> itemDtoList =  dao.getItemDtoList(searchWord);
		if(itemDtoList.size()==0) {
			itemDtoList=null;
		}
	System.out.println(itemDtoList);
	
		req.setAttribute("searchWord", searchWord);
		req.setAttribute("itemDtoList", itemDtoList);
	}

}
