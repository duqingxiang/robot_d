package com.demo.util;

import java.util.Arrays;
import java.util.List;

public class TypeUtil {

	private static List<String> imgTypes = Arrays.asList(new String []{"jpg","png","bmp"});
	private static List<String> zipTypes =Arrays.asList(new String []{"rar","zip"});
	private static List<String> fileTypes = Arrays.asList(new String []{"txt","doc","docx"});
	
	public static int getType(String name){
		if (StringUtil.isEmpty(name))
			return -1;
		name = name.toLowerCase();
		String realName = "";
		if (name.lastIndexOf(".") >= 0) {  
            realName = name.substring(name.lastIndexOf(".")+1);  
        } else {
        	realName = name ;
        }
	
		if (imgTypes.contains(realName)){
			return 1 ;
		}else if (zipTypes.contains(realName)){
			return 2 ;
		}else if (fileTypes.contains(realName)){
			return 3 ;
		}
		
		return 0 ;
	}
	public static void main(String[] args) {
		System.out.println(getType("MP3"));
	}
}
