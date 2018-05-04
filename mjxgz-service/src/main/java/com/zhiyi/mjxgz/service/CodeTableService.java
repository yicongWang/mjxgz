package com.zhiyi.mjxgz.service;

import java.util.List;

import com.zhiyi.mjxgz.model.CodeTable;

/**
 * 码表值
 * @author wyc
 *
 */
public interface CodeTableService {
	/**
	 * 获取码表列表
	 * @return
	 */
	List<CodeTable> getAllCodeTableList();
	/**
	 * 保存
	 * @param codeTable
	 * @return
	 */
	int save(CodeTable codeTable);
}
