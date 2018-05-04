package com.zhiyi.mjxgz.service.mqtt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.zhiyi.mjxgz.util.RandCodeUtil;

@ConfigurationProperties(locations = "classpath:config/mqtt.properties", prefix = "mqtt")
@Component
public class MqttConstant {
	private String  mqttClientClientID = "jll:property"+RandCodeUtil.getRandomSmsCode(3); 
	private String mqttServiceClientID = "jll:property"+RandCodeUtil.getRandomSmsCode(3);
	private boolean mqttServiceCleanStart= true;
	private short mqttServiceKeepAlive= 30;// 低耗网络，但是又需要及时获取数据，心跳30s	
	private long mqttServiceReconnectionAttemptMax= 6;
	private long mqttServiceReconnectionRelay= 2000;
	private int mqttServiceSendBufferSize= 2 * 1024 * 1024;// 发送最大缓冲为2M
	private String mqttClientTopic= "/jll/property/#";
	private  String code;
	private String host;  
	private String username;  
	private String password;
	
	public String getMqttClientTopic() {
		return mqttClientTopic;
	}
	public void setMqttClientTopic(String mqttClientTopic) {
		this.mqttClientTopic = mqttClientTopic;
	}
	public String getMqttClientClientID() {
		return mqttClientClientID;
	}
	public void setMqttClientClientID(String mqttClientClientID) {
		this.mqttClientClientID = mqttClientClientID;
	}
	public String getMqttServiceClientID() {
		return mqttServiceClientID;
	}
	public void setMqttServiceClientID(String mqttServiceClientID) {
		this.mqttServiceClientID = mqttServiceClientID;
	}
	public boolean isMqttServiceCleanStart() {
		return mqttServiceCleanStart;
	}
	public void setMqttServiceCleanStart(boolean mqttServiceCleanStart) {
		this.mqttServiceCleanStart = mqttServiceCleanStart;
	}
	public short getMqttServiceKeepAlive() {
		return mqttServiceKeepAlive;
	}
	public void setMqttServiceKeepAlive(short mqttServiceKeepAlive) {
		this.mqttServiceKeepAlive = mqttServiceKeepAlive;
	}
	public long getMqttServiceReconnectionAttemptMax() {
		return mqttServiceReconnectionAttemptMax;
	}
	public void setMqttServiceReconnectionAttemptMax(long mqttServiceReconnectionAttemptMax) {
		this.mqttServiceReconnectionAttemptMax = mqttServiceReconnectionAttemptMax;
	}
	public long getMqttServiceReconnectionRelay() {
		return mqttServiceReconnectionRelay;
	}
	public void setMqttServiceReconnectionRelay(long mqttServiceReconnectionRelay) {
		this.mqttServiceReconnectionRelay = mqttServiceReconnectionRelay;
	}
	public int getMqttServiceSendBufferSize() {
		return mqttServiceSendBufferSize;
	}
	public void setMqttServiceSendBufferSize(int mqttServiceSendBufferSize) {
		this.mqttServiceSendBufferSize = mqttServiceSendBufferSize;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
