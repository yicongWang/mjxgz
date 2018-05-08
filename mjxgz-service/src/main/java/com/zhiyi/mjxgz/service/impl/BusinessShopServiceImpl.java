package com.zhiyi.mjxgz.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.dao.ex.BusinessShopMapperExt;
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
	
}
