package com.project.member.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.item.cmd.BasicCmd;
import com.project.member.db.MemberDAO;

public class UpdatePickCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MemberDAO dao = MemberDAO.getIns();
		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession();
		int result = 0;
		String signal = req.getParameter("signal");
		String pick_item_no = req.getParameter("item_no");
		if(signal.equals("add")) {
			result = dao.updatePick((Integer)session.getAttribute("u_no"), pick_item_no);
		}else if(signal.equals("del")) {
			result = dao.deletePick((Integer)session.getAttribute("u_no"), pick_item_no);
		}
		pw.print(result);
	}
}
