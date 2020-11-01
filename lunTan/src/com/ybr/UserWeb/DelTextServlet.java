package com.ybr.UserWeb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.ybr.UserService.UserService;
import com.ybr.UserService.Impl.UserServiceImpl;
import com.ybr.entity.User;

@WebServlet("/DelTextServlet")
public class DelTextServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;chaarset=utf8");
		
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)) {
			request.getRequestDispatcher("/UserInfoServlet").forward(request, response);
			return;
		}
		
		
		UserService us = new UserServiceImpl();
		
		us.delTextById(id);
		
		
		
		request.getRequestDispatcher("/UserInfoServlet").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
