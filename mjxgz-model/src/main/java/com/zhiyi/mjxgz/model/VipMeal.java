package com.zhiyi.mjxgz.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class VipMeal implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vip_meal.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vip_meal.type
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vip_meal.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vip_meal.old_price
     *
     * @mbggenerated
     */
    private BigDecimal oldPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vip_meal.price
     *
     * @mbggenerated
     */
    private BigDecimal price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vip_meal.day
     *
     * @mbggenerated
     */
    private Integer day;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vip_meal.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vip_meal.status
     *
     * @mbggenerated
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vip_meal.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vip_meal.create_user
     *
     * @mbggenerated
     */
    private String createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vip_meal.modify_time
     *
     * @mbggenerated
     */
    private Date modifyTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column vip_meal.modify_user
     *
     * @mbggenerated
     */
    private String modifyUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table vip_meal
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vip_meal.id
     *
     * @return the value of vip_meal.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vip_meal.id
     *
     * @param id the value for vip_meal.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vip_meal.type
     *
     * @return the value of vip_meal.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vip_meal.type
     *
     * @param type the value for vip_meal.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vip_meal.name
     *
     * @return the value of vip_meal.name
     *
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vip_meal.name
     *
     * @param name the value for vip_meal.name
     *
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vip_meal.old_price
     *
     * @return the value of vip_meal.old_price
     *
     * @mbggenerated
     */
    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vip_meal.old_price
     *
     * @param oldPrice the value for vip_meal.old_price
     *
     * @mbggenerated
     */
    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vip_meal.price
     *
     * @return the value of vip_meal.price
     *
     * @mbggenerated
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vip_meal.price
     *
     * @param price the value for vip_meal.price
     *
     * @mbggenerated
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vip_meal.day
     *
     * @return the value of vip_meal.day
     *
     * @mbggenerated
     */
    public Integer getDay() {
        return day;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vip_meal.day
     *
     * @param day the value for vip_meal.day
     *
     * @mbggenerated
     */
    public void setDay(Integer day) {
        this.day = day;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vip_meal.content
     *
     * @return the value of vip_meal.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vip_meal.content
     *
     * @param content the value for vip_meal.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vip_meal.status
     *
     * @return the value of vip_meal.status
     *
     * @mbggenerated
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vip_meal.status
     *
     * @param status the value for vip_meal.status
     *
     * @mbggenerated
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vip_meal.create_time
     *
     * @return the value of vip_meal.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vip_meal.create_time
     *
     * @param createTime the value for vip_meal.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vip_meal.create_user
     *
     * @return the value of vip_meal.create_user
     *
     * @mbggenerated
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vip_meal.create_user
     *
     * @param createUser the value for vip_meal.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vip_meal.modify_time
     *
     * @return the value of vip_meal.modify_time
     *
     * @mbggenerated
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vip_meal.modify_time
     *
     * @param modifyTime the value for vip_meal.modify_time
     *
     * @mbggenerated
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column vip_meal.modify_user
     *
     * @return the value of vip_meal.modify_user
     *
     * @mbggenerated
     */
    public String getModifyUser() {
        return modifyUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column vip_meal.modify_user
     *
     * @param modifyUser the value for vip_meal.modify_user
     *
     * @mbggenerated
     */
    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser == null ? null : modifyUser.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_meal
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
        sb.append(", type=").append(type);
        sb.append(", name=").append(name);
        sb.append(", oldPrice=").append(oldPrice);
        sb.append(", price=").append(price);
        sb.append(", day=").append(day);
        sb.append(", content=").append(content);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", createUser=").append(createUser);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", modifyUser=").append(modifyUser);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}