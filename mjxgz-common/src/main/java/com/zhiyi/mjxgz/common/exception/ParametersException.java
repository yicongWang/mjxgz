package com.zhiyi.mjxgz.common.exception;

import java.util.List;
import java.util.Map;

/**
 * 参数异常
 * Created by ONENET-XXMI on 2017/2/16.
 */
public class ParametersException extends  RuntimeException{
    private Map<String,String> errorMap;
    private List<?> data;
    public ParametersException(String message) {
        super(message);
    }
    public ParametersException(Map<String,String> errorMap){
        super("参数验证失败");
        this.errorMap = errorMap;
    }
    public ParametersException(List<?> data){
        super("参数验证失败");
        this.data = data;
    }
    public ParametersException(String message,List<?> data){
        super(message);
        this.data = data;
    }



    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    public List<?> getData() {
        return data;
    }
    public void setData(List<?> data) {
        this.data = data;
    }
}
