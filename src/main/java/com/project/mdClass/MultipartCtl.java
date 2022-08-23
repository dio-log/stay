package com.project.mdClass;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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

import com.project.item.db.ItemsDTO;

//파일 받을때 쓰는거임
public class MultipartCtl {
	private List<?> items;
	private PrintWriter pw;
	private String dirName ;
	private String reviewDirName ;
	HttpSession session;
	
	public MultipartCtl(HttpServletRequest req, HttpServletResponse resp) throws FileUploadException, IOException {
//		FileItemFactory fif = new DiskFileItemFactory();
		session = req.getSession();
		int u_no = (Integer)session.getAttribute("u_no");
		dirName = "item"+u_no;
		reviewDirName = "review"+u_no;
		ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
		items = upload.parseRequest(req);
		pw = resp.getWriter();
	}
	//실제저장된건 이미지뿐, 나머지는 제이슨으로 반환
	//insert, update,
	//
//	public JSONArray getFiles(){
//		
//	}
	public Map<String,String> saveItemImg() throws Exception {
		Map<String, String> map = new HashMap<>();
		Iterator<?> itr = items.iterator();
		File file = new File("C:\\programing\\gitrep\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\webProject\\img\\item\\"+dirName);
		file.mkdirs();
		String fullFileName ="";
		ItemsDTO dto = null;
		while(itr.hasNext()) {
			FileItem item = (FileItem)itr.next();
			if(item.isFormField()) {
				map.put(item.getFieldName(), item.getString("utf-8"));
			}else {
				item.write(new File(file.getPath()+"\\"+item.getName()));
				fullFileName+=",../../img/item/"+dirName+"/"+item.getName();
			}
		}
		map.put("fullFileName", fullFileName);
		return map;
	}
	
	public Map saveRoomImg() throws Exception {
		Map<String, String> map = new HashMap<>();
		Iterator<?> itr = items.iterator();
		File file = new File("C:\\programing\\gitrep\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\webProject\\img\\item\\"+dirName);
		file.mkdirs();
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
//				if(firstImgFlag) {
//					map.put("roomFirstImg", "../../img/room/"+id+"/"+item.getName());
//					firstImgFlag=false;
//				}
				item.write(new File(file.getPath()+"\\"+item.getName()));
				fullFileName+=",../../img/item/"+dirName+"/"+item.getName();
				
				
			}
		}
		map.put("room_extraopt", checkStr);
		map.put("fullFileName", fullFileName);
		
		return map;
	}
	public Map<String,String> saveReviewImg() throws Exception {
		Map<String, String> map = new HashMap<>();
		Iterator<?> itr = items.iterator();
		File file = new File("C:\\programing\\gitrep\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\webProject\\img\\review\\"+reviewDirName);
		file.mkdirs();
		String fullFileName ="";
		ItemsDTO dto = null;
		while(itr.hasNext()) {
			FileItem item = (FileItem)itr.next();
			if(item.isFormField()) {
				map.put(item.getFieldName(), item.getString("utf-8"));
			}else {
				item.write(new File(file.getPath()+"\\"+item.getName()));
				fullFileName+=",../../img/review/"+reviewDirName+"/"+item.getName();
			}
		}
		map.put("fullFileName", fullFileName);
		return map;
	}

}
