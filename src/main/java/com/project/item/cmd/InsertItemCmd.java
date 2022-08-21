package com.project.item.cmd;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.item.db.ItemsDAO;
import com.project.item.db.ItemsDTO;
import com.project.mdClass.MultipartCtl;

public class InsertItemCmd implements BasicCmd{

	@Override
	public void excute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ItemsDAO dao = ItemsDAO.getIns();
		ItemsDTO dto = new ItemsDTO();
		HttpSession session = req.getSession();
		Map<String,String> map = null;
		try {
			MultipartCtl mtc = new MultipartCtl(req, resp);
			map = mtc.saveItemImg();
		}catch( Exception e) {
			e.printStackTrace();
		}
		
		
		dto.setU_no((Integer)session.getAttribute("u_no"));
		dto.setItem_name(map.get("item_name"));
		dto.setItem_div(map.get("item_div"));
		dto.setItem_addr(map.get("item_addr"));
		dto.setItem_addr_detail(map.get("item_addr_detail"));
		dto.setItem_addr_extra(map.get("item_addr_extra"));
		dto.setItem_event(map.get("item_event"));
		dto.setItem_imgpath(map.get("item_imgpath"));
		dto.setItem_arround(map.get("item_arround"));
		dto.setItem_basic(map.get("item_basic"));
		dto.setItem_notice(map.get("item_notice"));
		dto.setItem_parking(map.get("item_parking"));
		dto.setItem_addpeople(map.get("item_addpeople"));
		dto.setItem_cancel_refund(map.get("item_cancel_refund"));
		dto.setItem_imgpath(map.get("fullFileName"));
		dao.insertItem(dto);
	}

}
