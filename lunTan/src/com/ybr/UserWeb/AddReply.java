package com.ybr.UserWeb;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.ybr.UserService.UserService;
import com.ybr.UserService.Impl.UserServiceImpl;
import com.ybr.entity.Reply;
import com.ybr.entity.User;

@WebServlet("/AddReply")
public class AddReply extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf8");
		
		//判断是否登录
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("jguser");
		if(user==null) {
			response.sendRedirect(request.getServletContext().getContextPath()+"/login.jsp");
			return;
		}
		
		//获取帖子的id
		String tid = request.getParameter("tid");
		Integer tzid = null;
		if(StringUtils.isNoneBlank(tid)) {
			tzid = Integer.parseInt(tid);
		}
		//获取回复的内容
		String replycontext = request.getParameter("replycontext");
		//判断回复的内容是否为空，如果为空则继续跳转到当前页面
		if(StringUtils.isBlank(replycontext)) {
			response.sendRedirect(request.getServletContext().getContextPath()+"/ReplyServlet?id="+tzid);
			return;
		}
		Reply reply = new Reply();
		reply.setUserid(user.getId());
		reply.setReplycontent(replycontext);
		reply.setTieziid(tzid);
		reply.setReplytime(new Date());
		
		UserService us = new UserServiceImpl();
		us.addReply(reply);
		
		response.sendRedirect(request.getServletContext().getContextPath()+"/ReplyServlet?id="+tzid);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
