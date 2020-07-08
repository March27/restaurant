package com.qzz.sys.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qzz.sys.bean.User;
import com.qzz.sys.dao.UserDao;
import com.qzz.sys.dao.UserDaoImpl;
import com.qzz.sys.service.UserService;
import com.qzz.sys.service.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/sys/loginsys.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//post传递数据过来会乱码
		String method = request.getParameter("method");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		System.out.println("method:"+method);
		
		if(method!=null &&method.equals("submitTable")) {
			//提交登陆表单
			if(name != null && !name.equals("")) {
				UserServiceImpl userService = new UserService();
				User user  = userService.findByLoginNameAndPass(name,password);
				System.out.println("user"+user);
				if(user !=null) {
					//保存在session中的数据是在整个浏览器中有效，默认30分钟
					request.getSession().setAttribute("session_user", user);
					//登陆成功 跳转到首页
					response.sendRedirect(getServletContext().getContextPath()+"/sys/index.do");
				}else {
					//shibai 跳转到登录页面
					
					request.getRequestDispatcher("/WEB-INF/jsp/sys/login.jsp").forward(request, response);
				}
			}
		}
		else {
			//跳转到登录页面
			
			request.getRequestDispatcher("/WEB-INF/jsp/sys/login.jsp").forward(request, response);
		}
	}

}
