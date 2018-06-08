package com.zhiyi.mjxgz.service.impl;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.common.exception.BizException;
import com.zhiyi.mjxgz.common.exception.DataNotExistsException;
import com.zhiyi.mjxgz.dao.ex.VipMealMapperExt;
import com.zhiyi.mjxgz.dao.ex.VipOrderMapperExt;
import com.zhiyi.mjxgz.model.VipMeal;
import com.zhiyi.mjxgz.model.VipOrder;
import com.zhiyi.mjxgz.model.VipOrderExample;
import com.zhiyi.mjxgz.service.VipOrderService;
import com.zhiyi.mjxgz.util.DateUtil;
import com.zhiyi.mjxgz.util.HttpRequest;
import com.zhiyi.mjxgz.util.RandomStr;
import com.zhiyi.mjxgz.util.WXPayUtil;

/**
 * vip订单
 * @author wyc
 *
 */
@Service
public class VipOrderServiceImpl implements VipOrderService {
	
    private Logger logger = LoggerFactory.getLogger(VipOrderServiceImpl.class);
    @Autowired
    private VipOrderMapperExt vipOrderMapperExt;
    @Autowired
    private VipMealMapperExt vipMealMapperExt;
    @Value("${app_id}")
    private String app_id;
    @Value("${mch_id}")
    private String mch_id;
    @Value("${notify_url}")
    private String notify_url;
	@Override
	public String buyorder(String vipMealId, String accountId) {
		String orderNumber = null;
		VipMeal vipMeal = vipMealMapperExt.selectByPrimaryKey(Long.valueOf(vipMealId));
		if(null != vipMeal){
		    orderNumber = "V"+DateUtil.dateToStr(new Date(), "yyyyMMddHHmmss"+RandomStr.randomStr(5));
			VipOrder vipOrder = new VipOrder();
			vipOrder.setVipMealId(Integer.valueOf(vipMealId));
			vipOrder.setOrderNumber(orderNumber);
			vipOrder.setOrderAmount(vipMeal.getPrice());
			vipOrder.setPayStatus(0);//待支付
			vipOrder.setAccountId(accountId);
			vipOrder.setCreateTime(new Date());
			vipOrderMapperExt.insert(vipOrder);
		}else{
			throw new DataNotExistsException("VIP会员套餐不存在");
		}
		return orderNumber;
	}

	
	@Override
	public  Map<String, Object>  getPayInformation(String openId,String orderNumber,String requestIp) throws Exception{
		VipOrderExample example = new VipOrderExample();
		example.createCriteria().andOrderNumberEqualTo(orderNumber);
		List<VipOrder> vipOrderList = vipOrderMapperExt.selectByExample(example);
		if(CollectionUtils.isEmpty(vipOrderList)){
				throw new DataNotExistsException("该订单不存在");
		}
         Map<String, Object> reqMap = new TreeMap<String, Object>(
	        new Comparator<String>() {
	          public int compare(String obj1, String obj2) {
	            // 升序排序
	            return obj1.compareTo(obj2);
	          }
	        });
	      reqMap.put("appid",app_id);
	      reqMap.put("mch_id",mch_id);
	      reqMap.put("nonce_str", WXPayUtil.getNonce_str());
	      reqMap.put("body","购买会员卡");
	      reqMap.put("openid", openId);
	      reqMap.put("out_trade_no", orderNumber);
	      reqMap.put("total_fee",vipOrderList.get(0).getOrderAmount().multiply(BigDecimal.valueOf(100))); //订单总金额，单位为分
	      reqMap.put("spbill_create_ip",requestIp); //用户端ip
	      reqMap.put("notify_url",notify_url); //通知地址
	      reqMap.put("trade_type","JSAPI"); //trade_type=JSAPI时（即公众号支付），此参数必传，此参数为微信用户在商户对应appid下的唯一标识
	      String reqStr = WXPayUtil.map2Xml(reqMap);
	      String resultXml = HttpRequest.sendPost(reqStr);
	      logger.info("微信请求返回:" + resultXml);
	      Map<String, Object> resultMap = new TreeMap<>(
		            new Comparator<String>() {
		              public int compare(String obj1, String obj2) {
		                // 升序排序
		                return obj1.compareTo(obj2);
		              }
		            });
	      
	      //解析微信返回串 如果状态成功 则返回给前端
	      if (WXPayUtil.getReturnCode(resultXml) != null && WXPayUtil.getReturnCode(resultXml).equals("SUCCESS")) {
	        //成功
	        resultMap.put("appId",app_id);
	        resultMap.put("nonceStr", WXPayUtil.getNonceStr(resultXml));//解析随机字符串
	        resultMap.put("package", "prepay_id=" + WXPayUtil.getPrepayId(resultXml));
	        resultMap.put("signType", "MD5");
	        resultMap.put("timeStamp", String.valueOf((System.currentTimeMillis() / 1000)));//时间戳
	        String paySign = WXPayUtil.getSign(resultMap);
	        resultMap.put("paySign", paySign);
         }else{
        	 throw new BizException("微信请求支付失败");
         }
	      return resultMap;	      
	}


	@Override
	public void handlePayResult(SortedMap<Object, Object> packageParams) {
	/*	  String mch_id = (String)packageParams.get("mch_id");  
          String openid = (String)packageParams.get("openid");  
          String is_subscribe = (String)packageParams.get("is_subscribe");  
          String out_trade_no = (String)packageParams.get("out_trade_no");  
          String total_fee = (String)packageParams.get("total_fee"); */ 
		
		
		
	}
	
}
