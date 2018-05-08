package com.zhiyi.mjxgz.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zhiyi.mjxgz.model.BusinessCoupon;
import com.zhiyi.mjxgz.model.BusinessShop;

import io.swagger.annotations.ApiModelProperty;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BusinessInfoVO{
	
	@ApiModelProperty(value ="商家门店信息列表")
	private List<BusinessShop> shopList;
	
	@ApiModelProperty(value ="商家Logo图")
	private String logoImg;
	
	public String getLogoImg() {
		return logoImg;
	}

	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}

	@ApiModelProperty(value ="商家编号")
	private String businessId;
	
	@ApiModelProperty(value ="商家名称")
	private String businessName;
	
	@ApiModelProperty(value ="商家简介")
	private String businessIntroduction;
	
	@ApiModelProperty(value ="营业开始时间")
	private String businessStart;
	
	@ApiModelProperty(value ="营业结束日期")
	private String businessEnd;
	
	@ApiModelProperty(value ="评分")
	private String grade;
	
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
	
	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getBusinessIntroduction() {
		return businessIntroduction;
	}

	public void setBusinessIntroduction(String businessIntroduction) {
		this.businessIntroduction = businessIntroduction;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
