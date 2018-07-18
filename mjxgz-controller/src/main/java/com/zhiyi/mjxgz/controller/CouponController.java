   package com.zhiyi.mjxgz.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhiyi.mjxgz.common.response.CommonResponse;
import com.zhiyi.mjxgz.common.response.PageResponse;
import com.zhiyi.mjxgz.common.response.ResponseCode;
import com.zhiyi.mjxgz.controller.common.AccessRequired;
import com.zhiyi.mjxgz.controller.common.CurrentRedisUserData;
import com.zhiyi.mjxgz.dto.AccountCouponInfoDTO;
import com.zhiyi.mjxgz.dto.GoodsDTO;
import com.zhiyi.mjxgz.dto.RedisUserData;
import com.zhiyi.mjxgz.service.AccountCouponService;
import com.zhiyi.mjxgz.util.DateUtil;
import com.zhiyi.mjxgz.vo.CouponVO;
import com.zhiyi.mjxgz.vo.VerificateCouponInfoVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author wyc
 *
 */
@AccessRequired
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
            logger.error("----findAccountCouponList---error:"+e.getMessage(),e);
        }
        return commonResponse;
    }
      
    @ApiOperation(value = "移除用户券")
    @RequestMapping(value = "/removeAccountCoupon", method = RequestMethod.GET)
    public CommonResponse removeAccountCoupon(@ApiParam(name="accountCouponId",value="用户券ID") @RequestParam(name="accountCouponId") Long accountCouponId) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            commonResponse.setCode(ResponseCode.SUCCESS);
            accountCouponService.updateAccountCoupon(accountCouponId,2);
        } catch (Exception e) {
            commonResponse.setCode(ResponseCode.SERVER_ERROR);
            commonResponse.setMsg("请求数据异常,请稍后");
            logger.error("----removeAccountCoupon---error:"+e.getMessage(),e);
        }
        return commonResponse;
    }
    
    @ApiOperation(value = "领取商品券")
    @RequestMapping(value = "/takeCoupon", method = RequestMethod.GET)
    public CommonResponse takeCoupon(@ApiParam(name="couponId",value="商品券ID")@RequestParam(name = "couponId") Long couponId, @RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData) {
        CommonResponse commonResponse = new CommonResponse();
        try {
        	long time = new Date().getTime();
        	if(null == redisUserData.getExpireTime()){
        		commonResponse.setMsg("您还未开通会员");
        		commonResponse.setCode(ResponseCode.VIP_EXPIR);
        	}else if(DateUtil.strToDate(redisUserData.getExpireTime(), DateUtil.DATETIME_DEFAULT_FORMAT).getTime() < time){
        		commonResponse.setCode(ResponseCode.VIP_EXPIR);
        		commonResponse.setMsg("您的会员已过期,请续费");
        	}else{
        		accountCouponService.takeCoupon(couponId,redisUserData.getId());
        		commonResponse.setCode(ResponseCode.SUCCESS);
        		commonResponse.setMsg("领取成功");
        	}
           
        } catch (Exception e) {
            commonResponse.setCode(ResponseCode.SERVER_ERROR);
            commonResponse.setMsg("请求数据异常,请稍后");
            logger.error("----takeCoupon---error:"+e.getMessage(),e);
        }
        return commonResponse;
    }
    
    @ApiOperation(value = "核销用户商品券")
    @RequestMapping(value = "/makeUseCoupon", method = RequestMethod.POST)
    public CommonResponse makeUseCoupon(@RequestBody VerificateCouponInfoVO verificateCouponInfoVO,@CurrentRedisUserData RedisUserData redisUserData) {
        CommonResponse commonResponse = new CommonResponse();
        try {
        		accountCouponService.makeUseCoupon(verificateCouponInfoVO,redisUserData.getId());
        		commonResponse.setCode(ResponseCode.SUCCESS);
        		commonResponse.setMsg("核销成功");
        } catch (Exception e) {
            commonResponse.setCode(ResponseCode.SERVER_ERROR);
            commonResponse.setMsg("请求数据异常,请稍后");
            logger.error("----makeUseCoupon---error:"+e.getMessage(),e);
        }
        return commonResponse;
    }
    
    @ApiOperation(value = "通过用户券码获取券的信息",response = AccountCouponInfoDTO.class)
    @RequestMapping(value = "/findCouponInfoByAccountCouponCode", method = RequestMethod.POST)
    public CommonResponse findCouponInfoByAccountCouponCode(@RequestBody CouponVO couponVO, @RequestHeader String access_token,@CurrentRedisUserData RedisUserData redisUserData) {
        CommonResponse commonResponse = new CommonResponse();
        try {
        		commonResponse.setData(accountCouponService.findCouponInfoByAccountCouponCode(couponVO));
        		commonResponse.setCode(ResponseCode.SUCCESS);
        		commonResponse.setMsg("success");
        } catch (Exception e) {
            commonResponse.setCode(ResponseCode.SERVER_ERROR);
            commonResponse.setMsg("请求数据异常,请稍后");
            logger.error("----findCouponInfoByAccountCouponCode---error:"+e.getMessage(),e);
        }
        return commonResponse;
    }
    
    @ApiOperation(value = "店铺分页获取核销记录",response=GoodsDTO.class )
    @RequestMapping(value = "/findVerificateCouponPage/{shopId}", method = RequestMethod.GET)
    public PageResponse findVerificateCouponPage(@ApiParam(value="店铺ID") @RequestParam(name="shopId") Long shopId
 		   ,@RequestParam(name="pageNum",required =false) Integer pageNum ,@RequestParam(name="pageSize",required =false) Integer pageSize) throws Exception{
 	   
 	   if(pageNum == null){
 		   pageNum = 1;
 	   }
 	   
 	   if(pageSize == null){
 		   pageSize = 20;
 	   }
 	   
        PageResponse response = new PageResponse(ResponseCode.SUCCESS, "OK");
        response.setData(accountCouponService.findVerificateCouponPage(shopId, pageNum, pageSize));
    	return response;
    }
    
}
