package com.zhiyi.mjxgz.dao.ex;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zhiyi.mjxgz.dao.LoginLogMapper;
import com.zhiyi.mjxgz.dto.LoginLogDTO;

/**
 * 登录日志Mapper
 * Created by DW on 2017/6/28.
 */
@Mapper
public interface LoginLogMapperExt extends LoginLogMapper{


    /**
     * 查询登录日志集合
     * @param loginLog
     * @return
     */
    List<LoginLogDTO> findDTO(LoginLogDTO loginLog);
}
