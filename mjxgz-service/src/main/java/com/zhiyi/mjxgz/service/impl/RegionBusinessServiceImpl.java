package com.zhiyi.mjxgz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.dao.ex.RegionBusinessMapperExt;
import com.zhiyi.mjxgz.service.RegionBusinessService;

/**
 * 商圈
 * @author wyc
 *
 */
@Service
public class RegionBusinessServiceImpl implements RegionBusinessService {

    private Logger logger = LoggerFactory.getLogger(RegionBusinessServiceImpl.class);
    
    @Autowired
    private RegionBusinessMapperExt regionbusinessMapperExt;


	
}
