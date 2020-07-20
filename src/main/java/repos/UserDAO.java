package repos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.*;
import util.ConnectionUtil;

public class UserDAO implements UserDAOInterface{

	@Override
	public List<User> findAll() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM users";
			
			Statement statement = conn.createStatement();
			
			List<User> userList = new ArrayList<>();
			
			ResultSet result = statement.executeQuery(sql);
			
			while(result.next()) {
				User user = new User();
				user.setId(result.getInt("user_id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("pass"));
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setEmail(result.getString("email"));
				
				userList.add(user);
			}
			
			return userList; 
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User findById(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM users WHERE user_id = " + id+ ";";
			
			Statement statement = conn.createStatement();
			
			List<User> userList = new ArrayList<>();
			
			ResultSet result = statement.executeQuery(sql);
			
			if(result.next()) {
				User user = new User();
				user.setId(result.getInt("user_id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("pass"));
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setEmail(result.getString("email"));
				return user;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}

	@Override
	public User updateUserbyId(User user) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE SET "
					+ "username = " + user.getUsername() + ", " + 
					"pass = " + user.getPassword() + ", " + 
					"first_name = " + user.getFirstName() + ", " + 
					"last_name = " + user.getLastName() + ", " + 
					"email = "+ user.getEmail() + 
					" WHERE user_id = " + user.getId() + ";";
			
			Statement statement = conn.createStatement();
			statement.execute(sql);
			
			return user;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
