package com.project.my.front;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.item.cmd.BasicCmd;

/**
 * Servlet implementation class MyPageCtl
 */
@WebServlet("*.my")
public class MyPageCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageCtl() {
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
		boolean flag = true;
		if(path.equals("/app/myPage/myReservation.my")) {
			//예약내역 조회
			flag=false;
			viewPage="/app/myPage/myReservation.jsp";
		}else if(path.equals("/app/myPage/myInfo.my") ||path.equals("/app/signPage/myInfo.my") || path.equals("/app/itemPage/myInfo.my") ||path.equals("/app/mainPage/myInfo.my")) {
			viewPage = "/app/myPage/myInfo.jsp";
			flag =false; //주소 날림
			
		}else if(path.equals("/app/myPage/myPoint.my")) {
			//포인트조회
			viewPage= "/app/myPage/myPoint.jsp";
			flag=false;
		}
		
		
		if(flag) {
			RequestDispatcher rd = req.getRequestDispatcher(viewPage);
			rd.forward(req, resp);
		}else {
			resp.sendRedirect(viewPage);
		}
	}

}
