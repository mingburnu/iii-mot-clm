package org.iii.core.enums;

/**
 * AuditState 審核狀態
 * @author Gina Chen
 * @version 2014/5/15
 */
public enum AuditState {

	/**
	 * 處理中
	 */
	PROCESS("處理中"),
	
	/**
	 * 待審核
	 */
	PENDING("待審核"),
	
	/**
	 * 簽核完成
	 */
	SIGNOFF("簽核完成")
	
	;
	
	private String auditState;
	
	private AuditState(){
		
	}
	
	private AuditState(String auditState){
		this.auditState = auditState;
	}

	public String getAuditState() {
		return auditState;
	}
	
	
}
