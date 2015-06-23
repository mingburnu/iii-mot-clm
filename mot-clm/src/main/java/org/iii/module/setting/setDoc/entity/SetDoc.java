package org.iii.module.setting.setDoc.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * 文件管理
 * @author Gina Chen
 * @version 2014/3/21
 */
@Entity
@Table(name="SET_DOC")
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetDoc extends GenericEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -144494693597526340L;

	/** The class_id. */
	@Column(name="class_id")
	private Long classId;
	
	/** The code. */
	@Column(name="code")
	private String code;
	
	/** The localName. */
	@Column(name="local_name")
	private String localName;

	@Override
	public String toString() {
		return "SetDoc [classId=" + classId + ", code=" + code + ", localName="
				+ localName + "]";
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
	
	
}
