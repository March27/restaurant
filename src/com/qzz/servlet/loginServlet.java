package com.qzz.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qzz.bean.User;
import com.qzz.service.UserService;
import com.qzz.service.UserServiceImpl;

/**
 * Servlet implementation class loginServlet
 */
@WebServlet("/app/login.do")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");//post�������ݹ���������
		String method = request.getParameter("method");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		System.out.println("method:"+method);
		
		if(method!=null &&method.equals("submitTable")) {
			//�ύ��½��
			if(name != null && !name.equals("")) {
				UserServiceImpl userService = new UserService();
				User user  = userService.findByLoginNameAndPass(name,password);
				System.out.println("user"+user);
				if(user !=null) {
					//������session�е����������������������Ч��Ĭ��30����
					request.getSession().setAttribute("session_user", user);
					//��½�ɹ� ��ת����ҳ
					response.sendRedirect(getServletContext().getContextPath()+"/app/index.do");
				}else {
					//shibai ��ת����¼ҳ��
					
					request.getRequestDispatcher("/WEB-INF/jsp/app/login.jsp").forward(request, response);
				}
			}
		}
		else {
			//��ת����¼ҳ��
			
			request.getRequestDispatcher("/WEB-INF/jsp/app/login.jsp").forward(request, response);
		}
		
		
	}

}
