package com.zhiyi.mjxgz.service;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhiyi.mjxgz.dto.AccountCouponInfoDTO;
import com.zhiyi.mjxgz.vo.CouponVO;
import com.zhiyi.mjxgz.vo.VerificateCouponInfoVO;

/**
 * 
 * @author wyc
 *
 */
public interface AccountCouponService {
	/**
	 * 用户券列表
	 * @param accountId
	 * @param status 
	 * @return
	 */
	List<AccountCouponInfoDTO> findAccountCouponList(String accountId, Integer status);

	/**
	 * 更新用户券状态
	 * @param accountCouponId
	 */
	void updateAccountCoupon(Long accountCouponId,int status);

	/**
	 * 领取券
	 * @param couponId
	 * @param id
	 */
	void takeCoupon(Long couponId, String accountId);
	
	/**
	 * 核销券
	 * @param verificateCouponInfoVO
	 * @param id
	 */
	void makeUseCoupon(VerificateCouponInfoVO verificateCouponInfoVO, String id);

	/**
	 * 获取券信息
	 * @param couponVO
	 */
	AccountCouponInfoDTO findCouponInfoByAccountCouponCode(CouponVO couponVO);
	/**
	 * 分页获取核销记录
	 * @param shopId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo findVerificateCouponPage(Long shopId, Integer pageNum, Integer pageSize);
}
