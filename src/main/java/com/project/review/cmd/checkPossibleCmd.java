package com.project.review.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.project.item.cmd.BasicCmd;
import com.project.payment.db.PaymentDAO;
import com.project.review.db.ReviewDAO;

public class checkPossibleCmd implements BasicCmd {

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PaymentDAO dao = PaymentDAO.getIns();
		ReviewDAO reviewDao = ReviewDAO.getIns();
		HttpSession session = req.getSession();
		PrintWriter pw = resp.getWriter();
		JSONObject obj = null;
		try {
			int u_no = (Integer) session.getAttribute("u_no");
			int item_no = Integer.parseInt(req.getParameter("item_no"));
			obj = dao.hasPayment(u_no, item_no);
			boolean result = reviewDao.hasWriteReview((Integer) obj.get("re_p_no"));
			if (result) {
				obj = null;
				pw.print(obj);
				//트루면 이미 썼다는것임
			}else {
				pw.print(obj);
			}
		} catch (Exception e) {
			System.out.println("널예외 패스");
			obj=null;
			pw.print(obj);
		}

	}

}
