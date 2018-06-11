package com.zhiyi.mjxgz.dao;

import com.zhiyi.mjxgz.model.AccountVipRecord;
import com.zhiyi.mjxgz.model.AccountVipRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountVipRecordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_vip_record
     *
     * @mbggenerated
     */
    int countByExample(AccountVipRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_vip_record
     *
     * @mbggenerated
     */
    int deleteByExample(AccountVipRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_vip_record
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_vip_record
     *
     * @mbggenerated
     */
    int insert(AccountVipRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_vip_record
     *
     * @mbggenerated
     */
    int insertSelective(AccountVipRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_vip_record
     *
     * @mbggenerated
     */
    List<AccountVipRecord> selectByExample(AccountVipRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_vip_record
     *
     * @mbggenerated
     */
    AccountVipRecord selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_vip_record
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") AccountVipRecord record, @Param("example") AccountVipRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_vip_record
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") AccountVipRecord record, @Param("example") AccountVipRecordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_vip_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(AccountVipRecord record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table account_vip_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(AccountVipRecord record);
}