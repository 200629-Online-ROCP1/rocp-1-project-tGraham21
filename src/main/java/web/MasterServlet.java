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

		try {
			switch (portions[1]) {
			case "users":
				HttpSession ses = req.getSession(false);
				if (ses != null && ((Boolean) ses.getAttribute("loggedIn"))) {
					if (portions.length == 3) {
						int id = Integer.parseInt(portions[2]);
						if (req.getMethod().equals("PUT")) {
							
							if (ses.getAttribute("userId").equals(id) || ses.getAttribute("role").equals("Admin")) {

								BufferedReader reader = req.getReader();

								StringBuilder s = new StringBuilder();

								String line = reader.readLine();
								while (line != null) {
									s.append(line);
									line = reader.readLine();
								}

								String body = new String(s);

								User userUpdate = om.readValue(body, User.class);

								User output = uc.updateUserById(userUpdate.getId(), userUpdate);

								res.setStatus(200);
								res.getWriter().println(om.writeValueAsString(output));

								// print user
							} else {
								res.setStatus(401);
								res.getWriter().println("Don't have permission to edit");
							}
						}

						else {
							if (ses.getAttribute("userId").equals(id) || ses.getAttribute("role").equals("Admin")
									|| ses.getAttribute("role").equals("Employee")) {
								User user = uc.findByInt(id);
								res.setStatus(200);

								if (user != null) {
									String json = om.writeValueAsString(user);
									res.getWriter().println(json);
								} else {
									res.getWriter().println("User ID doesn't exist");
								}

							} else {
								res.setStatus(401);
								res.getWriter().print("Incorrect Credentials");
							}
						}

					} else {
						if (ses.getAttribute("role").equals("Admin") || ses.getAttribute("role").equals("Employee")) {
							List<User> users = uc.findAll();
							res.setStatus(200);
							if (users.size() > 0) {
								res.getWriter().println(om.writeValueAsString(users));
							} else {
								res.getWriter().println("No Users Avaliable");
							}
						} else {
							res.setStatus(401);
							res.getWriter().print("Incorrect Credentials");
						}
					}
				} else {
					res.setStatus(401);
					res.getWriter().print("Must be logged in");
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

				if (newUser != null) {
					res.getWriter().println(om.writeValueAsString(newUser));
					res.setStatus(201);
				} else {
					res.getWriter().println("Invalid Fields");
					res.setStatus(400);
				}
				break;
			case "accounts":
				HttpSession ses1 = req.getSession(false);
				if (ses1 != null && ((Boolean) ses1.getAttribute("loggedIn"))) {
					if (portions.length == 3) {
						
					}
				}

			}
		}

		catch (NumberFormatException e) {
			e.printStackTrace();
			res.setStatus(400);
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doGet(req, res);
	}
	protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		doGet(req, res);
	}
}
