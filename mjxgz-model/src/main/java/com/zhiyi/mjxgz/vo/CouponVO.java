package com.zhiyi.mjxgz.vo;

import io.swagger.annotations.ApiModelProperty;

public class CouponVO{
	
	@ApiModelProperty(value ="用户券码")
	private String couponCode;

	@ApiModelProperty(value ="商家id")
	private String businessId;
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

}
