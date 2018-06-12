package com.zhiyi.mjxgz.dao.ex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zhiyi.mjxgz.dao.BusinessShopMapper;
import com.zhiyi.mjxgz.dto.ShopInfoDTO;

/**
 * 商家店铺
 * @author wyc
 *
 */
@Mapper
public interface BusinessShopMapperExt  extends BusinessShopMapper{
	/**
	 * 获取用户店铺ID
	 * @param map
	 * @return
	 */
	List<ShopInfoDTO> getShopInfoByAccountId(Map<String,Object> map);
}
