package com.itheima.web.utils;

import java.util.UUID;

public class FIleUtil {
	
	public static void main(String[] args) {
		String fileName = "abd.jpg";
		System.out.println(getFileDir(fileName));
	}
	
	/**
	 * 算出来文件分离目录
	 * @param fileName
	 * @return
	 */
	public static String getFileDir(String fileName){
		
		int hashCode = fileName.hashCode();
		int d1 = hashCode & 0xf;  //&上0xf  & 上4个1111  一级目录
		
		hashCode = hashCode >>> 4; //右移4位
		int d2 = hashCode & 0xf; //二级目录
		return d1 + "/" + d2;
	}

	
	/**
	 * 获取文件名字~~~
	 * @param fileName
	 * @return
	 */
	public static String getFileName(String fileName){ 
		//icon_01.jpg  ---lakjlsdjfaljsdlfjalsdf.jpg
		String prefix = UUID.randomUUID().toString().replaceAll("-", "");
		String suffix = fileName.substring(fileName.lastIndexOf(".")); //.jpg
		return  prefix +suffix;
	}
}
