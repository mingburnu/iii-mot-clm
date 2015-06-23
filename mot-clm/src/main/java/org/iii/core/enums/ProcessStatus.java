package org.iii.core.enums;
/**
 * ProcessStatus 案件狀態
 * @author Mark Huang 	
 * @version 2014/4/6
 */
public enum ProcessStatus {

	/**
	 * 處理中
	 */
	PROCESS("處理中"),
	
	/**
	 * 結案
	 */
	HISTORY("結案")
	
	;
	
	private String processStatus;
	
	private ProcessStatus(){		
	}
	
	private ProcessStatus(String status){
		this.processStatus = status;
	}
	public String getProcessStatus() {
		return processStatus;
	}	
}
