package com.project.member.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.item.cmd.BasicCmd;
import com.project.member.db.MemberDAO;
import com.project.member.db.MemberDTO;

public class UpdateMemberCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDTO dto = new MemberDTO();
		MemberDAO dao = MemberDAO.getIns();
		
		dto.setU_nick(req.getParameter("u_nick").trim());
		dto.setU_pw(req.getParameter("u_pw").trim());
		dto.setU_email(req.getParameter("u_email").trim());
		dto.setU_phone(req.getParameter("u_phone").trim());
		dao.updateMember(dto);

	}

}
