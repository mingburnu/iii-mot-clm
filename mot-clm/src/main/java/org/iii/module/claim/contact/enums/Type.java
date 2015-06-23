package org.iii.module.claim.contact.enums;
/**
 * Type 類別
 * @author 黃小貓
 * @version 2014/4/9
 */
public enum Type {

	/** 家屬. */
	FAMILY("家屬"),
	
	/** 保險公司. */
	INSURANCE("保險公司"),
	
	/** 公證公司. */
	NOTARY("公證公司"),
	
	/** 律師. */
	LAWYER("律師"),
	
	/** 調查人/公司. */
	INVESTIGATOR("調查人/公司"),
	
	/** 事故者. */
	ACCIDENT_PERSON("事故者"),
	
	/** 請求權人. */
	CLAIMANT("請求權人"),
	
	/** 警方. */
	POLICE("警方"),
	
	/** 追償對象. */
	RECOVERY_PERSON("追償對象"),
	
	/** 修理廠. */
	REPAIRS("修理廠"),
	
	/** 業務招攬人. */
	SOLICITING_BUSINESS("業務招攬人"),
	
	/** 醫院. */
	HOSPITAL("醫院"),
	
	/** 受害人. */
	VICTIMS("受害人"),
	
	/** 被保險人. */
	INSURED("被保險人"),
	
	/** 駕駛人. */
	DRIVER("駕駛人"),
	
	/** 其他. */
	OTHER("其他");
	
	private String type;
	
	private Type(){
		
	}
	private Type(String type){
		this.type = type;
	}
	public String getType(){
		return type;
	}
}
