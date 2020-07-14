package repos;

import java.sql.*;
import java.util.Set;
import java.util.HashSet;

import models.User;
import util.ConnectionUtil;

public class BankDAOImp implements BankDAO{
	// singleton
	private static BankDAOImp repo = null;
	
	private BankDAOImp() {}
	
	public static BankDAOImp getInstance() {
		if(repo == null) {
			repo = new BankDAOImp();
		}
		return repo;
	}
	
	
	@Override
	public boolean insert(User user) {
		try(Connection conn = ConnectionUtil.getConnection()){
			int index = 0;
			String sql = "INSERT INTO users(user_id, username, pass, first_name, last_name, email)"
					+ "VALUES(?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(++index, user.getId());
			statement.setString(++index, user.getUsername());
			statement.setString(++index, user.getPass());
			statement.setString(++index, user.getFirstName());
			statement.setString(++index, user.getLastName());
			statement.setString(++index, user.getEmail());
			
			if(statement.execute()) {
				return true;
			}
		} catch(SQLException e ) {
			System.out.println(e);
		}
		return false;
	}

	@Override
	public boolean insertStatement(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findByUsername(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<User> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
