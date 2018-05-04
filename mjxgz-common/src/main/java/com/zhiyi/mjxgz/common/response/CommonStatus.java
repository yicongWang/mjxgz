package com.zhiyi.mjxgz.common.response;

/**
 * <pre>
 * 业务处理状态信息，状态由状态码(code)和状态消息(message)组成。
 *
 * 状态码一共有5位数，由三部分构成：状态码层级 + 服务模块代码 + 具体错误代码。
 * 1、状态码层级，由一位数构成：1=系统级错误；2=服务级错误
 * 2、服务模块代码，由两位数构成，从01开始
 *    01=权限管理
 *    02=用户管理
 *    03=控制管理
 *    04=策略管理
 *    05=故障管理
 * 3、具体错误代码：由两位数构成，从01开始
 *    03-控制管理模块
 *      01-10：项目管理
 *      11-20：分组管理
 *      21-50：设备管理
 * 需要注意的是，代码0不是一个错误码，它表示没有发生任何错误或异常，请求的操作都成功的执行。
 * </pre>
 *
 * Created by Floki on 2017/7/6.
 */
public enum CommonStatus implements Status {
    /** 成功 */
    OK(0, "OK")

    /** 系统错误 */
    , SYSTEM_ERROR(10001, "系统内部错误")

    /** 参数错误 */
    , PARAMETERS_ERROR(10005, "参数错误")

    /** 不支持请求的方法 */
    , METHOD_NOT_SUPPORTED(10006, "系统不支持请求的方法")

    /** 设备的分组数量错误 */
    , DEVICE_GROUP_SIZE_ERROE(20325, "设备分组数量错误")

    ;

    /** 状态代码 */
    private final int code;

    /** 状态消息 */
    private final String message;

    CommonStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
