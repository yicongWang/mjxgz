package com.zhiyi.mjxgz.service;

import java.util.List;

import com.zhiyi.mjxgz.model.BusinessCoupon;

/**
 * 商家券
 * @author wyc
 *
 */
public interface BusinessCouponService {
	
	/**
	 * 获取商品券
	 * @param businessId
	 * @param goodsId
	 * @return
	 */
	List<BusinessCoupon> findCouponByGoodsId(Long businessId, Long goodsId);
	/**
	 * 获取商家券
	 * @param businessId
	 * @return
	 */
	List<BusinessCoupon> findCouponByBusinessId(Long businessId);
	
	void insertAll();
}
