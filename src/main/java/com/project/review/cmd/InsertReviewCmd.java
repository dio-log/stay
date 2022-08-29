package com.project.review.cmd;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;

import com.project.item.cmd.BasicCmd;
import com.project.mdClass.MultipartCtl;
import com.project.review.db.ReviewDAO;
import com.project.review.db.ReviewDTO;

public class InsertReviewCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReviewDAO dao = ReviewDAO.getIns();
		ReviewDTO dto = new ReviewDTO();
		HttpSession session = req.getSession();
		try {
		
			dto.setU_no((Integer)session.getAttribute("u_no"));
			dto.setItem_no(Integer.parseInt(req.getParameter("re_item_no")));
			dto.setRoom_no(Integer.parseInt(req.getParameter("re_room_no")));
			dto.setRe_grade(req.getParameter("re_grade"));
			dto.setRe_title(req.getParameter("re_title"));
			dto.setRe_content(req.getParameter("re_content"));
			dto.setP_no(Integer.parseInt(req.getParameter("re_p_no")));
			dao.insertReview(dto);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
