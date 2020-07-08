<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台菜品列表</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sys/index.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
	  //文档加载完后
		window.onload=function(){
			
			//获取后台保存的disabled值
			var foodTypeId = "${foodTypeId}";
			//遍历菜系是否删除状态的select标签中所有的option标签
			var disabledSelect = document.getElementById("foodTypeId")
			//获取下拉框中所有的option
			var options = disabledSelect.options;
			
			$.each(options,function(i,option){
				$(option).attr("selected",option.value == foodTypeId);
			});
			
		}
	    
    	function foodTypeChange(obj){
    		//获取用户输入的关键字
    		var keyword = $("#keyword").val();
    		//获取被选择的
    		var foodTypeId = obj.value;
    		
    		//发送请求
    		window.location="${pageContext.request.contextPath}/sys/foodList.do?method=list&keyword="+keyword+"&foodTypeId="+foodTypeId;
    	}
    
    </script>
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
            <a  href="${pageContext.request.contextPath}/sys/foodTypeList.do?method=list">菜系管理</a>
        </li>
        <li class="sysli">
            <a  href="${pageContext.request.contextPath}/sys/foodList.do?method=list" class="active">菜品管理</a>
        </li>
        <li class="sysli">
            <a href="${pageContext.request.contextPath}/sys/orderList.do?method=list">订单管理</a>
        </li>
        <li class="sysli">
            <a href="${pageContext.request.contextPath}/sys/saleList.do?method=list" >销量统计</a>
        </li>
    </ul>

    <div class="sysright">

<!--        筛选菜品-->
        <div>
            <form action="${pageContext.request.contextPath}/sys/foodList.do" method="get">
            <input type="hidden" name="method" value="list">
                <input id="keyword" name="keyword" type="text" placeholder="请输入菜品名称" value="${keyword }">
                <select name="foodTypeId" id="foodTypeId" onchange="foodTypeChange(this)">
                    <option value="">全部</option>
                    <c:forEach items="${foodTypes }" var="foodType">
                    
                    	<option value="${foodType.id}">${foodType.typeName}</option>
                    </c:forEach>
                    
                </select>
                <input type="submit" value="搜索">
                <a href="${pageContext.request.contextPath}/sys/foodList.do?method=addPage"><input type="button"  value="添加" ></a>
            </form>
        </div><br>
<!--        菜品列表显示-->
        <div>
            <table class="table1">
                <thead>
                <tr align="center" valign="middle" id="TableTitle">
                    <td>编号</td>
                    <td>菜名</td>
                    <td>所属菜系</td>
                    <td>价格</td>
                    <td>创建时间</td>
                    <td>修改时间</td>
                    <td>是否删除</td>
                    <td>操作</td>
                </tr>
                </thead>
                <!--显示数据列表 -->
                <tbody id="TableData">
                
                <c:choose>
                	<c:when test="${not empty foods }">
	                	<c:forEach items="${foods}" var="food" varStatus="status">
	                	<tr class="TableDetail1">
	                
		                	<td>${status.index+1 }&nbsp;</td>
		                    <td>${food.foodName }&nbsp;</td>
		                    <td>${food.foodType.typeName}&nbsp;</td>
		                    <td>${food.price }&nbsp;</td>
		                    <td><fmt:formatDate value="${food.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>   </td>
		                    <td><fmt:formatDate value="${food.updateDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		                    <td>
		                    <c:if test="${food.disabled==1 }">
		                    		已删
		                    </c:if>
		                    <c:if test="${food.disabled==0 }">
		                    		未删
		                     </c:if> 
		                    </td>
		                    <td>
		                        <a href="${pageContext.request.contextPath}/sys/foodList.do?method=viewUpdate&id=${food.id }"  class="FunctionButton">更新</a>
		                        <c:if test="${food.disabled==1 }">
		                        	<a href="${pageContext.request.contextPath}/sys/foodList.do?method=update&id=${food.id }&disabled=0" class="FunctionButton">上架</a>
		                        </c:if>
		                   		<c:if test="${food.disabled==0 }">
		                        	<a href="${pageContext.request.contextPath}/sys/foodList.do?method=update&id=${food.id }&disabled=1" class="FunctionButton">下架</a>
		                    	</c:if>
		                    </td>
	 
	                </tr>
	                	</c:forEach>
                	
                	</c:when>
                	<c:when test="${ empty foods }">
                		没有你要找的数据！
                	</c:when>
                </c:choose>
               
                	

                </tbody>
            </table>
        </div>


    </div>

</div>
</body>
</html>