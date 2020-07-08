<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/app/index.css">
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript">
    function submitTable() {
        var name = document.getElementById("name").value;
        var password = document.getElementById("password").value;

        if(name == null || name == ""){
            $("#message").val("请输入用户名");
            document.getElementById("name").focus();
            return false;//阻止默认行为
        }
        if(password == null || password == ""){
            $("#message").val("请输入密码");
            document.getElementById("password").focus();
            return false;
        }
        document.getElementById("loginform").submit();
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
<div class="register">
    <h2 style="text-align: center">登录</h2>
    <div class="registerForm" >
        <form method="post" action="${pageContext.request.contextPath}/app/login.do" id="loginform">
        
        	<input type="hidden"  name="method" value="submitTable">
            <center> <font color="red" id="message"></font></center>
            <input type="text" name="name" id="name" placeholder="用户名"><br>

            <input type="password" name="password"  id="password" placeholder="密码"><br>

            <input type="submit" name="login" value="登录"  id="login" onclick="submitTable()">
        </form>
    </div>

</div>

</body>
</html>