package org.iii.core.security.secUser.enums;

/**
 * UserType 
 * @author Mark Huang
 * @version 2014/5/14
 */
public enum UserType {

	/**
	 * a系統管理員
	 */
	ADMIN("系統管理員"),
	
	/**
	 * 理賠人員
	 */
	CALIMSTAFF("理賠人員"),
	
	/**
	 * 經辦
	 */
	PROCESS("經辦"),
	
	/**
	 * 法務人員
	 */
	LAWSTAFF("法務人員"),
	
	/**
	 * 主管
	 */
	MANAGER("主管"),
	
	;
	
	private String userType;
	
	private UserType(){
		
	}
	
	private UserType(String userType){
		this.userType = userType;
	}

	public String getUserType() {
		return userType;
	}

}
