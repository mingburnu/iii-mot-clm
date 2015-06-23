package org.iii.module.claim.register.enums;
/**
 * AccidentDeal 事故處理
 * @author Mark Huang 	
 * @version 2014/4/7
 */
public enum AccidentDeal {

	/**
	 * 無
	 */
	NOTHING("無"),
	
	/**
	 * 警方現場處理
	 */
	POLICEDEAL("警方現場處理"),
	
	/**
	 * 警方備案
	 */
	POLICERECORD("警方備案"),
	
	/**
	 * 0800備案
	 */
	RECORD0800("0800備案"),
	
	/**
	 * 其他
	 */
	ANOTHER("其他")
	
	;
	
	private String accidentDeal;
	
	private AccidentDeal(){		
	}
	
	private AccidentDeal(String deal){
		this.accidentDeal = deal;
	}

	public String getAccidentDeal() {
		return accidentDeal;
	}
	
}
