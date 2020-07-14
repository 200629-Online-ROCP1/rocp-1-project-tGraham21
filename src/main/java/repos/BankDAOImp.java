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
			statement.setString(++index, user.id);
		}
	}

	@Override
	public boolean insertStatement(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findByFirstName(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<User> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
