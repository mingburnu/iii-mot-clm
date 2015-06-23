package org.iii.core.enums;

/**
 * Sex 性別
 * @author Mark Huang
 * @version 2014/4/6
 */
public enum Sex {

	/**
	 * 男
	 */
	BOY("男"),
	
	/**
	 * 女
	 */
	GIRL("女")
	
	;
	
	private String sex;
	
	private Sex(){
		
	}
	
	private Sex(String sex){
		this.sex = sex;
	}

	public String getSex() {
		return sex;
	}
	
}
