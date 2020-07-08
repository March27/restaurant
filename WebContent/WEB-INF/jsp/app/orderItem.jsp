<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>订单详情</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app/index.css">
    <script type="text/javascript">
    	function pay(orderId){
    		window.location.href = "${pageContext.request.contextPath}/app/order.do?method=pay&orderId="+orderId;
    	}
    	
    	function deleteOrder(orderId){
    		window.location.href = "${pageContext.request.contextPath}/app/order.do?method=delete&orderId="+orderId;
    	}
    
    </script>
</head>
<body>
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

<!--订单详情-->
<div class="orderItem">
    <h2>订单详情</h2>
    
    
    <c:if test="${not empty orders}">
    	<c:forEach items="${orders }" var="order">
			<div>
			    <span>订单编号：${order.orderCode }</span>
			    <SPAN>下单时间：${order.orderTime }</SPAN>
			</div>
			<div>
			    <ul>
			    	<c:if test="${not empty order.orderDetail }">
			    		<c:forEach items="${ order.orderDetail}" var="orderDetail">
				    		<li>
					            <span>${orderDetail.food.foodName }</span>
					            <span class="orderNum">${orderDetail.buyNum }份</span>
					            <span class="orderPrice">￥${orderDetail.food.price}</span>
				        	</li>
			    		</c:forEach>
			    		
			    	</c:if>
			       
			    </ul>
			    <span class="orderPrice">总计：${order.totalPrice}</span><br>
			    <div class="orderBtn">
			    	<c:if test="${order.status == 0}">
			    		<!-- 参数为订单id -->
				        <input type="button" value="付款"  onclick="pay(${order.id})">
				        <input type="button" value="取消订单"  onclick="deleteOrder(${order.id})">
			       </c:if>
			    </div>
			</div>
			<br>
			<hr>
    	</c:forEach>
    
    </c:if>

</div>

</body>
</html>