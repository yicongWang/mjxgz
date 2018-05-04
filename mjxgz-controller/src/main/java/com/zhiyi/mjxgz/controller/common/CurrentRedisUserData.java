package com.zhiyi.mjxgz.controller.common;

import java.lang.annotation.*;

/**
 * 当前RedisUserData数据注解
 * Created by DW on 2016/8/25.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentRedisUserData {

}
