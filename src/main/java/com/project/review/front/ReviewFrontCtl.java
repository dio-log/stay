package com.project.review.front;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.item.cmd.BasicCmd;
import com.project.reveiw.cmd.InsertReviewCmd;
import com.project.reveiw.cmd.checkPossibleCmd;

/**
 * Servlet implementation class ReviewFrontCtl
 */
@WebServlet("*.re")
public class ReviewFrontCtl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewFrontCtl() {
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
		doAction(request, response);
	}
	protected void doAction(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

 		String referer = req.getHeader("REFERER");
 
		String viewPage=null;
		String path = req.getServletPath();
		int idx = path.lastIndexOf("/");
		String lastPath = path.substring(idx);
		BasicCmd cmd=null;
		boolean flag = true;
		
		if(lastPath.equals("/checkPossibleReview.re")) {
			cmd = new checkPossibleCmd();
			cmd.excute(req, resp);
			return;
		}else if(lastPath.equals("/insertReview.re")){
			cmd = new InsertReviewCmd();
			cmd.excute(req, resp);
			viewPage = referer;
			flag =false;
		}
		
		
		

		if(flag) {
			RequestDispatcher rd = req.getRequestDispatcher(viewPage);
			rd.forward(req, resp);
			
		}else {
			resp.sendRedirect(viewPage);
		}
	}
	

}
