package com.zhiyi.mjxgz.model;

import java.io.Serializable;
import java.util.Date;

public class LoginLog implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.login_user
     *
     * @mbggenerated
     */
    private String loginUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.ip
     *
     * @mbggenerated
     */
    private String ip;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.terminal_type
     *
     * @mbggenerated
     */
    private String terminalType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.login_time
     *
     * @mbggenerated
     */
    private Date loginTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sys_login_log.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table sys_login_log
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.id
     *
     * @return the value of sys_login_log.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.id
     *
     * @param id the value for sys_login_log.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.login_user
     *
     * @return the value of sys_login_log.login_user
     *
     * @mbggenerated
     */
    public String getLoginUser() {
        return loginUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.login_user
     *
     * @param loginUser the value for sys_login_log.login_user
     *
     * @mbggenerated
     */
    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser == null ? null : loginUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.ip
     *
     * @return the value of sys_login_log.ip
     *
     * @mbggenerated
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.ip
     *
     * @param ip the value for sys_login_log.ip
     *
     * @mbggenerated
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.terminal_type
     *
     * @return the value of sys_login_log.terminal_type
     *
     * @mbggenerated
     */
    public String getTerminalType() {
        return terminalType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.terminal_type
     *
     * @param terminalType the value for sys_login_log.terminal_type
     *
     * @mbggenerated
     */
    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType == null ? null : terminalType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.login_time
     *
     * @return the value of sys_login_log.login_time
     *
     * @mbggenerated
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.login_time
     *
     * @param loginTime the value for sys_login_log.login_time
     *
     * @mbggenerated
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sys_login_log.remark
     *
     * @return the value of sys_login_log.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sys_login_log.remark
     *
     * @param remark the value for sys_login_log.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_login_log
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", loginUser=").append(loginUser);
        sb.append(", ip=").append(ip);
        sb.append(", terminalType=").append(terminalType);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}