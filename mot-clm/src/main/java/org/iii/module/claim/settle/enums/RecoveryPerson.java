package org.iii.module.claim.settle.enums;

/**
 * RecoveryPerson 追償人員
 * @author Gina Chen
 * @version 2014/5/15
 */
public enum RecoveryPerson {

	/**
	 * 理賠
	 */
	CLM("理賠")
	
	;
	
	private String recoveryPerson;
	
	private RecoveryPerson(){
		
	}
	
	private RecoveryPerson(String recoveryPerson){
		this.recoveryPerson = recoveryPerson ;
	}

	public String getRecoveryPerson() {
		return recoveryPerson;
	}
	
	
}
