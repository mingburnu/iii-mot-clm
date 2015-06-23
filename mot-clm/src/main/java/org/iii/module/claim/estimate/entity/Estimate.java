package org.iii.module.claim.estimate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;


/**
 * 理算賠款
 * 
 * @author Rodrigo Chun-Ming Chu
 * @version 2014/5/9
 */
@Entity
@Table(name = "CLM_ESTIMATE")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Estimate extends GenericEntity {

	private static final long serialVersionUID = -3341699448780079579L;

	/** 賠案號碼 */
	@Column(name = "CASE_CODE")
	private String caseCode;

	/** 出險日期 */
	@Column(name = "ACCIDCIR_DATE")
	private LocalDateTime accidcirDate;

	/** 被保險人ID */
	@Column(name = "INSURED_ID")
	private String insuredId;

	/** 被保險人姓名 */
	@Column(name = "INSURED_NAME")
	private String insuredName;

	/** 車牌號碼 */
	@Column(name = "PLATE")
	private String plate;

	/** 申請次數 */
	@Column(name = "APPLY_BOUT")
	private int applyBout;

	/** 事故原因 */
	@Column(name = "ACCIDENT")
	private String accident;

	/** 保單號碼 */
	@Column(name = "POLICY_CODE")
	private String policyCode;

	/** 收費情形 */
	@Column(name = "FEE_STATUS")
	private String feeStatus;

	/** 投保期間 */
	@Column(name = "INSURED_PERIOD")
	private String insuredPeriod;

	/** 營業經辦 */
	@Column(name = "PROCESS_USER_CODE")
	private String processUserCode;

	/** 幣別 */
	@Column(name = "CURRENCY")
	private String currency;

	/** 保額 */
	@Column(name = "INSURED_AMOUNT")
	private String insuredAmount;

	/** 險種 */
	@Column(name = "INSURED_TYPE")
	private String insuredType;

	/** 受損標的所有權人 */
	@Column(name = "DAMAGE_OBJECT_OWNER")
	private String damageObjectOwner;

	/** 預估金額 */
	@Column(name = "ESTIMATED_AMOUNT")
	private Double estimatedAmount;
	
	/** 預估金額幣別 */
	@Column(name = "AMOUNT_CURRENCY")
	private String amoutCurrency;

	/** 總預估金額 */
	@Column(name = "TOTAL_ESTIMATED_AMOUNT")
	private double totalEstimatedAmount;
	
	/** 理算確認 */
	@Column(name = "ESTIMATED_CHECK")
	private boolean estimatedCheck;

	public double getTotalEstimatedAmount() {
		return totalEstimatedAmount;
	}

	public void setTotalEstimatedAmount(double totalEstimatedAmount) {
		this.totalEstimatedAmount = totalEstimatedAmount;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public LocalDateTime getAccidcirDate() {
		return accidcirDate;
	}

	public void setAccidcirDate(LocalDateTime accidcirDate) {
		this.accidcirDate = accidcirDate;
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

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public int getApplyBout() {
		return applyBout;
	}

	public void setApplyBout(int applyBout) {
		this.applyBout = applyBout;
	}

	public String getAccident() {
		return accident;
	}

	public void setAccident(String accident) {
		this.accident = accident;
	}

	public String getPolicyCode() {
		return policyCode;
	}

	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}


	public String getInsuredPeriod() {
		return insuredPeriod;
	}

	public void setInsuredPeriod(String insuredPeriod) {
		this.insuredPeriod = insuredPeriod;
	}

	public String getProcessUserCode() {
		return processUserCode;
	}

	public void setProcessUserCode(String processUserCode) {
		this.processUserCode = processUserCode;
	}


	public String getInsuredAmount() {
		return insuredAmount;
	}

	public void setInsuredAmount(String insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	public String getInsuredType() {
		return insuredType;
	}

	public void setInsuredType(String insuredType) {
		this.insuredType = insuredType;
	}

	public String getDamageObjectOwner() {
		return damageObjectOwner;
	}

	public void setDamageObjectOwner(String damageObjectOwner) {
		this.damageObjectOwner = damageObjectOwner;
	}

	public Double getEstimatedAmount() {
		return estimatedAmount;
	}

	public void setEstimatedAmount(Double estimatedAmount) {
		this.estimatedAmount = estimatedAmount;
	}
	
	
	
	public boolean getEstimatedCheck() {
		return estimatedCheck;
	}

	public void setEstimatedCheck(boolean estimatedCheck) {
		this.estimatedCheck = estimatedCheck;
	}

	public String getFeeStatus() {
		return feeStatus;
	}

	public void setFeeStatus(String feeStatus) {
		this.feeStatus = feeStatus;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getAmoutCurrency() {
		return amoutCurrency;
	}

	public void setAmoutCurrency(String amoutCurrency) {
		this.amoutCurrency = amoutCurrency;
	}
	
	
	
}
