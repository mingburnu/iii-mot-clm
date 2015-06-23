package org.iii.module.setting.setCarType.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.iii.core.entity.GenericEntity;
import org.iii.core.enums.SystemStatus;
import org.iii.module.setting.setCarType.enums.CarUsage;
import org.iii.module.setting.setClass.entity.SetClass;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * SetCarType 車種管理Entity
 * @author Ruo Hsu
 * @version 2014/3/26
 */
@Entity
@Table(name="SET_CAR_TYPE")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetCarType extends GenericEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 325623828177797778L;

	/**
	 * 產品別
	 */
	@Column(name="CLASS_ID")
	private Long classId;
	
	@Transient
	private SetClass setClass;	
	
	/**
	 * 車種代碼
	 */
	@Column(name="TYPE_CODE")
	private String typeCode;
	
	/**
	 * 車種名稱
	 */
	@Column(name="TYPE_NAME")
	private String typeName;
	
	/**
	 * 使用方式
	 */
	@Column(name="USAGE")
	@Enumerated(EnumType.STRING)
	private CarUsage usage;
	
	/**
	 * 乘載人數上限
	 */
	@Column(name="PE_LIMIT")
	private Integer peLimit;
	
	/**
	 * 乘載噸數上限
	 */
	@Column(name="TO_LIMIT")
	private Integer toLimit;
	
	/**
	 * 是否需要車籍資料
	 */
	@Column(name="NEED_CARINFO")
	@Enumerated(EnumType.STRING)
	private SystemStatus needCarinfo;

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public CarUsage getUsage() {
		return usage;
	}

	public void setUsage(CarUsage usage) {
		this.usage = usage;
	}

	public Integer getPeLimit() {
		return peLimit;
	}

	public void setPeLimit(Integer peLimit) {
		this.peLimit = peLimit;
	}

	public Integer getToLimit() {
		return toLimit;
	}

	public void setToLimit(Integer toLimit) {
		this.toLimit = toLimit;
	}

	public SystemStatus getNeedCarinfo() {
		return needCarinfo;
	}

	public void setNeedCarinfo(SystemStatus needCarinfo) {
		this.needCarinfo = needCarinfo;
	}
	
	public SetClass getSetClass(){
		return setClass;
	}
	
	public void setSetClass(SetClass _class){
		this.setClass=_class;
	}
	
}
