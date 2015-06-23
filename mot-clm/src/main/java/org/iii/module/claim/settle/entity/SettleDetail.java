package org.iii.module.claim.settle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * 理算簽結 理賠金額 detail Entity
 * @author Mark Huang
 * @version 2014/6/2
 */
@Entity
@Table(name = "CLM_SETTLE_DETAIL")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SettleDetail extends GenericEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3044441492376260755L;

	/** 賠案編號  */
	@Column(name="CASE_CODE")
	private String caseCode;
	
	/** 理賠項目 ID */
	@Column(name="ESTIMATE_DETAIL_ID")
	private long estimateDetailId;

	/** 
	 * 險種
	 */
	@Column(name="INSURED_TYPE")
	private String insuredType;
	
	/** 
	 * 已決金額
	 */
	@Column(name="SETTLE_AMOUNT")
	private Double settleAmount;
	
	/** 
	 * 已決金額
	 */
	@Column(name="SETTLE_ID")
	private long settleId;
	
	/** 
	 * 賠付序號
	 */
	@Column(name="PAY_ID")
	private String payId;

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public long getEstimateDetailId() {
		return estimateDetailId;
	}

	public void setEstimateDetailId(long estimateDetailId) {
		this.estimateDetailId = estimateDetailId;
	}

	public String getInsuredType() {
		return insuredType;
	}

	public void setInsuredType(String insuredType) {
		this.insuredType = insuredType;
	}

	public Double getSettleAmount() {
		return settleAmount;
	}

	public void setSettleAmount(Double settleAmount) {
		this.settleAmount = settleAmount;
	}

	public long getSettleId() {
		return settleId;
	}

	public void setSettleId(long settleId) {
		this.settleId = settleId;
	}

	public String getPayId() {
		return payId;
	}

	public void setPayId(String payId) {
		this.payId = payId;
	}	
	
	
	
}
