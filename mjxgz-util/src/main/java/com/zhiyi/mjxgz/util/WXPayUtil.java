package com.zhiyi.mjxgz.util;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.w3c.dom.NodeList;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
 
 
public class WXPayUtil {
	  private static Logger logger = LoggerFactory.getLogger(WXPayUtil.class);  
  //生成随机字符串
  public static String getNonce_str() {
    String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Random random = new Random();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 15; i++) {
      int number = random.nextInt(base.length());
      sb.append(base.charAt(number));
    }
    return sb.toString();
  }
 
  //map转xml 加上签名信息
  public static String map2Xml(Map<String, Object> map,String api_key) throws UnsupportedEncodingException {
    StringBuffer sb = new StringBuffer();
    StringBuilder sb2 = new StringBuilder();
    sb2.append("<xml>");
    for (String key : map.keySet()) {
      sb.append(key);
      sb.append('=');
      sb.append(map.get(key));
      sb.append('&');
      // sb2是用来做请求的xml参数
      sb2.append("<" + key + ">");
//      sb2.append("<![CDATA[" + map.get(key) + "]]>");
      sb2.append(map.get(key));
      sb2.append("</" + key + ">");
    }
  //  sb.append(System.getenv("signKey"));
    sb.append("key="+api_key);
    
    String sign = Md5Utils.hash(sb.toString()).toUpperCase();//MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
    sb2.append("<sign>");
    sb2.append(sign);
    sb2.append("</sign>");
    sb2.append("</xml>");
    return sb2.toString();
  }
 
  //解析微信返回return_code SUCCESS或FILE
  //根据微信返回resultXml再次生成签名
  public static String getSign(Map<String, Object> map,String api_key) {
    StringBuffer sb = new StringBuffer();
    for (String key : map.keySet()) {
      sb.append(key);
      sb.append('=');
      sb.append(map.get(key));
      sb.append('&');
    }
    sb.append("key="+api_key);
    System.out.println("第二次签名内容:" + Md5Utils.hash(sb.toString()).toUpperCase());
    
    
   // System.out.println("第二次签名SING:" + MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase());
    return Md5Utils.hash(sb.toString()).toUpperCase();//MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
  }
 
  //解析微信返回return_code SUCCESS或FILE
  public static String getReturnCode(String resultXml) {
    String nonceStr;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;
    try {
      builder = dbf.newDocumentBuilder();
      InputStream inputStream = new ByteArrayInputStream(resultXml.getBytes());
      org.w3c.dom.Document doc = builder.parse(inputStream); //
      // 下面开始读取
      org.w3c.dom.Element root = doc.getDocumentElement(); // 获取根元素
      NodeList nl = root.getElementsByTagName("return_code");
      org.w3c.dom.Element el = (org.w3c.dom.Element) nl.item(0);
      org.w3c.dom.Node nd = el.getFirstChild();
      nonceStr = nd.getNodeValue();
      return nonceStr;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
 
  //解析微信返回return_msg
  public static String getReturn_msg(String resultXml) {
    String nonceStr;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;
    try {
      builder = dbf.newDocumentBuilder();
      InputStream inputStream = new ByteArrayInputStream(resultXml.getBytes());
      org.w3c.dom.Document doc = builder.parse(inputStream); //
      // 下面开始读取
      org.w3c.dom.Element root = doc.getDocumentElement(); // 获取根元素
      NodeList nl = root.getElementsByTagName("return_msg");
      org.w3c.dom.Element el = (org.w3c.dom.Element) nl.item(0);
      org.w3c.dom.Node nd = el.getFirstChild();
      nonceStr = nd.getNodeValue();
      return nonceStr;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
 
  //解析微信返回appid
  public static String getAppId(String resultXml) {
    String nonceStr;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;
    try {
      builder = dbf.newDocumentBuilder();
      InputStream inputStream = new ByteArrayInputStream(resultXml.getBytes());
      org.w3c.dom.Document doc = builder.parse(inputStream); //
      // 下面开始读取
      org.w3c.dom.Element root = doc.getDocumentElement(); // 获取根元素
      NodeList nl = root.getElementsByTagName("appid");
      org.w3c.dom.Element el = (org.w3c.dom.Element) nl.item(0);
      org.w3c.dom.Node nd = el.getFirstChild();
      nonceStr = nd.getNodeValue();
      return nonceStr;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
 
  //解析微信返回mch_id
  public static String getMchId(String resultXml) {
    String nonceStr;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;
    try {
      builder = dbf.newDocumentBuilder();
      InputStream inputStream = new ByteArrayInputStream(resultXml.getBytes());
      org.w3c.dom.Document doc = builder.parse(inputStream); //
      // 下面开始读取
      org.w3c.dom.Element root = doc.getDocumentElement(); // 获取根元素
      NodeList nl = root.getElementsByTagName("mch_id");
      org.w3c.dom.Element el = (org.w3c.dom.Element) nl.item(0);
      org.w3c.dom.Node nd = el.getFirstChild();
      nonceStr = nd.getNodeValue();
      return nonceStr;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
 
  //解析微信返回nonce_str
  public static String getNonceStr(String resultXml) {
    String nonceStr;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;
    try {
      builder = dbf.newDocumentBuilder();
      InputStream inputStream = new ByteArrayInputStream(resultXml.getBytes());
      org.w3c.dom.Document doc = builder.parse(inputStream); //
      // 下面开始读取
      org.w3c.dom.Element root = doc.getDocumentElement(); // 获取根元素
      NodeList nl = root.getElementsByTagName("nonce_str");
      org.w3c.dom.Element el = (org.w3c.dom.Element) nl.item(0);
      org.w3c.dom.Node nd = el.getFirstChild();
      nonceStr = nd.getNodeValue();
      return nonceStr;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
 
  //解析微信返回prepay_id
  public static String getPrepayId(String resultXml) {
    String nonceStr;
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder;
    try {
      builder = dbf.newDocumentBuilder();
      InputStream inputStream = new ByteArrayInputStream(resultXml.getBytes());
      org.w3c.dom.Document doc = builder.parse(inputStream); //
      // 下面开始读取
      org.w3c.dom.Element root = doc.getDocumentElement(); // 获取根元素
      NodeList nl = root.getElementsByTagName("prepay_id");
      org.w3c.dom.Element el = (org.w3c.dom.Element) nl.item(0);
      org.w3c.dom.Node nd = el.getFirstChild();
      nonceStr = nd.getNodeValue();
      return nonceStr;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
 
//刷新access_token 100分钟刷新一次,服务器启动的时候刷新一次（access_token有效期是120分钟，我设置的是每100分钟刷新一次）
 // @Scheduled(initialDelay = 1000, fixedDelay = 100*60*1000)
  public static String get_access_token(String appid,String appsecret) {
	  String access_token = null;
      try {
         // String appid = "wxd42ab7a49b945f43";
          //String appsecret = "bd2f1b889b02d4010d35ae2e1da97e7a";
          String requestUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
          requestUrl = requestUrl.replace("APPID",appid).replace("APPSECRET",appsecret);
          JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
          logger.debug(jsonObject.toString());
          if(jsonObject.getString("access_token")!=null){
        	  access_token = jsonObject.getString("access_token");
          }
          else{
              logger.info("定时刷新access_token失败，微信返回的信息是"+jsonObject.toJSONString());
          }
      }
      catch (Exception e){
          logger.info("更新access_token的过程当中发生了异常，异常的信息是"+e.getMessage());
      }
      return access_token;
  }
  
  public static String findCode(String appid,String appsecret,String token,Map<String, Object> params,String path) {
	  String url = null;
      try {
    	  //String imei ="867186032552993";
          String requestUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+token;
         //String requestUrl = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+token;
         System.out.println("++++++++++++++++++转成的json格式字符串："+JSONObject.toJSONString(params));
         InputStream instreams = httpRequest1(requestUrl, "POST",JSONObject.toJSONString(params));
         // ResourceBundle systemConfig = ResourceBundle.getBundle("config/system", Locale.getDefault());
        //  String uploadSysUrl = systemConfig.getString("agentImgUrl")+id+"/";
        //  File saveFile = new File(uploadSysUrl+id+".jpg");
         //String uploadSysUrl = "D:\\upload"+"/";
         url = params.get("scene")+".jpg";
         File saveFile = new File(path+url);
         // 判断这个文件（saveFile）是否存在
         if (!saveFile.getParentFile().exists()) {
             // 如果不存在就创建这个文件夹
             saveFile.getParentFile().mkdirs();
         }
         FileUtil.saveToImgByInputStream(instreams, path, url);
      }
      catch (Exception e){
          logger.info("更新access_token的过程当中发生了异常，异常的信息是"+e.getMessage());
      }
      return url;
  }
  
  public  static void main(String[] args){
	 // get_access_token("wxd42ab7a49b945f43", "bd2f1b889b02d4010d35ae2e1da97e7a");
      Map<String, Object> params = new HashMap<>();
      params.put("scene", "867186032552993");  //参数
      params.put("path", "pages/shop?id=10"); //位置
      params.put("width", 430);
	 // findCode("wxd42ab7a49b945f43", "bd2f1b889b02d4010d35ae2e1da97e7a",get_access_token("wxd42ab7a49b945f43", "bd2f1b889b02d4010d35ae2e1da97e7a"),params);
	  
  }
  /** 
   * 发起https请求并获取结果 
   *  
   * @param requestUrl 请求地址 
   * @param requestMethod 请求方式（GET、POST） 
   * @param outputStr 提交的数据 
   * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值) 
   */  
  public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {  
      JSONObject jsonObject = null;  
      StringBuffer buffer = new StringBuffer();  
      try {  
          // 创建SSLContext对象，并使用我们指定的信任管理器初始化  
          TrustManager[] tm = { new MyX509TrustManager() };  
          SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
          sslContext.init(null, tm, new java.security.SecureRandom());  
          // 从上述SSLContext对象中得到SSLSocketFactory对象  
          SSLSocketFactory ssf = sslContext.getSocketFactory();  

          URL url = new URL(requestUrl);  
          HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
          httpUrlConn.setSSLSocketFactory(ssf);  
          httpUrlConn.setDoOutput(true);  
          httpUrlConn.setDoInput(true);  
          httpUrlConn.setUseCaches(false);  
          // 设置请求方式（GET/POST）  
          httpUrlConn.setRequestMethod(requestMethod);  

          if ("GET".equalsIgnoreCase(requestMethod))  
              httpUrlConn.connect();  

          // 当有数据需要提交时  
          if (null != outputStr) {  
              OutputStream outputStream = httpUrlConn.getOutputStream(); 
              // 注意编码格式，防止中文乱码  
              outputStream.write(outputStr.getBytes());  
              outputStream.close();  
          }  

          // 将返回的输入流转换成字符串  
          InputStream inputStream = httpUrlConn.getInputStream();  
          InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
          BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  

          String str = null;  
          while ((str = bufferedReader.readLine()) != null) {  
              buffer.append(str);  
          }  
          bufferedReader.close();  
          inputStreamReader.close();  
          // 释放资源  
          inputStream.close();  
          inputStream = null;  
          httpUrlConn.disconnect();  
          jsonObject = JSONObject.parseObject(buffer.toString());  
      } catch (ConnectException ce) {  
    	  logger.error("Weixin server connection timed out.");  
      } catch (Exception e) {  
    	  logger.error("https request error:{}", e);  
      }  
      return jsonObject;  
  }  
  
  
  public static InputStream httpRequest1(String requestUrl, String requestMethod, String outputStr) {  
	  InputStream inputStream = null;
      try {  
          // 创建SSLContext对象，并使用我们指定的信任管理器初始化  
          TrustManager[] tm = { new MyX509TrustManager() };  
          SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
          sslContext.init(null, tm, new java.security.SecureRandom());  
          // 从上述SSLContext对象中得到SSLSocketFactory对象  
          SSLSocketFactory ssf = sslContext.getSocketFactory();  

          URL url = new URL(requestUrl);  
          HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
          httpUrlConn.setSSLSocketFactory(ssf);  
          httpUrlConn.setDoOutput(true);  
          httpUrlConn.setDoInput(true);  
          httpUrlConn.setUseCaches(false);  
          // 设置请求方式（GET/POST）  
          httpUrlConn.setRequestMethod(requestMethod);  

          if ("GET".equalsIgnoreCase(requestMethod))  
              httpUrlConn.connect();  

          // 当有数据需要提交时  
          if (null != outputStr) {  
              OutputStream outputStream = httpUrlConn.getOutputStream(); 
              // 注意编码格式，防止中文乱码  
              outputStream.write(outputStr.getBytes());  
              outputStream.close();  
          }  

          // 将返回的输入流转换成字符串  
         inputStream = httpUrlConn.getInputStream();  
         
      } catch (ConnectException ce) {  
    	  logger.error("Weixin server connection timed out.");  
      } catch (Exception e) {  
    	  logger.error("https request error:{}", e);  
      }  
      return inputStream;  
  }  
}
