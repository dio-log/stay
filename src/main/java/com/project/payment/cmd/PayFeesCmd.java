package com.project.payment.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.item.cmd.BasicCmd;
import com.project.payment.db.PaymentDAO;
import com.project.payment.db.PaymentDTO;

public class PayFeesCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PaymentDAO dao = PaymentDAO.getIns();
		PaymentDTO dto = new PaymentDTO();
		HttpSession session = req.getSession();
		
		dto.setRoom_no(Integer.parseInt(req.getParameter("room_no")));
		dto.setP_checkin(req.getParameter("checkin"));
		dto.setP_checkout(req.getParameter("checkout"));
		dto.setP_pay(Integer.parseInt(req.getParameter("p_pay")));
		dto.setP_point(Integer.parseInt(req.getParameter("p_point")));
		dto.setP_no(((Integer)session.getAttribute("u_no")));
		dto.setP_name(req.getParameter("p_name"));
		dto.setP_phone(req.getParameter("p_phone"));
		
	}

}
