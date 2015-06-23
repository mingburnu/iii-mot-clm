package org.iii.module.claim.remark.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;
import org.iii.module.claim.remark.enums.RemarkProcessStatus;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * 報案處理記錄 Entity
 * @author Gina Chen
 * @version 2014/4/7
 */
@Entity
@Table(name = "CLM_GI_REMARK")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Remark extends GenericEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5134520640091355736L;

	/** 報案編號  */
	@Column(name="REG_CODE")
	private String regCode;
		
	/** 賠案編號  */
	@Column(name="CASE_CODE")
	private String caseCode;
	
	/** 被保險人ID  */
	@Column(name="INSURED_ID")
	private String insuredId;
	
	/** 被保險人姓名  */
	@Column(name="INSURED_NAME")
	private String insuredName;
	
	/** 出險日期  */
	@Column(name="ACCIDCIR_DATE")
	private LocalDateTime accidcirDate;
	
	/** 出險原因  */
	@Column(name="MAIN_REASON")
	private String mainReason;
	
	/** 處理狀態  */
	@Column(name="REMARK_PROCESS_STATUS")
	@Enumerated(EnumType.STRING)
	private RemarkProcessStatus remarkProcessStatus;
	
	/** 受理日期  */
	@Column(name="ACCEPTED_DATE")
	private LocalDateTime acceptedDate;
	
	/** 立案日  */
	@Column(name="POST_DATE")
	private LocalDateTime postDate;

	
	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}
	
	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getInsuredId() {
		return insuredId;
	}

	public void setInsuredId(String insuredId) {
		this.insuredId = insuredId;
	}

	public String getInsuredName() {
		return insuredName;
	}

	public void setInsuredName(String insuredName) {
		this.insuredName = insuredName;
	}

	public LocalDateTime getAccidcirDate() {
		return accidcirDate;
	}

	public void setAccidcirDate(LocalDateTime accidcirDate) {
		this.accidcirDate = accidcirDate;
	}

	public String getMainReason() {
		return mainReason;
	}

	public void setMainReason(String mainReason) {
		this.mainReason = mainReason;
	}

	public RemarkProcessStatus getRemarkProcessStatus() {
		return remarkProcessStatus;
	}

	public void setRemarkProcessStatus(RemarkProcessStatus remarkProcessStatus) {
		this.remarkProcessStatus = remarkProcessStatus;
	}

	public LocalDateTime getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(LocalDateTime acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public LocalDateTime getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDateTime postDate) {
		this.postDate = postDate;
	}
	
	
}
