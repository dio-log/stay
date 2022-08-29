package com.project.review.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.item.cmd.BasicCmd;
import com.project.review.db.ReviewDAO;
import com.project.review.db.ReviewDTO;

public class InsertAnswerCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReviewDAO dao = ReviewDAO.getIns();
		ReviewDTO dto = new ReviewDTO();
		HttpSession session = req.getSession();
		
		dto.setU_no((Integer)session.getAttribute("u_no"));
		dto.setRe_no(Integer.parseInt(req.getParameter("answer_re_no")));
		dto.setItem_no(Integer.parseInt(req.getParameter("answer_item_no")));
		dto.setRe_content(req.getParameter("answerContent"));
		dao.insertAnswer(dto);
		
	}

}
