package com.zhiyi.mjxgz.common;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查询条件的基类
 *
 * Created by Floki on 2017/6/29.
 */
public class BasicCriteria {
    /** 页码 */
    @ApiModelProperty(value = "当前页码(默认第一页)")
    private Integer pageNum = 1;

    /** 每页显示的条数 */
    @ApiModelProperty(value = "每页显示的条数(默认20条)")
    private Integer pageSize = 20;
    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
