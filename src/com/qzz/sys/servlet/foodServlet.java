package com.qzz.sys.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.qzz.sys.bean.Food;
import com.qzz.sys.bean.FoodType;
import com.qzz.sys.service.FoodService;
import com.qzz.sys.service.FoodServiceImpl;
import com.qzz.sys.service.FoodTypeService;
import com.qzz.sys.service.FoodTypeServiceImpl;
import com.qzz.sys.util.MyUTF;

/**
 * Servlet implementation class foodServlet
 */
@WebServlet("/sys/foodList.do")
@MultipartConfig //标识一个servlet支持文件的上传
public class foodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public foodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("==============/sys/foodList.do=============");
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			FoodServiceImpl foodService = new FoodService();
			FoodTypeServiceImpl foodTypeService=new FoodTypeService();
			//查询所有菜系名
			List<FoodType> foodTypes = foodTypeService.find(null,null);
			request.setAttribute("foodTypes", foodTypes);
			
			String keyword = request.getParameter("keyword");
			String foodTypeId = request.getParameter("foodTypeId");
			String method = request.getParameter("method");
			String id  = request.getParameter("id");
			String disabled = request.getParameter("disabled");
			String foodName = request.getParameter("foodName");
			String price = request.getParameter("price");
			String remark = request.getParameter("remark");
//			String img = request.getParameter("img");
//			System.out.println("keyword:"+keyword+"     foodTypeId:"+foodTypeId);
			
			if(method !=null && !method.equals("") && method.equals("list")) {
				List<Food> foods = foodService.find(keyword,foodTypeId);
				System.out.println(foods);
				
				
				
				request.setAttribute("keyword", keyword);
				request.setAttribute("foods", foods);
				request.setAttribute("foodTypeId", foodTypeId);
				
				request.getRequestDispatcher("/WEB-INF/jsp/sys/foodList.jsp").forward(request, response);
			}else if(method !=null && !method.equals("") && method.equals("update")) {
				//上架和下架（更新）
				//通过id来查找菜品，通过id来查找这个功能很常见，别的地方也可能用得到，将其封装，把对象传进去，再进行修改所需要的值，
				//如果把两个字段传过去更新也可以，但若是要传多个字段就会有点长，这样可以提高代码的重复利用率，再去修改其他值也行
				Food food = foodService.findById(Integer.parseInt(id));
				food.setDisabled(Integer.parseInt(disabled));
				System.out.println(food);
				//更新
				foodService.update(food);
				
				//不需要传值所以用了response
				response.sendRedirect(getServletContext().getContextPath()+"/sys/foodList.do?method=list");
			}else if(method !=null && !method.equals("") && method.equals("addPage")) {
				
				request.getRequestDispatcher("/WEB-INF/jsp/sys/foodAdd.jsp").forward(request, response);
			}else if(method !=null && !method.equals("") && method.equals("ajaxFoodName")) {
				//查询用户输入的菜品名是否存在
				Food  food = foodService.findByFoodName(foodName);
				System.out.println("ajaxFoodName:"+food);
				if(food!=null) {//菜系已存在
					response.getWriter().print("fail");
				}
				
//				
				
				
			}else if(method !=null && !method.equals("") && method.equals("addSubmit")) {
				//tianjia
				System.out.println("************添加***************");
				//@MultipartConfig使用该注解后，可通过getPart获取上传的文件，返回的是part类
				Part part =  request.getPart("img");
				
				//上传图片到项目目录，只保存上图片地址到数据库
				
				String filePath = getServletContext().getRealPath("upload/food");
				System.out.println("filepath:"+filePath);
				
				File file = new File(filePath);
				if(!file.exists()) {
					file.mkdirs();
				}
				String fileName = part.getSubmittedFileName();
				//为了防止用户上传的图片名重复，一般上传图片会给图片重新取一个不会重复的名字
				//截取文件的扩展名
				String extName = fileName.substring(fileName.lastIndexOf("."));
				String name = UUID.randomUUID().toString();
				
				StringBuffer newName = new StringBuffer();
				newName.append(name).append(extName);
				
				//上传到指定目录
				part.write(filePath+File.separator+newName.toString());
				
				//保存菜品相关信息到数据库
				Food food = new Food(foodName,Integer.parseInt(foodTypeId),Double.parseDouble(price),remark,newName.toString(),0);
				foodService.save(food);
				
				response.sendRedirect(getServletContext().getContextPath()+"/sys/foodList.do?method=list");
				
			}else if(method !=null && !method.equals("") && method.equals("viewUpdate")) {
				System.out.println("************viewupdate**************");
				Food food = foodService.findById(Integer.parseInt(id));
	
				request.setAttribute("food", food);
				request.getRequestDispatcher("/WEB-INF/jsp/sys/foodUpdate.jsp").forward(request, response);
			}else if(method !=null && !method.equals("") && method.equals("updateSubmit")) {
				//更新菜品
				System.out.println("************updatesubmit**************");
				//@MultipartConfig使用该注解后，可通过getPart获取上传的文件，返回的是part类
				Part part =  request.getPart("img");
				
				//上传图片到项目目录，只保存上图片地址到数据库
				
				String filePath = getServletContext().getRealPath("upload/food");
				System.out.println("filepath:"+filePath);
				
				File file = new File(filePath);
				if(!file.exists()) {
					file.mkdirs();
				}
				//xxx.jpg
				String fileName = part.getSubmittedFileName();
				
				if(fileName != null && !fileName.equals("")) {//用户更改了原来的照片
					
					//为了防止用户上传的图片名重复，一般上传图片会给图片重新取一个不会重复的名字
					//截取文件的扩展名
					String extName = fileName.substring(fileName.lastIndexOf("."));
					String name = UUID.randomUUID().toString();
					
					StringBuffer newName = new StringBuffer();
					newName.append(name).append(extName);
					
					//上传到指定目录
					part.write(filePath+File.separator+newName.toString());
					
					//更新菜品
					Food food = foodService.findById(Integer.parseInt(id));
					food.setFoodName(foodName);
					food.setFoodTypeId(Integer.parseInt(foodTypeId));
					food.setImg(newName.toString());
					food.setPrice(Double.parseDouble(price));
					food.setRemark(remark);
					foodService.update(food);
					
					
				}else {//没有修改图片
					//更新菜品
					Food food = foodService.findById(Integer.parseInt(id));
					food.setFoodName(foodName);
					food.setFoodTypeId(Integer.parseInt(foodTypeId));
//					food.setImg();
					food.setPrice(Double.parseDouble(price));
					food.setRemark(remark);
					foodService.update(food);
				}
					
				response.sendRedirect(getServletContext().getContextPath()+"/sys/foodList.do?method=list");
				
				
			}
			
	}

}
