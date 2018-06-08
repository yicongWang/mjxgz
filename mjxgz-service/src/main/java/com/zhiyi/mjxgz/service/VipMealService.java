package com.zhiyi.mjxgz.service;

import java.util.List;

import com.zhiyi.mjxgz.model.VipMeal;

/**
 *
 * @author wyc
 *
 */
public interface VipMealService {
	
	/**
	 * 获取vip套餐列表
	 * @return
	 */
	 List<VipMeal> findVipMealList();
}
