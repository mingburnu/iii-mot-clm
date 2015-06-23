package org.iii.module.setting.setClass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;




import org.iii.core.entity.GenericEntity;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * 產品別
 * @author Mark Huang
 * @version 2014/3/22
 */
@Entity
@Table(name="SET_CLASS")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetClass extends GenericEntity {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7462759195480203514L;

	/** 產品別代碼  */
	@Column(name="CODE")
	private String code;
	
	/** 產品別全名 */
	@Column(name="LOCAL_NAME")
	private String localName;
	
	/** 產品別英文 */
	@Column(name="ENG_NAME")
	private String engName;
	
	/** 產品別描述 */
	@Column(name="DESCRIPT")
	private String descript;

	@Override
	public String toString() {
		return "SetClass [code=" + code + ", localName=" + localName
				+ ", engName=" + engName + ", descript=" + descript + "]";
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

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

}
