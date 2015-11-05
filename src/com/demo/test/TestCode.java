package com.demo.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestCode {

	private static String[] word = {"an","hehe"};
	
	public static void main(String[] args) {
	
		String str = "I have a an dream hehe ;";
		String a = outWord(str);
		System.out.println(a);
	}
	public static String outWord(String str) {
		for (String key : word) {
			//这种方式就行
			if (str.indexOf(key) > -1) {
				str = str.replace(key, "");
			}
			//这种用正则的匹配没啥逼用
			StringBuilder matchStr = new StringBuilder("^.*");
			Pattern pattern = Pattern.compile(matchStr.append(key)
					.append(".*$").toString());

			Matcher matcher = pattern.matcher(str);
			if (matcher.find()) {
				str = str.replace(key, "");
			}
		}
		return str;
	}
	
	
}
