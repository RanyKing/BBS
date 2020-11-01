<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/index.css">
<title>讨论区</title>
<%@ include file="inc/header.jsp"%>
	<div id="index-center">

		<div id="crumbs">
			<a href="${pageContext.request.contextPath }/IndexServlet">论坛首页</a>  <a href="${pageContext.request.contextPath }/IndexServlet?tid=${tiezi.type.id }">${tiezi.type.name }</a>
		</div>
      
		<div id="text-title">${tiezi.title }</div>
	
		<div id="text-info">
		
			<span>楼主：<a href="">${tiezi.user.username }</a></span> 
			<span>时间：<fmt:formatDate value="${tiezi.fabutime }" pattern="yyyy-MM-dd HH:mm:ss"/> </span>
				 <span>回复：${tiezi.count }</span>
		</div>

		<div id="text-con">
			${tiezi.content }
		</div>
 
 		<c:forEach items="${tiezi.list }" var="tl" varStatus="status">
		<div class="text-item">
			<div class="item-info">
				<span>${status.getCount() }楼：<a href="">${tl.user.username }</a></span> 
				<span>
				时间：<fmt:formatDate value="${tl.replytime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</span>
			</div>
			<div class="item-con">${tl.replycontent }</div>

		</div>
       </c:forEach>
		 <div id="text-con">
		  <form action="${pageContext.request.contextPath }/AddReply?tid=${tiezi.id}" method="post">
		   <table>
		   <tr><td><textarea id="textAreaContainer" class="textAreaContainer" name="replycontext" style="height: 100px;"></textarea></td></tr>
			<tr><td align="right" ><button >评论</button></td></tr>
			</table>
			</form>
		</div> 
		
	</div>
	
	<%@ include file="inc/footer.jsp"%>
