package com.qa.service;

import static javax.transaction.Transactional.TxType.REQUIRED;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

public class AccountDBService {

	@Inject
	private JSONUtil util;
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

	@Transactional(REQUIRED)
	public String addAccount(String account) {
		Account anAccount = util.getObjectForJSON(account, Account.class);
		manager.persist(anAccount);      
		return "{\"message\": \"account sucessfully added\"}";
	}
	public Account findAccount(Long id) {
		return manager.find(Account.class, id);
	}

	@Transactional(REQUIRED)
	public String removeAccount(Long id)
	{
		Account anAccount = findAccount(id);
		if(anAccount!=null)
		{
			manager.remove(id);
			return "{\"message\": \"account sucessfully removed\"}";
		}
		else
			return "{\"message\": \"account couldn't be removed\"}";
		
	}
}
