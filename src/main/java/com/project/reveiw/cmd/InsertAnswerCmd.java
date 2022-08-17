package com.project.reveiw.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.item.cmd.BasicCmd;
import com.project.reveiw.db.ReviewDAO;
import com.project.reveiw.db.ReviewDTO;

public class InsertAnswerCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReviewDAO dao = ReviewDAO.getIns();
		ReviewDTO dto = new ReviewDTO();
		HttpSession session = req.getSession();
		
		dto.setR_no(Integer.parseInt(req.getParameter("r_no")));
		dto.setU_no((Integer)session.getAttribute("u_no"));
		dto.setItem_no(Integer.parseInt(req.getParameter("item_no")));
		dto.setRoom_no(Integer.parseInt(req.getParameter("room_no")));
		dto.setR_content(req.getParameter("r_content"));
		dto.setR_indent(Integer.parseInt(req.getParameter("r_indent")));
		dao.insertAnswer(dto);
		
	}

}
