package com.qzz.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qzz.bean.Food;
import com.qzz.bean.FoodType;


import com.qzz.service.FoodService;
import com.qzz.service.FoodServiceImpl;
import com.qzz.service.FoodTypeService;
import com.qzz.service.FoodTypeServiceImpl;

/**
 * Servlet implementation class indexServlet
 */
@WebServlet("/app/index.do")
public class indexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public indexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		System.out.println("===============index.do==============");
		int start = 0;
		int count = 10;
		
		try {
		    start = Integer.parseInt(request.getParameter("page.start"));
		    count = Integer.parseInt(request.getParameter("page.count"));
		} catch (Exception e) {
		}
		

	//	List<Student> students = studentDAO.list(page.getStart(), page.getCount());

		FoodTypeServiceImpl foodTypeService = new FoodTypeService();
		
		String foodTypeId= request.getParameter("foodTypeId");
		
		//判断
	
		//1.查询所有未删除的菜系的名字
		List<FoodType> foodTypes =foodTypeService.findAll();
		System.out.println(foodTypes);

		
		
		//2.默认查询所有菜系中第一个未删除菜系的菜品（如招牌菜）
		//从0开始，第一个查询，通过菜系id来查到相关菜品
		
		if(foodTypeId == null || foodTypeId.equals("")) {
			
			Integer foodTypeIdint=foodTypes.get(0).getId();
			foodTypeId = Integer.toString(foodTypeIdint);
		}
		
		
		FoodServiceImpl foodService = new FoodService();
		 List<Food>foods =foodService.findByFoodTypeId(Integer.parseInt(foodTypeId));
		

		 System.out.println(foods);
		
		
		//3.左边餐桌的购物车展示
		HttpSession session = request.getSession();
		Map<Integer,Integer> shopCar = (Map<Integer, Integer>) session.getAttribute("shopCar");
		List<Food> foods2 = new ArrayList<>();
		Double total = 0.00;
		if(shopCar!=null) {
			Set<Integer> foodids =shopCar.keySet();
			for (Integer foodid : foodids) {
				//通过菜品id找到菜品
				Food food = foodService.findByFoodId(foodid);
				System.out.println("通过菜品id查询food："+food);
				Integer buyNum = shopCar.get(foodid);
				food.setBuyNum(buyNum);
				foods2.add(food);
				//总金额
				Double price = food.getPrice()*food.getBuyNum();
				total = total + price;
			}
		}
		
		//查询出来后保存起来，等下传给前端展示
		
		request.setAttribute("foodTypes", foodTypes);
		request.setAttribute("foods", foods);
		request.setAttribute("foods2", foods2);
		request.setAttribute("total", total);
		request.getRequestDispatcher("/WEB-INF/jsp/app/index.jsp").forward(request, response);
	}

}
