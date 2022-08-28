package com.project.item.front;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;

import com.project.item.cmd.BasicCmd;
import com.project.item.cmd.FilteredMaxItem;
import com.project.item.cmd.InsertItemCmd;
import com.project.item.cmd.InsertRoomCmd;
import com.project.item.cmd.SearchItemListCmd;
import com.project.item.cmd.SelectItemCmd;
import com.project.item.cmd.SelectMoreItemListCmd;
import com.project.item.cmd.SelectRoomCmd;
import com.project.item.cmd.SortItemCmd;
import com.project.mdClass.MultipartCtl;

/**
 * Servlet implementation class ItemFrontCtl
 */
@WebServlet("*.it")
public class ItemFrontCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemFrontCtl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAction(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAction(request, response);
	}
	protected void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		String viewPage=null;
		String path = req.getServletPath();
		int idx = path.lastIndexOf("/");
		String lastPath = path.substring(idx);
		BasicCmd cmd=null;
		
		boolean flag = true;
	
		System.out.println(req.getServletPath());
		
		if(path.equals("/app/itemPage/insertRoom.it")){
		 cmd = new InsertRoomCmd();
		 cmd.excute(req, resp);
		 return;

		}else if(lastPath.equals("/itemUpload.it")) {
			viewPage = "/app/itemPage/itemUpload.jsp";
			flag =false;
		}else if(lastPath.equals("/insertItem.it")) {
			cmd = new InsertItemCmd();
			cmd.excute(req, resp);
			viewPage = "/app/myPage/myItemManage.jsp";
			flag =false;
		
		}else if(lastPath.equals("/selectMoreItemList.it")) {
			cmd = new SelectMoreItemListCmd();
			cmd.excute(req, resp);
			return;
		}else if(lastPath.equals("/itemView.it")) {
			cmd = new SelectItemCmd();
			cmd.excute(req, resp);
			viewPage="/app/itemPage/itemView.jsp";
		}else if(lastPath.equals("/searchItemList.it")) {
			cmd = new SearchItemListCmd();
			cmd.excute(req, resp);
			viewPage="/app/itemPage/itemList.jsp";
		}else if(lastPath.equals("/selectRoom.it")) {
			cmd= new SelectRoomCmd();
			cmd.excute(req, resp);
			return;
		}else if(lastPath.equals("/sortItem.it")) {
			cmd = new SortItemCmd();
			cmd.excute(req, resp);
			return;
		}else if(lastPath.equals("/filteredMaxItem.it")) {
			cmd = new FilteredMaxItem();
			cmd.excute(req, resp);
			return;
		}
		
		
		
		if(flag) {
			RequestDispatcher rd = req.getRequestDispatcher(viewPage);
			rd.forward(req, resp);
			
		}else {
			resp.sendRedirect(viewPage);
		}
	}

	

}
