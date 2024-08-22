package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class ConnectionJDBCUtil {
	static final String url = "jdbc:mysql://localhost:3306/estate";
	static final String uname = "root";
	static final String pass = "12345";
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn  = DriverManager.getConnection(url, uname, pass);
			return conn;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
