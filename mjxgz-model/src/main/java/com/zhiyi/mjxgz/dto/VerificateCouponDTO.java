package com.zhiyi.mjxgz.dto;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class VerificateCouponDTO{
	
	@ApiModelProperty(value ="券图片")
	private String couponImg;
	@ApiModelProperty(value ="名称")
	private String couponName;
	@ApiModelProperty(value ="标题")
	private String couponTitle;
	@ApiModelProperty(value ="用户券码")
	private String couponCode;
	@ApiModelProperty(value ="用户ID")
	private String accountId;
	@ApiModelProperty(value ="商家ID")
	private String businessId;

	@ApiModelProperty(value ="核销日期")
	private Date verificateTime;
	@ApiModelProperty(value ="核销店铺ID")
	private String shopId;
	public String getCouponImg() {
		return couponImg;
	}
	public void setCouponImg(String couponImg) {
		this.couponImg = couponImg;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getCouponTitle() {
		return couponTitle;
	}
	public void setCouponTitle(String couponTitle) {
		this.couponTitle = couponTitle;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public Date getVerificateTime() {
		return verificateTime;
	}
	public void setVerificateTime(Date verificateTime) {
		this.verificateTime = verificateTime;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

}
