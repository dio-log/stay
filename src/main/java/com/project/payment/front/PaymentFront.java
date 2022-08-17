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
import com.project.payment.cmd.GetCartListCmd;
import com.project.payment.cmd.InsertCartCmd;
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
		String path = req.getServletPath();
		String viewPage = null;
		BasicCmd cmd = null;
		
		if(path.equals("/app/payment/insertCart.pay")) {
			cmd = new InsertCartCmd();
			cmd.excute(req, resp);
//			viewPage= "insertCart.jsp";
		}else if(path.equals("/app/payment/getCartList.pay")) {
			cmd = new GetCartListCmd();
			cmd.excute(req, resp);
			viewPage= "CartList.jsp";
		}else if(path.equals("/app/payment/payFees.pay")) {
			cmd = new PayFeesCmd();
			cmd.excute(req, resp);
			viewPage= "payFees.jsp";
		}else if(path.equals("/app/payment/deleteCart.pay")) {
			cmd=new DeleteCardCmd();
			cmd.excute(req, resp);
			viewPage= "cartList.jsp";
		}
		
		RequestDispatcher rd = req.getRequestDispatcher(viewPage);
		rd.forward(req, resp);
	}

}
