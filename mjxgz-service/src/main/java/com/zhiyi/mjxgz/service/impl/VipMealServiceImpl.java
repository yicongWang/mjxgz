package com.zhiyi.mjxgz.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.dao.ex.VipMealMapperExt;
import com.zhiyi.mjxgz.model.VipMeal;
import com.zhiyi.mjxgz.model.VipMealExample;
import com.zhiyi.mjxgz.service.VipMealService;

/**
 * vip套餐
 * @author wyc
 *
 */
@Service
public class VipMealServiceImpl implements VipMealService {

    private Logger logger = LoggerFactory.getLogger(VipMealServiceImpl.class);
    
    @Autowired
    private VipMealMapperExt vipMealMapperExt;

	@Override
	public List<VipMeal> findVipMealList() {
		VipMealExample example = new VipMealExample();
		example.createCriteria().andStatusEqualTo(1);//开启
		return vipMealMapperExt.selectByExample(example);
	}
   
    
}
