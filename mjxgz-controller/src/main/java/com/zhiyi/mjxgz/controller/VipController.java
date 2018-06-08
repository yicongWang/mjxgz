package com.zhiyi.mjxgz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhiyi.mjxgz.common.response.CommonResponse;
import com.zhiyi.mjxgz.model.VipMeal;
import com.zhiyi.mjxgz.service.VipMealService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * VIP套餐
 * @author wyc
 *
 */
@Api(description = "VIP套餐")
@RestController
@RequestMapping("/vip")
public class VipController {
    private Logger logger = LoggerFactory.getLogger(VipController.class);
    
    @Autowired private VipMealService vipMealService;
    
    @ApiOperation(value = "获取Vip套餐列表",response=VipMeal.class)
    @RequestMapping(value = "/findVipMealList", method = RequestMethod.GET)
    public CommonResponse findVipMealList(){
    	return CommonResponse.success(vipMealService.findVipMealList());
    }
}
