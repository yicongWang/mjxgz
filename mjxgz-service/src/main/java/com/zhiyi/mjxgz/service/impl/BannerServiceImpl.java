package com.zhiyi.mjxgz.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.common.constants.InfoState;
import com.zhiyi.mjxgz.dao.ex.BannerMapperExt;
import com.zhiyi.mjxgz.model.Banner;
import com.zhiyi.mjxgz.model.BannerExample;
import com.zhiyi.mjxgz.model.BannerExample.Criteria;
import com.zhiyi.mjxgz.service.BannerService;

/**
 * 广告图ServiceImpl
 * @author wyc
 *
 */
@Service
public class BannerServiceImpl implements BannerService {

    private Logger logger = LoggerFactory.getLogger(BannerServiceImpl.class);
    
    @Autowired
    private BannerMapperExt bannerMapperExt;
    
	@Override
	public List<Banner> findBannerByPosition(int position) {
		BannerExample example = new  BannerExample();
		Criteria  criteria = example.createCriteria();
		criteria.andBannerPositionEqualTo(position);
		criteria.andStatusEqualTo(InfoState.NORMAL);
		example.setOrderByClause(" sort asc");
		return bannerMapperExt.selectByExample(example);
	}
}
