package com.zhiyi.mjxgz.service.common;

public enum RoleEnum {
	
	    ADMIN("admin"," 超级管理员"),
	    LEVEL_ONE("level-one","一级用户"),
	    LEVEL_TWO("level-two", "二级用户"), 
	    LEVEL_THREE("level-three", "三级用户");  
	
	    // 成员变量  
		private RoleEnum(String code, String name) {  
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
