package com.zhiyi.mjxgz.vo;

import io.swagger.annotations.ApiModelProperty;

public class MealOrderInfoVO{
	@ApiModelProperty(value ="套餐id")
	private String vipMealId;
	@ApiModelProperty(value ="账号id")
	private String accountId;
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getVipMealId() {
		return vipMealId;
	}

	public void setVipMealId(String vipMealId) {
		this.vipMealId = vipMealId;
	}
	
	
}
