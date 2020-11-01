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

@WebServlet("/UpdateSexServlet")
public class UpdateSexServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf8");
		
		User jguser = (User)request.getSession().getAttribute("jguser");
		System.out.println(jguser);
		if(jguser==null) {
			response.sendRedirect(request.getServletContext().getContextPath()+"/login.jsp");
			return;
		}
		
		//获取当前的userid
		Integer userid = jguser.getId();
		
		//获取性别
		String sex = request.getParameter("sex");
		System.out.println(sex);
		UserService us = new UserServiceImpl();
		//根据userid进行更改性别
		us.updateSex(userid,sex);
		
		
//		//更改用户的密码
//		//获取用户输入的密码信息
//		String pwd = request.getParameter("pwd");
//		String newpwd = request.getParameter("newpwd");
//		String rnpwd = request.getParameter("rnpwd");
//		//健壮性判断
//		if(pwd!=null&&newpwd!=null&&rnpwd!=null) {
//			if(pwd!=newpwd&&newpwd==rnpwd) {
////				us.updatePwd(newpwd,jguser);
//				//修改成功后跳转到login页面
//				response.sendRedirect(request.getServletContext().getContextPath()+"/login.jsp");
//				return;
//			}else if(pwd==newpwd&&newpwd==rnpwd) {
//				request.setAttribute("msg", "新密码与初始密码一致");
//				return;
//			}else {
//				request.setAttribute("msg", "两次密码输入不一致");
//				return;
//			}
//		}
		
		
		
		request.getRequestDispatcher("/UserEditServlet?id="+userid).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
