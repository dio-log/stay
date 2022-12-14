package com.project.member.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.item.cmd.BasicCmd;
import com.project.member.db.MemberDAO;

public class SelectPointCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MemberDAO dao = MemberDAO.getIns();
		HttpSession session = req.getSession();
		int u_point = dao.getPoint((Integer)session.getAttribute("u_no"));
		req.setAttribute("u_point", u_point);
	}
 
}
