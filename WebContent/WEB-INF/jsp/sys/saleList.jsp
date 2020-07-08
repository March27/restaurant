<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>销量统计</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sys/index.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
</head>
<body>
<div id="banner">
    <div class="resname">
        <span >清蒸籽</span>
    </div>
    <ul class="bannerul">
        <span style="float: right">欢迎！admin</span>
    </ul>
</div>

<div id="contentsys">
    <ul class="sysul">
        <li class="sysli">
            <a  href="${pageContext.request.contextPath}/sys/index.do" >主页</a>
        </li>
        <li class="sysli">
            <a  href="${pageContext.request.contextPath}/sys/foodTypeList.do?method=list" >菜系管理</a>
        </li>
        <li class="sysli">
            <a  href="${pageContext.request.contextPath}/sys/foodList.do?method=list">菜品管理</a>
        </li>
        <li class="sysli">
            <a href="${pageContext.request.contextPath}/sys/orderList.do?method=list" >订单管理</a>
        </li>
        <li class="sysli">
            <a href="${pageContext.request.contextPath}/sys/saleList.do?method=list" class="active">销量统计</a>
        </li>
    </ul>

    <div class="sysright">

        <!--        筛选菜品-->
        <div>
            <form>
				<a href="${pageContext.request.contextPath}/sys/saleList.do?method=month"><input type="button"  value="按月来统计" ></a>
                <a href="${pageContext.request.contextPath}/sys/saleList.do?method=week"><input type="button"  value="按周来统计" ></a>
				<a href="${pageContext.request.contextPath}/sys/saleList.do?method=day"><input type="button"  value="按日来统计" ></a>	
            </form>
        </div><br>
        <!--        菜品列表显示-->
        <div>
            <table class="table1">
                <thead>
                <tr align="center" valign="middle" id="TableTitle">
                    <td>菜品名</td>
                    <td>销量</td>
                    <td>时间</td>
                </tr>
                </thead>
                <!--显示数据列表 -->
                <tbody id="TableData">
                <c:if test="${not empty orders}">
                	<c:forEach items="${orders }" var="order">
				          <tr class="TableDetail1">
				
				           		<td>${order.food.foodName }&nbsp;</td>
				
				            	<td>${order.orderDetail.buyNum }&nbsp;</td>
				
				            	<td>${order.payTime }</td>

				      	</tr>
		                	
                	</c:forEach>
                
                </c:if>
                
                </tbody>
            </table>
        </div>


    </div>

</div>
</body>
</html>