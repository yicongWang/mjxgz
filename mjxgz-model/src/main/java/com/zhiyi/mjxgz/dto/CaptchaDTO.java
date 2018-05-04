package com.zhiyi.mjxgz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhiyi.mjxgz.model.Captcha;

/**
 * 验证码扩展DTO
 * Created by DW on 2017/6/28.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CaptchaDTO  extends Captcha{

    /**
     * 时间字符串
     */
    private String dateStr;

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }
}
