package com.ybr.UserWeb;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.ybr.UserService.UserService;
import com.ybr.UserService.Impl.UserServiceImpl;
import com.ybr.entity.Page;
import com.ybr.entity.Tiezi;
import com.ybr.entity.User;

@WebServlet("/UserInfoServlet")
public class UserInfoServlet extends HttpServlet {
	/**
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf8");
		
		HttpSession session = request.getSession();
		//获取session中jguser的信息
		User user = (User)session.getAttribute("jguser");
		//判断是否登录
		if(null==user) {
			//如果user为空则跳转到登录页面
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		UserService us = new UserServiceImpl();
		User rsuser = us.checkUser(user);
		request.setAttribute("rsuser", rsuser);
		Integer userid = rsuser.getId();
		
		//再根据获得的用户id来查询用户的帖子列表并根据页面条数来进行分页
		String strPage=request.getParameter("pageNum");
		Integer pageNum = 1;
		if(StringUtils.isNotBlank(strPage)) {
			pageNum = Integer.parseInt(strPage);
		}
		request.setAttribute("pageNum", pageNum);
		Page pageList = us.checkTieziByUid(userid,pageNum);
		request.setAttribute("pageList", pageList);
		
		request.getRequestDispatcher("/userinfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
