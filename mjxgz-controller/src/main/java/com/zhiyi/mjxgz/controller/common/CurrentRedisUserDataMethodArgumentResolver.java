package com.zhiyi.mjxgz.controller.common;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.zhiyi.mjxgz.dto.RedisUserData;
import com.zhiyi.mjxgz.util.RedisUtil;

/**
 * 注解CurrentRedisUserData 的实现
 * Created by DW on 2016/8/25.
 */
public class CurrentRedisUserDataMethodArgumentResolver implements HandlerMethodArgumentResolver {
    //private static Logger logger = LoggerFactory.getLogger(CurrentRedisUserDataMethodArgumentResolver.class);

    @Autowired
    private RedisUtil redisUtil;

    CurrentRedisUserDataMethodArgumentResolver() {
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentRedisUserData.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        String accessToken = webRequest.getHeader("access_token");
        if (accessToken != null) {
            RedisUserData redisUserData = (RedisUserData) redisUtil.get(accessToken);
            return redisUserData != null ? redisUserData : null;
        }
        return null;
    }
}
