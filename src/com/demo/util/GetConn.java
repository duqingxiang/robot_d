package com.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConn {

	final private static String USER_NAME=IniReader.getInstance().getValue("jdbc", "username");
	final private static String PASSWORD=IniReader.getInstance().getValue("jdbc", "password");
	final private static String CON_URL =IniReader.getInstance().getValue("jdbc", "url");
	
	public static Connection getCon(){
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(CON_URL, USER_NAME, PASSWORD);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con ; 
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		getCon();

	}

}
