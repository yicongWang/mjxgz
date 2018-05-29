package com.zhiyi.mjxgz.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyi.mjxgz.dao.ex.GoodsDetailMapperExt;
import com.zhiyi.mjxgz.dao.ex.GoodsMapperExt;
import com.zhiyi.mjxgz.dto.GoodsDTO;
import com.zhiyi.mjxgz.model.GoodsDetail;
import com.zhiyi.mjxgz.model.GoodsDetailExample;
import com.zhiyi.mjxgz.service.GoodsService;
import com.zhiyi.mjxgz.vo.GoodsInfoVO;

/**
 * 商品
 * @author wyc
 *
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    private Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);
    
    @Autowired
    private GoodsMapperExt goodsMapperExt;
    @Autowired
    private GoodsDetailMapperExt goodsDetailMapperExt;
	@Override
	public List<GoodsDTO> findGoodsList(Long categoryId, String businessName) {
		Map<String,Object> map = new java.util.HashMap<>();
		map.put("categoryId", categoryId);
		map.put("businessName", businessName);
		return goodsMapperExt.findGoodsList(map);
	}

	@Override
	public PageInfo<GoodsDTO>  findGoodsPage(Long categoryId, String businessName,Integer pageNum, Integer pageSize) throws Exception{
        	PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findGoodsList(categoryId, businessName));
    }

	@Override
	public List<GoodsDTO> findBusinessGoodsList(Long businessId, String excludeGoodsId) {
		Map<String,Object> map = new java.util.HashMap<>();
		map.put("businessId", businessId);
		map.put("excludeGoodsId", excludeGoodsId);
		return goodsMapperExt.findGoodsList(map);
	}

	@Override
	public GoodsInfoVO findGoodsInfoByGoodsId(Long goodsId) {
		GoodsInfoVO goodsInfoVO = null;
		Map<String,Object> map = new java.util.HashMap<>();
		map.put("goodsId", goodsId);
		List<GoodsInfoVO> list = goodsMapperExt.findGoodsInfoByGoodsId(map);
		if(CollectionUtils.isNotEmpty(list)){
			goodsInfoVO = list.get(0);
		}
		return goodsInfoVO;
	}

	@Override
	public List<GoodsDetail> findGoodsDetailList(Long goodsId) {
		GoodsDetailExample example = new GoodsDetailExample();
		example.createCriteria().andGoodsIdEqualTo(goodsId);
		return goodsDetailMapperExt.selectByExample(example);
	}
}
