package com.zhiyi.mjxgz.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by ONENET-XXMI on 2017/1/19.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    /**
     * api 的相关信息
     * @return
     */
    private ApiInfo apiInfo(){
    	 ApiInfoBuilder info = new ApiInfoBuilder();
         info.title("魔镜小公主 API");
         info.description("魔镜小公主 在线API");
         info.termsOfServiceUrl("服务条款：");
//         Contact contact = new Contact("邬晓波，周强，陈勇","","");
         info.contact("");
         info.version("版本：1.0");
        return info.build();
    }
}
