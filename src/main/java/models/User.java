package models;

import  models.*;

public class User {
	private int userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Role role;
	private List<Account> accounts;
	
	public User(int userId, String username, String password, String firstName, String lastName, String email, Role role) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}
	
	private boolean login(String user, String pass) {
		return (username.equals(user) && password.equals(pass));
	}
	
	public void viewInfo() {
		System.out.println("Current Account Information: ");
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
		System.out.println("Account Name: " + firstName + " " + lastName);
		System.out.println("Email: " + email);
	}
	public boolean update(String field, String data) {
		if(field.equals("username")) {
			this.username = data;
		}
		else if(field.equals("password")){
			this.password = data;
		}
		else if(field.equals("firstName")) {
			this.firstName = data;
		}
		else if(field.equals("lastName")) {
			this.lastName = data;
		}
		else if(field.equals("email")) {
			this.email = data;
		}
		else {
			return false;
		}
		return true;
	
	}
}
