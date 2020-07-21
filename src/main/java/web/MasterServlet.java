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

public class MasterServlet extends HttpServlet{
	
	private static final ObjectMapper om = new ObjectMapper();
	private static final UserController uc = new UserController();
	
	
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
				System.out.println("giggity");
				HttpSession ses = req.getSession(false);
				List<User> users = uc.findAll();
				res.setStatus(200);
				res.getWriter().println(om.writeValueAsString(users));
			}
		}
		
		catch (NumberFormatException e ) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}
}
