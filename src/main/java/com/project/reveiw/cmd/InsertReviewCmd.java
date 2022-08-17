package com.project.reveiw.cmd;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;

import com.project.item.cmd.BasicCmd;
import com.project.mdClass.MultipartCtl;
import com.project.reveiw.db.ReviewDAO;
import com.project.reveiw.db.ReviewDTO;

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
			Map map = mtc.saveFiles();
		
			dto.setU_no((Integer)session.getAttribute("u_no"));
			dto.setItem_no(Integer.parseInt(req.getParameter("item_no")));
			dto.setRoom_no(Integer.parseInt(req.getParameter("room_no")));
			dto.setR_grade(Integer.parseInt(req.getParameter("r_grade")));
			dto.setR_content(req.getParameter("r_content"));
			dto.setR_imgpath((String) (map.get("fullFileName")));
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
