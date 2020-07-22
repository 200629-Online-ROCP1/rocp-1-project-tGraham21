package repos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Account;
import models.AccountStatus;
import models.Role;
import models.User;
import util.ConnectionUtil;

public class AccountDAO implements AccountDAOInterface{

	@Override
	public double withdraw(double amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double deposit(double amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean transfer(double amount, Account acct) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account findAccountById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findAll() {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM account";
			
			Statement statement = conn.createStatement();
			
			List<Account> accountList = new ArrayList<>();
			
			ResultSet result = statement.executeQuery(sql);

			while(result.next()) {
				Account acct = new Account();
				acct.setId(result.getInt("account_id"));
				acct.setBalance(result.getDouble("balance"));
				accountList.add(acct);
			}
			
			return accountList;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
			
			return null;
	}

	@Override
	public Account findByStatus(AccountStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account submitAccount(Account acct) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account updateAccount(Account acct) {
		// TODO Auto-generated method stub
		return null;
	}

}
