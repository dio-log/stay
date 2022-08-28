package com.project.member.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.item.cmd.BasicCmd;

public class BeLoginCheckCmd implements BasicCmd {

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session  = req.getSession();
		PrintWriter pw = resp.getWriter();
		Object obj =session.getAttribute("u_no");
		
		if(obj==null) {
			pw.print(obj);
		}else {
			pw.print((Integer)obj);
		}
	
	
	}
 
}
