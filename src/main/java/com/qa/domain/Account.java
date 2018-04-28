package com.qa.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity 
public class Account {
	
	@Id
	@GeneratedValue
	private Long id;
	private String firstName;
	private String lastName;
	private String accountNumber;
	
	public Account() {
		
	}
	
	public Account(String firstName,String lastName,String accountNumber) {
		this.firstName=firstName;
		this.lastName=lastName;
		this.accountNumber=accountNumber;
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
