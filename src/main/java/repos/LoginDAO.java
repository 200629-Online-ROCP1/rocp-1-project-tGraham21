package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionUtil;
import models.LoginDTO;

public class LoginDAO implements LoginDAOInterface{

	@Override
	public int checkLogin(LoginDTO dto) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM users where " +
					"username = '" + dto.username + 
					"' AND pass = '" + dto.password + "';";
			Statement statement = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			if(result.next()) {
				int id = result.getInt("user_id");
				return id;
			}
					
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	

}
