package com.zhiyi.mjxgz.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.dao.ex.RegionMapperExt;
import com.zhiyi.mjxgz.service.RegionService;

/**
 * 区域
 * @author wyc
 *
 */
@Service
public class RegionServiceImpl implements RegionService {

    private Logger logger = LoggerFactory.getLogger(RegionServiceImpl.class);
    
    @Autowired
    private RegionMapperExt regionMapperExt;


	
}
