package com.zhiyi.mjxgz.vo;

import io.swagger.annotations.ApiModelProperty;

public class VerificateCouponInfoVO{
	
	@ApiModelProperty(value ="用户券码")
	private String couponCode;

	@ApiModelProperty(value ="核销店铺ID（注意不是商家id）")
	private String shopId;
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	

}
