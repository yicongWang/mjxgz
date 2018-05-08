package com.zhiyi.mjxgz.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhiyi.mjxgz.dto.GoodsDTO;
import com.zhiyi.mjxgz.model.BusinessCoupon;
import com.zhiyi.mjxgz.model.BusinessShop;

import io.swagger.annotations.ApiModelProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoodsInfoVO extends GoodsDTO{
	
	@ApiModelProperty(value ="商家门店信息列表")
	private List<BusinessShop> shopList;
	

	@ApiModelProperty(value ="商家福利券列表")
	private List<BusinessCoupon> couponList;
	
	
	@ApiModelProperty(value ="商家名称")
	private String businessName;
	
	@ApiModelProperty(value ="福利内容 (最好用webview解析)")
	private String welfareContent;
	
	@ApiModelProperty(value ="特别提醒 (最好用webview解析)")
	private String specialRemind;

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public List<BusinessShop> getShopList() {
		return shopList;
	}

	public void setShopList(List<BusinessShop> shopList) {
		this.shopList = shopList;
	}

	public List<BusinessCoupon> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<BusinessCoupon> couponList) {
		this.couponList = couponList;
	}

	public String getWelfareContent() {
		return welfareContent;
	}

	public void setWelfareContent(String welfareContent) {
		this.welfareContent = welfareContent;
	}

	public String getSpecialRemind() {
		return specialRemind;
	}

	public void setSpecialRemind(String specialRemind) {
		this.specialRemind = specialRemind;
	}
	
	
}