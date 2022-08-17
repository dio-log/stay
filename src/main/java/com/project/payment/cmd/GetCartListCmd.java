package com.project.payment.cmd;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.item.cmd.BasicCmd;
import com.project.payment.db.PaymentDAO;
import com.project.payment.db.PaymentDTO;

public class GetCartListCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PaymentDAO dao = PaymentDAO.getIns();
		List<PaymentDTO> cartList = dao.getCartList(Integer.parseInt(req.getParameter("u_no")));
		
		req.setAttribute("cartList", cartList);
	}

}
