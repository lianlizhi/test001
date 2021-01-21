package com.itheima.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.itheima.web.utils.FIleUtil;


public class UploadServlet2 extends HttpServlet {
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//这是一个fileItem的工厂。 针对的是，在处理文件的时候，收到的那些东西。如果是一个比较小的文件，那么这些数据
			//都先放置在默认的地方， 放在内存
			FileItemFactory fileItemFactory  = new DiskFileItemFactory();
			//构建这个ServletFileUpload ，一会用它来解析我们的request请求，从request请求里面解析出来一段一段的数据。
			//commons这个jar在处理文件上传的时候，把每一段数据看成了fileItem
			ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory);
			//解析请求对象
			List<FileItem> list = servletFileUpload.parseRequest(request);
			//遍历每一段数据
			for (FileItem fileItem : list) {
				//这一份数据是不是普通的字符串数据  username  , password
				if(fileItem.isFormField()){
					
				}else{
					long size = fileItem.getSize();
					System.out.println("size=="+size);
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
