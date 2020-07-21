package controllers;

import java.util.List;

import models.*;
import services.UserService;

public class UserController {
	
	private final UserService us = new UserService();
	
	public List<User> findAll(){
		return us.findAll();
	}
	
	public User findByInt(int id) {
		return us.findById(id);
	}
	
	public boolean addUser(User user) {
		return us.addUser(user);
	}
	
//	public User updateUser(User user) {
//		return us.updateUser();
//	}
	
}
