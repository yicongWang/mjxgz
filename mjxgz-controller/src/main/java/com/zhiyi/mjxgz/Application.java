package com.zhiyi.mjxgz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

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
    
/*    *//**
     * 设置视图解析器
     * @param templateEngine
     * @return
     *//*
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine){
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine);
        return resolver;
    }

    *//**
     * 设置模板引擎
     * @param templateResolver
     * @return
     *//*
    @Bean
    public SpringTemplateEngine templateEngine(TemplateResolver templateResolver){
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver);
        return engine;
    }

    *//**
     * 模板解析引擎
     * @return
     *//*
    @Bean
    public TemplateResolver templateResolver(){
        TemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setPrefix("classpath:/templates/");//设置地址前缀
        resolver.setSuffix(".html");//设置后缀
        resolver.setCacheable(false);//设置不缓存
        resolver.setTemplateMode("HTML5");
        return resolver;
        //spring.thymeleaf.content-type=text/html
        //spring.thymeleaf.mode=LEGACYHTML5
    }*/
}
