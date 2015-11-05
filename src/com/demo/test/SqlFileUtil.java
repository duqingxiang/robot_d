package com.demo.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SqlFileUtil {

	
	/**
	 * 获取文件
	 * @param path  路径
	 * @return
	 */
	public static File getOutputFile(String path){
		if (isEmpty(path)) {
			System.out.println("----SqlFileUtil  getOutputFile ： 输入的文件路径为空，仔细检查参数");
			return null;
		}
		File file = null ;
		try {
			file = new File(path);
			
			if (!file.getParentFile().exists()&&!file.getParentFile().isDirectory()) {
				file.getParentFile().mkdirs();
				System.out.println("----SqlFileUtil  getOutputFile ： 目录不存在，已创建");
			}
			
			if (!file.exists()) {
				file.createNewFile();
				System.out.println("----SqlFileUtil  getOutputFile ：文件不存在，已创建");
			}
		} catch (Exception e) {
			System.out.println("----SqlFileUtil  getOutputFile ：出现异常！！");
			e.printStackTrace();
		}
		return file ;
	}
	
	
	/**
	 * 读取文件里的sql 
	 * @param fileName
	 * @param isTest
	 * @return
	 */
	public static String readFile(String fileName,boolean isTest) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer res = new StringBuffer();
		try {
			System.out.println("-----SqlFileUtil  readFile ： 文件读取开始：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				if (isTest) {//测试数据每一行的内容
					System.out.println("line " + line + ": " + tempString);
				}
				res.append(tempString);
				line++;
			}
			System.out.println("-----SqlFileUtil  readFile ： sql 文件读取结束：");
			System.out.println("-----SqlFileUtil  readFile ： 输出的sql :"+res.toString());
			reader.close();
		} catch (IOException e) {
			System.out.println("-----SqlFileUtil  readFile ：出现异常！！");
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		
		if (isEmpty(res.toString())) {
			System.out.println("------------SqlFileUtil  readFile ：  读取到的文件内容是空的！");
			return "";
		}
		
		return res.toString();
	}
	
	
	/**
	 * 判断是否为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if(str == null) 
			return true; 
		String tempStr = str.trim(); 
		if(tempStr.length() == 0)
			return true; 
		if(tempStr.equals("null"))
			return true;
		return false; 
	}

}
