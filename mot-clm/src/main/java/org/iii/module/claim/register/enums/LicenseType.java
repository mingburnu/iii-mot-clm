package org.iii.module.claim.register.enums;

/**
 * LicenseType 駕照種類
 * @author Mark Huang
 * @version 2014/4/6
 */
public enum LicenseType {

	/**
	 * 汽車
	 */
	CAR("汽車"),
	
	/**
	 * 機車
	 */
	MOTO("機車")
	;
	
	private String licenseType;
	
	private LicenseType(){
		
	}
	
	private LicenseType(String licenseType){
		this.licenseType = licenseType;
	}

	public String getLicenseType() {
		return licenseType;
	}
	
}
