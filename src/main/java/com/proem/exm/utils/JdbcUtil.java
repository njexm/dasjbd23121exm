package com.proem.exm.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//测试库jdbc
public class JdbcUtil {
	/**
	 * 正式网站地址
	 * @return
	 */
	public static String url = "jdbc:mysql://115.29.232.148:3306/exmnew?useUnicode=true&characterEncoding=UTF-8";
	public static String user = "root";
	public static String password = "admin073271";
//	public static String url = "jdbc:mysql://115.29.232.148:3306/njexm2015?useUnicode=true&characterEncoding=UTF-8";
//	public static String user = "njexm2015";
//	public static String password = "12345678";
//	public static String url = "jdbc:sqlserver://localhost:1433;databaseName=isszmbalv3";
//	public static String user = "sa";
//	public static String password = "1qaz2wsx";
	public static Connection getConnection(){
		Connection conn=null;
		try{
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url, user, password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	static void close(ResultSet rs){
		try{
			if(rs!=null)
				rs.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public static void close(PreparedStatement ps){
		try{
			if(ps!=null)
				ps.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	public static void close(Connection conn){
		try{
			if(conn!=null)
				conn.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	
}
