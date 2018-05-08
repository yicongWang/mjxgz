package com.zhiyi.mjxgz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhiyi.mjxgz.common.response.CommonResponse;
import com.zhiyi.mjxgz.common.response.PageResponse;
import com.zhiyi.mjxgz.common.response.ResponseCode;
import com.zhiyi.mjxgz.dto.GoodsDTO;
import com.zhiyi.mjxgz.model.Banner;
import com.zhiyi.mjxgz.model.GoodsCategory;
import com.zhiyi.mjxgz.service.BannerService;
import com.zhiyi.mjxgz.service.GoodsCategoryService;
import com.zhiyi.mjxgz.service.GoodsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

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
    
    @Autowired GoodsCategoryService goodsCategoryService;
    
    @Autowired GoodsService goodsService;
    
    @ApiOperation(value = "获取首页Banner",response=Banner.class)
    @RequestMapping(value = "/findIndexBanner", method = RequestMethod.GET)
    public CommonResponse findIndexBanner(){
    	//首页banner
    	return CommonResponse.success(bannerService.findBannerByPosition(0));
    }
    
    
    @ApiOperation(value = "获取分类列表",response=GoodsCategory.class)
    @RequestMapping(value = "/findCategoryList", method = RequestMethod.GET)
    public CommonResponse findCategoryList(){
    	return CommonResponse.success(goodsCategoryService.findGoodsCategoryByParentId(0));
    }
   
  
   @ApiOperation(value = "分页获取首页商品列表",response=GoodsDTO.class )
   @RequestMapping(value = "/findIndexGoodsPage", method = RequestMethod.GET)
   public PageResponse findIndexGoodsPage(@ApiParam(value="分类ID")Long categoryId,@ApiParam(value="商家名称 (支持模糊查询)") String businessName,Integer pageNum ,Integer pageSize ) throws Exception{
	   
	   if(pageNum == null){
		   pageNum = 1;
	   }
	   
	   if(pageSize == null){
		   pageSize = 20;
	   }
	   
       PageResponse response = new PageResponse(ResponseCode.SUCCESS, "OK");
       response.setData(goodsService.findGoodsPage(categoryId, businessName, pageNum, pageSize));
   	return response;
   }
   
}
