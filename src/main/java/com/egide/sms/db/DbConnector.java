package com.egide.sms.db;

import java.sql.*;

public class DbConnector {
	private String url = "jdbc:mysql://localhost:3306/students_db";
	private String user = "egide";
	private String pass = "dbadmin_123";
	private Connection connection = null;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		if(connection!=null) return connection;
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(url, user, pass);
		return connection;
	}
}
