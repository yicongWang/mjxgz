package com.zhiyi.mjxgz.dao;

import com.zhiyi.mjxgz.model.VipOrder;
import com.zhiyi.mjxgz.model.VipOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VipOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_order
     *
     * @mbggenerated
     */
    int countByExample(VipOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_order
     *
     * @mbggenerated
     */
    int deleteByExample(VipOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_order
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String orderNumber);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_order
     *
     * @mbggenerated
     */
    int insert(VipOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_order
     *
     * @mbggenerated
     */
    int insertSelective(VipOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_order
     *
     * @mbggenerated
     */
    List<VipOrder> selectByExample(VipOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_order
     *
     * @mbggenerated
     */
    VipOrder selectByPrimaryKey(String orderNumber);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_order
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") VipOrder record, @Param("example") VipOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_order
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") VipOrder record, @Param("example") VipOrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(VipOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table vip_order
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(VipOrder record);
}