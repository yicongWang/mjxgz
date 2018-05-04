package com.zhiyi.mjxgz.service;

import java.util.List;

import com.zhiyi.mjxgz.model.Account;

/**
 * 
 * @author wyc
 *
 */
public interface AccountService {
	
	 List<Account> findAccounts(Account account);
}
