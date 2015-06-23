package org.iii.core.enums;

/**
 * FeeStatus 繳費情形
 * @author Rodrigo Chun-Ming Chu
 * @version 2014/5/13
 */
public enum FeeStatus {

	paid("已收足"),
	
	unpaid("未收足")
	
	;
	
	private String feeStatus;
	
	private FeeStatus(){
		
	}

	private FeeStatus(String feeStatus){
		this.feeStatus = feeStatus;
	}
	
	public String getFeeStatus() {
		return feeStatus;
	}
}