package com.demo.test;

import java.util.HashMap;
import java.util.Map;

public class RandomCodeTest {

	private static Map<String,Long> codeMap;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		buildCodeMap();
		
		if (codeMap == null) {
			System.out.println("-------->codeMap is null");
		} else {
			for (String key : codeMap.keySet()) {
				System.out.println("---code--->"+key);
			}
		}

	}
	
	public static void buildCodeMap(){
		codeMap = new HashMap<String, Long>();
		
		for (int i=0 ;i<10;i++) {
			
			while(true) {
				String code = getCode();
				if (codeMap.get(code) == null) {
					codeMap.put(code, 1L);
					break;
				}
					
			}
			
		}
		
	}
	
	
	public static String getCode(){
		
		long nanoTime = System.nanoTime();
		long r = (long)(Math.random()*100+1);
		
		String res = (nanoTime * r)+"";
		res = res.substring(res.length()-6, res.length());
		
		return res;
	}
	
	

}
