package com.zhiyi.mjxgz.service.common;

/**
 *  Redis Key 常量定义
 * @author wyc
 *
 */
public class RedisKeyConstant {
    /** 角色 Redis Key */
    public static String ROLE_KEY = "role:{0}";
    
    /** 项目的 Redis Key */
    public static String PROJECT_KEY = "project:{0}";
    
    /** 业务组的 Redis Key */
    public static String BUSINESSGROUP_KEY = "businessgroup:{0}";
    

    /** 系统超级管用员账户*/
    public static String SUPER_AMDMIN_ACCOUNT = "super:amdmin:account";
    /**
     * 表面
     */
    public static String CODE_TABLE_TYPE_KEY = "codetable:{0}:{1}";
}
