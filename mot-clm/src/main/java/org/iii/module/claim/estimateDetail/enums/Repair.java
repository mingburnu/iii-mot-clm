package org.iii.module.claim.estimateDetail.enums;

/**
 * Repair 修理廠
 * @author 黃小貓	
 * @version 2014/5/13
 */

public enum Repair {
	
	/** 請選擇. */
	CHOOSE("請選擇"),
	
	/** 修理廠. */
	REPAIRA("A修理廠"),
	
	/** 修理廠. */
	REPAIRB("B修理廠"),
	
	/** 修理廠. */
	REPAIRC("C修理廠");
	
	private String repair;
	
	private Repair(){
		
	}
	
	private Repair(String repair) {
		this.repair = repair;
	}
	
	public String getRepair() {
		return repair;
	}

	
}

