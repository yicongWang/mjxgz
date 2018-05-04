package com.zhiyi.mjxgz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * 用于Redis保存用户信息的普通bean<br/>
 * 【保存：基本信息，权限菜单，token等】<br/>
 * Created by DW on 2017/6/28.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RedisUserData implements Serializable {
    /**
     * token
     */
    private String accessToken;
    /**
     * 用户id
     */
    private Long id;
    /**
     * 登录帐号
     */
    private String account;
    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobilePhone;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 头像路径
     */
    private String headImgPath;

    /**
     * 角色
     */
    private String roleName;

    /**
     * 角色名称
     */
    private String roleIdentity;
    
    /**
     * 登录时间
     */
    private String loginTime;

  
    /**
     * 请求ip [不返回前端]
     */
    private String requestIP;

    /**
     * （请求）终端类型 [不返回前端]
     */
    private String terminalType;

   

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }


    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadImgPath() {
        return headImgPath;
    }

    public void setHeadImgPath(String headImgPath) {
        this.headImgPath = headImgPath;
    }


    public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleIdentity() {
		return roleIdentity;
	}

	public void setRoleIdentity(String roleIdentity) {
		this.roleIdentity = roleIdentity;
	}

	public String getRequestIP() {
        return requestIP;
    }

    public void setRequestIP(String requestIP) {
        this.requestIP = requestIP;
    }

    public String getTerminalType() {
        return terminalType;
    }

    public void setTerminalType(String terminalType) {
        this.terminalType = terminalType;
    }

}
