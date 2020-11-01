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



@WebServlet("/checkUserServlet")
public class checkUserServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf8");
		request.setCharacterEncoding("utf8");
		
		String username = request.getParameter("username");
		if(StringUtils.isBlank(username)) {
			response.getWriter().write("用户名不能为空");
			return;
		}
		
		UserService us = new UserServiceImpl();
		User user = us.checkUsername(username);
		
		if(user==null) {
			response.getWriter().write("0");
		}else {
			response.getWriter().write("1");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
