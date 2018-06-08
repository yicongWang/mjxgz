package com.zhiyi.mjxgz.common.exception;

/**
 * 业务异常处理
 * @author wyc
 *
 */
public class BizException extends  RuntimeException{
	  /**
     * 实例化一个新的业务异常
     *
     * @param msg 异常信息
     */
    public BizException(String msg) {
        super(msg);
    }

    /**
     * 实例化一个新的业务异常
     *
     * @param cause 异常原因
     */
    public BizException(Throwable cause) {
        super(cause);
    }

    /**
     * 实例化一个新的业务异常
     *
     * @param msg 异常信息
     * @param cause 异常原因
     */
    public BizException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
