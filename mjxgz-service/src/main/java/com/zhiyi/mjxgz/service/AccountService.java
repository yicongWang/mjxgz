package com.zhiyi.mjxgz.service;

import java.util.List;

import com.zhiyi.mjxgz.model.Account;
import com.zhiyi.mjxgz.vo.UserInfoVO;

/**
 * 
 * @author wyc
 *
 */
public interface AccountService {
	
	 List<Account> findAccounts(Account account);
	 
	 void insertAccounts(Account account);

	 void updateAccount(UserInfoVO userInfoVO,String openid);
	 
	 void updateAccountTimeCache(String accountId);
}
