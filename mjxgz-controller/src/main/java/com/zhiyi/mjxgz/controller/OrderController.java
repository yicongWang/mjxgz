package com.zhiyi.mjxgz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhiyi.mjxgz.common.response.CommonResponse;
import com.zhiyi.mjxgz.controller.common.AccessRequired;
import com.zhiyi.mjxgz.controller.common.CurrentRedisUserData;
import com.zhiyi.mjxgz.dto.RedisUserData;
import com.zhiyi.mjxgz.service.VipOrderService;
import com.zhiyi.mjxgz.vo.MealOrderInfoVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *订单接口
 * @author wyc
 */

@Api(description = "订单接口")
@RestController
@RequestMapping("/order")
public class OrderController {
    private Logger logger = LoggerFactory.getLogger(OrderController.class);
    
    @Autowired private VipOrderService vipOrderService;
    @AccessRequired
    @ApiOperation(value = "购买会员套餐下单,返回订单号")
    @RequestMapping(value = "/buyorder", method = RequestMethod.POST)
    public CommonResponse buyorder(@RequestBody MealOrderInfoVO mealOrderInfoVO,@CurrentRedisUserData RedisUserData redisUserData,  @RequestHeader String access_token){
    	return CommonResponse.success(vipOrderService.buyorder(mealOrderInfoVO.getVipMealId(),redisUserData.getId()));
    }
    @AccessRequired
    @ApiOperation(value = "调起支付")
    @RequestMapping(value = "/getPayInformation/{orderNumber}", method = RequestMethod.POST)
    public CommonResponse getPayInformation(@PathVariable String orderNumber,@CurrentRedisUserData RedisUserData redisUserData,  @RequestHeader String access_token) throws Exception{
    	return CommonResponse.success(vipOrderService.getPayInformation(redisUserData.getOpenid(), orderNumber, redisUserData.getRequestIP()));
    }
    
}
