package com.zhiyi.mjxgz.service;

import org.springframework.mobile.device.Device;

import com.zhiyi.mjxgz.dto.CaptchaDTO;

/**
 * 验证码接口
 * Created by DW on 2017/6/28.
 */
public interface CaptchaService {

    /**
     * 新增
     * @param captchaDTO
     * @return 验证码
     */
    String add(CaptchaDTO captchaDTO);



    /**
     * 校验验证码
     * @param captchaDTO
     * @param device  当前设备
     * @return
     */
    String validation(CaptchaDTO captchaDTO,Device device);
}
