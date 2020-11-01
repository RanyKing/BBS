<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/index.css">
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<title>讨论区-登录</title>
<%@ include file="inc/header.jsp" %>
	<div id="login">
		<div id="login-top">
			<h2>登录</h2>
			<a href="register.jsp"> 还没有账号，立即注册</a>
		</div>
		<form action="${pageContext.request.contextPath }/UserLoginServlet" method="post">
			<div class="login-item">
				<label>用户名<span>*</span></label>
				
				<input class="login-txt" name="username"  placeholder="用户名" value="${cookie.username.value }">
			</div>
			<div class="login-item">
				<label>密码<span>*</span></label> <input class="login-txt" type="password" name="password" placeholder="登录密码"value="${cookie.password.value }"/>
			</div>
			<div class="login-item">
				<label>验证码<span>*</span></label> <input class="login-txt login-code" name="code" placeholder="验证码" /> 
				<img src="${pageContext.request.contextPath }/TestCode"
					onclick="this.src='${pageContext.request.contextPath }/TestCode?'+new Date();" alt="点击修改验证码" title="点击刷新验证码">
			</div>
			<div class="login-item">
				<input type="checkbox" name="saveUserName" value="1" ${p==y?"":"checked" }> 记住我&nbsp;&nbsp; <a href="#">忘记密码？</a>
			</div>
			<div class="login-item">
				<button type="submit" class="login-btn">登录</button>
				<font color="red">${msg }</font>
			</div>
		</form>
	</div>





<%@ include file="inc/footer.jsp"%>

