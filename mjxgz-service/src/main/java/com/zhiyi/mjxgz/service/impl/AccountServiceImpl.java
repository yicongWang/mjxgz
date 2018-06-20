package com.zhiyi.mjxgz.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.common.constants.InfoState;
import com.zhiyi.mjxgz.dao.ex.AccountMapperExt;
import com.zhiyi.mjxgz.dto.RedisUserData;
import com.zhiyi.mjxgz.model.Account;
import com.zhiyi.mjxgz.model.AccountExample;
import com.zhiyi.mjxgz.service.AccountService;
import com.zhiyi.mjxgz.util.DateUtil;
import com.zhiyi.mjxgz.util.MailUtill;
import com.zhiyi.mjxgz.util.RedisUtil;
import com.zhiyi.mjxgz.util.UserSettings;
import com.zhiyi.mjxgz.vo.UserInfoVO;

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
    private UserSettings userSettings;
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
	            
	            if(StringUtils.isNotBlank(account.getOpenid())){
	                criteria.andOpenidEqualTo( account.getOpenid());
	            }
	        }
	        criteria.andStatusNotEqualTo(InfoState.DELETED);
	        return accountMapperExt.selectByExample(accountExample);
	}
	@Override
	public void insertAccounts(Account account) {
		accountMapperExt.insert(account);
	}
	@Override
	public void updateAccount(UserInfoVO userInfoVO,String openid) {
		   Account account = new Account();
		   BeanUtils.copyProperties(userInfoVO, account);
		   account.setHeardImg(userInfoVO.getAvatarUrl());
		   account.setSex(userInfoVO.getGender());
		   AccountExample example = new AccountExample();
		   example.createCriteria().andOpenidEqualTo(openid);
		   accountMapperExt.updateByExampleSelective(account, example);
		   try{
			  String accessToken = (String) redisUtil.get(openid);
			  RedisUserData redisUserData = (RedisUserData) redisUtil.get(accessToken);
			  redisUserData.setAvatarUrl(userInfoVO.getAvatarUrl());
			  redisUserData.setNickName(userInfoVO.getNickName());
			  Long invalid = Long.parseLong(userSettings.getSessionInvalid());//要小于30天session_key
			  redisUtil.set(accessToken, redisUserData, invalid);
		   }catch(Exception e){
			   logger.error("更新用户信息缓存异常："+e.getMessage(),e);
		   }
	}
	
}
