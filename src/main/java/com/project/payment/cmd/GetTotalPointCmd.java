package com.project.payment.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.item.cmd.BasicCmd;
import com.project.payment.db.PaymentDAO;

public class GetTotalPointCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PaymentDAO dao = PaymentDAO.getIns();
		HttpSession session = req.getSession();
		int totalPoint = dao.getTotalPoint((Integer)session.getAttribute("u_no"));
		req.setAttribute("totalPoint", totalPoint);
	}
	
}
