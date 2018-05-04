package com.zhiyi.mjxgz.service;

import java.util.List;

import com.zhiyi.mjxgz.model.AccountJpush;

/**
 *  账户极光接口
 * @author wyc
 *
 */
public interface AccountJpushService {
	/**
	 * 保存账号推送关联
	 * @param accountId
	 * @param roleId
	 * @return
	 */
	void saveAccountJpush(AccountJpush accountJpush,String account,String userRole);
	
	
	/**
	 * 根据账号Id 移除账号推送关联
	 * @param accountId
	 */
	void deleteAccountJpushByAccountId(Long accountId);
	
	/**
	 *  根据账号Id获取账号推送关联
	 * @param accountId
	 * @return
	 */
	List<AccountJpush> findAccountJpushByAccountId(Long accountId);
}
