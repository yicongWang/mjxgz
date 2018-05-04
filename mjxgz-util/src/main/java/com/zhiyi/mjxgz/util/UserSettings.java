package com.zhiyi.mjxgz.util;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Created by Huangchen on 2016/5/23.
 */
@ConfigurationProperties(prefix = "user")
public class UserSettings {

    // 默认密码
    private String defaultPwd;

    //短信验证码长度
    private Integer codeNum;

    // 随机密码长度
    private Integer pwdNum;

    // 验证码失效时间
    private String invalidTime;

    // 会话失效时间
    private String sessionInvalid;
    /**
     * 发送验证码限制规则<br/>
     * 【每小时最大发送条数:验证码过期时间(单位分钟)】
     */
    private String captchaLimitRule;
    /**
     * 系统【超级管理员】角色Id
     */
    private Long superAdminRoleId;
    /**
     * 系统【项目管理员】角色Id
     */
    private Long projectAdminRoleId;
    /**
     * 系统【普通用户】角色Id
     */
    private Long commonUserRoleId;
    /**
     * 系统【应用管理员】角色Id
     */
    private Long applicationAdminRoleId;


    //用户url 不需要授权的连接地址集合
    private List<String> urlWithoutAuthorizations;


    public String getDefaultPwd() {
        return defaultPwd;
    }

    public void setDefaultPwd(String defaultPwd) {
        this.defaultPwd = defaultPwd;
    }

    public Integer getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(Integer codeNum) {
        this.codeNum = codeNum;
    }

    public Integer getPwdNum() {
        return pwdNum;
    }

    public void setPwdNum(Integer pwdNum) {
        this.pwdNum = pwdNum;
    }

    public String getInvalidTime() {
        return invalidTime;
    }

    public void setInvalidTime(String invalidTime) {
        this.invalidTime = invalidTime;
    }

    public String getSessionInvalid() {
        return sessionInvalid;
    }

    public void setSessionInvalid(String sessionInvalid) {
        this.sessionInvalid = sessionInvalid;
    }

    public List<String> getUrlWithoutAuthorizations() {
        return urlWithoutAuthorizations;
    }

    public void setUrlWithoutAuthorizations(List<String> urlWithoutAuthorizations) {
        this.urlWithoutAuthorizations = urlWithoutAuthorizations;
    }

    public String getCaptchaLimitRule() {
        return captchaLimitRule;
    }

    public void setCaptchaLimitRule(String captchaLimitRule) {
        this.captchaLimitRule = captchaLimitRule;
    }

    public Long getSuperAdminRoleId() {
        return superAdminRoleId;
    }

    public void setSuperAdminRoleId(Long superAdminRoleId) {
        this.superAdminRoleId = superAdminRoleId;
    }

    public Long getProjectAdminRoleId() {
        return projectAdminRoleId;
    }

    public void setProjectAdminRoleId(Long projectAdminRoleId) {
        this.projectAdminRoleId = projectAdminRoleId;
    }

    public Long getCommonUserRoleId() {
        return commonUserRoleId;
    }

    public void setCommonUserRoleId(Long commonUserRoleId) {
        this.commonUserRoleId = commonUserRoleId;
    }

    public Long getApplicationAdminRoleId() {
        return applicationAdminRoleId;
    }

    public void setApplicationAdminRoleId(Long applicationAdminRoleId) {
        this.applicationAdminRoleId = applicationAdminRoleId;
    }
}