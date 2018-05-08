package com.zhiyi.mjxgz.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.dao.ex.GoodsCategoryMapperExt;
import com.zhiyi.mjxgz.model.GoodsCategory;
import com.zhiyi.mjxgz.model.GoodsCategoryExample;
import com.zhiyi.mjxgz.model.GoodsCategoryExample.Criteria;
import com.zhiyi.mjxgz.service.GoodsCategoryService;

/**
 * 商品分类
 * @author wyc
 *
 */
@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    private Logger logger = LoggerFactory.getLogger(GoodsCategoryServiceImpl.class);
    
    @Autowired
    private GoodsCategoryMapperExt goodsCategoryExt;

	@Override
	public List<GoodsCategory> findGoodsCategoryByParentId(int parentId) {
		GoodsCategoryExample example = new  GoodsCategoryExample();
		Criteria  criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		example.setOrderByClause(" category_sort asc");
		return goodsCategoryExt.selectByExample(example);
	}
}
