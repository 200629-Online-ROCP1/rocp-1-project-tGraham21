package repos;

import java.util.List;

import models.*;

public interface AccountDAOInterface {
	public double withdraw(double amount);
	public double deposit(double amount);
	public boolean transfer(double amount, Account acct); 
	public Account findAccountById(int id);
	public List<Account> findAll();
	public Account findByStatus(AccountStatus status);
	public Account findByUser(User user);
	public Account submitAccount(Account acct, int id);
	public Account updateAccount(Account acct);
	
	
}
