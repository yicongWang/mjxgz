package com.zhiyi.mjxgz.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zhiyi.mjxgz.service.AccountService;
import com.zhiyi.mjxgz.service.ActivationCodeService;
import com.zhiyi.mjxgz.service.BusinessCouponService;
import com.zhiyi.mjxgz.util.MailUtill;

/**
 * Created by DW on 2017/6/26.
 */
@RestController
public class TestController {
	 @Autowired
	 private MailUtill mailUtill; 
 
    @Autowired
    private AccountService accountService;
    @Autowired
    private  ActivationCodeService activationCodeService;
    @Autowired
    private BusinessCouponService businessCouponService;
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String sayHello(){
        return "hello !";
    }
    
    @RequestMapping(value = "/sendemail",method = RequestMethod.GET)  
    public String sendEmail()  
    {  
    	//mailUtill.sendEmail("499345857@qq.com", "主题", "内容");
        return  "success";
    }  
    
    @RequestMapping(value = "/insertAll",method = RequestMethod.GET)  
    public String insertAll(@RequestParam(name ="keys") String keys)  
    {  
    	if(StringUtils.equals(keys, "123456")){
    		businessCouponService.insertAll();
    	}
    	//mailUtill.sendEmail("499345857@qq.com", "主题", "内容");
        return  "success";
    }
    
    @RequestMapping(value = "/createActivationCode",method = RequestMethod.GET)  
    public String createActivationCode(@RequestParam(name ="keys") String keys,@RequestParam(name ="batch") Integer batch,@RequestParam(name ="number") Integer number)  
    {  
    	if(StringUtils.equals(keys, "123456")){
    		activationCodeService.createActivationCode(batch, number);
    	}
    	//mailUtill.sendEmail("499345857@qq.com", "主题", "内容");
        return  "success";
    }
    
}
