package com.project.member.cmd;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.project.item.cmd.BasicCmd;

public class GetCookiesCmd implements BasicCmd {

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Cookie[] cookies = req.getCookies();
		JSONArray jsonArr = null;
		PrintWriter pw = resp.getWriter();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("recentSearch")) {
					// jsonArrString value받음
					JSONParser parser = new JSONParser();
					String jsonArrStr = URLDecoder.decode(c.getValue(), "utf-8");
					try {
						jsonArr = (JSONArray) parser.parse(jsonArrStr);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
		}
		pw.print(jsonArr);
	}

}
