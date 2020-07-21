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
	
	public boolean addUser(User user) {
		List<User> userList = findAll();
		// could use compareTo()??
		
		for(User u: userList) {
			if( (user.getFirstName().equals(u.getFirstName())) && (user.getLastName().equals(u.getLastName()))) {
				return false; 
			}
			// check if already in list
		}
		
		boolean out = dao.addUser(user);
		return out;
		
	}
	
//	public boolean updateUser(User user) {
//		return dao.updateUserbyId(user)
//	}
//	
}
