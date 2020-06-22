package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	public static String userName = "";
	public static String password = "";
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:jtds:sqlserver://localhost:1433;QLBX;instance=SQLEXPRESS";
		
		Connection conn = DriverManager.getConnection(url, userName, password);
		
		return conn;
	}
}
