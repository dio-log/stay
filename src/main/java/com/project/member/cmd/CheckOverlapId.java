package com.project.member.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.item.cmd.BasicCmd;
import com.project.member.db.MemberDAO;

public class CheckOverlapId implements BasicCmd {

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDAO dao = MemberDAO.getIns();
		PrintWriter pw = resp.getWriter();
	
		String u_id_value = req.getParameter("u_id_value");
		String u_nick_value = req.getParameter("u_nick_value");
		
		System.out.println("u_id_value"+u_id_value);
		System.out.println("u_nick_value"+u_nick_value);
		if(!(u_id_value==null) && !(u_id_value=="")) {
			if(dao.hasId(u_id_value)) pw.write("true");
			else pw.write("false");
		}
		else if(!(u_nick_value==null) && !(u_nick_value=="")) {
			if(dao.hasNick(u_nick_value)) pw.write("true");
			else pw.write("false");
		}
		
	}

}
