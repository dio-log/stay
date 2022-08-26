package com.project.item.cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.item.db.ItemsDAO;
import com.project.item.db.ItemsDTO;
import com.project.item.db.RoomDTO;
import com.project.review.db.ReviewDAO;
import com.project.review.db.ReviewDTO;

public class SelectItemCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ItemsDAO itemDao = ItemsDAO.getIns();
		ReviewDAO reviewDao = ReviewDAO.getIns();
		ItemsDTO itemDto = null;
		List<RoomDTO> roomDtoList = null;
		List<ReviewDTO> reviewDtoList = null;
		
		int item_no = Integer.parseInt(req.getParameter("item_no"));
		
		roomDtoList = itemDao.getRoomDtoList(item_no);
		itemDto = itemDao.getItemDto(item_no);
		reviewDtoList = reviewDao.getReviewDtoList(item_no);
		int maxAnswer = reviewDao.getMaxAnswer(item_no);
		int maxReview = reviewDao.getMaxReview(item_no);
		itemDto.setItem_no(item_no);
		
		double item_grade = Double.parseDouble(itemDto.getItem_grade());
		if(item_grade*10%10<3) {
			 item_grade=Math.floor(item_grade);
		}else if(item_grade*10%10>=3 && item_grade*10%10<8) {
			item_grade=Math.floor(item_grade)+0.5;
		}else if(item_grade*10%10>=8) {
			item_grade = Math.ceil(item_grade);
		}
		itemDto.setItem_grade(""+item_grade*10);
		
		req.setAttribute("roomDtoList", roomDtoList);
		req.setAttribute("itemDto", itemDto);
		req.setAttribute("reviewDtoList", reviewDtoList);
		req.setAttribute("maxAnswer", maxAnswer);
		req.setAttribute("maxReview", maxReview);
		
	}
	

}
