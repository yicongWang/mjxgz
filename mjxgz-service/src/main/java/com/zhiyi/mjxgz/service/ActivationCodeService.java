package com.zhiyi.mjxgz.service;

import com.zhiyi.mjxgz.vo.ActiveInfoVO;

/**
 * 激活码
 * @author wyc
 *
 */
public interface ActivationCodeService {

	void useActiveCode(ActiveInfoVO activeInfoVO, String accountId);

	void createActivationCode(int batch, int number);
	
}
