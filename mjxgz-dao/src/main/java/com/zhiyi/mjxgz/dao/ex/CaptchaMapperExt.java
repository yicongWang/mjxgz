package com.zhiyi.mjxgz.dao.ex;

import org.apache.ibatis.annotations.Mapper;

import com.zhiyi.mjxgz.dao.CaptchaMapper;
import com.zhiyi.mjxgz.dto.CaptchaDTO;

/**
 * 验证码Mapper
 * Created by DW on 2017/6/28.
 */
@Mapper
public interface CaptchaMapperExt extends CaptchaMapper {

    /**
     * 根据条件获取最新的验证码
     * @param captchaDTO
     * @return
     */
    CaptchaDTO getLatestCaptcha(CaptchaDTO captchaDTO);

    /**
     * 根据条件获取已保存的验证码条数
     * @param captchaDTO
     * @return
     */
    int queryCount(CaptchaDTO captchaDTO);
}
