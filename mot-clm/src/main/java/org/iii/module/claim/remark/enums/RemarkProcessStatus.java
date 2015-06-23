package org.iii.module.claim.remark.enums;

/**
 * RemarProcessStatus 處理狀態
 * @author Gina Chen
 * @version 2014/4/8
 */
public enum RemarkProcessStatus {

	/**
	 * 受理中
	 */
	RECEIVE("受理中"),
	
	/**
	 * 建立賠案
	 */
	CONVERT("建立賠案"),
	
	/**
	 * 理算中
	 */
	ADJUST("理算中"),
	
	/**
	 * 簽結中
	 */
	SIGNED("簽結中"),
	
	/**
	 * 送審中
	 */
	SUBMIT_REVIEW("送審中"),
	
	/**
	 * 結案
	 */
	CLOSED("結案"),
	
	/**
	 * 追償中
	 */
	RECOVERY("追償中");
	
	private String remarkProcessStatus;
	
	private RemarkProcessStatus(){
		
	}
	
	private RemarkProcessStatus(String remarkProcessStatus){
		this.remarkProcessStatus = remarkProcessStatus;
	}

	public String getRemarkProcessStatus() {
		return remarkProcessStatus;
	}

	
	
}
