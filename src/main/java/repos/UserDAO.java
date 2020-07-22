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
			Statement statementRole = conn.createStatement();
			Statement statementAcct = conn.createStatement();
			
			List<User> userList = new ArrayList<>();
			
			ResultSet result = statement.executeQuery(sql);

			while(result.next()) {
				User user = new User();
				Role role = new Role();
				
				int id = result.getInt("user_id");
				user.setId(id);
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("pass"));
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setEmail(result.getString("email"));
				
				String sqlRole = "SELECT * FROM user_role WHERE user_id_fk = " + id + ";";
				ResultSet resultRole = statementRole.executeQuery(sqlRole);
				
				if(resultRole.next()) {
					
					role.setRole(resultRole.getString("user_role"));
					role.setRoleId(resultRole.getInt("role_id"));				
					user.setRole(role);
				}
				
				String sqlAccounts = "SELECT * FROM account WHERE user_id_fk = " + id + ";";
				ResultSet resultAcct = statementAcct.executeQuery(sqlAccounts);
				while(resultAcct.next()) {
					Account acct = new Account();
					acct.setId(resultAcct.getInt("account_id"));
					acct.setBalance(resultAcct.getDouble("balance"));
					user.addAccount(acct);
				}

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
			Role role = new Role();
			
			String sql = "SELECT * FROM users WHERE user_id = " + id + ";";
			
			Statement statement = conn.createStatement();
			Statement statementRole = conn.createStatement();
			Statement statementAcct = conn.createStatement();
			
			ResultSet result = statement.executeQuery(sql);
			
			if(result.next()) {
				User user = new User();
				user.setId(result.getInt("user_id"));
				user.setUsername(result.getString("username"));
				user.setPassword(result.getString("pass"));
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setEmail(result.getString("email"));
				String sqlRole = "SELECT * FROM user_role WHERE user_id_fk = " + id + ";";
				System.out.println(sqlRole);
				ResultSet resultRole = statementRole.executeQuery(sqlRole);
				
				if(resultRole.next()) {
					
					role.setRole(resultRole.getString("user_role"));
					role.setRoleId(resultRole.getInt("role_id"));				
					user.setRole(role);
				}
				
				String sqlAccounts = "SELECT * FROM account WHERE user_id_fk = " + id + ";";
				ResultSet resultAcct = statementAcct.executeQuery(sqlAccounts);
				while(resultAcct.next()) {
					Account acct = new Account();
					acct.setId(resultAcct.getInt("account_id"));
					acct.setBalance(resultAcct.getDouble("balance"));
					user.addAccount(acct);
				}
				
				return user;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}

	@Override
	public User updateUserById(int id, User user) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "UPDATE users SET "
					+ "username = '" + user.getUsername() + "', " + 
					"pass = '" + user.getPassword() + "', " + 
					"first_name = '" + user.getFirstName() + "', " + 
					"last_name = '" + user.getLastName() + "', " + 
					"email = '"+ user.getEmail() + 
					"' WHERE user_id = " + id + ";";
			
			Statement statement = conn.createStatement();
			statement.execute(sql);
			System.out.println(sql);
			
			String sqlRole = "UPDATE user_role SET user_role =  '" +
					user.getRole().getRole()+ "' " +
					" WHERE user_id_fk = " + id + "; ";
			Statement statementRole = conn.createStatement();
			statementRole.execute(sqlRole);
			System.out.println(sqlRole);
			
			return user;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public User addUser(User user) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			int index = 0; 
			 
			String sql = "INSERT INTO users(username, pass, first_name, last_name, email)"
					+" VALUES(?,?,?,?,?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(++index, user.getUsername());
			statement.setString(++index, user.getPassword());
			statement.setString(++index, user.getFirstName());
			statement.setString(++index, user.getLastName());
			statement.setString(++index, user.getEmail());
			
			
			statement.execute();
			
			
			// return user with correct id
			String query = "SELECT * FROM users WHERE username = '" + user.getUsername() + "';";
			Statement statementOutput = conn.createStatement();
			
			ResultSet result = statementOutput.executeQuery(query);
			if(result.next()) {
				User userOutput = new User();
				userOutput.setId(result.getInt("user_id"));
				userOutput.setUsername(result.getString("username"));
				userOutput.setPassword(result.getString("pass"));
				userOutput.setFirstName(result.getString("first_name"));
				userOutput.setLastName(result.getString("last_name"));
				userOutput.setEmail(result.getString("email"));
				
				String sqlRole = "INSERT INTO user_role (user_role, user_id_fk)" +
						" VALUES(?,?);";
				PreparedStatement sendRole = conn.prepareStatement(sqlRole);
				
				sendRole.setString(1, user.getRole().getRole());
				sendRole.setInt(2, userOutput.getId());
				
				sendRole.execute();
				
				String queryRole = "SELECT * FROM user_role WHERE user_id_fk = " + userOutput.getId() + ";";
				System.out.println(queryRole);
				Statement statementRole = conn.createStatement();
				ResultSet resultRole = statementRole.executeQuery(queryRole);
				
				if(resultRole.next()) {
					Role role = new Role();
					role.setRole(resultRole.getString("user_role"));
					role.setRoleId(resultRole.getInt("role_id"));
					System.out.println(role.getRole());
					userOutput.setRole(role);
				}
				
				return userOutput;
			}
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
}
