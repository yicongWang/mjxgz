package com.zhiyi.mjxgz.service.common;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by Floki on 2017/7/20.
 */
@ConfigurationProperties(prefix = "system")
public class SystemConfig {
    private String sunriseTime;

    private String sunsetTime;

    public String getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(String sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(String sunsetTime) {
        this.sunsetTime = sunsetTime;
    }
}
