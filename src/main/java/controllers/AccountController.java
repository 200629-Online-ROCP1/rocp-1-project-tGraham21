package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import models.*;
import services.AccountService;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AccountController {
	private static final AccountService ac = new AccountService();
	
	public List<Account> findAll(){
		return ac.findAll();
	}
	
	public Account findById(int id) {
		return ac.findById(id);
	}
}
