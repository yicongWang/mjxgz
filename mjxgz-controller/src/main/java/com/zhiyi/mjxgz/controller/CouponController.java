   package com.zhiyi.mjxgz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhiyi.mjxgz.common.response.CommonResponse;
import com.zhiyi.mjxgz.common.response.ResponseCode;
import com.zhiyi.mjxgz.service.AccountCouponService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author wyc
 *
 */
@Api(description = "券管理接口")
@RestController
@RequestMapping("/coupon")
public class CouponController {
    private Logger logger = LoggerFactory.getLogger(CouponController.class);

    @Autowired
    private AccountCouponService accountCouponService;	
    
    /**
     * 获取用户券列表
     * @param accountId
     * @return
     */
    @ApiOperation(value = "获取用户券列表")
    @RequestMapping(value = "/findAccountCouponList/{accountId}", method = RequestMethod.GET)
    public CommonResponse findAccountCouponList(@PathVariable String accountId,@ApiParam(name="status",value="用户券状态 0:未使用 1:已使用") @RequestParam(name="status") Integer status) {
        CommonResponse commonResponse = new CommonResponse();
        try {
        	if(status == null){
        		status = 0;
        	}
            commonResponse.setCode(ResponseCode.SUCCESS);
            commonResponse.setData(accountCouponService.findAccountCouponList(accountId,status));
        } catch (Exception e) {
            commonResponse.setCode(ResponseCode.SERVER_ERROR);
            commonResponse.setMsg("请求数据异常,请稍后");
            logger.error("----goodsBanner---error:"+e.getMessage(),e);
        }
        return commonResponse;
    }
    
    
    @ApiOperation(value = "移除用户券")
    @RequestMapping(value = "/removeAccountCoupon", method = RequestMethod.POST)
    public CommonResponse removeAccountCoupon(@ApiParam(name="accountCouponId",value="用户券ID") @RequestParam(name="accountCouponId") Long accountCouponId) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            commonResponse.setCode(ResponseCode.SUCCESS);
            accountCouponService.updateAccountCoupon(accountCouponId,2);
        } catch (Exception e) {
            commonResponse.setCode(ResponseCode.SERVER_ERROR);
            commonResponse.setMsg("请求数据异常,请稍后");
            logger.error("----goodsBanner---error:"+e.getMessage(),e);
        }
        return commonResponse;
    }
}
