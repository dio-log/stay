package com.project.payment.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.item.cmd.BasicCmd;
import com.project.item.db.ItemsDAO;
import com.project.member.db.MemberDAO;
import com.project.payment.db.PaymentDTO;

public class MovePayPageCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PaymentDTO dto = new PaymentDTO();
		MemberDAO memberDao = MemberDAO.getIns();
		HttpSession session = req.getSession();
	    
		String calendarInput = req.getParameter("calendarInput");
		String[] dateArr = calendarInput.split("/");
		String nightStr = dateArr[2].substring(0, 1);
		int night = Integer.parseInt(nightStr);
		
		String p_men = "성인:"+req.getParameter("p_adult")+"/아동:"+ req.getParameter("p_child")+"/펫:"+ req.getParameter("p_pet");
		
		dto.setItem_no(Integer.parseInt(req.getParameter("item_no")));
		dto.setRoom_no(Integer.parseInt(req.getParameter("room_no")));
		dto.setP_room_name(req.getParameter("p_room_name"));
		dto.setP_item_name(req.getParameter("p_item_name"));
		String p_feeStr = req.getParameter("p_fee");
		dto.setP_fee(p_feeStr);
		int p_fee =Integer.parseInt(p_feeStr);
		dto.setP_totalFee(p_fee*night+"");
		dto.setP_checkin(dateArr[0]);
		dto.setP_checkout(dateArr[1]);
		dto.setP_night(dateArr[2]);
		dto.setP_men(req.getParameter(p_men));
		
		int u_point = memberDao.getPoint((Integer)session.getAttribute("u_no"));
		
		req.setAttribute("paymentDto", dto);
		req.setAttribute("u_point",u_point);
		
		
	}

}
