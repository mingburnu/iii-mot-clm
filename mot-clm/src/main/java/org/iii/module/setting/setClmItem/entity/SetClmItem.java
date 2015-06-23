package org.iii.module.setting.setClmItem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * 理賠項目管理
 * @author 黃小貓
 * @version 2014/3/21
 */
@Entity
@Table(name="SET_CLM_ITEM")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetClmItem extends GenericEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2470904600202986982L;

	/**
	 * 理賠項目代碼
	 */
	@Column(name="code")
	private String code;
	
	/**
	 * 理賠項目全名
	 */
	@Column(name="local_name")
	private String localName;
	
	/**
	 * 理賠項目英文
	 */
	@Column(name="eng_name")
	private String engName;
	
	@Override
	public String toString() {
		return "SetClmItem [code=" + code + ", localName=" + localName
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
