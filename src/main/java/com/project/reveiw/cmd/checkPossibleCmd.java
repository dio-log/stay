package com.project.reveiw.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.project.item.cmd.BasicCmd;
import com.project.payment.db.PaymentDAO;
import com.project.reveiw.db.ReviewDTO;

public class checkPossibleCmd implements BasicCmd {

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PaymentDAO dao = PaymentDAO.getIns();
		HttpSession session = req.getSession();
		PrintWriter pw = resp.getWriter();
		JSONObject obj = null;
		try {
			int u_no = (Integer) session.getAttribute("u_no");
			int item_no = Integer.parseInt(req.getParameter("item_no"));
			obj = dao.getPayment(u_no, item_no);
		}catch(Exception e) {
			System.out.println("널예외 패스");
		}finally {
			pw.print(obj);
		}
	
		
	}

}
