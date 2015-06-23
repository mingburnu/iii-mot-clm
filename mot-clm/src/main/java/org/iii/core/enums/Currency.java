package org.iii.core.enums;

/**
 * Currency 幣別
 * @author Gina Chen
 * @version 2014/5/13
 */
public enum Currency {

	/**
	 * 台幣
	 */
	TWD("台幣"),
	
	/**
	 * 美元
	 */
	USD("美元"),
	
	/**
	 * 人民幣
	 */
	CNY("人民幣"),
	
	/**
	 * 日圓
	 */
	JPY("日圓"),
	
	/**
	 * 歐元
	 */
	EUR("歐元"),
	
	/**
	 * 港幣
	 */
	HKD("港幣"),
	
	/**
	 * 英磅
	 */
	GBP("英磅"),
	
	/**
	 * 澳幣
	 */
	AUD("澳幣")
	
	;
	
	private String currency;
	
	private Currency(){
		
	}

	private Currency(String currency){
		this.currency = currency;
	}
	
	public String getCurrency() {
		return currency;
	}

	
}
