package com.qa.integration;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.qa.service.AccountService;


@Path("/account")
public class AccountServiceEndPoint {

	@Inject
	private AccountService repo;
	
	
	@GET
	@Path("/json")
	@Produces({ "application/json" })
	public String getAllAccounts()
	{
		return repo.getAllAccounts();
	}
}
