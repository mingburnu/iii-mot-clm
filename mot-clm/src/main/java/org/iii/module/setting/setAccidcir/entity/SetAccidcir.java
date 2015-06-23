package org.iii.module.setting.setAccidcir.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;
import org.iii.module.setting.setAccidcir.enums.AccidcirType;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * 出險原因管理
 * @author Gina Chen
 * @version 2014/3/21
 */
@Entity
@Table(name="SET_ACCIDCIR")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetAccidcir extends GenericEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4701710668237993678L;

	/** The class_id. */
	@Column(name="class_id")
	private Long classId;
	
	/** The code. */
	@Column(name="code")
	private String code;
	
	/** The localName. */
	@Column(name="local_name")
	private String localName;
	
	/** The engName. */
	@Column(name="eng_name")
	private String engName;
	
	/** The accidcir_type. */
	@Column(name="accidcir_type")
	@Enumerated(EnumType.STRING)
	private AccidcirType accidcirType;
	
	@Override
	public String toString() {
		return "SetAccidcir [classId=" + classId + ", code=" + code
				+ ", localName=" + localName + ", engName=" + engName
				+ ", accidcirType=" + accidcirType + "]";
	}
	
	public Long getClassId() {
		return classId;
	}
	public void setClassId(Long classId) {
		this.classId = classId;
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
	public AccidcirType getAccidcirType() {
		return accidcirType;
	}
	public void setAccidcirType(AccidcirType accidcirType) {
		this.accidcirType = accidcirType;
	}
	
}
