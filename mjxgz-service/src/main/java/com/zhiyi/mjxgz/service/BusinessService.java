package com.zhiyi.mjxgz.service;

import com.zhiyi.mjxgz.vo.BusinessInfoVO;

/**
 * 商家
 * @author wyc
 *
 */
public interface BusinessService {
	
	 /**
	  * 根据商家id获取商家详情
	  * @param businessId
	  * @return
	  */
	BusinessInfoVO findBusinessInfoByBusinessId(Long businessId);
}
