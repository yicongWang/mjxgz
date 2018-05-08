package com.zhiyi.mjxgz.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.dao.ex.BusinessImgMapperExt;
import com.zhiyi.mjxgz.model.BusinessImg;
import com.zhiyi.mjxgz.model.BusinessImgExample;
import com.zhiyi.mjxgz.model.BusinessImgExample.Criteria;
import com.zhiyi.mjxgz.service.BusinessImgService;

/**
 * 商家图片
 * @author wyc
 *
 */
@Service
public class BusinessImgServiceImpl implements BusinessImgService {

    private Logger logger = LoggerFactory.getLogger(BusinessImgServiceImpl.class);
    
    @Autowired
    private BusinessImgMapperExt businessImgMapperExt;

	@Override
	public List<BusinessImg> findBusinessImgList(Long businessId) {
		BusinessImgExample example = new BusinessImgExample();
		Criteria criteria = example.createCriteria();
		criteria.andBusinessIdEqualTo(businessId);
		return businessImgMapperExt.selectByExample(example);
	}

}
