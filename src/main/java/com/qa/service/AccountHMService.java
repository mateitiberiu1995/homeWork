package com.qa.service;

import java.util.HashMap;
import java.util.Map;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

public class AccountHMService {

	private Map<Long, Account> accountMap  = new HashMap<Long,Account>();
	private JSONUtil util = new JSONUtil();
	
	public String addAccount(String account)
	{
		
		Account anAccount = util.getObjectForJSON(account , Account.class);
		
		if(accountMap.values().stream().filter(value ->value.getAccountNumber().equals(anAccount.getAccountNumber())).count()>0)
		{
			return "{\"message\": \"account is already in the database\"}";
		}
		else
		{
		accountMap.put((long) (accountMap.size()+1), anAccount);
		return "{\"message\": \"account sucessfully added\"}";
		}

	}
	
	public String removeAccount(long id) {
		if(accountMap.containsKey(id))
		{
			accountMap.remove(id);
			return "{\"message\": \"account sucessfully removed\"}";
		}
		else
			return "{\"message\": \"account couldn't be removed\"}";
	}

	public String updateAccount(String updateAccount) {
		Account updatedAccount = util.getObjectForJSON(updateAccount, Account.class);
		if(accountMap.values().stream().filter(value->value.getId()==updatedAccount.getId())!=null)
		{
			//accountMap.forEach((key,value) -> {if(value.getId()==updatedAccount.getId()) accountMap.replace(key, updatedAccount); });
			//accountMap.forEach((key,value) -> accountMap.replace(key, value.getId()==updatedAccount.getId() ? updatedAccount : value));
			accountMap.entrySet().stream().filter(entrySet->entrySet.getValue().getId()==updatedAccount.getId()).forEach(entrySet -> accountMap.replace(entrySet.getKey(), updatedAccount));
			return "{\"message\": \"account sucessfully updated\"}";
		}
		else
			return "{\"message\": \"account couldn't be updated\"}";
	}
	
	
}
