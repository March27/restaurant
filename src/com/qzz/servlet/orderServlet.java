package com.qzz.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qzz.bean.Order;
import com.qzz.service.OrderService;
import com.qzz.service.OrderServiceImpl;

import javafx.scene.control.Alert;

/**
 * Servlet implementation class orderServlet
 */
@WebServlet("/app/order.do")
public class orderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public orderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("==============order.do=============");
		String method = request.getParameter("method");
		String total = request.getParameter("total");
		String orderId = request.getParameter("orderId");
		String userId = request.getParameter("userId");
		
		
		OrderServiceImpl orderService = new OrderService();
		
		
		if(method != null && method.equals("order")) {
			HttpSession session = request.getSession();
			
			//获取购物车(foodid,buynum)
			Map<Integer,Integer>shopCar = (Map<Integer, Integer>) session.getAttribute("shopCar");
			if(shopCar!=null && !shopCar.isEmpty()) {//Map问题
			//保存数据到数据库			
			orderService.order(shopCar,total,Integer.parseInt(userId));			
			//下单了，删除购物车数据
			session.removeAttribute("shopCar");			
			//跳转到订单详情页面,查询购物车中所有未删除得订单
			//需要订单id 订单编号 下单时间 菜品名 数量 金额 总金额
//			订单表：订单id 订单编号 下单时间  总金额 
//			菜品表：菜品名 金额 
//			订单明细表：数量
//			List<Order> orders = OrderServiceImpl.findByTableId(dinnerTableId);
			List<Order> orders = orderService.findDetails(Integer.parseInt(userId));
			System.out.println(orders);
			
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("/WEB-INF/jsp/app/orderItem.jsp").forward(request, response);
			}else {
				response.sendRedirect(getServletContext().getContextPath()+"/app/index.do");
			}
			
		}
		
		else if(method != null && method.equals("pay")) {
			//付款 修改订单状态status=1 付款时间
			Order order = orderService.findById(Integer.parseInt(orderId));
			
			order.setStatus(1);
			order.setPayTime(new Date());
			order.setUpdateTime(new Date());
			
			orderService.pay(order);
			response.getWriter().print("<script language='javascript'>alert('付款成功')</script>");
			response.sendRedirect(getServletContext().getContextPath()+"/app/index.do");
			
			
		}
		
		else if(method != null && method.equals("delete")) {
			//取消订单 将订单的disabled=1 
			Order order = orderService.findById(Integer.parseInt(orderId));
			
			order.setDisabled(1);
			order.setUpdateTime(new Date());
			orderService.deleteOrder(order);
			
			response.sendRedirect(getServletContext().getContextPath()+"/app/index.do");
			
		}
		else if(method != null && method.equals("list")) {
			//查询所有订单
//			List<Order> orders = orderService.findAll();
//			System.out.println(orders);
//			
//			request.setAttribute("orders", orders);
			List<Order> orders = orderService.findDetails(Integer.parseInt(userId));
			request.setAttribute("orders", orders);
			request.getRequestDispatcher("/WEB-INF/jsp/app/orderItem.jsp").forward(request, response);
		}
	}

}
