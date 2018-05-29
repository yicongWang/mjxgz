package com.zhiyi.mjxgz.dao.ex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zhiyi.mjxgz.dao.AccountCouponMapper;
import com.zhiyi.mjxgz.vo.AccountCouponInfoVO;;

/**
 * 极光推送关联账户
 * @author wyc
 *
 */
@Mapper
public interface AccountCouponMapperExt  extends AccountCouponMapper{
	
	/**
	 * 获取用户券列表
	 * @param accountId
	 * @return
	 */
	List<AccountCouponInfoVO> findAccountCouponList(Map<String, Object> map);
}
