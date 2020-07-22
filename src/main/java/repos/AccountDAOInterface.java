package repos;

import models.Account;

public interface AccountDAOInterface {
	public double withdraw(double amount);
	public double deposit(double amount);
	public boolean transfer(double amount, Account acct); 
}
