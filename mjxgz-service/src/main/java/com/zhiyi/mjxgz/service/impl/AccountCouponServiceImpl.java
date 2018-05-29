package com.zhiyi.mjxgz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.dao.ex.AccountCouponMapperExt;
import com.zhiyi.mjxgz.model.AccountCoupon;
import com.zhiyi.mjxgz.service.AccountCouponService;
import com.zhiyi.mjxgz.vo.AccountCouponInfoVO;

/**
 * 用户券
 * @author wyc
 *
 */
@Service
public class AccountCouponServiceImpl implements AccountCouponService {
	@Autowired
	private AccountCouponMapperExt accountCouponMapperExt;

	@Override
	public  List<AccountCouponInfoVO> findAccountCouponList(String accountId, Integer status) {
		Map<String, Object> map = new HashMap<>();
		map.put("accountId", accountId);
		map.put("status", status);
		return accountCouponMapperExt.findAccountCouponList(map);
	}

	@Override
	public void updateAccountCoupon(Long accountCouponId, int status) {
		AccountCoupon record = new AccountCoupon();
		record.setId(accountCouponId);
		record.setStatus(status);
		accountCouponMapperExt.updateByPrimaryKeySelective(record);
		
	}

	
}
