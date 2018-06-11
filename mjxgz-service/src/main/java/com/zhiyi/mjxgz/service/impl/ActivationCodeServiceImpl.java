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

import com.zhiyi.mjxgz.common.exception.DataAlreadyExistsException;
import com.zhiyi.mjxgz.common.exception.DataNotExistsException;
import com.zhiyi.mjxgz.dao.ex.AccountMapperExt;
import com.zhiyi.mjxgz.dao.ex.AccountVipRecordMapperExt;
import com.zhiyi.mjxgz.dao.ex.ActivationCodeMapperExt;
import com.zhiyi.mjxgz.model.AccountVipRecord;
import com.zhiyi.mjxgz.model.ActivationCode;
import com.zhiyi.mjxgz.model.ActivationCodeExample;
import com.zhiyi.mjxgz.model.ActivationCodeExample.Criteria;
import com.zhiyi.mjxgz.service.ActivationCodeService;
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
	public void useActiveCode(ActiveInfoVO activeInfoVO, String accountId) {
		ActivationCodeExample  example = new ActivationCodeExample();
		Criteria criteria = example.createCriteria();
		criteria.andCodeEqualTo(activeInfoVO.getCode());
		List<ActivationCode> activationCodeList = activationCodeMapperExt.selectByExample(example);
		if(CollectionUtils.isNotEmpty(activationCodeList)){
			ActivationCode activationCode = activationCodeList.get(0);
			
			if(activationCode.getStatus() == 1){
				throw new DataAlreadyExistsException("激活码已被使用,不能重复使用");
			}
			
			Date current = new Date();
			if(activationCode.getExpiryTime() != null && current.getTime() >= activationCode.getExpiryTime().getTime()){
				throw new DataAlreadyExistsException("激活码已过期");
			}
			Map<String,Object> map = new HashMap<>();
			map.put("days", activationCode.getDay());
			accountMapperExt.updateAccountVipExpireTime(map);
			
			AccountVipRecord accountVipRecord = new AccountVipRecord();
			accountVipRecord.setAccountId(accountId);
			accountVipRecord.setCode(activeInfoVO.getCode());
			accountVipRecord.setRemark("使用了激活码"+activeInfoVO.getCode()+"续费"+activationCode.getDay()+"天");
			accountVipRecord.setType(2);
			accountVipRecordMapperExt.insert(accountVipRecord);
		}else{
			throw new DataNotExistsException("激活码错误");
		}
	}


	
}
