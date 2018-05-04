package com.zhiyi.mjxgz.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zhiyi.mjxgz.dto.OperationLogDTO;
import com.zhiyi.mjxgz.model.OperationLog;

/**
 * 操作日志Service
 * Created by DW on 2017/6/27.
 */
public interface OperationLogService {

    int add(OperationLog operationLog);

    List<OperationLog> find(OperationLog operationLog);

    PageInfo<OperationLogDTO> findPage(OperationLogDTO operationLog, Integer pageNum, Integer pageSize);


}
