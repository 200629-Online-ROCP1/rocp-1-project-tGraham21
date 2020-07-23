package repos;

import java.util.List;

import models.*;

public interface AccountDAOInterface {
	public double withdraw(double amount, int id );
	public double deposit(double amount, int id);
	public boolean transfer(double amount, int idSource, int idTarget); 
	public Account findAccountById(int id);
	public List<Account> findAll();
	public Account submitAccount(Account acct, int id);
	public Account updateAccount(Account acct);
	
	
}
