package com.project.payment.front;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.item.cmd.BasicCmd;
import com.project.item.cmd.InsertRoomCmd;
import com.project.payment.cmd.DeleteCardCmd;
import com.project.payment.cmd.GetPaymentByRoomCmd;
import com.project.payment.cmd.GetPaymentListCmd;
import com.project.payment.cmd.InsertPaymentCmd;
import com.project.payment.cmd.MonthlySalesCmd;
import com.project.payment.cmd.MovePayPageCmd;
import com.project.payment.cmd.PayFeesCmd;

/**
 * Servlet implementation class PaymentFront
 */
@WebServlet("*.pay")
public class PaymentFront extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentFront() {
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
		resp.setCharacterEncoding("utf-8");
		String path = req.getServletPath();
		String viewPage = null;
		BasicCmd cmd = null;
		int idx = path.lastIndexOf("/");
		String lastPath= path.substring(idx);
		boolean flag = true;
		
		if(lastPath.equals("/insertCart.pay")) {
			cmd = new InsertPaymentCmd();
			cmd.excute(req, resp);
			viewPage= "myReservation.my";
		}else if(lastPath.equals("/getCartList.pay")) {
			cmd = new GetPaymentListCmd();
			cmd.excute(req, resp);
			viewPage= "/app/myPage/CartList.jsp";
		}else if(lastPath.equals("/payFees.pay")) {
			cmd = new PayFeesCmd();
			cmd.excute(req, resp);
			viewPage= "/app/myPage/payFees.jsp";
		}else if(lastPath.equals("/deleteCart.pay")) {
			cmd=new DeleteCardCmd();
			cmd.excute(req, resp);
			viewPage= "/app/myPage/cartList.jsp";
		}else if(lastPath.equals("/movePayPage.pay")) {
			cmd = new MovePayPageCmd();
			cmd.excute(req, resp);
			viewPage="/app/pay/pay.jsp";
		}else if(lastPath.equals("/getPaymentByRoom.pay")) {
			cmd = new GetPaymentByRoomCmd();
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
