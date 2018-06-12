package com.zhiyi.mjxgz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.common.exception.BizException;
import com.zhiyi.mjxgz.dao.ex.BusinessShopMapperExt;
import com.zhiyi.mjxgz.dto.ShopInfoDTO;
import com.zhiyi.mjxgz.model.BusinessShop;
import com.zhiyi.mjxgz.model.BusinessShopExample;
import com.zhiyi.mjxgz.model.BusinessShopExample.Criteria;
import com.zhiyi.mjxgz.service.BusinessShopService;

/**
 * 商家店铺
 * @author wyc
 *
 */
@Service
public class BusinessShopServiceImpl implements BusinessShopService {
    private Logger logger = LoggerFactory.getLogger(BusinessShopServiceImpl.class);
    
    @Autowired
    private BusinessShopMapperExt businessShopMapperExt;

	@Override
	public List<BusinessShop> findShopInfoList(Long businessId) {
		BusinessShopExample example = new BusinessShopExample();
		Criteria criteria = example.createCriteria();
		criteria.andBusinessIdEqualTo(businessId);
		return businessShopMapperExt.selectByExample(example);
	}

	@Override
	public ShopInfoDTO getShopInfo(String accountId) {
		Map<String,Object> map = new  HashMap<>();
		map.put("accountId", accountId);
		List<ShopInfoDTO> list = businessShopMapperExt.getShopInfoByAccountId(map);
		if(CollectionUtils.isEmpty(list)){
			throw new BizException("店铺不存在");
		}
		return list.get(0);
	}
	
}
