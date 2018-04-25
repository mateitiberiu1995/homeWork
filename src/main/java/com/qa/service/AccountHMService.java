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
		accountMap.put((long) (accountMap.size()+1), anAccount);
		return "{\"message\": \"account sucessfully added\"}";

	}
}
