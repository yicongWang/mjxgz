package com.zhiyi.mjxgz.service;

import java.util.List;

import com.zhiyi.mjxgz.vo.AccountCouponInfoVO;

/**
 * 
 * @author wyc
 *
 */
public interface AccountCouponService {
	/**
	 * 用户券列表
	 * @param accountId
	 * @param status 
	 * @return
	 */
	List<AccountCouponInfoVO> findAccountCouponList(String accountId, Integer status);

	/**
	 * 更新用户券状态
	 * @param accountCouponId
	 */
	void updateAccountCoupon(Long accountCouponId,int status);
}
