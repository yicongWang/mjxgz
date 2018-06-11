package com.zhiyi.mjxgz.dao.ex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zhiyi.mjxgz.dao.AccountCouponMapper;
import com.zhiyi.mjxgz.dto.AccountCouponInfoDTO;
import com.zhiyi.mjxgz.dto.VerificateCouponDTO;@Mapper
public interface AccountCouponMapperExt  extends AccountCouponMapper{
	
	/**
	 * 获取用户券列表
	 * @param accountId
	 * @return
	 */
	List<AccountCouponInfoDTO> findAccountCouponList(Map<String, Object> map);
	
	/**
	 * 获取店铺核销记录
	 * @param shopId
	 * @return
	 */
	List<VerificateCouponDTO> findVerificateCouponList(Long shopId);
}
