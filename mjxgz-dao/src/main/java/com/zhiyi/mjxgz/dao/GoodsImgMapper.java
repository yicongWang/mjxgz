package com.zhiyi.mjxgz.dao;

import com.zhiyi.mjxgz.model.GoodsImg;
import com.zhiyi.mjxgz.model.GoodsImgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GoodsImgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_img
     *
     * @mbggenerated
     */
    int countByExample(GoodsImgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_img
     *
     * @mbggenerated
     */
    int deleteByExample(GoodsImgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_img
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_img
     *
     * @mbggenerated
     */
    int insert(GoodsImg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_img
     *
     * @mbggenerated
     */
    int insertSelective(GoodsImg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_img
     *
     * @mbggenerated
     */
    List<GoodsImg> selectByExample(GoodsImgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_img
     *
     * @mbggenerated
     */
    GoodsImg selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_img
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") GoodsImg record, @Param("example") GoodsImgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_img
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") GoodsImg record, @Param("example") GoodsImgExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_img
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(GoodsImg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table goods_img
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(GoodsImg record);
}