package org.iii.module.setting.setCarType.enums;

/**
 * CarUsage 使用方式
 * @author Ruo Hsu
 * @version 2014/3/26
 */
public enum CarUsage {
	
	/**
	 * 自用
	 */
	PERSONAL_USE("自用"),
	
	/**
	 * 營業
	 */
	BUSINESS("營業"),
	
	/**
	 * 自用營業均可使用
	 */
	ALL("自用營業均可使用"),
	
	;
	
	private String carUsage;
	
	private CarUsage(){
		
	}
	
	private CarUsage(String carUsage){
		this.carUsage = carUsage;
	}

	public String getCarUsage() {
		return carUsage;
	}

}
