package com.project.member.cmd;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.project.item.cmd.BasicCmd;
import com.project.member.db.MemberDAO;
import com.project.member.db.MemberDTO;

public class SelectMemberCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberDAO dao = MemberDAO.getIns();
		MemberDTO dto = null;
		PrintWriter pw = resp.getWriter();
		HttpSession session = req.getSession();
		
		int u_no = (Integer)session.getAttribute("u_no");
		String pwValue = req.getParameter("pwValue");
		
		if(dao.checkPw(u_no, pwValue)) {
			dto = dao.getMemberInfo((Integer)session.getAttribute("u_no"));
			
			JSONObject obj = new JSONObject();
			obj.put("u_id", dto.getU_id());
			obj.put("u_email", dto.getU_email());
			obj.put("u_phone", dto.getU_phone());
			obj.put("result", true);
		
			pw.print(obj);
		}else {
			JSONObject obj = new JSONObject();
			obj.put("result", false);
			pw.print(obj);
		}
		
		
		//디스패처로 보낼것
	}

}
