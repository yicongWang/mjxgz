package com.zhiyi.mjxgz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.dao.ex.BusinessMapperExt;
import com.zhiyi.mjxgz.service.BusinessService;
import com.zhiyi.mjxgz.vo.BusinessInfoVO;

/**
 * 商家
 * @author wyc
 *
 */
@Service
public class BusinessServiceImpl implements BusinessService {

    private Logger logger = LoggerFactory.getLogger(BusinessServiceImpl.class);
    
    @Autowired
    private BusinessMapperExt businessMapperExt;

	@Override
	public BusinessInfoVO findBusinessInfoByBusinessId(Long businessId) {
		BusinessInfoVO businessInfoVO = null;
		Map<String,Object> map = new HashMap<>();
		map.put("businessId", businessId);
		List<BusinessInfoVO> list = businessMapperExt.findBusinessInfoByBusinessId(map);
		if(CollectionUtils.isNotEmpty(list)){
			businessInfoVO = list.get(0);
		}
		return businessInfoVO;
	}
	
}
