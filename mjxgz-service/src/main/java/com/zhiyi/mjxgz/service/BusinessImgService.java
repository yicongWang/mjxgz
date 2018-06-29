package com.zhiyi.mjxgz.service;

import java.util.List;

import com.zhiyi.mjxgz.model.BusinessImg;

/**
 * 商家Banner图
 * @author wyc
 *
 */
public interface BusinessImgService {
	/**
	 * 根据商家id获取商家banner图
	 * @param businessId
	 * @return
	 */
	 List<BusinessImg> findBusinessImgList(Long businessId);

	void saveBusinessImg(BusinessImg businessImg);
	
}
