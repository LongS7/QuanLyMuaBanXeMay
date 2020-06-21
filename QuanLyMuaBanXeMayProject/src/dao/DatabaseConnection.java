package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:jtds:sqlserver://localhost:1433;QLBX;instance=SQLEXPRESS";
		
		Connection conn = DriverManager.getConnection(url, "admin", "1530");
		
		return conn;
	}
}
