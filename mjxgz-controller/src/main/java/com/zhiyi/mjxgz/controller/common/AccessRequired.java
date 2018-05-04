package com.zhiyi.mjxgz.controller.common;

/**
 * Created by ztz on 2016/6/22.
 */

import java.lang.annotation.*;


@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessRequired {

}