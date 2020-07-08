<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en" xmlns:bothmargin-top="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <title>菜品详情</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app/index.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
	<script type="text/javascript">
		function comment(){
			var contents = document.getElementById("contents").value;
			document.getElementById("commentform").submit();
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
  
    </div>

    <!--    右-->
    <div class="right">
        <!--        右菜品-->
        <div class="rightheader">
            <h2>
            	菜品：简介&评论区
            </h2>
        </div>

        <div>
            <a href="${pageContext.request.contextPath}/app/index.do"><img src="${pageContext.request.contextPath}/img/app/back.jpg" style="width: 30px;height: 30px"></a>
        </div>
        
        <div style="margin: auto;width: 700px">
        <!--        右菜展示-->
        <div >
            <!--            1-->
            <div class="img" >
                <div class="imgkuang">
               			 	<img src="${pageContext.request.contextPath}/img/app/food/${food.img}"  alt="picture" width="300" height="200">
               			 </div>
                <div class="imgma">
                    <div>
                        <span><b>${food.foodName }</b></span>
                        <span class="spfloat">￥${food.price }</span>
                    </div>
                    <div class="xiangqing">
<!--                        <span><a href="#">详情</a></span>-->
                        <form method="post" class="spfloat">
                            <!-- <input type="submit" value="加购" class="btn"> -->
                          	  强烈推荐(*^▽^*)
                        </form>
                    </div>
                </div>
            </div>
<!--            菜品介绍-->
            <div class="detils">
                ${food.remark }

            </div>


            <h3 style="clear: both">评论区：</h3>
            <div >
                <form method="post" action="${pageContext.request.contextPath}/app/comment.do?method=submitTable&userId=${session_user.id}&foodId=${food.id}" id="commentform">
                <input type="hidden"  name="method" value="submitTable">
                <textarea class="comment" placeholder="发表评论" style="height: 100px" id="contents" name="contents"></textarea>
                <input type="submit" value="评论" style="margin-left: 480px" onclick="comment()">
                </form>
            </div>
            <br>

            <div class="comment">
                <ul class="carul">
                <c:if test="${not empty comments }">
                	<c:forEach items="${comments }" var="comment">
                    <li>
                        <div>
                            @${comment.user.name }：${comment.content }
                        </div>
                         <div style="text-align: right;color: #a3a3a3">
                           
                           <fmt:formatDate value="${comment.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </div>
                        <hr>
                    </li>
                    </c:forEach>
                 </c:if>
                    <c:if test="${ empty comments }">
                    	暂无评论。
                    </c:if>
                </ul>

            </div>
        </div>


        </div>


    </div>
</div>
<!--        分页-->


<div class="footer">
    <a href="#">管理中心</a>
</div>
</body>
</html>