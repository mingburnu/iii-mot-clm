package org.iii.module.claim.settle.enums;

/**
 * ClosedType 結案別
 * @author Gina Chen
 * @version 2014/5/13
 */
public enum ClosedType {

	
	
	/**
	 * 處理中
	 */
	PROCESS("處理中"),
	
	/**
	 * 結案轉追償
	 */
	RECOVERY("結案轉追償"),
	
	/**
	 * 全結
	 */
	CLOSED("全結")
	
	;
	
	private String closedType;
	
	private ClosedType(){
		
	}
	
	private ClosedType(String closedType){
		this.closedType = closedType;
	}

	public String getClosedType() {
		return closedType;
	}
	
	
}
