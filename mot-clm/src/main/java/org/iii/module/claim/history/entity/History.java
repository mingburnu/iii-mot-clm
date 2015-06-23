package org.iii.module.claim.history.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;
import org.iii.core.enums.ProcessStatus;
import org.iii.module.claim.register.enums.AccidentSecStatus;
import org.iii.module.claim.remark.enums.RemarkProcessStatus;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * History 狀態記錄/備註Entity
 * @author Ruo Hsu
 * @version 2014/5/15
 */
@Entity
@Table(name="CLM_GI_HISTORY")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class History extends GenericEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 93034081622685796L;
		
	/** 序號  */
	@Column(name="NUMBER")
	private Integer number;

	/** 修改日期  */
	@Column(name="MODIFY_DATE")
	private LocalDateTime modifyDate;
	
	/** 狀態  */
	@Column(name="ACCIDENT_STATUS")
	@Enumerated(EnumType.STRING)
	private ProcessStatus accidentStatus;
	
	/** 次狀態  */
	@Column(name="ACCIDENT_SEC_STATUS")
	@Enumerated(EnumType.STRING)
	private AccidentSecStatus accidentSecStatus;
	
	/** 處理狀態  */
	@Column(name="REMARK_PROCESS_STATUS")
	@Enumerated(EnumType.STRING)
	private RemarkProcessStatus remarkProcessStatus;
	
	/** 備註  */
	@Column(name="DESCRIPT")
	private String descript;
	
	/** 修改人員  */
	@Column(name="PERSON")
	private String person;
	
	/** 報案編號  */
	@Column(name="CODE")
	private String code;

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public ProcessStatus getAccidentStatus() {
		return accidentStatus;
	}

	public void setAccidentStatus(ProcessStatus accidentStatus) {
		this.accidentStatus = accidentStatus;
	}

	public AccidentSecStatus getAccidentSecStatus() {
		return accidentSecStatus;
	}

	public void setAccidentSecStatus(AccidentSecStatus accidentSecStatus) {
		this.accidentSecStatus = accidentSecStatus;
	}

	public RemarkProcessStatus getRemarkProcessStatus() {
		return remarkProcessStatus;
	}

	public void setRemarkProcessStatus(RemarkProcessStatus remarkProcessStatus) {
		this.remarkProcessStatus = remarkProcessStatus;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
