package com.zhiyi.mjxgz.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhiyi.mjxgz.dto.GoodsDTO;
import com.zhiyi.mjxgz.vo.GoodsInfoVO;

/**
 * 
 * @author wyc
 *
 */
public interface GoodsService {
	/**
	 * 根据分类或者商家名称获取商品列表
	 * @param categoryId
	 * @param businessName
	 * @return
	 */
	 List<GoodsDTO> findGoodsList(Long categoryId,String businessName);

	 /**
	  * 根据分类或者商家名称分页获取商品
	  * @param categoryId
	  * @param businessName
	  * @param pageNum
	  * @param pageSize
	  * @return
	  * @throws Exception
	  */
	 PageInfo<GoodsDTO> findGoodsPage(Long categoryId, String businessName, Integer pageNum, Integer pageSize) throws Exception;

	 /**
	  * 根据商家id获取商家商品
	  * @param businessId
	  * @param excludeGoodsId
	  * @return
	  */
	 List<GoodsDTO> findBusinessGoodsList(Long businessId, String excludeGoodsId);
	 
	/**
	 * 根据商品Id获取商品详情
	 * @param goodsId
	 * @return
	 */
	GoodsInfoVO findGoodsInfoByGoodsId(Long goodsId);
}
