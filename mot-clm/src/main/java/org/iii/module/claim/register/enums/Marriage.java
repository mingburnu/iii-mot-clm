package org.iii.module.claim.register.enums;

/**
 * Marriage 婚姻狀況
 * @author Mark Huang 	
 * @version 2014/4/6
 */
public enum Marriage {
	
	
	/**
	 * 未婚
	 */
	UNMARRIED("未婚"),

	/**
	 * 已婚
	 */
	MARRIED("已婚")
	
	;
	
	private String marriage;
	
	private Marriage(){
		
	}
	
	private Marriage(String marriage){
		this.marriage = marriage;
	}

	public String getMarriage() {
		return marriage;
	}
	
}
