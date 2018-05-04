package com.zhiyi.mjxgz.common.exception;

import com.zhiyi.mjxgz.common.response.Status;

/**
 * 业务异常类，这是一个运行时异常
 *
 * Created by Floki on 2017/7/6.
 */
public class BusinessException extends RuntimeException {
    /**
     * 业务异常的状态
     */
    private Status status;

    /**
     * 初始化业务异常实例
     *
     * @param status 业务异常状态，默认用状态信息作为异常说明
     */
    public BusinessException(Status status) {
        this(status, null);
    }

    /**
     * 初始化业务异常实例
     *
     * @param status 业务异常状态
     * @param messsage 业务异常说明信息
     */
    public BusinessException(Status status, String messsage) {
        super(null == messsage || messsage.length() == 0 ? status.getMessage() : messsage);
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

}
