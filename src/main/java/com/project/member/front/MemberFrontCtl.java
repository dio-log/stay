package com.project.member.front;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.item.cmd.BasicCmd;
import com.project.member.cmd.CheckOverlapId;
import com.project.member.cmd.InsertMemberCmd;

/**
 * Servlet implementation class MemberFrontCtl
 */
@WebServlet("*.m")
public class MemberFrontCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberFrontCtl() {
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
		BasicCmd cmd = null;
		String viewPage = null;
		String path = req.getServletPath();
		System.out.println(path);
		
		if(path.equals("/app/signPage/insertMember.m")) {
			cmd = new InsertMemberCmd();
			cmd.excute(req, resp);
			viewPage="signComp.jsp";
		}else if(path.equals("/app/signPage/checkOverlapId.m")) {
			cmd = new CheckOverlapId();
			cmd.excute(req, resp);
			return;
		}
		
		RequestDispatcher rd = req.getRequestDispatcher(viewPage);
		rd.forward(req, resp);
	}

}
