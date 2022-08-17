package com.project.item.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileUploadException;

import com.project.item.cmd.BasicCmd;
import com.project.item.cmd.InsertRoomCmd;
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
//	

		String viewPage=null;
		String path = req.getServletPath();
		HttpSession session = req.getSession();
		session.setAttribute("id", "admin");
		session.setAttribute("no", 99);
		BasicCmd cmd=null;
		
		if(path.equals("/app/itemPage/insertRoom.it")){
		 cmd = new InsertRoomCmd();
		 cmd.excute(req, resp);

		}
		
		
		
	}

	

}
