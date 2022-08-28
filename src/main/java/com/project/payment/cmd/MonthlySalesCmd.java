package com.project.payment.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;

import com.project.item.cmd.BasicCmd;
import com.project.payment.db.PaymentDAO;

public class MonthlySalesCmd implements BasicCmd {

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PaymentDAO dao = PaymentDAO.getIns();
		PrintWriter pw = resp.getWriter();
		int item_no= Integer.parseInt(req.getParameter("item_no"));
		String firstDate = req.getParameter("firstDate");
		String lastDate = req.getParameter("lastDate");
		JSONArray jsonArr = dao.getMonthlySales(item_no, firstDate, lastDate);
		pw.print(jsonArr);
	}
	
}
