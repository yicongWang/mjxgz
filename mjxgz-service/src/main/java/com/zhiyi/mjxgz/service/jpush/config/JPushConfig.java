package com.zhiyi.mjxgz.service.jpush.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(locations = "classpath:config/jpush.properties", prefix = "jpush")
@Component
public class JPushConfig {
		private  String appKey ;//"5f819ab3d90fb157b40902e3";
		private  String masterSecret ;//"90147998a75a0f8c26d58de1";
		public String getAppKey() {
			return appKey;
		}
		public void setAppKey(String appKey) {
			this.appKey = appKey;
		}
		public String getMasterSecret() {
			return masterSecret;
		}
		public void setMasterSecret(String masterSecret) {
			this.masterSecret = masterSecret;
		}
		
}
