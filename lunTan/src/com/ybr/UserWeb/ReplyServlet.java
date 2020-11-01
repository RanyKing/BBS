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
import com.ybr.entity.Tiezi;


@WebServlet("/ReplyServlet")
public class ReplyServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf8");
		
		//获取帖子的id
		String id = request.getParameter("id");
		if(StringUtils.isBlank(id)) {
			response.sendRedirect(request.getServletContext().getContextPath()+"/not_found.jsp");
			return;
		}
		
		UserService us = new UserServiceImpl();
		//根据帖子id去数据库中取数据
		Tiezi tiezi = us.findTieziCont(id);
		request.setAttribute("tiezi", tiezi);
		
		//判断是否查询到帖子
		if(tiezi==null) {
			response.sendRedirect(request.getServletContext().getContextPath()+"/not_found.jsp");
			return;
		}
		
		
		request.getRequestDispatcher("/reply.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
