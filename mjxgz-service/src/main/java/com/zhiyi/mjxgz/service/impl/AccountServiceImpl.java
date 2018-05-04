package com.zhiyi.mjxgz.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.common.constants.InfoState;
import com.zhiyi.mjxgz.dao.ex.AccountMapperExt;
import com.zhiyi.mjxgz.model.Account;
import com.zhiyi.mjxgz.model.AccountExample;
import com.zhiyi.mjxgz.service.AccountService;
import com.zhiyi.mjxgz.util.MailUtill;
import com.zhiyi.mjxgz.util.RedisUtil;

/**
 * 用户信息ServiceImpl
 * Created by DW on 2017/6/27.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
    
    @Autowired
    private AccountMapperExt accountMapperExt;
    
    @Autowired
	private MailUtill mailUtill; 
    @Autowired
	private RedisUtil redisUtil;
	@Override
	public List<Account> findAccounts(Account account) {
		 AccountExample accountExample = new AccountExample();
	        AccountExample.Criteria criteria = accountExample.createCriteria();
	        if(account!=null){
	            if(!StringUtils.isBlank(account.getMobilePhone())){
	                criteria.andMobilePhoneEqualTo( account.getMobilePhone());
	            }

	        }
	        criteria.andStatusEqualTo(InfoState.DELETED);
	        return accountMapperExt.selectByExample(accountExample);
	}
   
   

	
}
