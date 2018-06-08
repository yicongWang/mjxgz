package com.zhiyi.mjxgz.vo;

import io.swagger.annotations.ApiModelProperty;

public class ActiveInfoVO{
	@ApiModelProperty(value ="激活码")
	private String code;
	@ApiModelProperty(value ="手机账号")
	private String mobilePhone;
	

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
}
