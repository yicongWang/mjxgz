package com.zhiyi.mjxgz.service.common.log;

/**
 * 操作日志
 * Created by DW on 2016/10/7.
 */
public enum OperationLogType {
    /**
     * 新增操作
     */
    ADD("新增"),

    /**
     *删除操作
     */
    DELTE("删除"),

    /**
     * 修改操作
     */
    UPDATE("修改"),
    /**
     * 查询操作
     */
    QUERY("查询"),

    /**
     * 导出Excel操作
     */
    EXPROT("导出Excel"),

    /**
     * 打印操作
     */
    PRINT("打印"),

    /**
     * 下载操作
     */
    DOWNLOAD("下载"),

    /**
     * 上传操作
     */
    UPLOAD("上传");

    private String operationName;

    private OperationLogType(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
