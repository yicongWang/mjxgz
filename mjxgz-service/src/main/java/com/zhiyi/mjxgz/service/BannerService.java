package com.zhiyi.mjxgz.service;

import java.util.List;

import com.zhiyi.mjxgz.model.Banner;

/**
 * 
 * @author wyc
 *
 */
public interface BannerService {
	
	/**
	 * 根据位置获取banner
	 * @param position
	 * @return
	 */
	 List<Banner> findBannerByPosition(int position);
}
