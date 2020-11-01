<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="${pageContext.request.contextPath }/css/mycss.css"
	type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/index.css">
<title>讨论区-用户设置</title>
<%@ include file="inc/header.jsp"%>

<!-- 布局中部 -->
	<div id="middle">

		<div class="user-left">


			<div class="user-info">
				<p>
					<img class="user-pic" src="/pic/${user.faceimage}">
				</p>
				<p class='user-nikename'>${user.username }</p>
				<p>
					<span>性别</span>${user.sex }
				</p>

				<p>
					<span>最新登录</span><fmt:formatDate value="${user.logintime }" pattern="yyyy-MM-dd HH:mm:ss" />
				</p>
				<p>
					<span>注册日期</span><fmt:formatDate value="${user.regtime }" pattern="yyyy-MM-dd" />
				</p>
				<p>
					<span><a href="#">修改资料</a></span>
				</p>
			</div>
		</div>

		<div class="user-right user-edit">

			<form action="${pageContext.request.contextPath }/UploadServlet" method="post" enctype="multipart/form-data">


				<table>
					<tbody>
						<tr>
							<td class="title" colspan="2"><h5>头像修改</h5></td>

						</tr>
						<tr>
							<td class="label"><img class="user-pic"
								src="/pic/${user.faceimage}" /></td>
							<!--type属性file是文件上传，不要写成text
							encpype="multipart/form-data" 确保匿名上传不正确的编码（以二进制提交到后台，完成传输）
							默认是 application/X-www-form-rulencode不能用上传文件						  -->
							<td><input type="file" name="file"></td>
						</tr>
						<tr>
							<td colspan="2" class="button">
								<button type="submit" class="login-btn">确认修改</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
			<form action="${pageContext.request.contextPath }//UpdateSexServlet" method="post">
				<table>
					<tbody>
						<tr>
							<td class="title" colspan="2"><h5>信息修改</h5></td>

						</tr>
						<tr>
							<td class="label">性别</td>
							<td><input type="radio" name="sex" ${user.sex=='男'?'checked':'' } value="男">男&nbsp;&nbsp;<input
								type="radio" name="sex" ${user.sex=='女'?'checked':'' } value="女">女</td>
						</tr>
						<tr>
							<td colspan="2" class="button">
								<button type="submit" class="login-btn">确认修改</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>

			<form action="${pageContext.request.contextPath }//UpdatePwdServlet" method="post">
				<table>
					<tbody>
						<tr>
							<td class="title" colspan="2"><h5>密码修改</h5></td>
						</tr>
						<tr>
							<td class="label">原密码</td>
							<td><input type="password" class="user-txt"
								placeholder="原密码" name="pwd"></td>
						</tr>
						<tr>
							<td class="label">新密码</td>
							<td><input type="password" class="user-txt"
								placeholder="新密码" name="newpwd" id="newpassword"><span id="npwd"></span></td>
						</tr>
						<tr>
							<td class="label">确认新密码</td>
							<td><input type="password" class="user-txt"
								placeholder="确认新密码" name="rnpwd" id="rnewpassword"><span id="rnpwd"></span></td>
						</tr>
						<tr>
							<td colspan="2" class="button">
								<button type="submit" class="login-btn">确认修改</button>
								<font color="red">${msg }</font>
							</td>
						</tr>
					</tbody>
				</table>
			</form>



		</div>

	</div>
	
	
	<%@ include file="inc/footer.jsp"%>
	
	
	