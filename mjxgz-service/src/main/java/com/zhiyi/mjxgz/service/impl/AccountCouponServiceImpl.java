package com.zhiyi.mjxgz.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyi.mjxgz.common.exception.BizException;
import com.zhiyi.mjxgz.common.exception.DataNotExistsException;
import com.zhiyi.mjxgz.dao.ex.AccountCouponMapperExt;
import com.zhiyi.mjxgz.dao.ex.BusinessCouponMapperExt;
import com.zhiyi.mjxgz.dao.ex.GoodsMapperExt;
import com.zhiyi.mjxgz.dto.AccountCouponInfoDTO;
import com.zhiyi.mjxgz.dto.VerificateCouponDTO;
import com.zhiyi.mjxgz.model.AccountCoupon;
import com.zhiyi.mjxgz.model.AccountCouponExample;
import com.zhiyi.mjxgz.model.BusinessCoupon;
import com.zhiyi.mjxgz.model.Goods;
import com.zhiyi.mjxgz.service.AccountCouponService;
import com.zhiyi.mjxgz.util.DateUtil;
import com.zhiyi.mjxgz.util.RandomStr;
import com.zhiyi.mjxgz.vo.CouponVO;
import com.zhiyi.mjxgz.vo.VerificateCouponInfoVO;

/**
 * 用户券
 * @author wyc
 *
 */
@Service
public class AccountCouponServiceImpl implements AccountCouponService {
	@Autowired
	private AccountCouponMapperExt accountCouponMapperExt;
	@Autowired
	private BusinessCouponMapperExt businessCouponMapperExt;
	@Autowired
	private GoodsMapperExt goodsMapperExt;
	@Override
	public  List<AccountCouponInfoDTO> findAccountCouponList(String accountId, Integer status) {
		Map<String, Object> map = new HashMap<>();
		map.put("accountId", accountId);
		map.put("status", status);
		return accountCouponMapperExt.findAccountCouponList(map);
	}

	@Override
	public void updateAccountCoupon(Long accountCouponId, int status) {
		AccountCoupon record = new AccountCoupon();
		record.setId(accountCouponId);
		record.setStatus(status);
		accountCouponMapperExt.updateByPrimaryKeySelective(record);
		
	}

	@Override
	@Transactional
	public void takeCoupon(Long couponId, String accountId) {
		BusinessCoupon businessCoupon = businessCouponMapperExt.selectByPrimaryKey(couponId);
		if(null != businessCoupon){
			synchronized (couponId) {
				Goods goods = null;
				if(null != businessCoupon.getGoodsId()){
					goods = goodsMapperExt.selectByPrimaryKey(businessCoupon.getGoodsId());
				}
				
				if(null == goods){
					throw new DataNotExistsException("该卷商品已不存在");
				}
				
				if(1 != goods.getStatus()){
					throw new DataNotExistsException("该商品已下架");
				}
				
				//商品券要减1
				if(goods.getSurplusQuantity() <= 0){
					throw new BizException("该券已领光了");
				}
				
				//更新商品库存量
				Goods newGoods = new Goods();
				newGoods.setId(goods.getId());
				newGoods.setSurplusQuantity(goods.getSurplusQuantity() - 1);
				goodsMapperExt.updateByPrimaryKeySelective(newGoods);
				
				AccountCoupon record = new AccountCoupon();
				record.setStatus(0);
				record.setAccountId(accountId);
				record.setBusinessId(businessCoupon.getBusinessId());
				record.setCreateTime(new Date());
				record.setCouponId(Integer.valueOf(couponId+""));
				if(businessCoupon.getActiveType() == null || businessCoupon.getActiveType() == 1){
					record.setStartTime(businessCoupon.getStartTime());
					record.setEndTime(businessCoupon.getEndTime());
				}else{
					record.setStartTime(new Date());
					record.setEndTime(DateUtil.addDay(businessCoupon.getActiveDay().intValue()));
				}
				record.setCouponCode("C"+DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss")+RandomStr.randomStr(5));
				accountCouponMapperExt.insert(record);
			}
			
		}else{
			throw new DataNotExistsException("该券不存在");
		}
	}

	@Override
	@Transactional
	public void makeUseCoupon(VerificateCouponInfoVO verificateCouponInfoVO, String accountId) {
		Map<String, Object> map = new HashMap<>();
		map.put("couponCode", verificateCouponInfoVO.getCouponCode());
		List<AccountCouponInfoDTO> list = accountCouponMapperExt.findAccountCouponList(map);
		if(CollectionUtils.isNotEmpty(list)){
			AccountCouponInfoDTO accountCouponInfoVO = list.get(0);
			if(StringUtils.equals("1", accountCouponInfoVO.getStatus())){
				throw new BizException("该券已被使用");
			}else if(StringUtils.equals("-1", accountCouponInfoVO.getStatus())){
				throw new BizException("该券已过期");
			}
			AccountCoupon record = new AccountCoupon();
			record.setStatus(1);
			record.setVerificateTime(new Date());
			record.setVerificateShopId(Long.valueOf(verificateCouponInfoVO.getShopId()));
			AccountCouponExample example = new AccountCouponExample();
			example.createCriteria().andCouponCodeEqualTo(verificateCouponInfoVO.getCouponCode());
			accountCouponMapperExt.updateByExampleSelective(record, example);
		}else{
			throw new BizException("该券不存在");
		}
	}

	@Override
	public AccountCouponInfoDTO findCouponInfoByAccountCouponCode(CouponVO couponVO) {
		AccountCouponInfoDTO accountCouponInfoVO = null;
		Map<String, Object> map = new HashMap<>();
		map.put("couponCode", couponVO.getCouponCode());
		List<AccountCouponInfoDTO> list = accountCouponMapperExt.findAccountCouponList(map);
		if(CollectionUtils.isNotEmpty(list)){
			accountCouponInfoVO = list.get(0);
			if(!StringUtils.equals(accountCouponInfoVO.getBusinessId(), couponVO.getBusinessId())){
				throw new BizException("本店不存在该券");
			}
			
		}else{
			throw new BizException("该券不存在");
		}
		return accountCouponInfoVO;
	}

	@Override
	public PageInfo findVerificateCouponPage(Long shopId, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(findVerificateCouponList(shopId));
	}

	private List<VerificateCouponDTO> findVerificateCouponList(Long shopId) {
		return accountCouponMapperExt.findVerificateCouponList(shopId);
	}
	
}
