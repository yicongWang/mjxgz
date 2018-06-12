package com.zhiyi.mjxgz.service;

import java.util.List;

import com.zhiyi.mjxgz.dto.ShopInfoDTO;
import com.zhiyi.mjxgz.model.BusinessShop;

/**
 * 商家店铺
 * @author wyc
 *
 */
public interface BusinessShopService {

	List<BusinessShop> findShopInfoList(Long businessId);
	
	ShopInfoDTO getShopInfo(String accountId);
}
