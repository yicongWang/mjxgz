package com.zhiyi.mjxgz.service;

import java.util.List;

import com.zhiyi.mjxgz.model.GoodsCategory;

/**
 * 
 * @author wyc
 *
 */
public interface GoodsCategoryService {
	
	 List<GoodsCategory> findGoodsCategoryByParentId(int parentId );
}
