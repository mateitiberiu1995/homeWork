package com.qa.service;
import static javax.transaction.Transactional.TxType.REQUIRED;

import javax.transaction.Transactional;
public interface AccountService {
	String getAllAccounts();
	String addAccount(String account);
	String updateAccount(String account);
	String removeAccount(Long id);
}
