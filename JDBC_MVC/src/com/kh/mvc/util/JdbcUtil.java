package com.kh.mvc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	
	//private final String URL = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
	//private final String USERNAME = "KH12_PTH";
	//private final String PASSWORD = "KH1234";
	
	/*
	 * JDBC API 사용 중 중복 코드가 너무 많음!
	 * 중복된 코드를 메소드로 분리하여 필요할 때 마다 '재사용'하자
	 */
	
	
	public static Connection getConnection() {
		
		final String URL = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
		final String USERNAME = "KH12_PTH";
		final String PASSWORD = "KH1234";
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch(SQLException e) {
			e.printStackTrace();			
		}
		return conn;
	}
	
	public static void close( ResultSet rset, Statement stmt, PreparedStatement pstmt, Connection conn) {
		try {
			if(rset != null && !rset.isClosed()) { 
				rset.close(); 
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("ResultSet 오류!");
		}
		
		
		try {
			if(stmt != null && !stmt.isClosed()) { 
				stmt.close(); 
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Statement 오류!");
		}
		
		
		try {
			if(pstmt != null && !pstmt.isClosed()) { 
				pstmt.close(); 
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("PreparedStatment 오류!");
		}
		
		
		try {
			if(conn != null && !conn.isClosed()) { 
				conn.close(); 
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Connection 오류!");
		}
		
	}
	
	
}
