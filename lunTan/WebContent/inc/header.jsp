<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 布局顶部登录状态 -->
	<div id="header">
		<table>
			<tr>
				<td><a href="${pageContext.request.contextPath }/IndexServlet"><img src="images/logo.png" />&nbsp;&nbsp;讨论区</a></td>
				<c:choose>
				<c:when test="${sessionScope.jguser.username !=null}">
				<td
					style="FONT-SIZE: 12px; FONT-WEIGHT: bold; COLOR: #fff; PADDING-TOP: 20px; PADDING-RIGHT: 20px"
					align=right>欢迎 <font color="red">${sessionScope.jguser.username }</font> 登录!&nbsp;| <a
					class="one" href="${pageContext.request.contextPath }/UserInfoServlet">个人中心</a>| <a href="${pageContext.request.contextPath }/LogoutServlet">退出</a>|
				</td>
				</c:when>
				<c:otherwise>
				<td
					style="FONT-SIZE: 12px; FONT-WEIGHT: bold; COLOR: #fff; PADDING-TOP: 20px; PADDING-RIGHT: 20px"
					align=right>您还未登录!&nbsp;| <a class="one" href="${pageContext.request.contextPath }/login.jsp">登录</a>|
					<a href="${pageContext.request.contextPath }/register.jsp">注册</a>|
				</td>
				</c:otherwise>
				</c:choose>

			</tr>

		</table>
	</div>