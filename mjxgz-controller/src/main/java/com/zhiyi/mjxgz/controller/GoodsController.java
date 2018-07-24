   package com.zhiyi.mjxgz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhiyi.mjxgz.common.exception.BizException;
import com.zhiyi.mjxgz.common.exception.DataNotExistsException;
import com.zhiyi.mjxgz.common.response.CommonResponse;
import com.zhiyi.mjxgz.common.response.ResponseCode;
import com.zhiyi.mjxgz.dto.GoodsDTO;
import com.zhiyi.mjxgz.service.BusinessCouponService;
import com.zhiyi.mjxgz.service.BusinessShopService;
import com.zhiyi.mjxgz.service.GoodsImgService;
import com.zhiyi.mjxgz.service.GoodsService;
import com.zhiyi.mjxgz.vo.GoodsInfoVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 商品控制器
 * @author wyc
 *
 */
@Api(description = "商品控制器")
@RestController
@RequestMapping("/goods")
public class GoodsController {
    private Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsImgService goodsImgService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private BusinessShopService businessShopService;
    @Autowired
    private BusinessCouponService businessCouponService;
    /**
     * 根据商品Id获取商品Banner图列表
     * @param goodsId
     * @return
     */
    @ApiOperation(value = "根据商品Id获取商品Banner图列表")
    @RequestMapping(value = "/goodsBanner/{goodsId}", method = RequestMethod.GET)
    public CommonResponse goodsBanner(@PathVariable Long goodsId) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            commonResponse.setCode(ResponseCode.SUCCESS);
            commonResponse.setData(goodsImgService.findGoodsImgList(goodsId));
        }catch (BizException e1) {
            commonResponse.setCode(ResponseCode.PARAMETER_ERROR);
            commonResponse.setMsg(e1.getMessage());
            logger.error("----takeCoupon---error:"+e1.getMessage(),e1);
        } catch (Exception e) {
            commonResponse.setCode(ResponseCode.SERVER_ERROR);
            commonResponse.setMsg("请求数据异常,请稍后");
            logger.error("----goodsBanner---error:"+e.getMessage(),e);
        }
        return commonResponse;
    }
   
    
  
    /**
     * 根据商品Id获取商品Banner图列表
     * @param goodsId
     * @return
     */
    @ApiOperation(value = "根据商家Id获取商品列表(服务项目)",response = GoodsDTO.class)
    @RequestMapping(value = "/findBusinessGoodsList/{businessId}", method = RequestMethod.GET)
    public CommonResponse findBusinessGoodsList(@PathVariable Long businessId,@ApiParam(value="排除的商品Id",name="excludeGoodsId")@RequestParam(name= "excludeGoodsId",required =false) String excludeGoodsId) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            commonResponse.setCode(ResponseCode.SUCCESS);
            commonResponse.setData(goodsService.findBusinessGoodsList(businessId,excludeGoodsId));
        } catch (Exception e) {
            commonResponse.setCode(ResponseCode.SERVER_ERROR);
            commonResponse.setMsg("请求数据异常,请稍后");
            logger.error("----goodsBanner---error:"+e.getMessage(),e);
        }
        return commonResponse;
    }
    
    /**
     * 获取商品详情
     * @param goodsId
     * @return
     */
    @ApiOperation(value = "获取商品详情", response = GoodsInfoVO.class)
    @RequestMapping(value = "/findGoodsInfoByGoodsId/{goodsId}", method = RequestMethod.GET)
    public CommonResponse findGoodsInfoByGoodsId(@PathVariable Long goodsId) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            commonResponse.setCode(ResponseCode.SUCCESS);
            GoodsInfoVO goodsInfoVO = goodsService.findGoodsInfoByGoodsId(goodsId);
            if(null != goodsInfoVO){
            	goodsInfoVO.setGoodsDetail(goodsService.findGoodsDetailList(goodsId));
            	goodsInfoVO.setShopList(businessShopService.findShopInfoList(goodsInfoVO.getBusinessId()));
                goodsInfoVO.setCouponList(businessCouponService.findCouponByGoodsId(goodsInfoVO.getBusinessId(),goodsId));//商品券
            }else{
            	throw new  DataNotExistsException("商品不存在");
            }
            commonResponse.setData(goodsInfoVO);
        } catch (Exception e) {
            commonResponse.setCode(ResponseCode.SERVER_ERROR);
            commonResponse.setMsg("请求数据异常,请稍后");
            logger.error("----goodsBanner---error:"+e.getMessage(),e);
        }
        return commonResponse;
    }
    
}
