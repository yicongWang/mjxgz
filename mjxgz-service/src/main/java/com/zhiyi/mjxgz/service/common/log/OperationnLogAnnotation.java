package com.zhiyi.mjxgz.service.common.log;

import java.lang.annotation.*;

/**
 * 操作日志注解
 * Created by DW on 2016/10/7.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationnLogAnnotation {
    /**
     * 描述
     * @return
     */
    String description()  default "";

    /**
     * 模块名称
     * @return
     */
    String moduleName() default "";

    /**
     * 操作类型 枚举
     * @return
     */
    OperationLogType operationLogType();

    /**
     * 操作终端类型  1:PC端 ；2：移动端
     * @return
     *//*
    String terminalType() default "1";*/
}
