package com.project.member.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.item.cmd.BasicCmd;
import com.project.member.db.MemberDAO;
import com.project.member.db.MemberDTO;

public class OtherLoginCmd implements BasicCmd {

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MemberDAO dao = MemberDAO.getIns();
		PrintWriter pw = resp.getWriter();
		String account = req.getParameter("account");
		HttpSession session = req.getSession();
		MemberDTO memberDto = dao.OtherLoginCheck(account);
		if(memberDto!=null) {
			session.setAttribute("u_nick", memberDto.getU_nick());
			session.setAttribute("u_div", memberDto.getU_div());
			pw.print(true);
		}else {
			pw.print(false);
		}
	}

}
