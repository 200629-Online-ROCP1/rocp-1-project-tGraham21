package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.*;
import models.*;

public class MasterServlet extends HttpServlet {

	private static final ObjectMapper om = new ObjectMapper();
	
	private static final UserController uc = new UserController();
	private static final LoginController lc = new LoginController();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("application/json");

		res.setStatus(404);

		final String URI = req.getRequestURI().replace("rocp-project/servlet/web.MasterServlet/BANKAPI/", "");

		String[] portions = URI.split("/");
		System.out.println(Arrays.toString(portions));

		try {
			switch (portions[1]) {
			case "users":
				HttpSession ses = req.getSession(false);

				if (portions.length == 3) {
					int id = Integer.parseInt(portions[2]);
					User user = uc.findByInt(id);
					res.setStatus(200);
					if(user != null) {				
					String json = om.writeValueAsString(user);
					res.getWriter().println(json);
					}
					else {
						res.getWriter().println("User ID doesn't exist");
					}
				} else {
					List<User> users = uc.findAll();
					res.setStatus(200);
					if(users.size() > 0) {
					res.getWriter().println(om.writeValueAsString(users));
					}
					else {
						res.getWriter().println("No Users Avaliable");
					}
				}
				break;
			case "login": 
				lc.login(req, res);
				break;
			case "logout":
				lc.logout(req, res);
				break;
			case "register":
				User newUser = uc.addUser(req, res);
				
				if(newUser != null) {
					res.getWriter().println(om.writeValueAsString(newUser));
					res.setStatus(201);
				}
				else {
					res.getWriter().println("Invalid Fields");
					res.setStatus(400);
				}
				break;
			}
		}

		catch (NumberFormatException e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
		doGet(req, res);
	}
}
