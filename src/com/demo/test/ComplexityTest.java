package com.demo.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 两种遍历过滤的复杂度测试
 * @author Administrator
 * @param <E>
 *
 */
public class ComplexityTest {
	
	public static List<TestUser> testList = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String abc = "123456789";
		abc = abc.substring(5);
		System.out.println(abc);
		
		
		/*buildTestList(100000);
		
		int [] users = {90000,90100,90200,90300,90400};
		filterListLoop(users);
		filterListHash(users);*/
	}
	
	
	public static void buildTestList(int size){
		testList = new ArrayList<TestUser>();
		for (int i=0;i< size ;i++) {
			TestUser t = new TestUser(i,i+10);
			testList.add(t);
		}
	}
	
	public static void filterListLoop(int [] users){
		int count = 0 ;
		if (users.length <= 0)
			System.out.println(" loop ---> users is null or length == 0");
		
		List<TestUser> newList = new ArrayList<TestUser>();
		long start = System.nanoTime();
		for (int i=0;i< users.length ;i++) {
			
			for (TestUser t : testList) {
				count++;
				if (t.getUserid() == users[i]) {
					newList.add(t);
					break;
				}
			}
		}
		long end = System.nanoTime();
		System.out.println(" loop ----> 从 "+testList.size()+" 个对象中  过滤    "+users.length+" 个对象，共执行 "+count+" 循环,耗时 "+(end-start)+" 纳秒,新对象长度 "+newList.size());
		
	}
	
	public static void filterListHash(int [] users){
		int count = 0 ;
		if (users.length <= 0)
			System.out.println(" hash ---> users is null or length == 0");
		
		List<TestUser> newList = new ArrayList<TestUser>();
		Map<Integer,TestUser> testMap = new HashMap<Integer,TestUser>();
		
		long start = System.nanoTime();
		for (TestUser t : testList) {
			testMap.put(t.getUserid(), t);
			count++;
		}
		long hashEnd = System.nanoTime();
		for (int i=0;i<users.length ;i++) {
			TestUser t = testMap.get(users[i]);
			if (t == null)
				continue;
			newList.add(t);
			count++;
		}
		long end = System.nanoTime();
		
		System.out.println(" hash ----> 从 "+testList.size()+" 个对象中  过滤    "+users.length+" 个对象，共执行 "+count+" 循环,耗时 "+(end-start)+" 纳秒,Hash耗时："+(hashEnd-start)+",新对象长度 "+newList.size());
	}
	
	
	static class TestUser{
		private int userid;
		private int age;
		
		public TestUser(int userid,int age){
			this.userid = userid;
			this.age = age;
		}

		public int getUserid() {
			return userid;
		}

		public void setUserid(int userid) {
			this.userid = userid;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}

}
