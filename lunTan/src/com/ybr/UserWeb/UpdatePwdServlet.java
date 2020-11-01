package com.ybr.UserWeb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ybr.UserService.UserService;
import com.ybr.UserService.Impl.UserServiceImpl;
import com.ybr.entity.User;

@WebServlet("/UpdatePwdServlet")
public class UpdatePwdServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf8");

		User jguser = (User) request.getSession().getAttribute("jguser");
		System.out.println(jguser);
		if (jguser == null) {
			response.sendRedirect(request.getServletContext().getContextPath() + "/login.jsp");
			return;
		}

		// 获取当前的userid
		Integer userid = jguser.getId();

		UserService us = new UserServiceImpl();
		
		// 更改用户的密码
		// 获取用户输入的密码信息
		String pwd = request.getParameter("pwd");
		String newpwd = request.getParameter("newpwd");
		String rnpwd = request.getParameter("rnpwd");
		// 健壮性判断
		if(StringUtils.isBlank(pwd) || StringUtils.isBlank(newpwd) || StringUtils.isBlank(rnpwd) ) {
			request.setAttribute("msg", "密码输入不能为空");
			request.getRequestDispatcher("/UserEditServlet?id=" + userid).forward(request, response);
			return;
		}
		if(StringUtils.equals(pwd, newpwd)&&StringUtils.equals(pwd, rnpwd)) {
			request.setAttribute("msg", "新密码与初始密码一致");
			request.getRequestDispatcher("/UserEditServlet?id=" + userid).forward(request, response);
			return;
		}
		if(!StringUtils.equals(rnpwd, newpwd)) {
			request.setAttribute("msg", "新密码与确认密码输入不一致");
			request.getRequestDispatcher("/UserEditServlet?id=" + userid).forward(request, response);
			return;
		}
		//将当前的用户名和输入的密码封装成user对象
		User user = new User();
		user.setUsername(jguser.getUsername());
		user.setPassword(pwd);
		//根据当前封装的用户进行密码跟新操作
		int jg =us.updatePwd(newpwd,user);
		
		if(jg==1) {
			//如果结果为1则修改成功跳转到登录页面
			response.sendRedirect(request.getServletContext().getContextPath() + "/login.jsp");
			return;
		}
		if(jg==0) {
			request.setAttribute("msg", "初始密码输入错误");
			request.getRequestDispatcher("/UserEditServlet?id=" + userid).forward(request, response);
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doGet(request, response);
	}

}
