package com.project.member.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.item.cmd.BasicCmd;
import com.project.member.db.MemberDAO;

public class CheckPwCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MemberDAO dao = MemberDAO.getIns();
		PrintWriter pw = resp.getWriter();
		String pwValue = req.getParameter("pwValue").trim();
		HttpSession session = req.getSession();
		int u_no = (Integer)session.getAttribute("u_no");
		boolean result = dao.checkPw(u_no, pwValue);
		if(result) {
			pw.write("true");
		}else {
			pw.write("false");
		}
		
		
	}
	

}
