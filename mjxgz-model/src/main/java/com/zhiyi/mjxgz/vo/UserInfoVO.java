package com.zhiyi.mjxgz.vo;

import io.swagger.annotations.ApiModelProperty;

public class UserInfoVO{
	@ApiModelProperty(value ="昵称")
	private String nickName;
	@ApiModelProperty(value ="头像")
	private String avatarUrl;
	@ApiModelProperty(value ="用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
	private Integer gender;
	@ApiModelProperty(value ="省份")
	private String province;
	@ApiModelProperty(value ="城市")
	private String city;
	@ApiModelProperty(value ="国家")
	private String country;
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
}
