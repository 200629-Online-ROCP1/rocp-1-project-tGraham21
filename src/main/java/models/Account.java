package models;


public class Account {
	private int accountId;
	private double balance;
	private AccountStatus status;
	private AccountType type;
	
	public Account ( int accountId, double balance, AccountStatus status, AccountType type) {
		this.accountId = accountId;
		this.balance = balance;
		this.status = status;
		this.type = type;
	}
	
	public double withdraw (double amount) {
		return balance - amount; 
	}
	
	public double deposit (double amount) {
		return balance + amount;
	}
	
	public void transfer (double amount, Account acct) {
		acct.deposit(amount);
		this.withdraw(amount);
	}
}
