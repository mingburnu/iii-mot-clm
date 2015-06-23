package org.iii.module.claim.register.enums;

/**
 * InsuredRelation 與被保人關係
 * @author Mark Huang
 * @version 2014/4/6
 */
public enum InsuredRelation {

	/**
	 * 本人
	 */
	MYSELF("本人"),
	
	/**
	 * 配偶
	 */
	SPOUSE("配偶"),
	
	/**
	 * 子女
	 */
	CHILD("子女"),
	
	/**
	 * 父母
	 */
	PARENT("父母")
	
	;
	
	private String insuredRelation;
	
	private InsuredRelation(){
		
	}
	
	private InsuredRelation(String insuredRelation){
		this.insuredRelation = insuredRelation;
	}

	public String getInsuredRelation() {
		return insuredRelation;
	}
	
}
