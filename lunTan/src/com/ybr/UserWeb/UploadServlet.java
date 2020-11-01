package com.ybr.UserWeb;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;

import com.ybr.UserService.UserService;
import com.ybr.UserService.Impl.UserServiceImpl;
import com.ybr.entity.User;
import com.ybr.utils.UUIDUtil;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		//获取当前用户的id
		User user = (User)request.getSession().getAttribute("jguser");
		
		try {
			List<FileItem> items = upload.parseRequest(request);
			
			for (FileItem fileItem : items) {
				if(!fileItem.isFormField()) {
					String fileName=fileItem.getName();
					if(StringUtils.isBlank(fileName)) {
						request.getRequestDispatcher("/UserEditServlet?id="+user.getId()).forward(request, response);
						return;
					}
					fileName=UUIDUtil.getUUID()+fileName.substring(fileName.lastIndexOf("."));
					File file = new File("D:\\img",fileName);
					try {
						//将文件保存到服务器中的一块磁盘上
						fileItem.write(file);
						//再将此图片名称保存到数据库中
						UserService us = new UserServiceImpl();
						user.setFaceimage(fileName);
						us.uploadImg(user);
						request.getRequestDispatcher("/UserEditServlet?id="+user.getId()).forward(request, response);
						return;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			
			
			
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
 	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
