package models;

import java.awt.List;

import  models.*;

public class User {
	private int userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Role role;
	private List accounts;
	
	public User(int userId, String username, String password, String firstName, String lastName, String email, Role role) {
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.role = role;
	}
	
	public int getId() {
		return this.userId;
	}
	
	public String getUsername () {
		return this.username;
	}
	
	public String getPass() {
		return this.password;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getEmail() {
		return this.email;
	}
	
}
