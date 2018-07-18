package com.zhiyi.mjxgz.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhiyi.mjxgz.common.jsonserializer.YMDHMSDateSerializer;

import io.swagger.annotations.ApiModelProperty;

/**
 * 商品信息
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoodsDTO{
	public String getOldPrice() {
		return oldPrice;
	}
	public void setOldPrice(String oldPrice) {
		this.oldPrice = oldPrice;
	}
	@JsonSerialize(using = YMDHMSDateSerializer.class)
	@ApiModelProperty(value ="活动结束日期")
	private Date activeTimeEnd;
	@JsonSerialize(using = YMDHMSDateSerializer.class)
	@ApiModelProperty(value ="活动开始日期")
	private Date activeTimeStart;
	@ApiModelProperty(value ="商家Id")
	private Long businessId;
	@ApiModelProperty(value ="分类ID")
	private Long categoryId;
	@ApiModelProperty(value ="商品名称")
	private String goodsName;
	@ApiModelProperty(value ="商品简介/标题")
	private String goodsTitle;
	@ApiModelProperty(value ="产品Id")
	private Long goodsId;
	@ApiModelProperty(value ="图片url")
	private String goodsImg;
	@ApiModelProperty(value ="价格")
	private String price;
	@ApiModelProperty(value ="原价")
	private String oldPrice;
	@ApiModelProperty(value ="剩余数量")
	private Long surplusQuantity;
	@ApiModelProperty(value ="总数量")
	private Long totalQuantity;
	@ApiModelProperty(value ="单位")
	private String unit;
	public Date getActiveTimeEnd() {
		return activeTimeEnd;
	}
	public void setActiveTimeEnd(Date activeTimeEnd) {
		this.activeTimeEnd = activeTimeEnd;
	}
	public Date getActiveTimeStart() {
		return activeTimeStart;
	}
	public void setActiveTimeStart(Date activeTimeStart) {
		this.activeTimeStart = activeTimeStart;
	}
	public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsTitle() {
		return goodsTitle;
	}
	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsImg() {
		return goodsImg;
	}
	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Long getSurplusQuantity() {
		return surplusQuantity;
	}
	public void setSurplusQuantity(Long surplusQuantity) {
		this.surplusQuantity = surplusQuantity;
	}
	public Long getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(Long totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
