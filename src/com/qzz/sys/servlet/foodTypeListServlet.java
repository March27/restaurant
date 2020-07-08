package com.qzz.sys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qzz.sys.bean.FoodType;
import com.qzz.sys.service.FoodTypeService;
import com.qzz.sys.service.FoodTypeServiceImpl;

/**
 * Servlet implementation class foodTypeListServlet
 */
@WebServlet("/sys/foodTypeList.do")
public class foodTypeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public foodTypeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=========/sys/foodTypeList.do========");
		
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		
		String keyword = request.getParameter("keyword");
		String method = request.getParameter("method");
		String disabled = request.getParameter("disabled");
		String foodTypeName = request.getParameter("foodTypeName");
		String id = request.getParameter("id");
		
		FoodTypeServiceImpl foodTypeService=new FoodTypeService();
		if(method !=null && !method.equals("") && method.equals("list")) {
			//查询菜品
			List<FoodType> foodTypes = foodTypeService.find(keyword,disabled);
			System.out.println(foodTypes);
			
			request.setAttribute("keyword", keyword);
			request.setAttribute("foodTypes", foodTypes);
			request.setAttribute("disabled", disabled);
			request.getRequestDispatcher("/WEB-INF/jsp/sys/foodTypeList.jsp").forward(request, response);
		}else if(method !=null && !method.equals("") && method.equals("addPage")) {
			//跳转到菜系添加页面
			request.getRequestDispatcher("/WEB-INF/jsp/sys/foodTypeAdd.jsp").forward(request, response);
		}else if(method !=null && !method.equals("") && method.equals("addSubmit")) {
			//根据菜系名字查找菜系
			FoodType foodType = foodTypeService.findByFoodName(foodTypeName);
			
			String message = "";
			//如果菜系为null，表示用户输入的菜系名字目前数据库还没有，可保存到数据库
			if(foodType == null) {
				FoodType foodType2 = new FoodType();
				foodType2.setTypeName(foodTypeName);
				
				//保存菜系名到数据库
				foodTypeService.save(foodType2);
				message = "success";
			}else {
				//如果菜系不为null，再前端提示，当前菜系名称已存在，请重新输入
				message = "fail";
			}
			response.getWriter().print(message);
		}else if(method !=null && !method.equals("") && method.equals("viewUpdate")) {
			
			FoodType foodType = foodTypeService.findById(Integer.parseInt(id));
			
			request.setAttribute("foodType", foodType);
			request.getRequestDispatcher("/WEB-INF/jsp/sys/foodTypeUpdate.jsp").forward(request, response);
		}else if(method !=null && !method.equals("") && method.equals("updateSubmit")) {
			//根据菜系名字查找菜系
			FoodType foodType = foodTypeService.findByFoodName(foodTypeName);
			
			String message = "";
			//如果菜系为null，表示用户输入的菜系名字目前数据库还没有，可保存到数据库
			if(foodType == null) {
				FoodType foodType2 = foodTypeService.findById(Integer.parseInt(id));
				foodType2.setTypeName(foodTypeName);
				
				//更新菜系名到数据库
				foodTypeService.update(foodType2);
				message = "success";
			}else {
				//如果菜系不为null，再前端提示，当前菜系名称已存在，请重新输入
				message = "fail";
			}
			response.getWriter().print(message);
		}else if(method !=null && !method.equals("") && method.equals("update")) {
			//上架菜系下架菜系
			FoodType foodType2 = foodTypeService.findById(Integer.parseInt(id));
			foodType2.setDisabled(Integer.parseInt(disabled));
			//更新菜系名到数据库
			foodTypeService.update(foodType2);
			response.sendRedirect(getServletContext().getContextPath()+"/sys/foodTypeList.do?method=list");
			
		}
		
	}

}
