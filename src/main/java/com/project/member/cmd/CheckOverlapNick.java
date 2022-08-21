package com.project.member.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.item.cmd.BasicCmd;
import com.project.member.db.MemberDAO;

public class CheckOverlapNick implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MemberDAO dao = MemberDAO.getIns();
		PrintWriter pw = resp.getWriter();
		if(dao.hasNick(req.getParameter("nickValue").trim())){
			pw.print(true);
		}else {
			pw.print(false);
		}
		
	}

}
