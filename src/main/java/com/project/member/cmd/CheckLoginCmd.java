package com.project.member.cmd;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.item.cmd.BasicCmd;
import com.project.member.db.MemberDAO;
import com.project.member.db.MemberDTO;

public class CheckLoginCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDAO dao=MemberDAO.getIns();
		String u_id = req.getParameter("u_id").trim();
		boolean result = dao.hasMember(u_id, "u_pw");
		HttpSession session = null;
		MemberDTO dto = null;
		
		if(result) {
			session = req.getSession();
			dto =dao.getNoAndNick(u_id);
			
			session.setAttribute("u_no", dto.getU_no());
			session.setAttribute("u_nick", dto.getU_nick());
		}else {
			//세션을 그냥둘까 널을 넣을까?
		}
		
	}

}
