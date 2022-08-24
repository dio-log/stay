package com.project.member.front;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.project.item.cmd.BasicCmd;
import com.project.member.cmd.CheckLoginCmd;
import com.project.member.cmd.CheckOverlapId;
import com.project.member.cmd.CheckOverlapNick;
import com.project.member.cmd.CheckPwCmd;
import com.project.member.cmd.InsertMemberCmd;
import com.project.member.cmd.LogoutCmd;
import com.project.member.cmd.SelectMemberCmd;
import com.project.member.cmd.GetCookiesCmd;

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
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		BasicCmd cmd = null;
		String viewPage = null;
		String path = req.getServletPath();
		int idx = path.lastIndexOf("/");
		String lastPath = path.substring(idx);
		System.out.println(req.getServletPath());
		System.out.println(lastPath);
		boolean flag = true;
		
		if(lastPath.equals("/signComp.m")) {
			cmd = new InsertMemberCmd();
			cmd.excute(req, resp);
			viewPage="signComp.jsp";
		}else if(lastPath.equals("/checkOverlapId.m")) {
			cmd = new CheckOverlapId();
			cmd.excute(req, resp);
			return;
		}else if(lastPath.equals("/login.m")) {
			viewPage = "../signPage/login.jsp";
			flag=false;
		}else if(lastPath.equals("/signSelect.m")) {
			viewPage = "signSelect.jsp";
		}else if(lastPath.equals("/signAgree.m")) {
			viewPage="signAgree.jsp";
		}else if(lastPath.equals("/signForm.m")) {
			viewPage = "signForm.jsp";
		}else if(lastPath.equals("/main.m") ) {
			viewPage="/app/mainPage/main.jsp";
			flag=false;
		}else if(lastPath.equals("/checkLogin.m")) {
			cmd = new CheckLoginCmd();
			cmd.excute(req, resp);
			return;
			
		}else if(lastPath.equals("/myPagePwCheck.m")) {
			cmd = new CheckPwCmd();
			cmd.excute(req, resp);
			return;
		}else if(lastPath.equals("/logout.m") ) {
			cmd = new LogoutCmd();
			cmd.excute(req, resp);
			viewPage = "/main.m";
			flag = false;
		}else if(lastPath.equals("/selectMember.m")) {
			cmd = new SelectMemberCmd();
			cmd.excute(req, resp);
			return;
		}else if(lastPath.equals("/checkOverlapNick.m")) {
			cmd= new CheckOverlapNick();
			cmd.excute(req, resp);
			return;
		}else if(lastPath.equals("/getCookies.m")) {
			cmd = new GetCookiesCmd();
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
