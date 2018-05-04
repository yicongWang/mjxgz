package com.zhiyi.mjxgz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhiyi.mjxgz.common.response.CommonResponse;
import com.zhiyi.mjxgz.service.BannerService;
import com.zhiyi.mjxgz.service.common.log.OperationLogType;
import com.zhiyi.mjxgz.service.common.log.OperationnLogAnnotation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 首页接口
 * @author wyc
 *
 */
@Api(description = "首页接口")
@RestController
@RequestMapping("/index")
public class IndexController {
    private Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Autowired BannerService bannerService;
    
    @ApiOperation(value = "获取首页Banner")
    @RequestMapping(value = "/findIndexBanner", method = RequestMethod.GET)
    public CommonResponse findIndexBanner(){
    	//首页banner
    	return CommonResponse.success(bannerService.findBannerByPosition(0));
    }
    
}
