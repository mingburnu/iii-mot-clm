package org.iii.module.setting.setAuthority.enums;

/**
 * BusType 業務來源
 * @author Ruo Hsu
 * @version 2014/3/26
 */
public enum BusType {

	/**
	 * 一般
	 */
	DIRECT("一般"),
	
	/**
	 * 共保
	 */
	CO_INSURANCE("共保"),
	
	/**
	 * 國內分進
	 */
	FAC_IN("國內分進"),
	
	/**
	 * 海外分進
	 */
	FOREIGN_FAC_IN("海外分進"),
	
	/**
	 * 合約分進
	 */
	TREATY_IN("合約分進")
	
	;
	
	private String busType;
	
	private BusType(){
		
	}
	
	private BusType(String busType){
		this.busType = busType;
	}

	public String getBusType() {
		return busType;
	}
	
}
