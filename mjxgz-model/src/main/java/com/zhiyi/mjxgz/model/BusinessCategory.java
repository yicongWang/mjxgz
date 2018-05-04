package com.zhiyi.mjxgz.model;

import java.io.Serializable;
import java.util.Date;

public class BusinessCategory implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_category.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_category.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_category.category_img
     *
     * @mbggenerated
     */
    private String categoryImg;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_category.category_sort
     *
     * @mbggenerated
     */
    private Integer categorySort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_category.create_user_id
     *
     * @mbggenerated
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_category.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_category.modify_user_id
     *
     * @mbggenerated
     */
    private Long modifyUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column business_category.modify_time
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table business_category
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_category.id
     *
     * @return the value of business_category.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_category.id
     *
     * @param id the value for business_category.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_category.name
     *
     * @return the value of business_category.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_category.name
     *
     * @param name the value for business_category.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_category.category_img
     *
     * @return the value of business_category.category_img
     *
     * @mbggenerated
     */
    public String getCategoryImg() {
        return categoryImg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_category.category_img
     *
     * @param categoryImg the value for business_category.category_img
     *
     * @mbggenerated
     */
    public void setCategoryImg(String categoryImg) {
        this.categoryImg = categoryImg == null ? null : categoryImg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_category.category_sort
     *
     * @return the value of business_category.category_sort
     *
     * @mbggenerated
     */
    public Integer getCategorySort() {
        return categorySort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_category.category_sort
     *
     * @param categorySort the value for business_category.category_sort
     *
     * @mbggenerated
     */
    public void setCategorySort(Integer categorySort) {
        this.categorySort = categorySort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_category.create_user_id
     *
     * @return the value of business_category.create_user_id
     *
     * @mbggenerated
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_category.create_user_id
     *
     * @param createUserId the value for business_category.create_user_id
     *
     * @mbggenerated
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_category.create_time
     *
     * @return the value of business_category.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_category.create_time
     *
     * @param createTime the value for business_category.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_category.modify_user_id
     *
     * @return the value of business_category.modify_user_id
     *
     * @mbggenerated
     */
    public Long getModifyUserId() {
        return modifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_category.modify_user_id
     *
     * @param modifyUserId the value for business_category.modify_user_id
     *
     * @mbggenerated
     */
    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column business_category.modify_time
     *
     * @return the value of business_category.modify_time
     *
     * @mbggenerated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column business_category.modify_time
     *
     * @param modifyTime the value for business_category.modify_time
     *
     * @mbggenerated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table business_category
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
        sb.append(", name=").append(name);
        sb.append(", categoryImg=").append(categoryImg);
        sb.append(", categorySort=").append(categorySort);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyUserId=").append(modifyUserId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}