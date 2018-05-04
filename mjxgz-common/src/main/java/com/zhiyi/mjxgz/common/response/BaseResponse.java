package com.zhiyi.mjxgz.common.response;

/**
 * Created by ztz on 2016/7/5.
 */
public class BaseResponse {
    /**
     * 返回码
     */
    private int code;
    /**
     * 提示信息
     */
    private String msg;

    public BaseResponse() {

    }

    public BaseResponse(int code) {
        this.code = code;
    }

    public BaseResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

