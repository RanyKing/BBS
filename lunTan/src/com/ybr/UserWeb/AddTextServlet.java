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
import com.ybr.entity.Tiezi;
import com.ybr.entity.User;

@WebServlet("/AddTextServlet")
public class AddTextServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf8");
		
		String title = request.getParameter("title");
		String strtype = request.getParameter("type");
		String context = request.getParameter("context");
		if(StringUtils.isBlank(title) || StringUtils.isBlank(context) || StringUtils.isBlank(strtype)) {
			request.setAttribute("msg", "帖子的标题，内容和类型不能为空！");
			request.getRequestDispatcher("/AddTieziServlet").forward(request, response);
			return;
		}
		
		UserService us = new UserServiceImpl();
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("jguser");
		if(null==user) {
			//如果user为空则跳转到登录页面
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		User rsuser = us.checkUser(user);
		System.out.println(rsuser);
		request.setAttribute("rsuser", rsuser);
		Integer userid = rsuser.getId();
		
		
		
		Integer typeid = Integer.parseInt(strtype);
		
		Tiezi tiezi = new Tiezi();
		tiezi.setContent(context);
		tiezi.setTitle(title);
		tiezi.setTypeid(typeid);
		tiezi.setUserid(userid);
		tiezi.setFabutime(new Date());
		tiezi.setCount(0);
		
		//将封装好的帖子对象发送到服务器
		int jg=us.addText(tiezi);
		if(jg==0) {
			request.setAttribute("msg", "发帖失败！");
			request.getRequestDispatcher("/AddTieziServlet").forward(request, response);
			return;
		}
		if(jg==1) {
			request.getRequestDispatcher("/UserInfoServlet").forward(request, response);
			return;
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
