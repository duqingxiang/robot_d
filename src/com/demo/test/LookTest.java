package com.demo.test;

import java.util.Arrays;

public class LookTest {
	public static void main(String[] args) {
		
		StringBuffer sb = new StringBuffer("main--->");
		test(sb);
		System.out.println(sb.toString());
		
	}
	
	public static void test(StringBuffer sb){
		sb.append(" \n test 1");
		sb.append(" \n test 2");
		sb.append(" \n test 3");
		sb.append(" \n test 4");
	}
	
	public static void testArraycopy(){
		
		int a[] = new int[100];
		Arrays.fill(a, 250);

		long start, end, times;
		times = 10000000;

		start = System.currentTimeMillis();
		{
			int b[] = new int[100];
			for (int i = 0; i < times; i++) {
				for (int j = 0; j < b.length; j++)
					b[j] = a[j];
			}
			end = System.currentTimeMillis();
			System.out.println("Loop directly=" + (end - start));
			System.out.println("b[]=" + Arrays.toString(b));
		}

		start = System.currentTimeMillis();
		{
			int b[] = new int[100];
			for (int i = 0; i < times; i++) {
				System.arraycopy(a, 0, b, 0, b.length);
			}
			end = System.currentTimeMillis();
			System.out.println("System.arraycopy=" + (end - start));
			System.out.println("b[]=" + Arrays.toString(b));
		}
	}
}
