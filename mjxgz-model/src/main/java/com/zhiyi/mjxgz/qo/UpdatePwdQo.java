package com.zhiyi.mjxgz.qo;

import com.zhiyi.mjxgz.common.BasicQo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 修改密码(普通bean)
 * Created by DW on 2016/8/24.
 */
@ApiModel
public class UpdatePwdQo  extends BasicQo{
	@ApiModelProperty(value="用户ID")
    private Long accountId;
	@ApiModelProperty(value="原密码")
    private String oldPass;
	@ApiModelProperty(value="新密码")
    private String newPass;
    public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }
}
