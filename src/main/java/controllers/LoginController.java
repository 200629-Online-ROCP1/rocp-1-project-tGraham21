package controllers;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.*;
import services.*;

public class LoginController {
	
	private static final LoginService ls = new LoginService();
	private static final UserService us = new UserService();
	private static final ObjectMapper om = new ObjectMapper();
	
	public void login(HttpServletRequest req, HttpServletResponse res) throws IOException{
		
		if(req.getMethod().equals("POST")) {
			
			BufferedReader reader = req.getReader();
			
			StringBuilder s = new StringBuilder();
			
			String line = reader.readLine();
			
			while(line != null) {
				s.append(line);
				line = reader.readLine();
			}
			
			String body = new String(s);
			
			LoginDTO dto = om.readValue(body, LoginDTO.class);
			
			int id = ls.login(dto);
			if(id > 0) {
				HttpSession ses = req.getSession();
				ses.setAttribute("userId", id);
				ses.setAttribute("loggedIn", true);
				res.setStatus(200);
				User user = us.findById(id);
				res.getWriter().println(om.writeValueAsString(user));
			}
			else {
				HttpSession ses = req.getSession(false);
				if (ses != null) {
					ses.invalidate();
				}
				res.setStatus(401);
				res.getWriter().println("Login Failed");
			}
				
		}
		
		
	}
	
	public void logout(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession ses = req.getSession(false);
		
		if(ses != null) {
			res.setStatus(200);
			User user = us.findById( (int) ses.getAttribute("userId"));
			res.getWriter().println("Successfully logged out " + user.getUsername() );
			ses.invalidate();
		}
		else {
			res.getWriter().println("There was no user logged into the session");
		}
		
	}
	
	
}
