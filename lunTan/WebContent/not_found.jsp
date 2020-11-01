<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="${pageContext.request.contextPath }/css/mycss.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/index.css">
<title>讨论区</title>
<%@ include file="inc/header.jsp"%>
<!-- 布局中部 -->
<div id="middle">

	<!-- 布局中部左边 -->
	<div id="index_left">
		<table height="100%">
			<tr>
				<td vAlign=top align=middle>

					<table>
						<tr>
							<td><a href="${pageContext.request.contextPath }/IndexServlet">论坛首页</a></td>
						</tr>

					</table>

					<table>
						<c:forEach items="${typeList }" var="typeList">
						<tr>
							<td><a href="${pageContext.request.contextPath }/IndexServlet?tid=${typeList.id }">${typeList.name }</a></td>
						</tr>
						</c:forEach>
					</table>

				</td>
			</tr>
		</table>
	</div>


	<!-- 布局中部右边 -->
	<div id="index_center">
		<table class="text_list" id="index_center">
			<tr>

				<th>标题</th>
				<th>作者</th>
				<th>回复</th>
				<th>帖子类型</th>
				<th>发帖时间</th>

			</tr>
			<tr>
				<td colspan=5>页面不存在！</td>
			</tr>

		</table>
		

	</div>

</div>

<%@ include file="inc/footer.jsp"%>

