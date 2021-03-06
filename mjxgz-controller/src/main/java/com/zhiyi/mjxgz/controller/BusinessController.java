   package com.zhiyi.mjxgz.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhiyi.mjxgz.common.exception.BizException;
import com.zhiyi.mjxgz.common.exception.DataNotExistsException;
import com.zhiyi.mjxgz.common.response.CommonResponse;
import com.zhiyi.mjxgz.common.response.PageResponse;
import com.zhiyi.mjxgz.common.response.ResponseCode;
import com.zhiyi.mjxgz.service.BusinessImgService;
import com.zhiyi.mjxgz.service.BusinessService;
import com.zhiyi.mjxgz.service.BusinessShopService;
import com.zhiyi.mjxgz.util.RedisUtil;
import com.zhiyi.mjxgz.util.WXPayUtil;
import com.zhiyi.mjxgz.vo.BusinessInfoVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 商品控制器
 * @author wyc
 *
 */
@Api(description = "商家控制器")
@RestController
@RequestMapping("/business")
public class BusinessController {
    private Logger logger = LoggerFactory.getLogger(BusinessController.class);
    
    @Autowired
    private BusinessService businessService;
    
    @Autowired		
    private BusinessShopService businessShopService;
   
    @Autowired
    private BusinessImgService businessImgService;
    @Value("${web.upload-path}")
  	private String uploadPath;
	@Value("${app_id}")
	private String app_id;  // 账号信息 
	@Value("${app_secret}")
	private String app_secret;  // 账号信息
	@Autowired
	private RedisUtil redisUtil;
    /**
     * 根据商家ID获取门店信息列表
     * @param goodsId
     * @return
     */
    @ApiOperation(value = "根据商家ID获取门店信息列表")
    @RequestMapping(value = "/findShopInfoList/{businessId}", method = RequestMethod.GET)
    public CommonResponse findShopInfoList(@PathVariable Long businessId) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            commonResponse.setCode(ResponseCode.SUCCESS);
            commonResponse.setData(businessShopService.findShopInfoList(businessId));
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
    @ApiOperation(value = "根据商家Id获取商家Banner图列表")
    @RequestMapping(value = "/businessBanner/{businessId}", method = RequestMethod.GET)
    public CommonResponse businessBanner(@PathVariable Long businessId) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            commonResponse.setCode(ResponseCode.SUCCESS);
            commonResponse.setData(businessImgService.findBusinessImgList(businessId));
        } catch (BizException e1) {
            commonResponse.setCode(ResponseCode.PARAMETER_ERROR);
            commonResponse.setMsg(e1.getMessage());
            logger.error("----takeCoupon---error:"+e1.getMessage(),e1);
        }catch (Exception e) {
            commonResponse.setCode(ResponseCode.SERVER_ERROR);
            commonResponse.setMsg("请求数据异常,请稍后");
            logger.error("----businessBanner---error:"+e.getMessage(),e);
        }
        return commonResponse;
    }
    
    
    /**
     * 根据商家Id获取商家详情
     * @param goodsId
     * @return
     */
    @ApiOperation(value = "根据商家Id获取商家详情",response = BusinessInfoVO.class )
    @RequestMapping(value = "/findBusinessInfo/{businessId}", method = RequestMethod.GET)
    public CommonResponse findBusinessInfo(@PathVariable Long businessId) {
        CommonResponse commonResponse = new CommonResponse();
            commonResponse.setCode(ResponseCode.SUCCESS);
            BusinessInfoVO businessInfoVO = businessService.findBusinessInfoByBusinessId(businessId);
            if(null != businessInfoVO){
            	businessInfoVO.setShopList(businessShopService.findShopInfoList(businessId));
            }else{
            	throw new  DataNotExistsException("商家不存在或已下架");
            }
            commonResponse.setData(businessInfoVO);
        return commonResponse;
    }
    
    
    @ApiOperation(value = "分页获取商家列表",response=BusinessInfoVO.class )
    @RequestMapping(value = "/findBusinessInfoPage", method = RequestMethod.GET)
    public PageResponse findBusinessInfoPage(@ApiParam(value="商家名称 (支持模糊查询)")@RequestParam(name="businessName",required =false)  String businessName,
    		@RequestParam(name="pageNum",required =false) Integer pageNum ,
    		@RequestParam(name="pageSize",required =false) Integer pageSize ) throws Exception{
 	   
 	   if(pageNum == null){
 		   pageNum = 1;
 	   }
 	   
 	   if(pageSize == null){
 		   pageSize = 20;
 	   }
 	   
        PageResponse response = new PageResponse(ResponseCode.SUCCESS, "OK");
        
        response.setData(businessService.findusinessInfoPage(businessName, pageNum, pageSize));
    	return response;
    }
    
    
    @ApiOperation(value = "获取商家二维码")
	@RequestMapping(value = "/findBusinessCode", method = RequestMethod.GET)
	public CommonResponse findBusinessCode(
			@ApiParam(value="businessId")@RequestParam(name="businessId",required =true)  String businessId,
			@ApiParam(value="scene")@RequestParam(name="scene",required = true)  String scene,
			@ApiParam(value="path")@RequestParam(name="path",required = true)  String path,
			@ApiParam(value="width")@RequestParam(name="width",required = true)  String width
			) {
			CommonResponse commonResponse = new CommonResponse();
			try {
			
			Map<String, Object> params = new HashMap<>();
			params.put("scene", scene);  //参数
			params.put("path", path); //位置
			params.put("width", width);
			commonResponse.setData("https://www.mjxgz.club/"+businessId+"/code/"+businessService.findCode(app_id, app_secret, params, uploadPath+businessId+"/code/"));
		} catch (BizException e1) {
            commonResponse.setCode(ResponseCode.PARAMETER_ERROR);
            commonResponse.setMsg(e1.getMessage());
            logger.error("----findBusinessCode---error:"+e1.getMessage(),e1);
        }catch (Exception e) {
			logger.error("findBusinessCode failed", e);
			commonResponse.setCode(ResponseCode.SERVER_ERROR);
			commonResponse.setMsg("service error");
		}
		return commonResponse;
	}
    
    
}
