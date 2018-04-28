package com.qa.service;

import static javax.transaction.Transactional.TxType.REQUIRED;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@Model
@Default
public class AccountDBService implements AccountService{

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

	@Override
	@Transactional(REQUIRED)
	public String removeAccount(Long id)
	{
		Account anAccount = findAccount(id);
		if(anAccount!=null)
		{
			manager.remove(anAccount);
			return "{\"message\": \"account sucessfully removed\"}";
		}
		else
			return "{\"message\": \"account couldn't be removed\"}";
		
	}
	
	@Transactional(REQUIRED)
	public String updateAccount(String account)
	{
		Account anAccount = util.getObjectForJSON(account, Account.class);
		Account idAccount = findAccount(anAccount.getId());
		if(idAccount!=null)
		{
			idAccount = anAccount;
			manager.merge(idAccount);
			return "{\"message\": \"account sucessfully updated\"}";
		}
		else
			return "{\"message\": \"account couldn't be updated\"}";
		
	}

	public String getAllAccounts() {
		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
	}
}
