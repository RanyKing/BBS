<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath }/css/mycss.css"
	type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/index.css">
<title>讨论区-发帖</title>
<%@ include file="inc/header.jsp"%>

<div id="index-center">

		<div id="crumbs">
			<a href="${pageContext.request.contextPath }/IndexServlet">论坛首页</a> > <a href="#">发新贴</a>
		</div>
		<form action="${pageContext.request.contextPath }/AddTextServlet" method="post">
		<table class="center-newtext">
			<colgroup>
				<col class="titleItem">
				<col class="item">
			</colgroup>
			<tbody>
				<tr>
					<td>标题：</td>
					<td><input type="text" class="text-title" maxlength="34" name="title" style="height: 26px"> <select
							class="selectCompose" name="type">
								<option value="">选择栏目</option>
								<c:forEach var="cate" items="${type }" >
									<option value="${cate.id}">${cate.name}</option>
                                </c:forEach>
						</select></td>
				</tr>

				<tr>
					<td valign="top">内容：</td>
					<td id="textAreaWrap"><div id="editorToolBar" class="editorToolBar">
						</div>
						<div >
							<textarea id="textAreaContainer" class="textAreaContainer" name="context"></textarea>
						</div></td>
				</tr>
				
				<tr>
					<font color="red">${msg }</font>
					<td align="right" colspan="2"><button class="submitBtn">发表</button></td>
				</tr>
			</tbody>
		</table>
       </form>
	</div>


<%@ include file="inc/footer.jsp"%>