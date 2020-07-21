package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException {
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url = "jdbc:postgresql://localhost:5432/project";
		String username = "postgres";
		
		String pass = System.getenv("PASS_DBEAVER");
		String password = pass; 
		
		
		return DriverManager.getConnection(url, username, password);
	}
	
}
