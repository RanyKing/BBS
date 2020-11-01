package com.ybr.UserWeb;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ybr.UserService.UserService;
import com.ybr.UserService.Impl.UserServiceImpl;
import com.ybr.entity.User;

@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf8");
		
		//获取用户名和密码
		String username = request.getParameter("username");
		if(StringUtils.isBlank(username)) {
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		String password = request.getParameter("password");
		if(StringUtils.isBlank(password)) {
			request.setAttribute("pmsg", "密码不能为空");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		
		//获取确认密码并判断两次密码是否相同
		String rpwd = request.getParameter("rpwd");
		if(!StringUtils.equals(password, rpwd)) {
			request.setAttribute("rpmsg", "两次密码输入不同");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		
		//获取验证码
		String code = request.getParameter("code");
		String checkcode =(String)request.getSession().getAttribute("checkcode");
		if(!code.equalsIgnoreCase(checkcode)) {
			request.setAttribute("cmsg", "验证码错误");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		
		//判断是否同意
		String remember = request.getParameter("remember");
		if(!"1".equals(remember)) {
			request.setAttribute("rmsg", "请同意用户协议");
			request.setAttribute("p", "y");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRegtime(new Date());
		
		UserService us = new UserServiceImpl();
		int jg =us.register(user);
		
		if(jg==0) {
			response.sendRedirect(request.getServletContext().getContextPath()+"/login.jsp");
			return;
		}else {
			System.out.println("服务器出错！");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
