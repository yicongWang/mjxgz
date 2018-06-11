package com.zhiyi.mjxgz.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zhiyi.mjxgz.service.VipOrderService;
import com.zhiyi.mjxgz.util.PayCommonUtil;
import com.zhiyi.mjxgz.util.XMLUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 *订单接口
 * @author wyc
 */

@Api(description = "微信支付接口")
@RestController
@RequestMapping("/wxpay")
public class WxPayController {
    private Logger logger = LoggerFactory.getLogger(WxPayController.class);
    @Autowired private VipOrderService vipOrderService;
    @Value("${api_key}")
    private String api_key;  // 账号信息 
    @ApiOperation(value = "支付回调")
    @RequestMapping(value = "/notify_url")
    public void  notifyUrl(HttpServletRequest request,HttpServletResponse response) throws IOException, JDOMException{
		logger.debug("-wxpay---notifyUrl----start----");
		 //读取参数  
        InputStream inputStream ;  
        StringBuffer sb = new StringBuffer();  
        inputStream = request.getInputStream();  
        String s ;  
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));  
        while ((s = in.readLine()) != null){  
            sb.append(s);  
        }  
        in.close();  
        inputStream.close();  
        //解析xml成map  
        Map<String, String> m = new HashMap<String, String>();  
        m = XMLUtil.doXMLParse(sb.toString());  
        //过滤空 设置 TreeMap  
        SortedMap<Object,Object> packageParams = new TreeMap<Object,Object>();        
        Iterator it = m.keySet().iterator();  
        while (it.hasNext()) {  
            String parameter = (String) it.next();  
            String parameterValue = m.get(parameter);  
              
            String v = "";  
            if(null != parameterValue) {  
                v = parameterValue.trim();  
            }  
            packageParams.put(parameter, v);  
        }  
          
       
        logger.info(packageParams.toString());  
        //判断签名是否正确  
        if(PayCommonUtil.isTenpaySign("UTF-8", packageParams,api_key)) {  
            //------------------------------  
            //处理业务开始  
            //------------------------------  
            String resXml = "";  
            if("SUCCESS".equals((String)packageParams.get("result_code"))){  
                // 这里是支付成功  
                //////////执行自己的业务逻辑////////////////  
                String mch_id = (String)packageParams.get("mch_id");  
                String openid = (String)packageParams.get("openid");  
                String is_subscribe = (String)packageParams.get("is_subscribe");  
                String out_trade_no = (String)packageParams.get("out_trade_no");  
                String total_fee = (String)packageParams.get("total_fee");  
                logger.info("mch_id:"+mch_id);  
                logger.info("openid:"+openid);  
                logger.info("is_subscribe:"+is_subscribe);  
                logger.info("out_trade_no:"+out_trade_no);  
                logger.info("total_fee:"+total_fee);  
                  
                //////////执行自己的业务逻辑////////////////  
                vipOrderService.handlePayResult(packageParams);  
                
                logger.info("支付成功");  
                //通知微信.异步确认成功.必写.不然会一直通知后台.八次之后就认为交易失败了.  
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>"  
                        + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";  
                  
            } else {  
                logger.info("支付失败,错误信息：" + packageParams.get("err_code"));  
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"  
                        + "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";  
            }  
            //------------------------------  
            //处理业务完毕  
            //------------------------------  
            BufferedOutputStream out = new BufferedOutputStream(  
                    response.getOutputStream());  
            out.write(resXml.getBytes());  
            out.flush();  
            out.close();  
        } else{  
            logger.info("通知签名验证失败");  
        }  
          
    } 
    
}
