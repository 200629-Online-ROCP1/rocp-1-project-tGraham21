package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException {
		
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String username = "postgres";
		
		String pass = System.getenv("PASS_DBEAVER");
		String password = pass; 
		
		
		return DriverManager.getConnection(url, username, password);
	}
	
	public static void main(String[] args) {
		
		//Try with resources will automatically close the resource at the end of the try or catch block
		try(Connection conn = ConnectionUtil.getConnection()){
			System.out.println("connection successful");
		}catch(SQLException e) {
			System.out.println(e);
		}
	}
}
