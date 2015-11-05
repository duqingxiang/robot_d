package com.demo.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBCTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url="jdbc:mysql://123.56.129.117:3306/robot_d?autoReconnect=true&user=robot&password=lonelyrobot";
		String sql = "select * from test";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(url);
			
			Statement stmt = con.createStatement();
			
			ResultSet rs=stmt.executeQuery(sql);
			
			long rsTime = System.currentTimeMillis();
			rs.last();
			int rowCount = rs.getRow();
			System.out.println("--------->共有"+rowCount+"行数据");
			rs.beforeFirst();
			long reTime = System.currentTimeMillis();
			System.out.println("-----cost---->"+(reTime-rsTime));
			
			
			List<String> list = new ArrayList();
			int count = 1 ;
			while(rs.next()) {
				String s = rs.getString(1);
				count ++;
				double bai = (count / rowCount )*100;
				
				list.add(s);
			}
			System.out.println("--------->"+list.size());
			System.out.println("----------->"+rowCount);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
