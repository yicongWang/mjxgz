package com.zhiyi.mjxgz.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhiyi.mjxgz.common.jsonserializer.YMDHMSDateSerializer;
import com.zhiyi.mjxgz.model.OperationLog;

/**
 * 操作日志扩展DTO
 * Created by DW on 2017/6/27.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OperationLogDTO extends OperationLog {


    @JsonSerialize(using = YMDHMSDateSerializer.class)
    @Override
    public Date getOperationTime() {
        return super.getOperationTime();
    }




}
