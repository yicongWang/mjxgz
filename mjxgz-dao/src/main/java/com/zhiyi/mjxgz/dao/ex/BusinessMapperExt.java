package com.zhiyi.mjxgz.dao.ex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.zhiyi.mjxgz.dao.BusinessMapper;
import com.zhiyi.mjxgz.vo.BusinessInfoVO;

/**
 * 商家
 * @author wyc
 *
 */
@Mapper
public interface BusinessMapperExt  extends BusinessMapper{

	List<BusinessInfoVO> findBusinessInfoByBusinessId(Map<String, Object> map);
}
