package com.zhiyi.mjxgz.dao.ex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zhiyi.mjxgz.dao.GoodsMapper;
import com.zhiyi.mjxgz.dto.GoodsDTO;
import com.zhiyi.mjxgz.vo.GoodsInfoVO;

/**
 * 商品
 * @author wyc
 *
 */
@Mapper
public interface GoodsMapperExt  extends GoodsMapper{
	/**
	 * 根据分类或者商家名称获取商品列表
	 * @param categoryId
	 * @param businessName
	 * @return
	 */
	List<GoodsDTO> findGoodsList(Map<String,Object> map);
	
	/**
	 * 根据商品Id获取商品信息
	 * @param map
	 * @return
	 */
	List<GoodsInfoVO> findGoodsInfoByGoodsId(Map<String,Object> map);
	
}
