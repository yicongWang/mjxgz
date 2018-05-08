package com.zhiyi.mjxgz.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhiyi.mjxgz.vo.BusinessInfoVO;

/**
 * 商家
 * @author wyc
 *
 */
public interface BusinessService {
	
	 /**
	  * 根据商家id获取商家详情
	  * @param businessId
	  * @return
	  */
	BusinessInfoVO findBusinessInfoByBusinessId(Long businessId);
	/**
	 *  获取商家列表
	 * @return
	 */
	List<BusinessInfoVO> findBusinessInfoList(String businessName);
	/**
	 * 分页获取商家列表
	 * @param businessName
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<BusinessInfoVO> findusinessInfoPage(String businessName, Integer pageNum, Integer pageSize);
}
