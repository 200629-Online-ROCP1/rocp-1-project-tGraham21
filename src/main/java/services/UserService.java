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
		List<User> userList = findAll();
		// could use compareTo()??
//		
//		for(User u: userList) {
//			if( (user.getUsername().equals(u.getUsername())) || (user.getLastName().equals(u.getLastName()))) {
//				return null; 
//			}
//			// check if already in list
//		}
		
		User newUser = dao.addUser(user);
		return newUser;
		
	}
	
//	public boolean updateUser(User user) {
//		return dao.updateUserbyId(user)
//	}
//	
}
