package com.project.member.cmd;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.project.item.cmd.BasicCmd;
import com.project.member.db.MemberDAO;
import com.project.member.db.MemberDTO;

public class CheckLoginCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String jsonStr = req.getParameter("jsonStr");
		JSONObject jobj = null;
		
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(jsonStr);
			jobj = (JSONObject)obj;
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		MemberDAO dao=MemberDAO.getIns();
		String u_id = (String) jobj.get("u_id");
		String u_pw =  (String) jobj.get("u_pw");
	
		boolean result = dao.hasMember(u_id, u_pw);
		HttpSession session = req.getSession();
		MemberDTO dto = null;
		PrintWriter pw = resp.getWriter();
		
		if(result) {
			dto =dao.getNoAndNick(u_id);
			JSONObject obj = new JSONObject();
			session.setAttribute("u_no",  dto.getU_no());
			session.setAttribute("u_nick",  dto.getU_nick());
			session.setAttribute("u_div",  dto.getU_div());
			session.setAttribute("u_id",  dto.getU_id());
			
			pw.print(true);
		}
			
		
	}

}
