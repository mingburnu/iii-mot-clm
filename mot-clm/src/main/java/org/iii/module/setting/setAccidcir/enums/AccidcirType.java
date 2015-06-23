package org.iii.module.setting.setAccidcir.enums;

/**
 * SystemStatus
 * @author Gina Chen
 * @version 2014/3/26
 */
public enum AccidcirType {
	
	/** 強制. */
	ENF("強制"),
	
	/** 任意 .*/
	ANY("任意"),
	
	/** 強任. */
	ENF_ANY("強任");
	
	private String accidcirType;
	
	private AccidcirType(){
		
	}
   
	private AccidcirType(String accidcirType){
		this.accidcirType = accidcirType;
	}

	public String getAccidcirType() {
		return accidcirType;
	}
	
	
	
}
