package com.zhiyi.mjxgz.service;

import java.util.Map;

/**
 *vip订单
 * @author wyc
 *
 */
public interface VipOrderService {
	
	 String buyorder(String vipMealId, String id);

	Map<String, Object> getPayInformation(String openId, String orderNumber, String requestIp) throws Exception;
	
}
