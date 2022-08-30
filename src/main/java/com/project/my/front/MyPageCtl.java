package com.project.my.front;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.item.cmd.BasicCmd;
import com.project.member.cmd.SelectPointCmd;
import com.project.my.cmd.MyItemManageCmd;
import com.project.payment.cmd.GetPaymentListCmd;
import com.project.payment.cmd.GetTotalPointCmd;
import com.project.payment.cmd.MonthlySalesCmd;
import com.project.review.cmd.SelectPerTotalReviewCmd;

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
		int idx = path.lastIndexOf("/");
		String lastPath = path.substring(idx);
		boolean flag = true;
		System.out.println(path);
		if(lastPath.equals("/myReservation.my")) {
			//예약내역 조회
			cmd = new GetPaymentListCmd();
			cmd.excute(req, resp);
			viewPage="/app/myPage/myReservation.jsp";
		}else if(lastPath.equals("/myInfo.my")) {
			viewPage = "/app/myPage/myInfo.jsp";
			flag =false; 
		}else if(lastPath.equals("/myPoint.my")) {
			cmd = new SelectPointCmd();
			cmd.excute(req, resp);
			viewPage= "/app/myPage/myPoint.jsp";
			
		}else if(lastPath.equals("/myReview.my")) {
			cmd = new SelectPerTotalReviewCmd();
			cmd.excute(req, resp);
			viewPage="/app/myPage/myReview.jsp";
			
		}else if(lastPath.equals("/notice.my")) {
			viewPage="/app/myPage/notice.jsp";
			
		}else if(lastPath.equals("/myItemManage.my")) {
			cmd= new MyItemManageCmd();
			cmd.excute(req, resp);
			viewPage="/app/myPage/myItemManage.jsp";
		}else if(lastPath.equals("/myStatics.my")) {
			cmd= new MyItemManageCmd();
			cmd.excute(req, resp);
			viewPage="/app/myPage/myStatics.jsp";
		}else if(lastPath.equals("/getMonthlySales.my")) {
			cmd = new MonthlySalesCmd();
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
