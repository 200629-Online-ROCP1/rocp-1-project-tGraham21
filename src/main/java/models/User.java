package models;


import java.util.ArrayList;

import models.*;

public class User {
	private int userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Role role;
	private ArrayList<Account> accounts;

	public User() {
		this.accounts = new ArrayList<Account>();
	};

	public User(int userId, String username, String password, String firstName, String lastName, String email,
			Role role) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
		this.accounts = new ArrayList<Account>();
	}

	public int getId() {
		return this.userId;
	}

	public void setId(int id) {
		this.userId = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Role getRole() {
		return this.role;
	}
	
	public void setRole(Role nRole) {
		this.role = nRole;
	}
	
	public void addAccount(Account acct) {
		this.accounts.add(acct);
	}
	
	public ArrayList<Account> getAccounts(){
		return this.accounts;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}


}
