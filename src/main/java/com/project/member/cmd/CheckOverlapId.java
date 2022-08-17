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
	
		if(dao.hasId(req.getParameter("u_id"))){
			pw.write("true");
		}else {
			pw.write("false");
		}
	}

}
