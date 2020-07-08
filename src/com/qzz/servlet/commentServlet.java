package com.qzz.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qzz.bean.Comment;
import com.qzz.bean.Food;
import com.qzz.service.CommentService;
import com.qzz.service.CommentServiceImpl;
import com.qzz.service.FoodService;
import com.qzz.service.FoodServiceImpl;
import com.sun.org.apache.xerces.internal.dom.CommentImpl;

/**
 * Servlet implementation class commentServlet
 */
@WebServlet("/app/comment.do")
public class commentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public commentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//post传递数据过来会乱码
		String userId = request.getParameter("userId");
		String foodId = request.getParameter("foodId");
		String content = request.getParameter("contents");
		String method = request.getParameter("method");
		System.out.println("comservlet:userId:"+userId+"  foodId:"+foodId +"  content:"+content +"  method:"+method);
		
		
		CommentServiceImpl commentService = new CommentService();
		if(method!=null && method.equals("index")) {
			
		
			FoodServiceImpl foodService = new FoodService();
			Food food = foodService.findByFoodId(Integer.parseInt(foodId));
			System.out.println(food);
			
			//评论区显示
			
			List<Comment> comments = commentService.findByFoodId(Integer.parseInt(foodId));
			System.out.println("commentservlet:"+comments);
			
			request.setAttribute("comments", comments);
			request.setAttribute("food", food);
	
			request.getRequestDispatcher("/WEB-INF/jsp/app/comment.jsp").forward(request, response);
		}
		//发表评论
		else if(method!=null && method.equals("submitTable")) {
			request.setCharacterEncoding("utf-8");//post传递数据过来会乱码
			commentService.comment(Integer.parseInt(userId),Integer.parseInt(foodId),content);
			response.sendRedirect(getServletContext().getContextPath()+"/app/index.do");
//			request.getRequestDispatcher("/WEB-INF/jsp/app/comment.jsp").forward(request, response);
		}

		
		
		
	}

}
