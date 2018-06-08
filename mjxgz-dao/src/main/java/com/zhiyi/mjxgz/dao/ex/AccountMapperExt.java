package com.zhiyi.mjxgz.dao.ex;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zhiyi.mjxgz.dao.AccountMapper;

/**
 * 用户信息Mapper
 * Created by DW on 2017/6/27.
 */
@Mapper
public interface AccountMapperExt extends AccountMapper{
  
	int updateAccountVipExpireTime(Map<String,Object> map);
}
