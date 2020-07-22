package services;

import java.util.List;

import models.*;
import repos.UserDAO;
import repos.UserDAOInterface;

public class UserService {
	
	private final UserDAOInterface dao = new UserDAO();
	
	public List<User> findAll(){
		return dao.findAll();
	}
	
	public User findById(int id) {
		return dao.findById(id);
	}
	
	public User addUser(User user) {
		
		User newUser = dao.addUser(user);
		return newUser;
		
	}
	
	public User updateUserById(int id, User user) {
		return dao.updateUserById(id, user);
	}
	
}
