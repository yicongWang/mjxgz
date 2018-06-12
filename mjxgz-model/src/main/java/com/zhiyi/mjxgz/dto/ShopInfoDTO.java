package com.zhiyi.mjxgz.dto;

import io.swagger.annotations.ApiModelProperty;

public class ShopInfoDTO{
	@ApiModelProperty(value ="商家logo")
	private String logo;
	@ApiModelProperty(value ="店铺名称")
	private String shopName;
	@ApiModelProperty(value ="区")
	private String areaName;
	@ApiModelProperty(value ="店鋪ID")
	private String shopId;
	@ApiModelProperty(value ="省")
	private String provinceName;
	@ApiModelProperty(value ="市")
	private String cityName;
	@ApiModelProperty(value ="商家id")
	private String businessId;
	@ApiModelProperty(value ="开始日期")
	private String businessStart;
	@ApiModelProperty(value ="截止日期")
	private String businessEnd;
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getBusinessStart() {
		return businessStart;
	}
	public void setBusinessStart(String businessStart) {
		this.businessStart = businessStart;
	}
	public String getBusinessEnd() {
		return businessEnd;
	}
	public void setBusinessEnd(String businessEnd) {
		this.businessEnd = businessEnd;
	}
	
}
