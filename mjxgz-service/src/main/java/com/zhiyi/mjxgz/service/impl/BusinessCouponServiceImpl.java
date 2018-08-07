package com.zhiyi.mjxgz.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.dao.ex.BusinessCouponMapperExt;
import com.zhiyi.mjxgz.dao.ex.BusinessShopMapperExt;
import com.zhiyi.mjxgz.dao.ex.GoodsMapperExt;
import com.zhiyi.mjxgz.model.BusinessCoupon;
import com.zhiyi.mjxgz.model.BusinessCouponExample;
import com.zhiyi.mjxgz.model.BusinessCouponExample.Criteria;
import com.zhiyi.mjxgz.model.BusinessShop;
import com.zhiyi.mjxgz.model.BusinessShopExample;
import com.zhiyi.mjxgz.model.Goods;
import com.zhiyi.mjxgz.model.GoodsExample;
import com.zhiyi.mjxgz.service.BusinessCouponService;
import com.zhiyi.mjxgz.util.DateUtil;
import com.zhiyi.mjxgz.util.RandomStr;

/**
 * 商家券
 * @author wyc
 *
 */
@Service
public class BusinessCouponServiceImpl implements BusinessCouponService {

    private Logger logger = LoggerFactory.getLogger(BusinessCouponServiceImpl.class);
    
    @Autowired
    private BusinessCouponMapperExt businessCouponMapperExt;
    
    @Autowired
    private BusinessShopMapperExt businessShopMapperExt;
    
    @Autowired
    private GoodsMapperExt goodsMapperExt;
	@Override
	public List<BusinessCoupon> findCouponByBusinessId(Long businessId) {
		BusinessCouponExample example = new BusinessCouponExample();
		Criteria criteria = example.createCriteria();
		criteria.andBusinessIdEqualTo(businessId);
		criteria.andStatusEqualTo(1);//启用
		criteria.andCouponTypeEqualTo(1);//商家券
		example.setOrderByClause("sort asc");
		return businessCouponMapperExt.selectByExample(example);
	}

	@Override
	public List<BusinessCoupon> findCouponByGoodsId(Long businessId, Long goodsId) {
		BusinessCouponExample example = new BusinessCouponExample();
		Criteria criteria = example.createCriteria();
		criteria.andBusinessIdEqualTo(businessId);
		criteria.andStatusEqualTo(1);//启用
		criteria.andGoodsIdEqualTo(goodsId);
		criteria.andCouponTypeEqualTo(2);//商品券
		example.setOrderByClause("sort asc");
		return businessCouponMapperExt.selectByExample(example);
	}
	
	@Override
	public void insertAll(){
		List<Goods> list = goodsMapperExt.selectByExample(new GoodsExample());
		if(CollectionUtils.isNotEmpty(list)){
			for(Goods goods : list){
				BusinessShopExample example = new BusinessShopExample();
				example.createCriteria().andBusinessIdEqualTo(Long.valueOf(goods.getBusinessId()));
				List<BusinessShop> listBusinessShop = businessShopMapperExt.selectByExample(example);
				if(CollectionUtils.isEmpty(listBusinessShop)){
					continue;
				}
				BusinessCouponExample example1 = new BusinessCouponExample();
				example1.createCriteria().andGoodsIdEqualTo(goods.getId());
				List<BusinessCoupon> list1 = businessCouponMapperExt.selectByExample(example1);
				if(CollectionUtils.isNotEmpty(list1)){
					continue;
				}
				
				BusinessCoupon businessCoupon = new BusinessCoupon();
				businessCoupon.setCode(RandomStr.randomStr(4)+ DateUtil.dateToStr(new Date(), "MMddHHmmss"));
				businessCoupon.setCouponImg(goods.getImgUrl());
				businessCoupon.setCouponName(goods.getGoodsName());
				businessCoupon.setCouponTitle(goods.getGoodsTitle());
				businessCoupon.setShopId(listBusinessShop.get(0).getId());
				businessCoupon.setBusinessId(Long.valueOf(goods.getBusinessId()));
				businessCoupon.setCouponType(2);
				businessCoupon.setActiveType(1);
				businessCoupon.setStartTime(DateUtil.strToDate("2018-08-01 10:00:00",DateUtil.DATETIME_DEFAULT_FORMAT ));
				businessCoupon.setEndTime(goods.getActiveTimeEnd());
				businessCoupon.setStatus(1);
				businessCoupon.setGoodsId(goods.getId());
				businessCouponMapperExt.insert(businessCoupon);
			}
		}
		
	}
	
}
