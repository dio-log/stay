package com.project.member.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.item.cmd.BasicCmd;
import com.project.member.db.MemberDAO;
import com.project.member.db.MemberDTO;

public class SelectMemberCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDAO dao = MemberDAO.getIns();
		MemberDTO dto = null;
		
		dto = dao.getMemberInfo(Integer.parseInt(req.getParameter("u_no").trim()));
		
		req.setAttribute("MemberDTO", dto);
		//디스패처로 보낼것
	}

}
