<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>后台登录</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/sys/index.css">
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
        <span style="float: right">欢迎！admin</span>
    </ul>
</div>

<div class="register">
    <h2 style="text-align: center">后台登录</h2>
    <div class="registerForm" >
        <form method="post" action="${pageContext.request.contextPath}/sys/loginsys.do" id="loginform">
            <input type="hidden"  name="method" value="submitTable">
            <input type="text" name="name" id="name" placeholder="用户名"><br>

            <input type="password" name="password"  id="password" placeholder="密码"><br>

            <input type="submit" name="login" value="登录"  id="login" onclick="submitTable()">
        </form>
    </div>

</div>
</body>
</html>