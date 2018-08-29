package com.zhiyi.mjxgz.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.zhiyi.mjxgz.model.Business;
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
	
	void saveBusiness(Business business);
	/**
	 * 获取商家小程序码
	 * @param app_id
	 * @param app_secret
	 * @param token
	 * @param params
	 * @param path
	 * @return 
	 */
	String findCode(String app_id, String app_secret, Map<String, Object> params, String path);
}
