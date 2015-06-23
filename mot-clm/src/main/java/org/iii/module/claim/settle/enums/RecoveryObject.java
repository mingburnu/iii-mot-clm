package org.iii.module.claim.settle.enums;

/**
 * RecoveryObject 追償對象
 * @author Gina Chen
 * @version 2014/4/7
 */
public enum RecoveryObject {

	/**
	 * 加害人
	 */
	OFFENDER("加害人"),
	
	/**
	 * 保險公司
	 */
	INSURER("保險公司")
	
	;
	
	private String recoveryObject;
	
	private RecoveryObject(){
		
	}
	
	private RecoveryObject(String recoveryObject){
		this.recoveryObject = recoveryObject;
	}

	public String getRecoveryObject() {
		return recoveryObject;
	}
	
	
}
