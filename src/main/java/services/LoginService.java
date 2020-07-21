package services;

import models.LoginDTO;
import repos.*;

public class LoginService {
	
	private final LoginDAOInterface LoginDAO = new LoginDAO();
	
	public int login(LoginDTO loginData) {
		return LoginDAO.checkLogin(loginData);
	}
	
}
