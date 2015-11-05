package com.demo.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FillNumberTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testHashMapKey();

		List<int[]> c1 = new ArrayList<int[]>();
		List<int[]> c2 = new ArrayList<int[]>();
		List<int[]> c3 = new ArrayList<int[]>();
		List<int[]> c4 = new ArrayList<int[]>();
		
		int groupCount = 0;
		for (int i = 1; i <= 8; i++){
			for (int j = 1; j <= 8; j++) {
				if (condition1(i,j)) {
					int [] intArray= new int[]{i,j};
					c1.add(intArray);
				}
				if (condition2(i,j)) {
					int [] intArray= new int[]{i,j};
					c2.add(intArray);			
				}
				if (condition3(i,j)) {
					int [] intArray= new int[]{i,j};
					c3.add(intArray);
				}
				if (condition4(i,j)) {
					int [] intArray= new int[]{i,j};
					c4.add(intArray);
				}
				groupCount++;
			}
		}
		
		System.out.println("group list ------->"+groupCount);
		List<int[]> m1 = margeList(c1, c2, 4);
		List<int[]> m2 = margeList(c3, c4, 4);
		List<int[]> m3 = margeList(m1, m2, 8);
		
		int index = 1;
		for (int [] array : m3){
			System.out.println("第"+index+"组结果：--->"+Arrays.toString(array));
			index++;
		}
	}
	
	
	public static boolean condition1(int i,int j){
		return (i - j == 1);
	}
	public static boolean condition2(int i,int j){
		return (i + j == 9);
	}
	public static boolean condition3(int i,int j){
		return (i - j == 3);
	}
	public static boolean condition4(int i,int j){
		return (i + j == 7);
	}
	
	public static List<int[]> margeList(List<int[]> l1 ,List<int[]> l2 ,int check){
		
		int margeCount = 0;
		List<int[]> resultList = new ArrayList<int[]>();
		for (int i=0 ;i < l1.size();i++) {
			for (int j=0 ;j < l2.size();j++) {
				List<Integer> set = new ArrayList<Integer>();
				for (int num : l1.get(i)){
					if (!set.contains(num)){
						set.add(num);
					}
				}
				for (int num : l2.get(j)) {
					if (!set.contains(num)){
						set.add(num);
					}
				}
				if (set.size() == check) {
					int [] array = new int[check];
					int index=0;
					for (int num : set) {
						array[index] = num;
						index++;
					}
					resultList.add(array);
				}
				margeCount++;
			}
		}
		System.out.println("marge list check:"+check+"------->"+margeCount);
		return resultList;
	}
	
	public static void testHashMapKey(){
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		map.put(3, 3);
		map.put(5, 5);
		map.put(2, 2);
		map.put(1, 1);
		map.put(4, 4);
		
		for (Integer i : map.keySet()) {
			System.out.println("testHashMapKey Integer =====>>"+i);
		}
		System.out.println("-------------------------------------------");
		Map<String,Integer> map1 = new HashMap<String,Integer>();
		map1.put("c", 3);
		map1.put("e", 5);
		map1.put("b", 2);
		map1.put("a", 1);
		map1.put("d", 4);
		
		for (String i : map1.keySet()) {
			System.out.println("testHashMapKey String =====>>"+i);
		}
		System.out.println("-------------------------------------------");
	}
}
