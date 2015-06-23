package org.iii.module.claim.register.enums;
/**
 * AccidentSecStatus 案件次狀態
 * @author Mark Huang 	
 * @version 2014/4/7
 */
public enum AccidentSecStatus {

	/**
	 * 處理中
	 */
	PROCESS("處理中"),
	
	/**
	 * 待理賠人員及待聯絡人資料
	 */
	WAITDATA("待理賠人員及待聯絡人資料")
	
	;
	
	private String accidentSecStatus;
	
	private AccidentSecStatus(){		
	}
	
	private AccidentSecStatus(String status){
		this.accidentSecStatus = status;
	}

	public String getAccidentSecStatus() {
		return accidentSecStatus;
	}
	
}
