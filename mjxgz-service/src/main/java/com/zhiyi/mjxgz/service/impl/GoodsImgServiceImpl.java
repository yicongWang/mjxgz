package com.zhiyi.mjxgz.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.dao.ex.GoodsImgMapperExt;
import com.zhiyi.mjxgz.model.GoodsImg;
import com.zhiyi.mjxgz.model.GoodsImgExample;
import com.zhiyi.mjxgz.model.GoodsImgExample.Criteria;
import com.zhiyi.mjxgz.service.GoodsImgService;

/**
 * 商品
 * @author wyc
 *
 */
@Service
public class GoodsImgServiceImpl implements GoodsImgService {

    private Logger logger = LoggerFactory.getLogger(GoodsImgServiceImpl.class);
    
    @Autowired
    private GoodsImgMapperExt goodsImgMapperExt;

	@Override
	public List<GoodsImg> findGoodsImgList(Long goodsId) {
		GoodsImgExample example = new GoodsImgExample();
		Criteria criteria = example.createCriteria();
		criteria.andGoodsIdEqualTo(goodsId);
		return goodsImgMapperExt.selectByExample(example);
	}

	
}
