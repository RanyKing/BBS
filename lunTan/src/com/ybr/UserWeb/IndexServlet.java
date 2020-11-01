package com.ybr.UserWeb;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.ybr.UserService.UserService;
import com.ybr.UserService.Impl.UserServiceImpl;
import com.ybr.entity.Page;
import com.ybr.entity.Tiezi;
import com.ybr.entity.Type;


@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//编码设置
		request.setCharacterEncoding("utf8");
		response.setContentType("text/html;charset=utf8");
		
		//获取帖子类型的id
		String tid = request.getParameter("tid");
		UserService us = new UserServiceImpl();
		//创建一个type对象接收服务器端传送来的数据
		List<Type> typeList = us.checkType();
		request.setAttribute("typeList", typeList);
		
//		//根据帖子类型的id来进行选择帖子
//		List<Tiezi> tieziList = us.checkTiezi(tid);
//		request.setAttribute("tieziList", tieziList);
		//获取分页列表
		//1.获取整个帖子列表
		//2.获取分类帖子列表
		//3.将整个列表封装成对象传到页面中去
		//获取pageNum
		String strNum = request.getParameter("pageNum");
		//健壮性判断
		Integer pageNum=1;
		if(StringUtils.isNotBlank(strNum)) {
			pageNum=Integer.parseInt(strNum);
		}
		request.setAttribute("pageNum", pageNum);
		//创建一个page对象用于接收数据信息
		Page page = us.checkTieziByPage(pageNum,tid);
		request.setAttribute("page", page);
		
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
