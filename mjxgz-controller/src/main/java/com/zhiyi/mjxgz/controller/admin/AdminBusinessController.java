   package com.zhiyi.mjxgz.controller.admin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zhiyi.mjxgz.common.response.CommonResponse;
import com.zhiyi.mjxgz.common.response.ResponseCode;
import com.zhiyi.mjxgz.model.Business;
import com.zhiyi.mjxgz.model.BusinessImg;
import com.zhiyi.mjxgz.model.BusinessShop;
import com.zhiyi.mjxgz.service.BusinessImgService;
import com.zhiyi.mjxgz.service.BusinessService;
import com.zhiyi.mjxgz.service.BusinessShopService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 后台商家控制器
 * @author wyc
 *
 */
@Api(description = "后台商家控制器")
@RestController
@RequestMapping("/admin/business")
public class AdminBusinessController {
    private Logger logger = LoggerFactory.getLogger(AdminBusinessController.class);
    @Value("${web.upload-path}")
    private String path;
    @Autowired
    private BusinessService businessService;
    
    @Autowired		
    private BusinessShopService businessShopService;
   
    @Autowired
    private BusinessImgService businessImgService;
    
    /**
     * 保存商家
     * @param 
     * @return
     */
    @ApiOperation(value = "保存商家")
    @RequestMapping(value = "/saveBusiness", method = RequestMethod.POST)
    public CommonResponse saveBusiness(@RequestBody Business business) {
        CommonResponse commonResponse = new CommonResponse();
        try {
        	businessService.saveBusiness(business);
            commonResponse.setCode(ResponseCode.SUCCESS);
        } catch (Exception e) {
            commonResponse.setCode(ResponseCode.SERVER_ERROR);
            commonResponse.setMsg("请求数据异常,请稍后");
            logger.error("----saveBusiness---error:"+e.getMessage(),e);
        }
        return commonResponse;
    }
  
    @ApiOperation(value = "保存商家店铺")
    @RequestMapping(value = "/saveBusinessShop", method = RequestMethod.POST)
    public CommonResponse saveBusiness(@RequestBody BusinessShop businessShop) {
        CommonResponse commonResponse = new CommonResponse();
        try {
        	businessShopService.saveBusinessShop(businessShop);
            commonResponse.setCode(ResponseCode.SUCCESS);
        } catch (Exception e) {
            commonResponse.setCode(ResponseCode.SERVER_ERROR);
            commonResponse.setMsg("请求数据异常,请稍后");
            logger.error("----saveBusiness---error:"+e.getMessage(),e);
        }
        return commonResponse;
    }
  
    @ApiOperation(value = "保存商家Banner")
    @RequestMapping(value = "/saveBusinessImg", method = RequestMethod.POST)
    public CommonResponse saveBusinessImg(@RequestBody BusinessImg businessImg,@RequestParam("file") MultipartFile file) {
        CommonResponse commonResponse = new CommonResponse();
        try {
        	/*//通过输入流获取图片数据 
        	InputStream inStream = file.getInputStream(); 
        	//得到图片的二进制数据，以二进制封装得到数据，具有通用性 
        	byte[] data = readInputStream(inStream); 
        	//new一个文件对象用来保存图片，默认保存当前工程根目录 
        	File imageFile = new File(path+"1.png"); 
        	//创建输出流 
        	FileOutputStream outStream = new FileOutputStream(imageFile); 
        	//写入数据 
        	outStream.write(data); 
        	//关闭输出流 
        	outStream.close(); */
        	//businessImgService.saveBusinessImg(businessImg);
            commonResponse.setCode(ResponseCode.SUCCESS);
        } catch (Exception e) {
            commonResponse.setCode(ResponseCode.SERVER_ERROR);
            commonResponse.setMsg("请求数据异常,请稍后");
            logger.error("----saveBusinessImg---error:"+e.getMessage(),e);
        }
        return commonResponse;
    }
    
    public static byte[] readInputStream(InputStream inStream) throws Exception{ 
    	ByteArrayOutputStream outStream = new ByteArrayOutputStream(); 
    	//创建一个Buffer字符串 
    	byte[] buffer = new byte[1024]; 
    	//每次读取的字符串长度，如果为-1，代表全部读取完毕 
    	int len = 0; 
    	//使用一个输入流从buffer里把数据读取出来 
    	while( (len=inStream.read(buffer)) != -1 ){ 
    	//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度 
    	outStream.write(buffer, 0, len); 
    	} 
    	//关闭输入流 
    	inStream.close(); 
    	//把outStream里的数据写入内存 
    	return outStream.toByteArray(); 
    	} 
}
