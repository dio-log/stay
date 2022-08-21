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
		int idx = path.lastIndexOf("/");
		String lastPath = path.substring(idx);
		System.out.println(req.getServletPath());
		System.out.println(lastPath);
		boolean flag = true;
		
		if(path.equals("/app/signPage/signComp.m")) {
			cmd = new InsertMemberCmd();
			cmd.excute(req, resp);
			viewPage="signComp.jsp";
		}else if(path.equals("/app/signPage/checkOverlapId.m")) {
			cmd = new CheckOverlapId();
			cmd.excute(req, resp);
			return;
		}else if(lastPath.equals("/login.m")) {
			viewPage = "../signPage/login.jsp";
			flag=false;
		}else if(path.equals("/app/signPage/signSelect.m")) {
			viewPage = "signSelect.jsp";
		}else if(path.equals("/app/signPage/signAgree.m")) {
			viewPage="signAgree.jsp";
		}else if(path.equals("/app/signPage/signForm.m")) {
			viewPage = "signForm.jsp";
		}else if(lastPath.equals("/main.m") ) {
			viewPage="../mainPage/main.jsp";
			flag=false;
		}else if(path.equals("/app/signPage/checkLogin.m")) {
			cmd = new CheckLoginCmd();
			cmd.excute(req, resp);
			return;
			
		}else if(path.equals("/app/myPage/myPagePwCheck.m")) {
			cmd = new CheckPwCmd();
			cmd.excute(req, resp);
			return;
		}else if(path.equals("/app/myPage/logout.m") || path.equals("/app/signPage/logout.m") || path.equals("/app/itemPage/logout.m")||path.equals("/app/mainPage/logout.m")) {
			cmd = new LogoutCmd();
			cmd.excute(req, resp);
			viewPage = "/app/mainPage/main.jsp";
			flag = false;
		}else if(path.equals("/app/myPage/selectMember.m")) {
			cmd = new SelectMemberCmd();
			cmd.excute(req, resp);
			return;
		}else if(path.equals("/app/myPage/checkOverlapNick.m")) {
			cmd= new CheckOverlapNick();
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
