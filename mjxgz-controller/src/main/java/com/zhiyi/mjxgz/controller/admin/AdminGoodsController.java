   package com.zhiyi.mjxgz.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
 * 后台商品控制器
 * @author wyc
 *
 */
@Api(description = "后台商品控制器")
@RestController
@RequestMapping("/admin/goods")
public class AdminGoodsController {
    private Logger logger = LoggerFactory.getLogger(AdminGoodsController.class);
    @Value("${web.upload-path}")
    private String path;
    @Autowired
    private GoodsImgService goodsImgService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private BusinessShopService businessShopService;
    @Autowired
    private BusinessCouponService businessCouponService;
   
}
