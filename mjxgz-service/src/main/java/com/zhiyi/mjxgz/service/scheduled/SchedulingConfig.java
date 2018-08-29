package com.zhiyi.mjxgz.service.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.zhiyi.mjxgz.service.AccountService;
import com.zhiyi.mjxgz.util.RedisUtil;
import com.zhiyi.mjxgz.util.WXPayUtil;

@Configuration
@EnableScheduling
public class SchedulingConfig {
	private Logger logger = LoggerFactory.getLogger(SchedulingConfig.class);
    @Value("${app_id}")
    private String app_id;  // 账号信息 
    @Value("${app_secret}")
    private String app_secret;  // 账号信息
	@Autowired 
	private AccountService accountService;
	@Autowired
	private RedisUtil redisUtil;
    @Scheduled(cron = "0 30 0 * * ?") // 每天凌晨12点半执行一次
    public void getToken() {
    	
    }
    
  //刷新access_token 100分钟刷新一次,服务器启动的时候刷新一次（access_token有效期是120分钟，我设置的是每100分钟刷新一次）
   @Scheduled(initialDelay = 1000, fixedDelay = 100*60*1000)
    public void getWX_access_token(){
	   redisUtil.set("wx_access_token", WXPayUtil.get_access_token(app_id, app_secret));
   }
}