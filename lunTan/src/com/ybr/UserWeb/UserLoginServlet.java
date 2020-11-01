package com.ybr.UserWeb;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ybr.UserService.UserService;
import com.ybr.UserService.Impl.UserServiceImpl;
import com.ybr.entity.User;


@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//编码设置
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf8");
		
		//获取用户名和密码，验证码，记住用户名
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		String saveUserName = request.getParameter("saveUserName");
		
		//健壮性判断
		if(StringUtils.isBlank(username)) {
			request.setAttribute("msg", "用户名不能为空");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		if(StringUtils.isBlank(password)) {
			request.setAttribute("msg", "密码不能为空");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		
		//获取session中存放的checkcode
		String checkcode = (String)request.getSession().getAttribute("checkcode");
		
		//验证码判断
		if(!StringUtils.equalsIgnoreCase(checkcode, code)) {
			request.setAttribute("msg", "验证码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		//将获取到的数据进行封装
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setLogintime(new Date());
		
		UserService us = new UserServiceImpl();
		User jguser = us.login(user);
		if(null==jguser) {
			request.setAttribute("msg", "用户名或密码错误！");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		//将用户名存到session中，以备再次调用
		request.getSession().setAttribute("jguser", jguser);
		
		//是否记住用户名
		if("1".equals(saveUserName)) {
			Cookie cookie1 = new Cookie("username", username);
			Cookie cookie2 = new Cookie("password", password);
			cookie1.setMaxAge(60*60*24*7);
			cookie2.setMaxAge(60*60*24*7);
			cookie1.setPath("/");
			cookie2.setPath("/");
			response.addCookie(cookie1);
			response.addCookie(cookie2);
		}else {
			request.setAttribute("p", "y");
			Cookie cookie1 = new Cookie("username", "");
			Cookie cookie2 = new Cookie("password", "");
			cookie1.setMaxAge(0);
			cookie2.setMaxAge(0);
			cookie1.setPath("/");
			cookie2.setPath("/");
			response.addCookie(cookie1);
			response.addCookie(cookie2);
		}
		
		
		
		request.getRequestDispatcher("/IndexServlet").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
