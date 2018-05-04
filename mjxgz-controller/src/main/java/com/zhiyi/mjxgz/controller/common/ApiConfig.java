package com.zhiyi.mjxgz.controller.common;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import java.util.List;

/**
 * Created by ztz on 2016/6/22.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.zhiyi.mjxgz")
public class ApiConfig extends WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter {

    @Bean
    public AccessApiInterceptor accessApiInterceptor(){
        return new AccessApiInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(accessApiInterceptor());
    }


    //注册解析器bean
    @Bean
    public CurrentRedisUserDataMethodArgumentResolver currentUserMethodArgumentResolver(){
        return new CurrentRedisUserDataMethodArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
    }

}
