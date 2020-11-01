<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/style.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/index.css">
<script src="${pageContext.request.contextPath }/js/jquery-1.8.3.js"></script>
<title>讨论区-注册</title>
<%@ include file="inc/header.jsp" %>
	<!-- 布局中部 -->
	<div id="login">
		<div id="login-top">
			<h2>注册</h2>
			<a href="login.jsp">已有账号，立即登陆</a>
		</div>
		<form action="${pageContext.request.contextPath }/UserRegisterServlet" method="post" id="myform">
			<input type="hidden" name="refer" value="">
			<div class="login-item">
				<label>用户名<span class="required">*</span></label> <input
					class="login-txt" type="text" id="username" autofocus="autofocus"
					autocomplete="off" placeholder="用户名" name="username"
					class="login-con " >
			</div>
			<span id="usernameinfo" style="font-size: 12px; float: right"></span>
			<div class="login-item">
				<label>密码<span class="required">*</span></label> <input
					class="login-txt" type="password" id="password" autocomplete="off"
					placeholder="登录密码" name="password" class="login-con ">
			</div>
			<span id="pwd" style="font-size: 12px; float: right; color:red;">${pmsg }</span>
			<div class="login-item">
				<label class="login-label">确认密码<span class="required">*</span></label>
				<input class="login-txt" type="password" id="rpwd"
					autocomplete="off" placeholder="登录密码" name="rpwd"
					class="login-con ">

			</div>
			<span id="repwd" style="font-size: 12px; float: right; color:red;">${rpmsg }</span>
			<div class="login-item">
				<label class="login-label">验证码<span class="required">*</span></label>
				<input class="login-txt login-code" id="scode" name="code"
					class="login-con login-code" placeholder="验证码"> <img
					src="${pageContext.request.contextPath }/TestCode" onclick="this.src='${pageContext.request.contextPath }/TestCode?'+new Date();"
					alt="点击获取验证码" title="点击刷新验证码"  id="img_scode"
					class="code_box">
			</div>
			<span id="code" style="font-size: 12px; float: right; color:red;">${cmsg }</span>
			<div class="login-item">
				<input name="remember" value="1" ${p==y?"":"checked" } type="checkbox">
				我已阅读并同意&nbsp;&nbsp; <a href="#">《用户协议》</a>
			</div>
			<div class="login-item">
				<button type="submit" class="login-btn" onclick="">注册</button>
			</div>
			<span id="rem" style="font-size: 12px; float: right; color:red;">${rmsg }</span>
		</form>
	</div>
	
	<script>
		var flag=false;
		$("#username").blur(function() {
			var username=$("#username").val();
			$.post("${pageContext.request.contextPath }/checkUserServlet",
			{"username":username},
			function(data) {
				if(data==0){
					$("#usernameinfo").text("用户名通过");
					$("#usernameinfo").css('color','green');
					return flag=true;
				}else if(data==1){
					$("#usernameinfo").text("用户名已存在");
					$("#usernameinfo").css('color','red');
				}else{
					$("#usernameinfo").text(data);
					$("#usernameinfo").css('color','red');
				}
			}
			)
		})
		$("#myform").submit(function() {
			
			return flag;
		})
		
	</script>

<%@ include file="inc/footer.jsp" %>

