package com.zhiyi.mjxgz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import com.zhiyi.mjxgz.client.config.RestServiceConfig;
import com.zhiyi.mjxgz.service.common.SystemConfig;
import com.zhiyi.mjxgz.util.UserSettings;


@SpringBootApplication
@EnableConfigurationProperties({ UserSettings.class, RestServiceConfig.class, SystemConfig.class})
@ComponentScan({"com.zhiyi.mjxgz"})
@EnableAsync
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
