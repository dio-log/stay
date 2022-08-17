package com.project.mdClass;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

//파일 받을때 쓰는거임
public class MultipartCtl {
	private List<?> items;
	private PrintWriter pw;
	private String id ;
	HttpSession session;
	
	public MultipartCtl(HttpServletRequest req, HttpServletResponse resp) throws FileUploadException, IOException {
//		FileItemFactory fif = new DiskFileItemFactory();
		session = req.getSession();
		id = (String) session.getAttribute("id");
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
		items = upload.parseRequest(req);
		pw = resp.getWriter();
	}
	//실제저장된건 이미지뿐, 나머지는 제이슨으로 반환
	//insert, update,
	//
	public JSONArray getFiles() throws Exception {
		Map<String, String> map = new HashMap<>();
		JSONObject objResult = new JSONObject();
		JSONArray objArr = new JSONArray();
		Iterator<?> itr = items.iterator();
		File file = new File("C:\\work_do\\roomImg\\"+id);
		file.mkdir();
		String fullFileName ="";
		while(itr.hasNext()) {
			FileItem item = (FileItem)itr.next();
			if(item.isFormField()) {
				JSONObject obj = new JSONObject();
				obj.put(item.getFieldName(), item.getString("utf-8"));
				objArr.add(obj);
			
			}else {
				item.write(new File(file.getPath()+"\\"+item.getName()));
				fullFileName+=",../../img/items/"+id+"/"+item.getName();
			}
		}
		JSONObject obj = new JSONObject();
		obj.put("fullFileName", fullFileName);
		objArr.add(obj);
		pw.write(objArr.toJSONString());
		return objArr;
	}
	
	public Map saveFiles() throws Exception {
		Map<String, String> map = new HashMap<>();
		Iterator<?> itr = items.iterator();
		File file = new File("C:\\work_do\\eclipse_web2\\project\\src\\main\\webapp\\img\\"+id);
		file.mkdir();
		String fullFileName ="";
		String checkStr="";
		boolean firstImgFlag=true;
		while(itr.hasNext()) {
			FileItem item = (FileItem)itr.next();
			if(item.isFormField()) {
				if(item.getFieldName().equals("room_extraopt")) {
					checkStr+=item.getString("utf-8")+",";
				}else {
//					fullFileName = ",..\\roomImg\\"+item.getString("utf-8");
				map.put(item.getFieldName(), item.getString("utf-8"));
				System.out.println(item.getString("utf-8"));
			}
			//C:\work_do\eclipse_web2\.metadata\.plugins\org.eclipse.wst.server.core\tmp2\wtpwebapps\project\img\room\admin
			}else {
				if(firstImgFlag) {
					map.put("roomFirstImg", "../../img/room/"+id+"/"+item.getName());
					firstImgFlag=false;
				}
				item.write(new File(file.getPath()+"\\"+item.getName()));
				fullFileName+=",../../img/room/"+id+"/"+item.getName();
				
				
			}
		}
		map.put("room_extraopt", checkStr);
		map.put("fullFileName", fullFileName);
		
		
		
		return map;
	}

}
