package com.zhiyi.mjxgz.qo;
import com.zhiyi.mjxgz.common.BasicQo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class LoginQo  extends BasicQo{
	@ApiModelProperty(name = "账号")
   public String account;
	@ApiModelProperty(name = "密码")
   public String password;
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
   
}
