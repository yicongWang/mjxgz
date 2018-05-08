package com.zhiyi.mjxgz.service;

import java.util.List;

import com.zhiyi.mjxgz.model.GoodsImg;

/**
 * 商品Banner图
 * @author wyc
 *
 */
public interface GoodsImgService {
	/**
	 * 根据商品ID获取商品Banner图
	 * @param goodsId
	 * @return
	 */
	 List<GoodsImg> findGoodsImgList(Long goodsId);
}
