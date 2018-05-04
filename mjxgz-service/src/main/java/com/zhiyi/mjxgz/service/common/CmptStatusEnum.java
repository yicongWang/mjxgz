package com.zhiyi.mjxgz.service.common;

/**
 * 系统类型(FAS系统数据携带)
 * @author  wyc
 *
 */
public enum CmptStatusEnum {
		FIRE("fire"," 火警","出现火警"),
		ABNORMAL("abnormal","故障","出现故障"),
		ISOLATION("isolation", "隔离","被隔离"), 
		ACTION("action", "动作","动作"), 
		NORMAL("normal", "正常","处于正常");  
	    // 成员变量  
		private CmptStatusEnum(String code, String name,String remark) {  
	        this.name = name;  
	        this.code = code;  
	        this.remark = remark;
	    }
	    private String code;  
	    private String name;
	    private String remark;
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		} 
	    
}
