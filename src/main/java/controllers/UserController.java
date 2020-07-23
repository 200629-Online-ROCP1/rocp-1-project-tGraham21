package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import models.*;
import services.UserService;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserController {
	
	private static final UserService us = new UserService();
	private static final ObjectMapper om = new ObjectMapper();
	
	public List<User> findAll(){
		return us.findAll();
	}
	
	public User findById(int id) {
		return us.findById(id);
	}
	
	public User addUser(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = new String(s);
		
		User user = om.readValue(body, User.class);
		
		return us.addUser(user);
	}
	
	public User updateUserById(int id, User user) {
		return us.updateUserById(id, user);
	}
	
}
