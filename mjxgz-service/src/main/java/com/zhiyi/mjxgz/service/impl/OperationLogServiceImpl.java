package com.zhiyi.mjxgz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhiyi.mjxgz.dao.ex.OperationLogMapperExt;
import com.zhiyi.mjxgz.dto.OperationLogDTO;
import com.zhiyi.mjxgz.model.OperationLog;
import com.zhiyi.mjxgz.model.OperationLogExample;
import com.zhiyi.mjxgz.service.OperationLogService;

/**
 * 操作日志ServiceImpl
 * Created by DW on 2017/6/27.
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {
    @Autowired
    private OperationLogMapperExt operationLogMapperExt;

    @Override
    public int add(OperationLog operationLog) {
        return operationLogMapperExt.insertSelective(operationLog);
    }

    @Override
    public List<OperationLog> find(OperationLog operationLog) {
        OperationLogExample operationLogExample = new OperationLogExample();
        OperationLogExample.Criteria criteria = operationLogExample.createCriteria();
        if(operationLog!=null){
            if(!StringUtils.isEmpty(operationLog.getOperationType())){
                criteria.andOperationTypeEqualTo(operationLog.getOperationType());
            }

            if(!StringUtils.isEmpty(operationLog.getOperationDesc())){
                criteria.andOperationDescLike("%"+operationLog.getOperationDesc()+"%");
            }
            if (!StringUtils.isEmpty(operationLog.getTerminalType())) {
                criteria.andTerminalTypeEqualTo(operationLog.getTerminalType());
            }

        }
        return operationLogMapperExt.selectByExample(operationLogExample);
    }

    @Override
    public PageInfo<OperationLogDTO> findPage(OperationLogDTO operationLogDTO, Integer pageNum, Integer pageSize) {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 10 : pageSize;
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(operationLogMapperExt.find(operationLogDTO));
    }
}
