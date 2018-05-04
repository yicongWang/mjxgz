package com.zhiyi.mjxgz.configurer;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zhiyi.mjxgz.common.DeviceEnumConverterFactory;

/**
 * Created by Floki on 2017/8/16.
 */
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurerAdapter {

    /**
     * 添加枚举转换工厂
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(new DeviceEnumConverterFactory());
    }
}
