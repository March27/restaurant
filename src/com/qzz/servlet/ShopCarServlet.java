package com.qzz.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ShopCarServlet
 */
@WebServlet("/app/shopCar.do")
public class ShopCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopCarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("==============shopCar.do=============");
		String method = request.getParameter("method");
		String foodId = request.getParameter("foodId");
		String buyNum = request.getParameter("buyNum");
//		String foodTypeId = request.getParameter("foodTypeId");
		
		Integer foodIdint = Integer.parseInt(foodId);
		
		
		HttpSession session = request.getSession();
		
		//获取购物车(foodid,buynum)
		Map<Integer,Integer>shopCar = (Map<Integer, Integer>) session.getAttribute("shopCar");
		
		if(method!=null&&method.equals("add")) {
			if(shopCar != null) {//购物车里有东西
				//找到购物车里有什么
				Set<Integer> foodids =shopCar.keySet();
				if(foodids.contains(foodIdint)) {//有已添加的food
					Integer buyNum1 = shopCar.get(foodIdint);
					shopCar.put(foodIdint, buyNum1+1);//则购买数量+1
				}
				else {//没有已添加的food
					shopCar.put(foodIdint, 1);
				}
			}else {
			
			//购物车没有东西
			shopCar = new HashMap<>();
			shopCar.put(foodIdint, 1);
			
			session.setAttribute("shopCar", shopCar);
			
			}
			
			System.out.println("===========test===========");
			Set<Integer> foodids2 =shopCar.keySet();
			for (Integer foodid : foodids2) {
				System.out.println(foodid+":"+shopCar.get(foodid));
				
			}
			
			//request.getRequestDispatcher("/app/index.do").forward(request, response);
			
		}else if (method!=null&&method.equals("update")) {
			//修改数量框，即时更新
			
			shopCar.put(foodIdint, Integer.parseInt(buyNum));
			
			//request.getRequestDispatcher("/app/index.do").forward(request, response);
			
		}else if (method!=null&&method.equals("delete")) {
			shopCar.remove(foodIdint);
		}
		
		response.sendRedirect(getServletContext().getContextPath()+"/app/index.do");
		
	}

}
