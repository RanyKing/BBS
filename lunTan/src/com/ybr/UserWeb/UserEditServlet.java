package com.ybr.UserWeb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ybr.UserService.UserService;
import com.ybr.UserService.Impl.UserServiceImpl;
import com.ybr.entity.User;


@WebServlet("/UserEditServlet")
public class UserEditServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf8");
		
		//判断是否登录
		User jguser = (User)request.getSession().getAttribute("jguser");
		if(jguser==null) {
			response.sendRedirect(request.getServletContext().getContextPath()+"/login.jsp");
			return;
		}
		
		//获取userid的值
		String id = request.getParameter("id");
		
		//根据userid去数据库中取当前用户的信息转发到useredit.jsp页面
		UserService us = new UserServiceImpl();
		User user = us.findUserById(id);
		
		//将获取到的user对象存到request请求中去
		request.setAttribute("user", user);
		
		
		request.getRequestDispatcher("/useredit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
