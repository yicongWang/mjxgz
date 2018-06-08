package com.zhiyi.mjxgz.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.common.exception.DataNotExistsException;
import com.zhiyi.mjxgz.dao.ex.AccountCouponMapperExt;
import com.zhiyi.mjxgz.dao.ex.BusinessCouponMapperExt;
import com.zhiyi.mjxgz.model.AccountCoupon;
import com.zhiyi.mjxgz.model.BusinessCoupon;
import com.zhiyi.mjxgz.service.AccountCouponService;
import com.zhiyi.mjxgz.util.DateUtil;
import com.zhiyi.mjxgz.util.RandomStr;
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
	@Autowired
	private BusinessCouponMapperExt businessCouponMapperExt;

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

	@Override
	public void takeCoupon(Long couponId, String accountId) {
		BusinessCoupon businessCoupon = businessCouponMapperExt.selectByPrimaryKey(couponId);
		if(null != businessCoupon){
			AccountCoupon record = new AccountCoupon();
			record.setStatus(0);
			record.setAccountId(accountId);
			record.setBusinessId(businessCoupon.getBusinessId());
			record.setCreateTime(new Date());
			record.setCouponId(Integer.valueOf(couponId+""));
			if(businessCoupon.getActiveType() == null || businessCoupon.getActiveType() == 1){
				record.setStartTime(businessCoupon.getStartTime());
				record.setEndTime(businessCoupon.getEndTime());
			}else{
				record.setStartTime(new Date());
				record.setEndTime(DateUtil.addDay(businessCoupon.getActiveDay().intValue()));
			}
			record.setCouponCode("C"+DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss"+RandomStr.randomStr(5)));
		}else{
			throw new DataNotExistsException("该券不存在");
		}
	}
	
}
