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
		MultipartCtl mtc;
		try {
			mtc = new MultipartCtl(req, resp);
			Map map = mtc.saveReviewImg();
		
			dto.setU_no((Integer)session.getAttribute("u_no"));
			dto.setItem_no((Integer)map.get("item_no"));
			dto.setRoom_no((Integer)map.get("room_no"));
			dto.setRe_grade((String)map.get("re_grade"));
			dto.setRe_grade((String)map.get("re_title"));
			dto.setRe_content((String)map.get("re_content"));
			dto.setRe_imgpath((String)(map.get("fullFileName")));
			dao.insertReview(dto);
		} catch (FileUploadException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("mtp에러");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
