package com.zhiyi.mjxgz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyi.mjxgz.dao.ex.LoginLogMapperExt;
import com.zhiyi.mjxgz.dto.LoginLogDTO;
import com.zhiyi.mjxgz.model.LoginLog;
import com.zhiyi.mjxgz.service.LoginLogService;

/**
 * 登录日志ServiceImpl
 * Created by DW on 2017/6/28.
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {

    @Autowired
    private LoginLogMapperExt loginLogMapperExt;



    @Override
    public int add(LoginLog loginLog) {
        return loginLogMapperExt.insertSelective(loginLog);
    }

    @Override
    public PageInfo<LoginLogDTO> findPage(LoginLogDTO loginLog, Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(loginLogMapperExt.findDTO(loginLog));
    }
}
