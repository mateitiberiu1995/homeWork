package com.qa.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

	public String addAccount(String mockObject) {
		// TODO Auto-generated method stub
		return null;
	}
}
