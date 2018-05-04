package com.zhiyi.mjxgz.common.response;

import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * Created by ztz on 2016/7/4.
 */
public class PageResponse<T> extends BaseResponse{
    /**
     * 对象信息
     */
    private PageInfo<T> data;

    public PageResponse() {
    }

    public PageResponse(int code,String msg,Map<String,String> errorMap){
        super(code, msg);
        this.errorMap = errorMap;
    }

    private Map<String,String> errorMap;

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    public PageResponse(int code) {
        super(code);
    }

    public PageResponse(int code, String msg) {
        super(code, msg);
    }

    public PageInfo<T> getData() {
        return data;
    }


    public void setData(PageInfo<T> data) {
        this.data = data;
    }

    public static PageResponse success(Object data){
        PageResponse pageResponse = new PageResponse();
        pageResponse.setData((PageInfo) data);
        pageResponse.setCode(ResponseCode.SUCCESS);
        pageResponse.setMsg("success");
        return pageResponse;
    }



}
