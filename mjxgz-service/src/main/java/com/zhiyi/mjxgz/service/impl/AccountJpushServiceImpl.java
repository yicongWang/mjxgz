package com.zhiyi.mjxgz.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.dao.ex.AccountJpushMapperExt;
import com.zhiyi.mjxgz.model.AccountJpush;
import com.zhiyi.mjxgz.model.AccountJpushExample;
import com.zhiyi.mjxgz.model.AccountJpushExample.Criteria;
import com.zhiyi.mjxgz.service.AccountJpushService;
import com.zhiyi.mjxgz.service.jpush.JPushHelper;

/**
 * 极光推送关联账户
 * @author wyc
 *
 */
@Service
public class AccountJpushServiceImpl implements AccountJpushService {
	@Autowired
	private AccountJpushMapperExt accountJpushMapperExt;

	@Autowired
	private JPushHelper jPushHelper;
	@Override
	public void saveAccountJpush(AccountJpush accountJpush,String account,String userRole) {
		List<AccountJpush> list = findAccountJpushByAccountId(accountJpush.getAccountId());
		if(CollectionUtils.isNotEmpty(list)){//更新操作
			AccountJpushExample  accountJpushExample = new AccountJpushExample();
			Criteria criteria = accountJpushExample.createCriteria();
			criteria.andAccountIdEqualTo(accountJpush.getAccountId());
			accountJpushMapperExt.updateByExampleSelective(accountJpush, accountJpushExample);
		}else{
			accountJpushMapperExt.insertSelective(accountJpush);
		}
		//设置别名
		jPushHelper.setAlias(accountJpush.getRegId(), account);
		//设置标签
		jPushHelper.setTags(accountJpush.getRegId(), userRole, null);
	}

	@Override
	public void deleteAccountJpushByAccountId(String accountId) {
		AccountJpushExample  accountJpushExample = new AccountJpushExample();
		Criteria criteria = accountJpushExample.createCriteria();
		criteria.andAccountIdEqualTo(accountId);
		accountJpushMapperExt.deleteByExample(accountJpushExample);
	}

	
	@Override
	public List<AccountJpush> findAccountJpushByAccountId(String accountId) {
		AccountJpushExample  accountJpushExample = new AccountJpushExample();
		Criteria criteria = accountJpushExample.createCriteria();
		criteria.andAccountIdEqualTo(accountId);
		return accountJpushMapperExt.selectByExample(accountJpushExample);
	}
}
