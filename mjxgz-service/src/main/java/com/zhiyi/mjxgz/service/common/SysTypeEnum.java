package com.zhiyi.mjxgz.service.common;

public enum SysTypeEnum {
		FIRE("fire"," 火警系统"),
		WATER("water","水系统");  
	    // 成员变量  
		private SysTypeEnum(String code, String name) {  
	        this.name = name;  
	        this.code = code;  
	    }
	    private String code;  
	    private String name;
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
