<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath }/css/mycss.css"
	type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/index.css">
<title>讨论区-个人中心</title>
<%@ include file="inc/header.jsp"%>

<!-- 布局中部 -->
<div id="middle">

	<div class="user-left">


		<div class="user-info">
			<p>
				<img class="user-pic" src="/pic/${rsuser.faceimage }">
			</p>
			<p class='user-nikename'>${rsuser.username }</p>
			<p>
				<span>性别</span>${rsuser.sex }
			</p>

			<p>
				<span>最新登录</span><fmt:formatDate value="${rsuser.logintime }" pattern="yyyy-MM-dd HH:mm:ss" />
			</p>
			<p>
				<span>注册日期</span><fmt:formatDate value="${rsuser.regtime }" pattern="yyyy-MM-dd" />
			</p>
			<p>
				<span><a href="${pageContext.request.contextPath }/UserEditServlet?id=${rsuser.id}">修改信息</a></span>
			</p>
		</div>
	</div>

	<div class="user-right text_list">

		<table>

			<tr>
				<td><h5>TA的贴子</h5></td>
				<td></td>
				<td></td>
				<td><h4>
						<a href="${pageContext.request.contextPath }/AddTieziServlet"><font color="green">发帖</font></a>
					</h4></td>
			</tr>
			<tr>
				<th>帖子名</th>
				<th>栏目</th>
				<th>发布时间</th>
				<th>删除</th>
			</tr>
			<c:forEach items="${pageList.list }" var="list">
			<tr>
				<td><a href="ReplyServlet?id=${list.id}" target="_blank">${list.title }</a></td>
				<td><a href="#" target="_blank">${list.type.name }</a></td>
				<td><fmt:formatDate value="${list.fabutime }" pattern="yyyy-MM-dd" /></td>
				<td><a href="${pageContext.request.contextPath }//DelTextServlet?id=${list.id}"onclick="return delText()">删除</a></td>
			</tr>
			</c:forEach>


		</table>
	</div>

	<div class="page">
		<ul id="pagination-flickr">
			<c:if test="${pageNum>1 }">
			<li class="pre-off"><a href="${pageContext.request.contextPath }/UserInfoServlet?pageNum=${pageNum-1 }">上一页</a></li>
			</c:if>
			
			<c:forEach begin="1" end="${pageList.end }" var="i">
			<li class=${pageNum==i?'active':''}><a href="${pageContext.request.contextPath }/UserInfoServlet?pageNum=${i}">${i }</a></li>
			</c:forEach>
			
			<c:if test="${pageNum<pageList.end }">
			<li ><a href="${pageContext.request.contextPath }/UserInfoServlet?pageNum=${pageNum+1 }">下一页</a></li>
			</c:if>
		</ul>
	</div>

</div>
<script type="text/javascript">
function delText() {
	var bool = confirm("是否删除该贴及其评论吗？")
	if(bool){
		return true;
	}else{
		return false;
	}
}
</script>

<%@ include file="inc/footer.jsp"%>

