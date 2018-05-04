package com.zhiyi.mjxgz.common.exception;

/**
 * 数据已存在的异常类
 *
 * Created by DW on 2016/8/25.
 */
public class DataAlreadyExistsException extends RuntimeException {
    /**
     * 实例化一个新的数据已存在异常
     *
     * @param msg 异常信息
     */
    public DataAlreadyExistsException(String msg) {
        super(msg);
    }

    /**
     * 实例化一个新的数据已存在异常
     *
     * @param cause 异常原因
     */
    public DataAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    /**
     * 实例化一个新的数据已存在异常
     *
     * @param msg 异常信息
     * @param cause 异常原因
     */
    public DataAlreadyExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
