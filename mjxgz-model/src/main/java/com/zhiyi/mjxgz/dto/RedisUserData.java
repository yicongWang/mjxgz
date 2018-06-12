package com.zhiyi.mjxgz.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

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
    private String id;
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
    private String avatarUrl;

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

    private String vipCode;
    
    public String getVipCode() {
		return vipCode;
	}

	public void setVipCode(String vipCode) {
		this.vipCode = vipCode;
	}

	private String openid;
    private String nickName;
    //会员到期时间
    private Date expireTime;
    
    public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	private String country;
    private String province;
    private String city;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

   

    public String getId() {
		return id;
	}

	public void setId(String id) {
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

 


    public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
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
