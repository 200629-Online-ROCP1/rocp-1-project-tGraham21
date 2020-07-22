package models;


public class Account {
	private int accountId;
	private double balance;
	private String status;
	private String type;
	
	public Account () {}
	
	public Account ( int accountId, double balance, String status, String type) {
		this.accountId = accountId;
		this.balance = balance;
		this.status = status;
		this.type = type;
	}
	public int getId() {
		return this.accountId;
	}
	public void setId(int id ) {
		this.accountId = id;
	}
	
	public double getBalance() {
		return this.balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setType(String type) {
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
