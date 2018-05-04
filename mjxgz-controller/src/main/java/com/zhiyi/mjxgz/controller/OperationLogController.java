package com.zhiyi.mjxgz.controller;


import com.zhiyi.mjxgz.common.response.PageResponse;
import com.zhiyi.mjxgz.common.response.ResponseCode;
import com.zhiyi.mjxgz.controller.common.AccessRequired;
import com.zhiyi.mjxgz.controller.common.CurrentRedisUserData;
import com.zhiyi.mjxgz.dto.OperationLogDTO;
import com.zhiyi.mjxgz.dto.RedisUserData;
import com.zhiyi.mjxgz.service.OperationLogService;
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
 * 操作日志信息管理接口
 * Created by DW on 2017/6/27.
 */
@AccessRequired
@RestController
@RequestMapping("/operationLogs")
@Api(value = "OperationLogController",description = "操作日志信息管理接口")
public class OperationLogController {
    private static Logger logger = LoggerFactory.getLogger(OperationLogController.class);

    @Autowired
    private OperationLogService operationLogService;

    /**
     * 获取操作日志列表集合
     * @param operationLogDTO   operationLog对象
     * @param pageNum   当前页数（不传值，默认第一页）
     * @param pageSize  每页显示条数（不传值，默认没有10条）
     * @return  请求响应
     */
    @ApiOperation(value = "查询操作日志列表接口",notes="分页查询操作日志列表接口")
    @RequestMapping(method = RequestMethod.GET)
    @OperationnLogAnnotation(description = "分页查询操作日志信息", moduleName = "操作日志信息",operationLogType = OperationLogType.QUERY)
    public PageResponse find(OperationLogDTO operationLogDTO,
                             @CurrentRedisUserData RedisUserData redisUserData,
                             @RequestParam(required = false) Integer pageNum,
                             @RequestParam(required = false) Integer pageSize){
        PageResponse pageResponse = new PageResponse();
        try{
            pageResponse.setData(operationLogService.findPage(operationLogDTO, pageNum, pageSize));
            pageResponse.setCode(ResponseCode.SUCCESS);
            pageResponse.setMsg("query operationLog list success");
            logger.info(pageResponse.getMsg());
        }catch (Exception e){
            logger.error("query operationLog list failed",e);
            pageResponse.setCode(ResponseCode.SERVER_ERROR);
            pageResponse.setMsg("service error");
        }
        return pageResponse;
    }


}
