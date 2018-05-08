package com.zhiyi.mjxgz.service;

import java.util.List;

import com.zhiyi.mjxgz.model.BusinessCoupon;

/**
 * 商家券
 * @author wyc
 *
 */
public interface BusinessCouponService {

	List<BusinessCoupon> findCouponByBusinessId(Long businessId);
}
