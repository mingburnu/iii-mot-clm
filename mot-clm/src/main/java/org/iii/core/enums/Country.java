package org.iii.core.enums;

/**
 * Country 國籍
 * @author Mark Huang
 * @version 2014/4/6
 */
public enum Country {

	/**
	 * 本國
	 */
	LOCAL("本國"),
	
	/**
	 * 外國
	 */
	FOREIGN("外國")
	
	;
	
	private String country;
	
	private Country(){
		
	}
	
	private Country(String country){
		this.country = country;
	}

	public String getCountry() {
		return country;
	}
	
}
