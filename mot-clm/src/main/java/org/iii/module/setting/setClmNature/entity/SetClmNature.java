package org.iii.module.setting.setClmNature.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * 賠款性質
 * @author Mark Huang
 * @version 2014/3/21
 */
@Entity
@Table(name="SET_CLM_NATURE")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetClmNature extends GenericEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2479604190643080889L;

	/** 賠款性質代碼 */
	@Column(name="CODE")
	private String code;
	
	/** 賠款性質全名 */
	@Column(name="LOCAL_NAME")
	private String localName;
	
	/** 賠款性質英文 */
	@Column(name="ENG_NAME")
	private String engName;
	
	@Override
	public String toString() {
		return "SetClmNature [code=" + code + ", localName=" + localName
				+ ", engName=" + engName + "]";
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLocalName() {
		return localName;
	}
	public void setLocalName(String localName) {
		this.localName = localName;
	}
	public String getEngName() {
		return engName;
	}
	public void setEngName(String engName) {
		this.engName = engName;
	}
}
