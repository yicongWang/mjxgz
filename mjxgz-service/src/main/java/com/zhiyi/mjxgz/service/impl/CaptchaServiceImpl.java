package com.zhiyi.mjxgz.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.common.constants.Constants;
import com.zhiyi.mjxgz.common.exception.DataAlreadyExistsException;
import com.zhiyi.mjxgz.common.exception.DataNotExistsException;
import com.zhiyi.mjxgz.dao.ex.CaptchaMapperExt;
import com.zhiyi.mjxgz.dto.CaptchaDTO;
import com.zhiyi.mjxgz.model.Account;
import com.zhiyi.mjxgz.model.Captcha;
import com.zhiyi.mjxgz.service.AccountService;
import com.zhiyi.mjxgz.service.CaptchaService;
import com.zhiyi.mjxgz.util.DateUtil;
import com.zhiyi.mjxgz.util.JwtHelper;
import com.zhiyi.mjxgz.util.RandCodeUtil;
import com.zhiyi.mjxgz.util.UserSettings;
import com.zhiyi.mjxgz.util.ValidatorUtil;

/**
 * 验证码ServiceImpl
 * Created by DW on 2017/6/28.
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    private Logger logger = LoggerFactory.getLogger(CaptchaServiceImpl.class);

    @Autowired
    private CaptchaMapperExt captchaMapperExt;

    @Autowired
    private UserSettings userSettings;
    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public String add(CaptchaDTO captchaDTO) {
        //提交前验证参数的有效性
        ValidatorUtil.validateParameter(this.checkValidParameter(captchaDTO, 1));
        //根据验证码类型为"忘记密码" 则需要判断手机号是否存在
        if(Constants.CAPTCHA_TYPE_RETRIEVE_PASSWORD.equals(captchaDTO.getType())){
            Account conditionSysUser = new Account();
            conditionSysUser.setMobilePhone(captchaDTO.getMobilePhone());
            List<Account> list = null;//accountService.findAccounts(conditionSysUser);
            if(list==null || list.size()<=0){
                throw new DataNotExistsException("系统通过该手机号未查询到数据，请检查！");
            }

        }
        //验证发送条数是否已超限制
        Date now = DateUtil.now();
        captchaDTO.setDateStr(DateUtil.dateToStr(now,"yyyy-MM-dd HH"));
        int count = captchaMapperExt.queryCount(captchaDTO);
        int maxCount = Integer.parseInt(userSettings.getCaptchaLimitRule().split(":")[0]);
        if(count < maxCount){
            // TODO: 2017/7/6  调用第三方接口发送手机短信，并把该条记录保存到数据库中
            String captcha = RandCodeUtil.getRandomSmsCode(6);
            captchaDTO.setGenerateTime(now);
            captchaDTO.setCaptcha(captcha);
            if(captchaMapperExt.insertSelective(captchaDTO)>0) {
                return captcha;
            }else
                throw new DataNotExistsException("影响行数为0，新增验证失败！");
        }else{
            throw new DataAlreadyExistsException("系统检测到该手机号在同一时段请求多次，请稍后再试");
        }
    }


    @Override
    public String validation(CaptchaDTO captchaDTO,Device device) {
        CaptchaDTO queryCaptchaDTO = captchaMapperExt.getLatestCaptcha(captchaDTO);
        if(queryCaptchaDTO==null){
            throw new DataNotExistsException("通过手机号和验证类型查询数据为空");
        }
        //1.先校验验证码是否相等 且时间是否过期
        int activeMinutes = Integer.parseInt(userSettings.getCaptchaLimitRule().split(":")[1]);
        Date maxActiveDate = DateUtils.addMinutes(queryCaptchaDTO.getGenerateTime(), activeMinutes);
        if(captchaDTO.getCaptcha().equals(queryCaptchaDTO.getCaptcha())){
            //如果当前时间 大于 最大有效时间  表示已过期
            Date now = DateUtil.now();
            if(DateUtils.truncatedCompareTo(now,maxActiveDate,Calendar.SECOND)>0){
                throw new DataNotExistsException("验证码已过期");
            }else{
                //更改验证码 为已使用并设置验证时间
                Captcha captcha = new Captcha();
                captcha.setId(queryCaptchaDTO.getId());
                captcha.setIsValidation(true);
                captcha.setValidationTime(now);
                int count = captchaMapperExt.updateByPrimaryKeySelective(captcha);
                if(count<=0){
                    throw new DataNotExistsException("修改验证码状态信息失败");
                }
                return jwtHelper.createJWT(captchaDTO.getMobilePhone(), device);
            }
        }else {
            throw new DataNotExistsException("输入的验证码有误");
        }
    }


    /**
     * 验证数据
     * @param captchaDTO 需检查的bean
     * @param type       1:新增；2：修改；3：查询
     * @return commonResponse
     */
    private Map<String, String> checkValidParameter(CaptchaDTO captchaDTO, int type) {
        Map<String, String> errorMap = new HashMap<String, String>();
        if (type == 1) {
            if (!ValidatorUtil.isMobile(captchaDTO.getMobilePhone())) {
                errorMap.put("mobilePhone", "格式错误");
                logger.info("Parameter [mobilePhone] check failed.");
            }
            if (!ValidatorUtil.validParameterAlert2(captchaDTO.getType(), "type", true, 1, 10, 4, errorMap)) {
                logger.info("Parameter [type] check failed.");
            }
        }
        if (type == 2) {
            if (captchaDTO.getId() == null || captchaDTO.getId() <= 0) {
                errorMap.put("id", "为空或小于等于0");
                logger.info("Parameter [id] check failed.");
            }
        }else if (type == 3) {//查询

        }
        return errorMap;
    }

}
