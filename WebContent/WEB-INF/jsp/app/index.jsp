
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ page language="java" import="java.util.*" %>

<%@ page language="java" import="com.qzz.bean.Food" %>
<!DOCTYPE html>
<html lang="en" xmlns:bothmargin-top="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>首页</title>
	
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app/index.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
	<script>
		function addShopCar(foodId){
			//加入商品到购物车，加入哪一个商品的foodId
			
			//到后端加入商品到购物车去
			
			window.location.href = "${pageContext.request.contextPath}/app/shopCar.do?method=add&foodId="+foodId;
			
		}
		
		function blurFn(obj,foodId,buyNum){
			var num = obj.value;
			//如果输入的数量<1或者不是一个数字,就赋值为原来的buyNum
			if(num<1||isNaN(num)){
				obj.value = buyNum;
			}else if(num!=buyNum){
			//反之，Math.ceil向上取整，修改购买数量
				window.location.href = "${pageContext.request.contextPath}/app/shopCar.do?method=update&foodId="+foodId+"&buyNum="+Math.ceil(num);
			}
		}
		
		function deleteFn(foodId){
			window.location.href = "${pageContext.request.contextPath}/app/shopCar.do?method=delete&foodId="+foodId;
		}
		
		function order(userId){
			window.location.href = "${pageContext.request.contextPath}/app/order.do?method=order&total="+${total}+"&userId="+userId;
		}
	</script>
	
</head>
<body>
<!-- 分页操作-->
<%--     <% 
         ArrayList<Food> list = (ArrayList<Food>)request.getAttribute("foods");  //此处是取出所存储的数据
         System.out.println("list："+list);
         int page_current = 1; //当前页数
         int page_begin = 0;  //起始点,注意:下标从0开始
         int page_end = 9;   //终点,每页十条信息
         int total_count = 0;
         if(list != null)
            total_count = list.size();   //信息的总量
         int page_total = total_count / 10 + (total_count % 10 != 0 ? 1 : 0);
         if(request.getParameter("begin") != null) {
        	 page_current = Integer.parseInt(request.getParameter("begin"));  //获取当前页数
                        }
         page_begin = (page_current - 1) * 10;
         page_end = page_begin + 9 > total_count ? total_count : page_begin + 9;
         request.getSession().setAttribute("page_current", page_current);  //保存到session中
         request.getSession().setAttribute("page_total", page_total);
    %>
 --%>



<div id="banner">
        <div class="resname">
            <span >清蒸籽</span>
        </div>
        <ul class="bannerul">
        <c:if test="${not empty session_user }">
        	<li class="bannerli">
                <a href="${pageContext.request.contextPath}/app/loginout.do">退出</a>
            </li>
        </c:if>
        <c:if test="${ empty session_user }">
            <li class="bannerli">
                <a href="${pageContext.request.contextPath}/app/register.html">注册</a>
            </li>
            <li class="bannerli">
                <a href="${pageContext.request.contextPath}/app/login.do">登录</a>
            </li>
         </c:if>
         <c:if test="${not empty session_user }">
            <li class="bannerli">
                <a href="${pageContext.request.contextPath}/app/order.do?method=list&userId=${session_user.id}">订单</a>
            </li>
          </c:if>
            <li class="bannerli">
                <a href="${pageContext.request.contextPath}/app/index.do">主页</a>
            </li>
        </ul>


</div>

<div id="content" class="clearfix">
<!--    左-->
<div class="left">
<!--        登录-->
        <div id="leftLogin" class="login">
        <c:if test="${not empty session_user }">
        	<div>
        		Hi~ o(*￣▽￣*)ブ${session_user.name} welcome！
        	</div>
        </c:if>
        <c:if test="${ empty session_user }">
        	<div>
        		请先登录！
        	</div>
        </c:if>
        </div>
<!--        购物车-->
    <div id="leftShopCar">
        <div class="login">
            <form method="post" >
                <ul class="carul">
                
                	<c:if test="${not empty foods2}">
                		<c:forEach items="${foods2 }" var="food">
                			<li>
                        		<div>
                            		<a href="#" class="cara">${food.foodName}</a>
                            		<input name="id" value="${food.id}" type="hidden"> 
                            		<!--  blurFn第二个参数商品id，第三个参数是购买数量,-->
                            		<input name=buyNum value="${food.buyNum}" onblur="blurFn(this,${food.id},${food.buyNum})" style="width: 15px">
                            		<!--  deleteFn菜品id-->
                            		<input type="button"  value="×" onclick="deleteFn(${food.id});" style="border-radius: 3px;border: 1px solid #a3a3a3;"></input>
                        		</div>
                        		<div class="pricespan">
                            		<span>${food.price}￥</span>
                        		</div>
                    		</li><hr>
                		</c:forEach>
                	</c:if>
                    
                </ul>

                <div class="pricespan">
                    <span>总金额：${total}</span>
                    <c:if test="${not empty session_user }">
                    	<input type="button" onclick="order(${session_user.id})" value="下单">
                    </c:if>
                </div>
                
            </form>
        </div>

    </div>
    </div>

<!--    右-->
    <div class="right">
<!--        右菜品-->
        <div class="rightheader">
            <h2>菜品：
            	<!-- 遍历所有未删除的菜系 -->
            	<c:if test="${not empty foodTypes }">
            		<c:forEach items="${foodTypes}" var="foodType" >
                		<a href="${pageContext.request.contextPath}/app/index.do?foodTypeId=${foodType.id}">${foodType.typeName}</a>
            		
            		</c:forEach>
            	</c:if>
            	<c:if test="${empty foodTypes }">当前没有菜系名</c:if>
                
            </h2>
        </div>
<!--        右菜展示-->
        <div >
<!--            1-->
            <c:if test="${not empty foods}">
            	<c:forEach items="${foods}" var="food" varStatus="index">
            		<div class="img">
            			 <div class="imgkuang">
               			 	<img src="${pageContext.request.contextPath}/img/app/food/${food.img}"  alt="picture" width="300" height="200">
               			 </div>
               			 <div class="imgma">
                 		 	<div>
                       		 	<span><b>${food.foodName}</b></span>
                        	 	<span class="spfloat">${food.price} ￥</span>
                    		</div>
                    		<div class="xiangqing">
                        		<span><a href="${pageContext.request.contextPath}/app/comment.do?method=index&userId=${session_user.id}&foodId=${food.id} ">详情</a></span>
                        		<form method="post" class="spfloat" action="#">
                        		<input type="hidden" id="dinnerTableId"  name="dinnerTableId" value="${dinnerTable.id }"> 
                            		<input type="button" onclick="addShopCar(${food.id})" value="加入购物车" class="btn">
                        		</form>
                    		</div>
                		 </div>
           			</div>
            	</c:forEach>
            </c:if>
<!--             2-->


        </div>


    </div>
</div>

    

    <div class="page">
        <ul class="pagination">
        
			
            <li><a href="${pageContext.request.contextPath}/app/index.do?foodTypeId=${foodType.id}">«</a></li>
            <li><a class="active" href="${pageContext.request.contextPath}/app/index.do?foodTypeId=${foodType.id}">1</a></li>
            <li><a href="${pageContext.request.contextPath}/app/index.do?foodTypeId=${foodType.id}">»</a></li>
    
        </ul>
    </div>

<!-- <div class="footer">
    <a href="#">管理中心</a>
</div> -->


</body>
</html>