package com.qa.service;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

public class AccountHMServiceTest {

	private AccountHMService service;
	private Account joeBloggs;
	private Account janeBloggs;
	private JSONUtil util;

	@Before
	public void init() {
		service = new AccountHMService();
		joeBloggs = new Account("Joe", "Bloggs", "1234");
		janeBloggs = new Account("Jane", "Bloggs", "1235");
		util = new JSONUtil();
	}
	@Test
	public void addAccountAndremoveAccount() {
		Assert.assertEquals("{\"message\": \"account sucessfully added\"}", service.addAccount(util.getJSONForObject(joeBloggs)));
		Assert.assertEquals("{\"message\": \"account sucessfully added\"}", service.addAccount(util.getJSONForObject(janeBloggs)));
		Assert.assertEquals("{\"message\": \"account is already in the database\"}", service.addAccount(util.getJSONForObject(janeBloggs)));
		Assert.assertEquals("{\"message\": \"account is already in the database\"}", service.addAccount(util.getJSONForObject(joeBloggs)));
		
		Assert.assertEquals("{\"message\": \"account sucessfully removed\"}", service.removeAccount((long) 2));
		Assert.assertEquals("{\"message\": \"account sucessfully removed\"}", service.removeAccount((long) 1));
		Assert.assertEquals("{\"message\": \"account couldn't be removed\"}", service.removeAccount((long) 1));
	
	}
	

}
