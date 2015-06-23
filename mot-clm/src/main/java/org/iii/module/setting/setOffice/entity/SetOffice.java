package org.iii.module.setting.setOffice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * 單位管理
 * @author 黃小貓
 * @version 2014/3/21
 */
@Entity
@Table(name="SET_OFFICE")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetOffice extends GenericEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3512673752651569610L;

	/**
	 * 單位代碼
	 */
	@Column(name="code")
	private String code;
	
	/**
	 * 單位全名
	 */
	@Column(name="local_name")
	private String localName;
	
	/**
	 * 電話(國碼)
	 */
	@Column(name="TEL_CTY")
	private String telCty;
	
	/**
	 * 電話(區碼)
	 */
	@Column(name="TEL_AREA")
	private String telArea;
	
	/**
	 * 電話
	 */
	@Column(name="tel")
	private String tel;
	
	/**
	 * 負責人
	 */
	@Column(name="manager")
	private String manager;

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

	public String getTelCty() {
		return telCty;
	}

	public void setTelCty(String telCty) {
		this.telCty = telCty;
	}

	public String getTelArea() {
		return telArea;
	}

	public void setTelArea(String telArea) {
		this.telArea = telArea;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}
	
	
	
}
