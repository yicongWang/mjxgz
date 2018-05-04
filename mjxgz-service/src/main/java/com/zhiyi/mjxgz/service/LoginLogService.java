package com.zhiyi.mjxgz.service;

import com.github.pagehelper.PageInfo;
import com.zhiyi.mjxgz.dto.LoginLogDTO;
import com.zhiyi.mjxgz.model.LoginLog;

/**
 * 登录日志Service
 * Created by DW on 2017/6/28.
 */
public interface LoginLogService {


    /**
     * 新增
     * @param loginLog
     * @return
     */
    int add(LoginLog loginLog);

    /**
     * 分页查询登录日志
     * @param loginLog
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<LoginLogDTO> findPage(LoginLogDTO loginLog, Integer pageNum, Integer pageSize);

}
