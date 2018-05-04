package com.zhiyi.mjxgz.model;

import java.io.Serializable;
import java.util.Date;

public class Banner implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column banner.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column banner.banner_title
     *
     * @mbggenerated
     */
    private String bannerTitle;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column banner.banner_url
     *
     * @mbggenerated
     */
    private String bannerUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column banner.banner_img
     *
     * @mbggenerated
     */
    private String bannerImg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column banner.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column banner.sort
     *
     * @mbggenerated
     */
    private Integer sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column banner.banner_type
     *
     * @mbggenerated
     */
    private String bannerType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column banner.relation_id
     *
     * @mbggenerated
     */
    private String relationId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column banner.create_user_id
     *
     * @mbggenerated
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column banner.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column banner.modify_user_id
     *
     * @mbggenerated
     */
    private Long modifyUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column banner.modify_time
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column banner.banner_position
     *
     * @mbggenerated
     */
    private Integer bannerPosition;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table banner
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column banner.id
     *
     * @return the value of banner.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column banner.id
     *
     * @param id the value for banner.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column banner.banner_title
     *
     * @return the value of banner.banner_title
     *
     * @mbggenerated
     */
    public String getBannerTitle() {
        return bannerTitle;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column banner.banner_title
     *
     * @param bannerTitle the value for banner.banner_title
     *
     * @mbggenerated
     */
    public void setBannerTitle(String bannerTitle) {
        this.bannerTitle = bannerTitle == null ? null : bannerTitle.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column banner.banner_url
     *
     * @return the value of banner.banner_url
     *
     * @mbggenerated
     */
    public String getBannerUrl() {
        return bannerUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column banner.banner_url
     *
     * @param bannerUrl the value for banner.banner_url
     *
     * @mbggenerated
     */
    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl == null ? null : bannerUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column banner.banner_img
     *
     * @return the value of banner.banner_img
     *
     * @mbggenerated
     */
    public String getBannerImg() {
        return bannerImg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column banner.banner_img
     *
     * @param bannerImg the value for banner.banner_img
     *
     * @mbggenerated
     */
    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg == null ? null : bannerImg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column banner.status
     *
     * @return the value of banner.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column banner.status
     *
     * @param status the value for banner.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column banner.sort
     *
     * @return the value of banner.sort
     *
     * @mbggenerated
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column banner.sort
     *
     * @param sort the value for banner.sort
     *
     * @mbggenerated
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column banner.banner_type
     *
     * @return the value of banner.banner_type
     *
     * @mbggenerated
     */
    public String getBannerType() {
        return bannerType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column banner.banner_type
     *
     * @param bannerType the value for banner.banner_type
     *
     * @mbggenerated
     */
    public void setBannerType(String bannerType) {
        this.bannerType = bannerType == null ? null : bannerType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column banner.relation_id
     *
     * @return the value of banner.relation_id
     *
     * @mbggenerated
     */
    public String getRelationId() {
        return relationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column banner.relation_id
     *
     * @param relationId the value for banner.relation_id
     *
     * @mbggenerated
     */
    public void setRelationId(String relationId) {
        this.relationId = relationId == null ? null : relationId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column banner.create_user_id
     *
     * @return the value of banner.create_user_id
     *
     * @mbggenerated
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column banner.create_user_id
     *
     * @param createUserId the value for banner.create_user_id
     *
     * @mbggenerated
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column banner.create_time
     *
     * @return the value of banner.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column banner.create_time
     *
     * @param createTime the value for banner.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column banner.modify_user_id
     *
     * @return the value of banner.modify_user_id
     *
     * @mbggenerated
     */
    public Long getModifyUserId() {
        return modifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column banner.modify_user_id
     *
     * @param modifyUserId the value for banner.modify_user_id
     *
     * @mbggenerated
     */
    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column banner.modify_time
     *
     * @return the value of banner.modify_time
     *
     * @mbggenerated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column banner.modify_time
     *
     * @param modifyTime the value for banner.modify_time
     *
     * @mbggenerated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column banner.banner_position
     *
     * @return the value of banner.banner_position
     *
     * @mbggenerated
     */
    public Integer getBannerPosition() {
        return bannerPosition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column banner.banner_position
     *
     * @param bannerPosition the value for banner.banner_position
     *
     * @mbggenerated
     */
    public void setBannerPosition(Integer bannerPosition) {
        this.bannerPosition = bannerPosition;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner
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
        sb.append(", bannerTitle=").append(bannerTitle);
        sb.append(", bannerUrl=").append(bannerUrl);
        sb.append(", bannerImg=").append(bannerImg);
        sb.append(", status=").append(status);
        sb.append(", sort=").append(sort);
        sb.append(", bannerType=").append(bannerType);
        sb.append(", relationId=").append(relationId);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", bannerPosition=").append(bannerPosition);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}