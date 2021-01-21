package com.itheima.web.utils;



import java.util.UUID;

public class UploadUtils {
	
	
	/**
	 * 11.bmp
	 * 获取文件真实名称
	 * @param name
	 * @return
	 */
	public static String getRealName(String name){
		// c:/upload/11.bmp    1.jpg
		//获取最后一个"/"
		int index = name.lastIndexOf("\\");
		return name.substring(index+1);
	}
	
	/**
	 * 获取文件目录
	 * @param name 文件名称
	 * @return 目录
	 */
	public static String getDir(String name){
		int i = name.hashCode();
		String hex = Integer.toHexString(i);
		int j=hex.length();
		for(int k=0;k<8-j;k++){
			hex="0"+hex;
		}
		return "/"+hex.charAt(0)+"/"+hex.charAt(1)+"/"+hex.charAt(2)+"/"+hex.charAt(3)+"/"+hex.charAt(4)+"/"+hex.charAt(5)+"/"+hex.charAt(6)+"/"+hex.charAt(7);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		
		String  fileName="ab.jpg";
		
		System.out.println(getDir(fileName));
				
		
		
	}
}
