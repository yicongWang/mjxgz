package com.zhiyi.mjxgz.controller;


import com.zhiyi.mjxgz.common.response.PageResponse;
import com.zhiyi.mjxgz.controller.common.AccessRequired;
import com.zhiyi.mjxgz.dto.LoginLogDTO;
import com.zhiyi.mjxgz.service.LoginLogService;
import com.zhiyi.mjxgz.service.common.log.OperationLogType;
import com.zhiyi.mjxgz.service.common.log.OperationnLogAnnotation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录日志信息管理接口
 * Created by DW on 2017/6/28.
 */
@AccessRequired
@RestController
@RequestMapping("/loginLogs")
@Api(value = "LoginLogController",description = "登录日志信息管理接口")
public class LoginLogController {
    private static Logger logger = LoggerFactory.getLogger(LoginLogController.class);

    @Autowired
    private LoginLogService loginLogService;


    /**
     * 获取管理员登录日志列表集合
     * @param loginLogDTO   loginLog对象
     * @param pageNum   当前页数（不传值，默认第一页）
     * @param pageSize  每页显示条数（不传值，默认没有10条）
     * @return  请求响应
     */
    @ApiOperation(value = "查询登录日志列表接口",notes="分页查询登录日志列表接口")
    @RequestMapping(method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "分页查询登录日志", moduleName = "登录日志信息",operationLogType = OperationLogType.QUERY)
    public PageResponse find(LoginLogDTO loginLogDTO,
//                             @CurrentRedisUserData RedisUserData redisUserData,
                             @RequestParam(required = false) Integer pageNum,
                             @RequestParam(required = false) Integer pageSize){
        return PageResponse.success(loginLogService.findPage(loginLogDTO, pageNum, pageSize));
    }
}
