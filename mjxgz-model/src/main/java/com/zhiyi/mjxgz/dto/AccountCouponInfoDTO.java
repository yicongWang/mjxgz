package com.zhiyi.mjxgz.dto;

import io.swagger.annotations.ApiModelProperty;

public class AccountCouponInfoDTO{
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
	@ApiModelProperty(value ="状态-1：过期 0：未使用 1:已使用")
	private String status;
	@ApiModelProperty(value ="券id")
	private String couponId;
	@ApiModelProperty(value ="状态名称")
	private String statusName;
	@ApiModelProperty(value ="券使用开始日期")
	private String startTime;
	@ApiModelProperty(value ="券使用截止日期")
	private String endTime;
	@ApiModelProperty(value ="地址")
	private String address;
	@ApiModelProperty(value ="联系方式")
	private String telphone;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
}
