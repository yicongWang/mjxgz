package com.zhiyi.mjxgz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhiyi.mjxgz.dao.ex.CodeTableMapperExt;
import com.zhiyi.mjxgz.model.CodeTable;
import com.zhiyi.mjxgz.model.CodeTableExample;
import com.zhiyi.mjxgz.service.CodeTableService;

/**
 * 码表
 * @author wyc
 *
 */
@Service
public class CodeTableServiceImpl implements  CodeTableService {

    @Autowired
    private CodeTableMapperExt codeTableMapperExt;

	@Override
	public List<CodeTable> getAllCodeTableList() {
		CodeTableExample example = new CodeTableExample();
        return codeTableMapperExt.selectByExample(example);
	}

	@Override
	public int save(CodeTable codeTable) {
		return codeTableMapperExt.insert(codeTable);
	}

}
