package com.project.member.cmd;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.project.item.cmd.BasicCmd;

public class removeCookieCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int jsonArrIdx = Integer.parseInt(req.getParameter("idx"));
		Cookie[] cookies = req.getCookies();
		JSONArray jsonArr= null;
		if(cookies!=null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("recentSearch")) {
					String jsonArrStr = URLDecoder.decode(c.getValue(), "utf-8");
					
					JSONParser parser = new JSONParser();
					try {
						jsonArr = (JSONArray) parser.parse(jsonArrStr);
						jsonArr.remove(jsonArrIdx);
	
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		Cookie cookie = new Cookie("recentSearch", URLEncoder.encode(jsonArr.toJSONString(), "utf-8"));
	
		cookie.setMaxAge(60 * 60 * 24);
		cookie.setPath("/"); // 모든 경로에서 접근 가능하도록 설정
		resp.addCookie(cookie);
	}

}
