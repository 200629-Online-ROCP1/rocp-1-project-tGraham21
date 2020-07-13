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
	
	private double deposit(double deposit) {
		balance += deposit;
		return balance;
	}
	
	private double withdrawl(double withdrawl) {
		balance -= withdrawl;
		return balance; 
	}
}
