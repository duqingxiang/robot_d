package com.demo.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class HiveConnection {
	
	
	public static String HIVE_CONNECTION_DIRVER = "org.apache.hive.jdbc.HiveDriver";
	
	//sql文件路径
	public static String SQL_FILE_PATH = "e:/hadoop/sql/20150804.txt";
	//文件输出路径
	public static String OUTPUT_FILE_PATH = "e:/hadoop/txt/0804_youpin.txt";
	
	public static void main(String[] args) throws Exception {
		Class.forName(HIVE_CONNECTION_DIRVER);
		Connection con = DriverManager.getConnection("jdbc:hive2://119.254.161.52:10002/default", "operate", "erewf23I*&dfBFS2d44erewf23");
		System.out.println("Connection Success." + new Date());
		Statement st = con.createStatement();
		String sql = SqlFileUtil.readFile(SQL_FILE_PATH, true);
		
		if (SqlFileUtil.isEmpty(sql)) {//读取文件中是否存在SQL
			System.out.println("-------->error  main sql is null");
			return ;
		}
		
		ResultSet rs = st.executeQuery(sql);

		int rowCount = rs.getRow();
		System.out.println("------------->"+rowCount);
		
		try {
			File outFile = new File(OUTPUT_FILE_PATH);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));//UTF-8
			while (rs.next()) {
				StringBuffer hisStr = new StringBuffer();
				for(int i=1;i<=rs.getMetaData().getColumnCount();i++){
			        hisStr.append(rs.getString(i));
			        if(i==rs.getMetaData().getColumnCount()) hisStr.append("\n");
			        else hisStr.append("\t");
				}
		        bw.write(hisStr.toString());
		        
			}
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Writer Success." + new Date());

	}
}