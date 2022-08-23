package com.project.reveiw.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.item.cmd.BasicCmd;
import com.project.reveiw.db.ReviewDAO;
import com.project.reveiw.db.ReviewDTO;

public class UpdateReviewCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReviewDAO dao = ReviewDAO.getIns();
		ReviewDTO dto = new ReviewDTO();
		HttpSession session = req.getSession();
		
		dto.setRe_content(req.getParameter("r_content"));
		dto.setRe_no(Integer.parseInt(req.getParameter("r_no")));
		dto.setRe_indent(Integer.parseInt(req.getParameter("r_indent")));
		dao.updateReview(dto);
	}

}
