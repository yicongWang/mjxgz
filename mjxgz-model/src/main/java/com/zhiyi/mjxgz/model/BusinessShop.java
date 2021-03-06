package com.zhiyi.mjxgz.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class BusinessShop implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.business_id
     *
     * @mbggenerated
     */
    private Long businessId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.shop_name
     *
     * @mbggenerated
     */
    private String shopName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.account_id
     *
     * @mbggenerated
     */
    private String accountId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.province_id
     *
     * @mbggenerated
     */
    private Integer provinceId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.city_id
     *
     * @mbggenerated
     */
    private Integer cityId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.area_id
     *
     * @mbggenerated
     */
    private Integer areaId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.address
     *
     * @mbggenerated
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.region_business_id
     *
     * @mbggenerated
     */
    private Integer regionBusinessId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.latitude
     *
     * @mbggenerated
     */
    private BigDecimal latitude;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.longitude
     *
     * @mbggenerated
     */
    private BigDecimal longitude;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.baidu_map_id
     *
     * @mbggenerated
     */
    private String baiduMapId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.business_start
     *
     * @mbggenerated
     */
    private String businessStart;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.business_end
     *
     * @mbggenerated
     */
    private String businessEnd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.province_name
     *
     * @mbggenerated
     */
    private String provinceName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.city_name
     *
     * @mbggenerated
     */
    private String cityName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.area_name
     *
     * @mbggenerated
     */
    private String areaName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.telphone
     *
     * @mbggenerated
     */
    private String telphone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.linkman
     *
     * @mbggenerated
     */
    private String linkman;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.create_user_id
     *
     * @mbggenerated
     */
    private String createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.modify_user_id
     *
     * @mbggenerated
     */
    private String modifyUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_shop.modify_time
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table business_shop
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.id
     *
     * @return the value of business_shop.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.id
     *
     * @param id the value for business_shop.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.business_id
     *
     * @return the value of business_shop.business_id
     *
     * @mbggenerated
     */
    public Long getBusinessId() {
        return businessId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.business_id
     *
     * @param businessId the value for business_shop.business_id
     *
     * @mbggenerated
     */
    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.shop_name
     *
     * @return the value of business_shop.shop_name
     *
     * @mbggenerated
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.shop_name
     *
     * @param shopName the value for business_shop.shop_name
     *
     * @mbggenerated
     */
    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.account_id
     *
     * @return the value of business_shop.account_id
     *
     * @mbggenerated
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.account_id
     *
     * @param accountId the value for business_shop.account_id
     *
     * @mbggenerated
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.province_id
     *
     * @return the value of business_shop.province_id
     *
     * @mbggenerated
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.province_id
     *
     * @param provinceId the value for business_shop.province_id
     *
     * @mbggenerated
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.city_id
     *
     * @return the value of business_shop.city_id
     *
     * @mbggenerated
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.city_id
     *
     * @param cityId the value for business_shop.city_id
     *
     * @mbggenerated
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.area_id
     *
     * @return the value of business_shop.area_id
     *
     * @mbggenerated
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.area_id
     *
     * @param areaId the value for business_shop.area_id
     *
     * @mbggenerated
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.address
     *
     * @return the value of business_shop.address
     *
     * @mbggenerated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.address
     *
     * @param address the value for business_shop.address
     *
     * @mbggenerated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.region_business_id
     *
     * @return the value of business_shop.region_business_id
     *
     * @mbggenerated
     */
    public Integer getRegionBusinessId() {
        return regionBusinessId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.region_business_id
     *
     * @param regionBusinessId the value for business_shop.region_business_id
     *
     * @mbggenerated
     */
    public void setRegionBusinessId(Integer regionBusinessId) {
        this.regionBusinessId = regionBusinessId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.latitude
     *
     * @return the value of business_shop.latitude
     *
     * @mbggenerated
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.latitude
     *
     * @param latitude the value for business_shop.latitude
     *
     * @mbggenerated
     */
    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.longitude
     *
     * @return the value of business_shop.longitude
     *
     * @mbggenerated
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.longitude
     *
     * @param longitude the value for business_shop.longitude
     *
     * @mbggenerated
     */
    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.baidu_map_id
     *
     * @return the value of business_shop.baidu_map_id
     *
     * @mbggenerated
     */
    public String getBaiduMapId() {
        return baiduMapId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.baidu_map_id
     *
     * @param baiduMapId the value for business_shop.baidu_map_id
     *
     * @mbggenerated
     */
    public void setBaiduMapId(String baiduMapId) {
        this.baiduMapId = baiduMapId == null ? null : baiduMapId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.business_start
     *
     * @return the value of business_shop.business_start
     *
     * @mbggenerated
     */
    public String getBusinessStart() {
        return businessStart;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.business_start
     *
     * @param businessStart the value for business_shop.business_start
     *
     * @mbggenerated
     */
    public void setBusinessStart(String businessStart) {
        this.businessStart = businessStart == null ? null : businessStart.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.business_end
     *
     * @return the value of business_shop.business_end
     *
     * @mbggenerated
     */
    public String getBusinessEnd() {
        return businessEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.business_end
     *
     * @param businessEnd the value for business_shop.business_end
     *
     * @mbggenerated
     */
    public void setBusinessEnd(String businessEnd) {
        this.businessEnd = businessEnd == null ? null : businessEnd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.province_name
     *
     * @return the value of business_shop.province_name
     *
     * @mbggenerated
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.province_name
     *
     * @param provinceName the value for business_shop.province_name
     *
     * @mbggenerated
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName == null ? null : provinceName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.city_name
     *
     * @return the value of business_shop.city_name
     *
     * @mbggenerated
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.city_name
     *
     * @param cityName the value for business_shop.city_name
     *
     * @mbggenerated
     */
    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.area_name
     *
     * @return the value of business_shop.area_name
     *
     * @mbggenerated
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.area_name
     *
     * @param areaName the value for business_shop.area_name
     *
     * @mbggenerated
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.telphone
     *
     * @return the value of business_shop.telphone
     *
     * @mbggenerated
     */
    public String getTelphone() {
        return telphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.telphone
     *
     * @param telphone the value for business_shop.telphone
     *
     * @mbggenerated
     */
    public void setTelphone(String telphone) {
        this.telphone = telphone == null ? null : telphone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.linkman
     *
     * @return the value of business_shop.linkman
     *
     * @mbggenerated
     */
    public String getLinkman() {
        return linkman;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.linkman
     *
     * @param linkman the value for business_shop.linkman
     *
     * @mbggenerated
     */
    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.create_user_id
     *
     * @return the value of business_shop.create_user_id
     *
     * @mbggenerated
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.create_user_id
     *
     * @param createUserId the value for business_shop.create_user_id
     *
     * @mbggenerated
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.create_time
     *
     * @return the value of business_shop.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.create_time
     *
     * @param createTime the value for business_shop.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.modify_user_id
     *
     * @return the value of business_shop.modify_user_id
     *
     * @mbggenerated
     */
    public String getModifyUserId() {
        return modifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.modify_user_id
     *
     * @param modifyUserId the value for business_shop.modify_user_id
     *
     * @mbggenerated
     */
    public void setModifyUserId(String modifyUserId) {
        this.modifyUserId = modifyUserId == null ? null : modifyUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_shop.modify_time
     *
     * @return the value of business_shop.modify_time
     *
     * @mbggenerated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_shop.modify_time
     *
     * @param modifyTime the value for business_shop.modify_time
     *
     * @mbggenerated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table business_shop
     *
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", businessId=").append(businessId);
        sb.append(", shopName=").append(shopName);
        sb.append(", accountId=").append(accountId);
        sb.append(", provinceId=").append(provinceId);
        sb.append(", cityId=").append(cityId);
        sb.append(", areaId=").append(areaId);
        sb.append(", address=").append(address);
        sb.append(", regionBusinessId=").append(regionBusinessId);
        sb.append(", latitude=").append(latitude);
        sb.append(", longitude=").append(longitude);
        sb.append(", baiduMapId=").append(baiduMapId);
        sb.append(", businessStart=").append(businessStart);
        sb.append(", businessEnd=").append(businessEnd);
        sb.append(", provinceName=").append(provinceName);
        sb.append(", cityName=").append(cityName);
        sb.append(", areaName=").append(areaName);
        sb.append(", telphone=").append(telphone);
        sb.append(", linkman=").append(linkman);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}