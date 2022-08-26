package com.project.review.cmd;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.item.cmd.BasicCmd;
import com.project.review.db.ReviewDAO;
import com.project.review.db.ReviewDTO;

public class SelectPerTotalReviewCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReviewDAO dao = ReviewDAO.getIns();
		HttpSession session = req.getSession();
		List<ReviewDTO> reviewDtoList =
				dao.getPerReviewDtoList((Integer)session.getAttribute("u_no"));
		
		req.setAttribute("reviewDtoList", reviewDtoList);
	}

}
