package com.zhiyi.mjxgz.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhiyi.mjxgz.common.exception.BizException;
import com.zhiyi.mjxgz.dao.ex.AccountMapperExt;
import com.zhiyi.mjxgz.dao.ex.AccountVipRecordMapperExt;
import com.zhiyi.mjxgz.dao.ex.ActivationCodeMapperExt;
import com.zhiyi.mjxgz.model.AccountVipRecord;
import com.zhiyi.mjxgz.model.AccountVipRecordExample;
import com.zhiyi.mjxgz.model.ActivationCode;
import com.zhiyi.mjxgz.model.ActivationCodeExample;
import com.zhiyi.mjxgz.model.ActivationCodeExample.Criteria;
import com.zhiyi.mjxgz.service.ActivationCodeService;
import com.zhiyi.mjxgz.util.DateUtil;
import com.zhiyi.mjxgz.util.Md5Utils;
import com.zhiyi.mjxgz.util.RandomStr;
import com.zhiyi.mjxgz.vo.ActiveInfoVO;

/**
 * 区域
 * @author wyc
 *
 */
@Service
public class ActivationCodeServiceImpl implements ActivationCodeService {

    private Logger logger = LoggerFactory.getLogger(ActivationCodeServiceImpl.class);
    
    @Autowired
    private ActivationCodeMapperExt activationCodeMapperExt;
    @Autowired
    private AccountMapperExt accountMapperExt;
    
    @Autowired
    private AccountVipRecordMapperExt accountVipRecordMapperExt;
	@Override
	@Transactional
	public void useActiveCode(ActiveInfoVO activeInfoVO, String accountId) {
		synchronized (accountId) {
			ActivationCodeExample  example = new ActivationCodeExample();
			Criteria criteria = example.createCriteria();
			criteria.andCodeEqualTo(activeInfoVO.getCode());
			List<ActivationCode> activationCodeList = activationCodeMapperExt.selectByExample(example);
			if(CollectionUtils.isNotEmpty(activationCodeList)){
				ActivationCode activationCode = activationCodeList.get(0);
				//该批的激活码是否已使用过
				if(null != activationCode.getBatch()){
					AccountVipRecordExample accountVipRecordExample = new AccountVipRecordExample();
					accountVipRecordExample.createCriteria().andAccountIdEqualTo(accountId).andBatchEqualTo(activationCode.getBatch());
					List<AccountVipRecord> accountVipRecordList = accountVipRecordMapperExt.selectByExample(accountVipRecordExample);
					if(CollectionUtils.isNotEmpty(accountVipRecordList)){
						throw new BizException("该批次的激活码不能重复使用");
					}
				}
				
				if(activationCode.getStatus() != 0){
					throw new BizException("激活码已被使用,不能重复使用");
				}
				
				Date current = new Date();
				if(activationCode.getExpiryTime() != null && current.getTime() >= activationCode.getExpiryTime().getTime()){
					throw new BizException("激活码已过期");
				}
				
				Map<String,Object> map = new HashMap<>();
				map.put("days", activationCode.getDay());
				map.put("accountId", accountId);
				accountMapperExt.updateAccountVipExpireTime(map);
				
				AccountVipRecord accountVipRecord = new AccountVipRecord();
				accountVipRecord.setAccountId(accountId);
				accountVipRecord.setCreateTime(new Date());
				accountVipRecord.setCode(activeInfoVO.getCode());
				accountVipRecord.setRemark("使用了激活码"+activeInfoVO.getCode()+"续费"+activationCode.getDay()+"天");
				accountVipRecord.setType(2);
				accountVipRecord.setBatch(activationCode.getBatch());
				accountVipRecordMapperExt.insert(accountVipRecord);
				
				ActivationCode activationCodeUpdate = new ActivationCode();
				activationCodeUpdate.setId(activationCode.getId());
				activationCodeUpdate.setStatus(1);
				activationCodeMapperExt.updateByPrimaryKeySelective(activationCodeUpdate);
			}else{
				throw new BizException("激活码错误");
			}
		}
	}
	
	@Override
	@Transactional
	public void createActivationCode(int batch,int number){
		ActivationCode activationCodeUpdate = null;
		for (int i = 0; i < number; i++) {
		   activationCodeUpdate = new ActivationCode();
			activationCodeUpdate.setStatus(0);
			activationCodeUpdate.setCode(Md5Utils.hash(RandomStr.randomStr(6)+DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss")).toUpperCase().substring(0, 20));
			activationCodeUpdate.setDay(30);
			activationCodeUpdate.setExpiryTime(DateUtil.strToDate("2018-10-01", "yyyy-MM-dd"));
			activationCodeUpdate.setType(5);
			activationCodeUpdate.setBatch(batch);
			activationCodeMapperExt.insert(activationCodeUpdate);
		}
	}
	
}
