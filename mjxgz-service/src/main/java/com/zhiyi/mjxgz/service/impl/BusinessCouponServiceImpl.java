package com.zhiyi.mjxgz.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.dao.ex.BusinessCouponMapperExt;
import com.zhiyi.mjxgz.model.BusinessCoupon;
import com.zhiyi.mjxgz.model.BusinessCouponExample;
import com.zhiyi.mjxgz.model.BusinessCouponExample.Criteria;
import com.zhiyi.mjxgz.service.BusinessCouponService;

/**
 * 商家券
 * @author wyc
 *
 */
@Service
public class BusinessCouponServiceImpl implements BusinessCouponService {

    private Logger logger = LoggerFactory.getLogger(BusinessCouponServiceImpl.class);
    
    @Autowired
    private BusinessCouponMapperExt businessCouponMapperExt;

	@Override
	public List<BusinessCoupon> findCouponByBusinessId(Long businessId) {
		BusinessCouponExample example = new BusinessCouponExample();
		Criteria criteria = example.createCriteria();
		criteria.andBusinessIdEqualTo(businessId);
		criteria.andStatusEqualTo(1);//启用
		example.setOrderByClause("sort asc");
		return businessCouponMapperExt.selectByExample(example);
	}


	
}
