package org.iii.module.setting.setAuthority.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.iii.core.entity.GenericEntity;
import org.iii.module.setting.setAuthority.enums.BusType;
import org.iii.module.setting.setClass.entity.SetClass;
import org.iii.module.setting.setOffice.entity.SetOffice;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * SetAuthority 分層授權管理Entity
 * @author Ruo Hsu
 * @version 2014/5/19
 */
@Entity
@Table(name="SET_AUTHORITY")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetAuthority extends GenericEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4468727701387997381L;

	/**
	 * 產品別
	 */  
	@Column(name="CLASS_ID")
	private Long classId;
	
	@Transient
	private SetClass setClass;
	
	/**
	 * 單位別
	 */
	@Column(name="OFFICE_ID")
	private String officeId;
	
	@Transient
	private SetOffice setOffice;	
	
	/**
	 * 角色
	 */
	@Column(name="ROLE")
	private String role;
	
	/**
	 * 業務來源
	 */
	@Column(name="BUS_TYPE")
	@Enumerated(EnumType.STRING)
	private BusType busType;
	
	/**
	 * 審核類型
	 */
	@Column(name="AUDIT_TYPE")
	private String auditType;
	
	/**
	 * 核決金額(起)
	 */
	@Column(name="MIN_AMOUNT")
	private Integer minAmount;
	
	/**
	 * 核決金額(迄)
	 */
	@Column(name="MAX_AMOUNT")
	private Integer maxAmount;
	
	/**
	 * 備註說明
	 */
	@Column(name="DESCRIPT")
	private String descript;

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public BusType getBusType() {
		return busType;
	}

	public void setBusType(BusType busType) {
		this.busType = busType;
	}

	public String getAuditType() {
		return auditType;
	}

	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}

	public Integer getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Integer minAmount) {
		this.minAmount = minAmount;
	}

	public Integer getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(Integer maxAmount) {
		this.maxAmount = maxAmount;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public SetClass getSetClass() {
		return setClass;
	}

	public void setSetClass(SetClass _class) {
		this.setClass = _class;
	}

	public SetOffice getSetOffice() {
		return setOffice;
	}

	public void setSetOffice(SetOffice _Office) {
		this.setOffice = _Office;
	}

}
