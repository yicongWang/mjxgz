package com.zhiyi.mjxgz.common.response;

import java.util.List;

/**
 * Created by liaoyubo on 2016/7/14.
 */
public class MultiDataResponse<T> extends BaseResponse {

    private List<T> data;

    public MultiDataResponse() {
        super();
    }

    public MultiDataResponse(int code) {
        super(code);
    }

    public MultiDataResponse(int code, String msg) {
        super(code, msg);
    }

    public MultiDataResponse(int code, String msg, List<T> data) {
        super(code, msg);
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public static MultiDataResponse success(){
        MultiDataResponse response = new MultiDataResponse();
        response.setCode(ResponseCode.SUCCESS);
        response.setMsg("导入成功。");
        return response;
    }

}
