package com.project.member.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.item.cmd.BasicCmd;
import com.project.member.db.MemberDAO;
import com.project.member.db.MemberDTO;

public class InsertMemberCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		MemberDAO dao = MemberDAO.getIns();
		MemberDTO dto  = new MemberDTO();
		dto.setU_name(req.getParameter("u_name"));
		dto.setU_id(req.getParameter("u_id"));
		dto.setU_nick(req.getParameter("u_nick"));
		dto.setU_pw(req.getParameter("u_pw"));
		dto.setU_email(req.getParameter("u_email"));
		dto.setU_phone(req.getParameter("u_phone"));
		dto.setU_gender(req.getParameter("u_gender"));
		
		dao.insertMember(dto);
	}
	
}
