package com.project.item.cmd;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.project.item.db.ItemsDAO;
import com.project.item.db.ItemsDTO;

public class SearchItemListCmd implements BasicCmd {

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ItemsDAO dao = ItemsDAO.getIns();
		String searchWord = req.getParameter("searchWord");
		String calendarInput = req.getParameter("calendarInput");
		if (calendarInput != null) {
			String[] calendarInputArr = calendarInput.split("/");
			String checkin = calendarInputArr[0];
			String checkout = calendarInputArr[1];
			String night = calendarInputArr[2];
		}

		String peopleInput = req.getParameter("peopleInput");
		List<ItemsDTO> itemDtoList = dao.getItemDtoList(searchWord);
		if (itemDtoList.size() == 0) {
			itemDtoList = null;
		}
		
		req.setAttribute("searchWord", searchWord);
		req.setAttribute("itemDtoList", itemDtoList);

		Cookie[] cookies = req.getCookies();
		JSONArray jsonArr = null;
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("recentSearch")) {
					String jsonArrStr = URLDecoder.decode(c.getValue(), "utf-8");
					
					JSONParser parser = new JSONParser();
					try {
						jsonArr = (JSONArray) parser.parse(jsonArrStr);
						if(jsonArr.size()>=3) {
							jsonArr.remove(0);
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					jsonArr = new JSONArray();
				}

			}
		} else {
			jsonArr = new JSONArray();
		}

		JSONObject obj = new JSONObject();
		obj.put("searchWord", searchWord);
		obj.put("calendarInput", calendarInput);
		obj.put("peopleInput", peopleInput);
		jsonArr.add(obj);

		Cookie cookie = new Cookie("recentSearch", URLEncoder.encode(jsonArr.toJSONString(), "utf-8"));

		cookie.setMaxAge(60 * 60 * 24);
		cookie.setPath("/"); // 모든 경로에서 접근 가능하도록 설정
		resp.addCookie(cookie);
	}

}
