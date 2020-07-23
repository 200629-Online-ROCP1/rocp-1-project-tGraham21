package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.*;
import services.AccountService;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AccountController {
	private static final AccountService ac = new AccountService();
	private static final UserController uc = new UserController();
	private static final ObjectMapper om = new ObjectMapper();
	
	public List<Account> findAll(){
		return ac.findAll();
	}
	
	public Account findById(int id) {
		return ac.findById(id);
	}
	
	public Account submitAccount(HttpServletRequest req, HttpServletResponse res, int id) throws IOException {
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		
		String body = new String(s);
		
		Account acct = om.readValue(body, Account.class);
		
		return ac.submitAccount(acct, id);
	}
	
	public Account updateAccount(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		BufferedReader reader = req.getReader();
		
		StringBuilder s = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			s.append(line);
			line = reader.readLine();
		}
		
		String body = new String(s);
		Account acct = om.readValue(body, Account.class);
		
		return ac.updateAccount(acct);
		
				
	}
	
	public List<Account> findByUserId(int id){
		
		User user = uc.findById(id);
		for(Account a: user.getAccounts()) {
			System.out.println(a.getBalance());
		}
		return user.getAccounts();
	}
	public List<Account> findByStatus(String status){
		List<Account> accounts = ac.findAll();
		List<Account> matches = new ArrayList<>();
		for(Account a: accounts) {
			if(a.getStatus().equals(status)) {
			matches.add(a);
			}
		}
		return matches;
	}
}
