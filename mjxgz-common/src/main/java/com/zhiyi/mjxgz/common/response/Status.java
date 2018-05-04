package com.zhiyi.mjxgz.common.response;

/**
 * 状态接口
 *
 * Created by Floki on 2017/5/9.
 */
public interface Status {

    /**
     * <pre>
     * 获取状态代码
     * </pre>
     *
     * @return 返回状态代码
     */
    int getCode();

    /**
     * <pre>
     * 获取状态信息
     * </pre>
     *
     * @return 返回状态信息
     */
    String getMessage();

}
