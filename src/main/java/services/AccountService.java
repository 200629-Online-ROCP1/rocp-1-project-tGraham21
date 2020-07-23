package services;

import java.util.List;

import models.*;
import repos.AccountDAOInterface;
import repos.AccountDAO;

public class AccountService {
	
	private final AccountDAOInterface dao = new AccountDAO();
	
	public List<Account> findAll(){
		return dao.findAll();
	}
	
	public Account findById(int id) {
		return dao.findAccountById(id);
	}
	
	public Account submitAccount(Account acct, int id) {
		return dao.submitAccount(acct, id);
	}
	
	public Account updateAccount(Account acct) {
		return dao.updateAccount(acct);
	}
}
