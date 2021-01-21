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


public class UploadServlet extends HttpServlet {
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		/*String username = request.getParameter("username");
		String password = request.getParameter("password");
		String pimage = request.getParameter("pimage");
		System.out.println("username="+username+": password="+password + " : pimage="+pimage);
		
		System.out.println("-------------------------------------");
		
		//获取到了页面提交过来的输入流
		InputStream is = request.getInputStream();
		byte[] buffer = new byte[1024];
		int len = 0 ;
		while((len =is.read(buffer)) !=-1){
			System.out.println(new String(buffer, 0, len));
		}*/
		
		
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
					
					String name = fileItem.getName();  //拿不到东西，都是null
					String fieldName = fileItem.getFieldName();  //拿到的是属性名 : username  | password  | addres
					String str = fileItem.getString(); //拿到的是属性的值，就是文本框的内容 ， admin  | 123456  |深圳
					
					System.out.println("name="+name);
					System.out.println("fieldName="+fieldName);
					System.out.println("str="+str);
					
					System.out.println("-------------------------------");
					
				}else{
					//如果是文件数据，就会进入这个else分支
					
					String name = fileItem.getName();  //拿到的是文件的名字  icon_01.jpg
					String fieldName = fileItem.getFieldName();  //拿到的是属性名 : pimage
					//String str = fileItem.getString(); //拿到文件的数据
					
					long size = fileItem.getSize();
					
					
					System.out.println("name2="+name);
					System.out.println("fieldName2="+fieldName);
					//System.out.println("str2="+str);
					
					//获取uuid的文件名字
					String fileName = FIleUtil.getFileName(name);
					InputStream is = fileItem.getInputStream();
					
					String dynamicDir = FIleUtil.getFileDir(fileName);
					
					File dir = new File("D:/heima26/images/"+dynamicDir);
					if(!dir.exists()){
						dir.mkdirs();
					}
					
					
					File file = new File(dir , fileName);
					OutputStream os = new FileOutputStream(file);
					
					
					IOUtils.copy(is, os);
					IOUtils.closeQuietly(is);
					IOUtils.closeQuietly(os);
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
