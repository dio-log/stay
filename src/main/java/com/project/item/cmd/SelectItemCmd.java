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
import com.project.reveiw.db.ReviewDAO;
import com.project.reveiw.db.ReviewDTO;

public class SelectItemCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ItemsDAO itemDao = ItemsDAO.getIns();
		ReviewDAO reviewDao = ReviewDAO.getIns();
		ItemsDTO ItemDto = null;
		List<RoomDTO> roomDtoList = null;
		List<ReviewDTO> reviewDtoList = null;
		int item_no = Integer.parseInt(req.getParameter("item_no"));
		roomDtoList = itemDao.getRoomDtoList(item_no);
		ItemDto = itemDao.getItemDto(item_no);
		reviewDtoList = reviewDao.getReviewDtoList(item_no);
		int maxAnswer = reviewDao.getMaxAnswer(item_no);
		int maxReview = reviewDao.getMaxAnswer(item_no);
		ItemDto.setItem_no(item_no);
		req.setAttribute("roomDtoList", roomDtoList);
		req.setAttribute("itemDto", ItemDto);
		req.setAttribute("reviewDtoList", reviewDtoList);
		req.setAttribute("maxAnswer", maxAnswer);
		req.setAttribute("maxReview", maxReview);
		
	}
	

}
