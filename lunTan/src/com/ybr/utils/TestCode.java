package com.ybr.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestCode")
public class TestCode extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置验证码的宽高
				int width = 120;
				int height =40;
				
				//创建一张画布
				BufferedImage bufi = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
				
				//在画布中创建画笔
				Graphics gs = bufi.getGraphics();
				//设置画笔的颜色
				gs.setColor(Color.white);
				//填充画布
				gs.fillRect(0, 0, width, height);
				
				gs.setColor(Color.black);
				//给画布描一个边框
				gs.drawRect(0, 0, width-1, height-1);
				
				//创建一个数据池
				String data = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
				
				//创建一个随机对象
				Random rd = new Random();
				
				String code="";
				for(int i=0;i<4;i++) {
					gs.setColor(new Color(rd.nextInt(255),rd.nextInt(255),rd.nextInt(255))); 
					gs.setFont(new Font("宋体", Font.ITALIC, 25));
					String d = data.charAt(rd.nextInt(data.length())) + "";
					gs.drawString(d, 20+(20*i), 30);
					code +=d;
				}
				
				request.getSession().setAttribute("checkcode", code);
				System.out.println(code);
				//添加随机线
				
				for(int j=0;j<4;j++) {
					//设置画笔颜色
					gs.setColor(new Color(rd.nextInt(255),rd.nextInt(255),rd.nextInt(255)));
					//给画笔设置随机落点
					gs.drawLine(rd.nextInt(width), rd.nextInt(height), rd.nextInt(width), rd.nextInt(height));
				}
				
				//输出画布
				ImageIO.write(bufi, "jpg", response.getOutputStream());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
