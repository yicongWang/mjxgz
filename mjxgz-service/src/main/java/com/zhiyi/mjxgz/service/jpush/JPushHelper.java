package com.zhiyi.mjxgz.service.jpush;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.service.jpush.config.JPushConfig;
import com.zhiyi.mjxgz.util.JSONUtil;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.DefaultResult;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;

/**
 * 极光推送工具类
 * 
 * @Title: com.team.cms.utils.jpushHelper.JPushHelper.java
 * @Description:
 * @author huangdg
 * @date 2017年1月3日
 */
@Service
public class JPushHelper {
	private static Logger logger = LoggerFactory.getLogger(JPushHelper.class);

	private static boolean apns = true;
	private static JPushClient jPushClient;
	
	@Autowired
	private JPushConfig jPushConfig;
    //private static String appKey = "da640a84e8641aba0f78d06c";
	//private static String masterSecret = "3d193ed21073cacca3c922f8";
	/**
	 * 实例化推送客户端
	 */
	public void getPushClient() {
		if (jPushClient != null) {
			return;
		}
		try {
			//apns = Boolean.parseBoolean(System.getProperty("jpush.apns"));
			ClientConfig config = ClientConfig.getInstance();
			jPushClient = new JPushClient(jPushConfig.getMasterSecret(), jPushConfig.getAppKey(), null, config);
		} catch (Exception e) {
			logger.error("----error---info:"+e.getMessage(),e);
		}
	}
	/**
	 * 设置设备的别名
	 * @param regID 极光注册ID
	 * @param aliasName 别名
	 */
	public  Map<String, Object> setAlias(String regID, String aliasName) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultcode", -1);
		resultMap.put("msg_id", 0);
		resultMap.put("sendno", 0);
		try {
			if (jPushClient == null) {
				getPushClient();
			}
			try {
				DefaultResult jResult =jPushClient.updateDeviceTagAlias(regID, true, false);
				logger.info(jResult.toString());
			} catch (Exception e) {
				logger.error("----error---info:"+e.getMessage(),e);
			}
			DefaultResult jResult = jPushClient.updateDeviceTagAlias(regID, aliasName, null, null);
			logger.info("regID:" + regID + ",aliasName:"+ aliasName +",Result:"+jResult.toString());
			Map<String, String> jMap = JSONUtil.parseObject(jResult.toString(), HashMap.class);
			resultMap.put("resultcode", jResult.getResponseCode());
			resultMap.put("msg_id", jMap.get("msg_id"));
			resultMap.put("sendno", jMap.get("sendno"));
		} catch (Exception e) {
			logger.error("----error---info:"+e.getMessage(),e);
		}
		return resultMap;
	}
	/**
	 * 设置设备标签
	 * @param regID 极光注册ID
	 * @param tagsName 标签名称
	 * @return
	 */
	public  Map<String, Object> setTags(String regID, String newtagsName, String oldtagsName) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultcode", -1);
		resultMap.put("msg_id", 0);
		resultMap.put("sendno", 0);
		try {
			if (jPushClient == null) {
				getPushClient();
			}
			Set<String> regIDSet = new HashSet<String>();
			regIDSet.add(regID);
			try {
				if (oldtagsName != null && !oldtagsName.isEmpty()) {
					DefaultResult jResultM = jPushClient.addRemoveDevicesFromTag(oldtagsName, null, regIDSet);
					logger.info("regID:" + regID + ",newtagsName:"+ newtagsName + ",oldtagsName:"+ oldtagsName +",Result:"+jResultM.toString());
				}
			} catch (Exception e) {
				logger.error("----error---info:"+e.getMessage(),e);
			}
			try {
				DefaultResult jResultA = jPushClient.addRemoveDevicesFromTag(newtagsName, regIDSet, null);
				logger.info("regID:" + regID + ",newtagsName:"+ newtagsName + ",oldtagsName:"+ oldtagsName +",Result:"+jResultA.toString());
				Map<String, String> jMap = JSONUtil.parseObject(jResultA.toString(), HashMap.class);
				resultMap.put("resultcode", jResultA.getResponseCode());
				resultMap.put("msg_id", jMap.get("msg_id"));
				resultMap.put("sendno", jMap.get("sendno"));
			} catch (Exception e) {
				logger.error("----error---info:"+e.getMessage(),e);
			}
		} catch (Exception e) {
			logger.error("----error---info:"+e.getMessage(),e);
		}
		return resultMap;
	}
	/**
	 * 添加设备标签
	 * @param regID 极光注册ID
	 * @param tagsName 标签名称
	 * @return
	 */
	public Map<String, Object> addTags(String regID, String tagsName) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultcode", -1);
		resultMap.put("msg_id", 0);
		resultMap.put("sendno", 0);
		try {
			if (jPushClient == null) {
				getPushClient();
			}
			Set<String> regIDSet = new HashSet<String>();
			regIDSet.add(regID);
			DefaultResult jResult = jPushClient.addRemoveDevicesFromTag(tagsName, regIDSet, null);
			logger.info("regID:" + regID + ",tagsName:"+ tagsName +",Result:"+jResult.toString());
			Map<String, String> jMap = JSONUtil.parseObject(jResult.toString(), HashMap.class); 
			resultMap.put("resultcode", jResult.getResponseCode());
			resultMap.put("msg_id", jMap.get("msg_id"));
			resultMap.put("sendno", jMap.get("sendno"));
		} catch (Exception e) {
			logger.error("----error---info:"+e.getMessage(),e);
		}
		return resultMap;
	}
	/**
	 * 根据注册ID，推送通知
	 * @param regID 极光注册ID
	 * @param noticeTitle 通知标题
	 * @param noticeContent 通知内容
	 * @param platForm 手机平台类型
	 * @return
	 */
	public Map<String, Object> sendNoticeWithRegID(String regID, String noticeTitle, String noticeContent, String platForm, Map<String, String> extras) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultcode", -1);
		resultMap.put("msg_id", 0);
		resultMap.put("sendno", 0);
		try {
			if (jPushClient == null) {
				getPushClient();
			}
			PushPayload pushPayload = null;
			if (platForm.equals("android")) {
				pushPayload = PushPayload.newBuilder()
						.setPlatform(Platform.android())
						.setAudience(Audience.registrationId(regID))
						.setNotification(Notification.android(noticeContent, noticeTitle, extras))
						.setOptions(Options.newBuilder().setApnsProduction(apns).build())
						.build();
			}
			else if (platForm.equals("ios")) {
				pushPayload = PushPayload.newBuilder()
						.setPlatform(Platform.ios())
						.setAudience(Audience.registrationId(regID))
						.setNotification(Notification.ios(noticeContent, extras))
						.setOptions(Options.newBuilder().setApnsProduction(apns).build())
						.build();
			}
			else {
				pushPayload = PushPayload.newBuilder()
						.setPlatform(Platform.winphone())
						.setAudience(Audience.registrationId(regID))
						.setNotification(Notification.winphone(noticeContent, extras))
						.setOptions(Options.newBuilder().setApnsProduction(apns).build())
						.build();
			}
			PushResult jResult = jPushClient.sendPush(pushPayload);
			logger.info("regID:" + regID + ",noticeContent:"+ noticeContent +",Result:"+jResult.toString());
			Map<String, String> jMap = JSONUtil.parseObject(jResult.toString(), HashMap.class);
			resultMap.put("resultcode", jResult.getResponseCode());
			resultMap.put("msg_id", jMap.get("msg_id"));
			resultMap.put("sendno", jMap.get("sendno"));
		} catch (Exception e) {
			logger.error("----error---info:"+e.getMessage(),e);
		}
		return resultMap;
	}
	/**
	 * 根据别名，推送通知
	 * @param aliasName 设备别名
	 * @param noticeTitle 通知标题
	 * @param noticeContent 通知内容
	 * @param platForm 手机平台类型 
	 * @return
	 */
	public Map<String, Object> sendNoticeWithAlias(String aliasName, String noticeTitle, String noticeContent, String platForm, Map<String, String> extras) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultcode", -1);
		resultMap.put("msg_id", 0);
		resultMap.put("sendno", 0);
		try {
			if (jPushClient == null) {
				getPushClient();
			}
			PushPayload pushPayload = null;
			if (platForm.equals("android")) {
				pushPayload = PushPayload.newBuilder()
						.setPlatform(Platform.android())
						.setAudience(Audience.alias(aliasName))
						.setNotification(Notification.android(noticeContent, noticeTitle, extras))
						.setOptions(Options.newBuilder().setApnsProduction(apns).build())
						.build();
			}
			else if (platForm.equals("ios")) {
				pushPayload = PushPayload.newBuilder()
						.setPlatform(Platform.ios())
						.setAudience(Audience.alias(aliasName))
						.setNotification(Notification.ios(noticeContent, extras))
						.setOptions(Options.newBuilder().setApnsProduction(apns).build())
						.build();
			}
			else {
				pushPayload = PushPayload.newBuilder()
						.setPlatform(Platform.all())
						.setAudience(Audience.alias(aliasName))
						.setNotification(Notification.winphone(noticeContent, extras))
						.setOptions(Options.newBuilder().setApnsProduction(apns).build())
						.build();
			}
			PushResult jResult = jPushClient.sendPush(pushPayload);
			logger.info("aliasName:" + aliasName + ",noticeContent:"+ noticeContent +",Result:"+jResult.toString());
			Map<String, String> jMap = JSONUtil.parseObject(jResult.toString(), HashMap.class);
			resultMap.put("resultcode", jResult.getResponseCode());
			resultMap.put("msg_id", jMap.get("msg_id"));
			resultMap.put("sendno", jMap.get("sendno"));
		} catch (Exception e) {
			logger.error("----error---info:"+e.getMessage(),e);
		}
		return resultMap;
	}
	/**
	 * 根据标签，推送通知
	 * @param tagsName 标签名称
	 * @param noticeTitle 通知标题
	 * @param noticeContent 通知内容
	 * @param platForm 手机平台类型
	 * @return
	 */
	public Map<String, Object> sendNoticeWithTags(String tagsName, String noticeTitle, String noticeContent, String platForm, Map<String, String> extras) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultcode", -1);
		resultMap.put("msg_id", 0);
		resultMap.put("sendno", 0);
		try {
			if (jPushClient == null) {
				getPushClient();
			}
			PushPayload pushPayload = null;
			if (platForm.equals("android")) {
				pushPayload = PushPayload.newBuilder()
						.setPlatform(Platform.android())
						.setAudience(Audience.tag(tagsName))
						.setNotification(Notification.android(noticeContent, noticeTitle, extras))
						.setOptions(Options.newBuilder().setApnsProduction(apns).build())
						.build();
				PushResult jResult = jPushClient.sendPush(pushPayload);
				logger.info("tagsName:" + tagsName + ",noticeContent:"+ noticeContent +",Result:"+jResult.toString());
				Map<String, String> jMap = JSONUtil.parseObject(jResult.toString(), HashMap.class);
				resultMap.put("resultcode", jResult.getResponseCode());
				resultMap.put("msg_id", jMap.get("msg_id"));
				resultMap.put("sendno", jMap.get("sendno"));
			}
			else if (platForm.equals("ios")) {
				pushPayload = PushPayload.newBuilder()
						.setPlatform(Platform.ios())
						.setAudience(Audience.tag(tagsName))
						.setNotification(Notification.ios(noticeContent, extras))
						.setOptions(Options.newBuilder().setApnsProduction(apns).build())
						.build();
				PushResult jResult = jPushClient.sendPush(pushPayload);
				logger.info("tagsName:" + tagsName + ",noticeContent:"+ noticeContent +",Result:"+jResult.toString());
				Map<String, String> jMap = JSONUtil.parseObject(jResult.toString(), HashMap.class);
				resultMap.put("resultcode", jResult.getResponseCode());
				resultMap.put("msg_id", jMap.get("msg_id"));
				resultMap.put("sendno", jMap.get("sendno"));
			}
			else {
				PushPayload pushPayload1 = null;
				PushPayload pushPayload2 = null;
				int result1 = -1;
				int result2 = -1;
				PushResult jResult1 = null;
				PushResult jResult2 = null;
				pushPayload1 = PushPayload.newBuilder()
						.setPlatform(Platform.android())
						.setAudience(Audience.tag(tagsName))
						.setNotification(Notification.android(noticeContent, noticeTitle, extras))
						.setOptions(Options.newBuilder().setApnsProduction(apns).build())
						.build();
				
				try {
					jResult1 = jPushClient.sendPush(pushPayload1);
					result1 = jResult1.getResponseCode();
				} catch (Exception e) {
					logger.error("----error---info:"+e.getMessage(),e);
				}
				
				pushPayload2 = PushPayload.newBuilder()
						.setPlatform(Platform.ios())
						.setAudience(Audience.tag(tagsName))
						.setNotification(Notification.ios(noticeContent, extras))
						.setOptions(Options.newBuilder().setApnsProduction(apns).build())
						.build();
				
				try {
					jResult2 = jPushClient.sendPush(pushPayload2);
					result2 = jResult2.getResponseCode();
				} catch (Exception e) {
					logger.error("----error---info:"+e.getMessage(),e);
				}
				if (result1 == 200||result2 == 200) {
					logger.info("tagsName:" + tagsName + ",noticeContent:"+ noticeContent +",Result:"+jResult1.toString()+"/"+jResult2.toString());
					Map<String, String> jMap1 = JSONUtil.parseObject(jResult1.toString(), HashMap.class);
					Map<String, String> jMap2 = JSONUtil.parseObject(jResult2.toString(), HashMap.class);
					resultMap.put("resultcode", 200);
					resultMap.put("msg_id", jMap1.get("msg_id")+","+jMap2.get("msg_id"));
					resultMap.put("sendno", jMap1.get("sendno")+","+jMap2.get("sendno"));
				}
			}
		} catch (Exception e) {
			logger.error("----error---info:"+e.getMessage(),e);
		}
		return resultMap;
	}
	/**
	 * 根据注册ID推送应用内消息
	 * @param regID 极光注册ID
	 * @param messageTitle 消息标题
	 * @param messageContent 消息内容
	 */
	public Map<String, Object> sendMessageWithRegID(String regID, String messageTitle, String messageContent, Map<String, String> extras) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultcode", -1);
		resultMap.put("msg_id", 0);
		resultMap.put("sendno", 0);
		try {
			if (jPushClient == null) {
				getPushClient();
			}
			PushPayload pushPayload = PushPayload.newBuilder()
					.setPlatform(Platform.all())
					.setAudience(Audience.registrationId(regID))
					.setMessage(Message.newBuilder().setTitle(messageTitle).setMsgContent(messageContent).addExtras(extras).build())
					.setOptions(Options.newBuilder().setTimeToLive(0l).setApnsProduction(apns).build())
					.build();
			PushResult jResult = jPushClient.sendPush(pushPayload);
			logger.info("regID:" + regID + ",messageContent:"+ messageContent +",Result:"+jResult.toString());
			Map<String, String> jMap = JSONUtil.parseObject(jResult.toString(), HashMap.class);
			resultMap.put("resultcode", jResult.getResponseCode());
			resultMap.put("msg_id", jMap.get("msg_id"));
			resultMap.put("sendno", jMap.get("sendno"));
		} catch (Exception e) {
			logger.error("----error---info:"+e.getMessage(),e);
		}
		return resultMap;
	}
	/**
	 * 根据别名推送应用内消息
	 * @param aliasName 别名集合
	 * @param messageTitle 消息标题
	 * @param messageContent 消息内容
	 */
	public Map<String, Object> sendMessageWithAlias(List<String> alias, String messageTitle, String messageContent, Map<String, String> extras) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultcode", -1);
		resultMap.put("msg_id", 0);
		resultMap.put("sendno", 0);
		try {
			if (jPushClient == null) {
				getPushClient();
			}
			PushPayload pushPayload = PushPayload.newBuilder()
					.setPlatform(Platform.all())
					.setAudience(Audience.alias(alias))
					.setMessage(Message.newBuilder().setTitle(messageTitle).setMsgContent(messageContent).addExtras(extras).build())
					.setOptions(Options.newBuilder().setTimeToLive(0l).setApnsProduction(apns).build())
					.build();
			PushResult jResult = jPushClient.sendPush(pushPayload);
			logger.info("aliasName:" + JSONUtil.toJSONString(alias) + ",messageContent:"+ messageContent +",Result:"+jResult.toString());
			Map<String, String> jMap = JSONUtil.parseObject(jResult.toString(), HashMap.class);
			resultMap.put("resultcode", jResult.getResponseCode());
			resultMap.put("msg_id", jMap.get("msg_id"));
			resultMap.put("sendno", jMap.get("sendno"));
		} catch (Exception e) {
			logger.error("----error---info:"+e.getMessage(),e);
		}
		return resultMap;
	}
	/**
	 * 根据标签推送应用内消息
	 * @param tagsName 标签名称集合
	 * @param messageTitle 消息标题
	 * @param messageContent 消息内容
	 */
	public Map<String, Object> sendMessageWithTags(List<String> tags, String messageTitle, String messageContent, Map<String, String> extras) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("resultcode", -1);
		resultMap.put("msg_id", 0);
		resultMap.put("sendno", 0);
		try {
			if (jPushClient == null) {
				getPushClient();
			}
			PushPayload pushPayload = PushPayload.newBuilder()
					.setPlatform(Platform.all())
					.setAudience(Audience.tag(tags))
					.setMessage(Message.newBuilder().setTitle(messageTitle).setMsgContent(messageContent).addExtras(extras).build())
					.setOptions(Options.newBuilder().setTimeToLive(0l).setApnsProduction(apns).build())
					.build();
			PushResult jResult = jPushClient.sendPush(pushPayload);
			logger.info("tagsName:" + JSONUtil.toJSONString(tags) + ",messageContent:"+ messageContent +",Result:"+jResult.toString());
			Map<String, String> jMap = JSONUtil.parseObject(jResult.toString(), HashMap.class);
			resultMap.put("resultcode", jResult.getResponseCode());
			resultMap.put("msg_id", jMap.get("msg_id"));
			resultMap.put("sendno", jMap.get("sendno"));
		} catch (Exception e) {
			logger.error("----error---info:"+e.getMessage(),e);
		}
		return resultMap;
	}
	
	public static void main(String[] args){
		//setAlias("160a3797c824880b9c7", "alias");
		//setTags("160a3797c824880b9c7", "admin", null);
		//sendMessageWithTags("admin", "标题2",  "呵呵", new HashMap<>());
		
		//Map<String,String> map = new HashMap<>();
		//map.put("user","sdfafd");
		//sendMessageWithAlias("alias", "标题1", "heihei", map);
	}
}
