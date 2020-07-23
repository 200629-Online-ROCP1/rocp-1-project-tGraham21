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
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AccountController {
	private static final AccountService as = new AccountService();
	private static final UserController uc = new UserController();
	private static final ObjectMapper om = new ObjectMapper();

	public List<Account> findAll() {
		return as.findAll();
	}

	public Account findById(int id) {
		return as.findById(id);
	}

	public Account submitAccount(HttpServletRequest req, HttpServletResponse res, int id) throws IOException {
		BufferedReader reader = req.getReader();

		StringBuilder s = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = new String(s);

		Account acct = om.readValue(body, Account.class);

		return as.submitAccount(acct, id);
	}

	public Account updateAccount(HttpServletRequest req, HttpServletResponse res) throws IOException {

		BufferedReader reader = req.getReader();

		StringBuilder s = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = new String(s);
		Account acct = om.readValue(body, Account.class);

		return as.updateAccount(acct);

	}

	public List<Account> findByUserId(int id) {

		User user = uc.findById(id);
		
		return user.getAccounts();
	}

	public List<Account> findByStatus(String status) {
		List<Account> accounts = as.findAll();
		List<Account> matches = new ArrayList<>();
		for (Account a : accounts) {
			if (a.getStatus().equals(status)) {
				matches.add(a);
			}
		}
		return matches;
	}

	public String withdraw(HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws IOException {
		boolean ownsAccount = false;
		
		BufferedReader reader = req.getReader();

		StringBuilder s = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = new String(s);

		DepositDTO dto = om.readValue(body, DepositDTO.class);
		
		//Check to see if user owns account
		User user = uc.findById((int)ses.getAttribute("userId"));
		for (Account a : user.getAccounts()) {
		if (a.getId() == dto.accountId) {
			ownsAccount = true;
			}
		}
		if(ses.getAttribute("role").equals("Admin") || ownsAccount) {
		if (as.withdraw(dto.amount, dto.accountId) != -1) {

			return "$" + dto.amount + " has been withdrawn from Account# " + dto.accountId;
		}
		return "error";
		}
		else {
			return "Permissions Error";
		}
	}

	public String deposit(HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws IOException {
		
		boolean ownsAccount = false;

		BufferedReader reader = req.getReader();

		StringBuilder s = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = new String(s);

		DepositDTO dto = om.readValue(body, DepositDTO.class);
		
		//Check to see if user owns account
		User user = uc.findById((int)ses.getAttribute("userId"));
		for (Account a : user.getAccounts()) {
			if (a.getId() == dto.accountId) {
				ownsAccount = true;
				}
			}
		if (ses.getAttribute("role").equals("Admin") || ownsAccount) {
			if (as.deposit(dto.amount, dto.accountId) != -1) {
				return "$" + dto.amount + " has been deposited to Account# " + dto.accountId;
			} else {
				return "error";
			}
		} else {
			return "Permissions Error";
		}
	}

	public String transfer(HttpServletRequest req, HttpServletResponse res, HttpSession ses) throws IOException {
		boolean ownsAccount = false;

		BufferedReader reader = req.getReader();

		StringBuilder s = new StringBuilder();

		String line = reader.readLine();

		while (line != null) {
			s.append(line);
			line = reader.readLine();
		}

		String body = new String(s);

		TransferDTO dto = om.readValue(body, TransferDTO.class);
		
		//Check to see if user owns account
		User user = uc.findById((int)ses.getAttribute("userId"));
		for (Account a : user.getAccounts()) {
			if (a.getId() == dto.sourceAccountId) {
				ownsAccount = true;
				}
			}
		
		if (ses.getAttribute("role").equals("Admin") || ownsAccount) {
			if (as.transfer(dto.amount, dto.sourceAccountId, dto.targetAccountId)) {
				return "$" + dto.amount + " has been transferred from Account# " + dto.sourceAccountId + " to Account#"
						+ dto.targetAccountId;
			} else {
				return "error";
			}
		} else {
			return "Permissions Error";
		}
	}
}
