package org.iii.module.claim.register.enums;

/**
 * Job 職業
 * @author Mark Huang 	
 * @version 2014/4/6
 */
public enum Job { 
	
	
	/**
	 * 第一類
	 */
	GROUP1("第一類"),

	/**
	 * 第二類
	 */
	GROUP2("第二類"),
	
	/**
	 * 第三類
	 */
	GROUP3("第三類"),
	
	/**
	 * 第四類
	 */
	GROUP4("第四類"),
	
	/**
	 * 第五類
	 */
	GROUP5("第五類"),
	
	/**
	 * 第六類
	 */
	GROUP6("第六類"),
	
	;
	
	private String job;
	
	private Job(){
		
	}
	
	private Job(String job){
		this.job = job;
	}

	public String getJob() {
		return job;
	}
	
}
