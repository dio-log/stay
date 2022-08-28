package com.project.my.cmd;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.item.cmd.BasicCmd;
import com.project.item.db.ItemsDAO;
import com.project.item.db.ItemsDTO;

public class MyItemManageCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ItemsDAO dao = ItemsDAO.getIns();
		HttpSession session  = req.getSession();
		List<ItemsDTO> itemDtoList = dao.getAllItemName((Integer)session.getAttribute("u_no"));
		
		req.setAttribute("itemDtoList", itemDtoList);
	}

}
