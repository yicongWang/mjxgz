package com.zhiyi.mjxgz.common.exception;

/**
 * 数据不存在的异常类
 *
 * Created by DW on 2016/8/22.
 */
public class DataNotExistsException extends RuntimeException {
    /**
     * 实例化一个新的数据不存在异常
     *
     * @param msg 异常信息
     */
    public DataNotExistsException(String msg) {
        super(msg);
    }

    /**
     * 实例化一个新的数据不存在异常
     *
     * @param cause 异常原因
     */
    public DataNotExistsException(Throwable cause) {
        super(cause);
    }

    /**
     * 实例化一个新的数据不存在异常
     *
     * @param msg 异常信息
     * @param cause 异常原因
     */
    public DataNotExistsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
