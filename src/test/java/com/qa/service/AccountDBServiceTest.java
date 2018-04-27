package com.qa.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class AccountDBServiceTest {

	@InjectMocks
	private AccountDBService repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;

	private static final String MOCK_DATA_ARRAY = "[{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}]";

	private static final String MOCK_OBJECT = "{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}";

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}
	
	@Test
	public void testCreateAccount() {
		String reply = repo.addAccount(MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"account sucessfully added\"}");
	}
	
	
	@Test
	public void testDeleteAccount() {
		
		Mockito.when(repo.findAccount((long) 1)).thenReturn(util.getObjectForJSON("{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}", Account.class));
		String reply = (String) repo.removeAccount((long) 1);
		Assert.assertEquals(reply, "{\"message\": \"account sucessfully removed\"}");
		
		Mockito.when(repo.findAccount((long) 1)).thenReturn(null);
		reply =  repo.removeAccount((long) 1);
		Assert.assertEquals(reply, "{\"message\": \"account couldn't be removed\"}");
		
	}
	
	@Test
	public void testUpdateAccount() {
		Mockito.when(repo.findAccount((long) 1)).thenReturn(util.getObjectForJSON("{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}", Account.class));
		String reply = repo.updateAccount("{\"id\":1,\"firstName\":\"Johny\",\"lastName\":\"Bloggs\",\"accountNumber\":\"1234\"}");
		Assert.assertEquals(reply, "{\"message\": \"account sucessfully updated\"}");
		
		Mockito.when(repo.findAccount((long) 1)).thenReturn(null);
		reply = repo.updateAccount("{\"id\":2,\"firstName\":\"Johny\",\"lastName\":\"Bloggs\",\"accountNumber\":\"1234\"}");
		Assert.assertEquals(reply, "{\"message\": \"account couldn't be updated\"}");
	}
	@Test
	public void testGetAllAccounts() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(util.getObjectForJSON("{\"firstName\":\"John\",\"secondName\":\"Doe\",\"accountNumber\":\"1234\"}", Account.class));
		Mockito.when(query.getResultList()).thenReturn(accounts);
		Assert.assertEquals(MOCK_DATA_ARRAY, repo.getAllAccounts());
	}
}
