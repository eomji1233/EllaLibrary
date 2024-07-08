package org.kh.library.common;

import java.sql.*;

public class JDBCTemplate {
	private static final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "ELLAJDBC";
	private static final String PASSWORD = "ELLAJDBC";
	
	private static Connection conn;
	private static JDBCTemplate instance;
	
	public static JDBCTemplate getInstance() {
		if(instance == null) {
			instance = new JDBCTemplate();
			// 싱글톤 적용
		}
		return instance;
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if(conn == null || conn.isClosed()) {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);			
		}
		return conn;
	}
}
