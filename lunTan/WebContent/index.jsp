<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath }/css/mycss.css"
	type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/index.css">

<title>讨论区-首页</title>
	
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
							<td><a
								href="${pageContext.request.contextPath }/IndexServlet">论坛首页</a></td>
						</tr>

					</table>

					<table>
						<c:forEach items="${typeList }" var="typeList">
							<tr>
								<td><a
									href="${pageContext.request.contextPath }/IndexServlet?tid=${typeList.id }">${typeList.name }</a></td>
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
			<c:forEach items="${page.list }" var="tieziList">
				<tr>
					<td><a href="${pageContext.request.contextPath }/ReplyServlet?id=${tieziList.id }"
						target="_blank">${tieziList.title } </a></td>
					<td><a href="#" target="_blank">${tieziList.user.username }</a></td>

					<td>${tieziList.count }</td>
					<td>${tieziList.type.name }</td>
					<td><fmt:formatDate value="${tieziList.fabutime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
				</tr>
			</c:forEach>

		</table>
		<div class="page">
			<ul id="pagination-flickr">
				<c:if test="${pageNum>1 }">
				<li><a href="${pageContext.request.contextPath }/IndexServlet?pageNum=${pageNum-1}&tid=${tieziList.typeid } ">上一页</a></li>
				</c:if>
				
				<c:forEach begin="1" end="${page.end }" var="num">
					<li class=${pageNum==num?'active':''}><a href="${pageContext.request.contextPath }/IndexServlet?pageNum=${num }&tid=${tieziList.typeid } ">${num }</a></li>
				</c:forEach>
				
				<c:if test="${pageNum<page.end }">
				<li><a href="${pageContext.request.contextPath }/IndexServlet?pageNum=${pageNum+1}&tid=${tieziList.typeid }">下一页</a></li>
				</c:if>
			</ul>
		</div>

	</div>

</div>


<%@ include file="inc/footer.jsp"%>
