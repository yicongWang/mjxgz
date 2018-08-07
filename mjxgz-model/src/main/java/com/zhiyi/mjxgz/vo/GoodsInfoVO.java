package com.zhiyi.mjxgz.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhiyi.mjxgz.dto.GoodsDTO;
import com.zhiyi.mjxgz.model.BusinessCoupon;
import com.zhiyi.mjxgz.model.BusinessShop;
import com.zhiyi.mjxgz.model.GoodsDetail;

import io.swagger.annotations.ApiModelProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoodsInfoVO extends GoodsDTO{
	
	@ApiModelProperty(value ="商家门店信息列表")
	private List<BusinessShop> shopList;
	

	@ApiModelProperty(value ="商家福利券列表")
	private List<BusinessCoupon> couponList;
	
	@ApiModelProperty(value ="产品详情")
	private List<GoodsDetail> goodsDetail;

	@ApiModelProperty(value ="商家名称")
	private String businessName;
	
	@ApiModelProperty(value ="福利内容 (最好用webview解析)")
	private String welfareContent;
	
	@ApiModelProperty(value ="特别提醒 (最好用webview解析)")
	private String specialRemind;

	@ApiModelProperty(value ="商家LOGO")
	private String logo;
	
	@ApiModelProperty(value ="商家简介")
	private String businessIntroduction;
	
	public List<GoodsDetail> getGoodsDetail() {
		return goodsDetail;
	}

	public void setGoodsDetail(List<GoodsDetail> goodsDetail) {
		this.goodsDetail = goodsDetail;
	}
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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getBusinessIntroduction() {
		return businessIntroduction;
	}

	public void setBusinessIntroduction(String businessIntroduction) {
		this.businessIntroduction = businessIntroduction;
	}
	
	
}
