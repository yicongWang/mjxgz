package com.zhiyi.mjxgz.service.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.zhiyi.mjxgz.service.AccountService;

@Configuration
@EnableScheduling
public class SchedulingConfig {
	private Logger logger = LoggerFactory.getLogger(SchedulingConfig.class);

	@Autowired 
	private AccountService accountService;
	
    @Scheduled(cron = "0 30 0 * * ?") // 每天凌晨12点半执行一次
    public void getToken() {
    	
    }
}