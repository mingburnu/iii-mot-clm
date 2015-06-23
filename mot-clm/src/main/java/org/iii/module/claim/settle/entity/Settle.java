package org.iii.module.claim.settle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;
import org.iii.core.enums.AuditState;
import org.iii.core.enums.SystemStatus;
import org.iii.module.claim.settle.enums.ClosedType;
import org.iii.module.claim.settle.enums.RecoveryObject;
import org.iii.module.claim.settle.enums.RecoveryPerson;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * 理算簽結 Entity
 * @author Gina Chen
 * @version 2014/5/13
 */
@Entity
@Table(name = "CLM_SETTLE")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Settle extends GenericEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6366957711273631542L;
	
	/** 賠案編號  */
	@Column(name="CASE_CODE")
	private String caseCode;
	
	/** 出險日期 */
	@Column(name="ACCIDCIR_DATE")
	private LocalDateTime accidcirDate;
	
	/** 被保險人ID */
	@Column(name="INSURED_ID")
	private String insuredId;
	
	/** 被保險人姓名 */
	@Column(name="INSURED_NAME")
	private String insuredName;
	
	/** 車牌號碼 */
	@Column(name="PLATE")
	private String plate;
	
	/** 申請次數 */
	@Column(name="APPLY_BOUT")
	private Integer applyBout;
	
	/** 事故原因 */
	@Column(name="ACCIDENT")
	private String accident;
	
	/** 總預估金額 -幣別 */
	@Column(name="TOTAL_AMOUNT_CUR")
	private String totalAmountCur;
	
	/** 總預估金額 */
	@Column(name="TOTAL_AMOUNT")
	private Double totalAmount;
	
	/** 總已決金額-幣別 */
	@Column(name="SETTLED_AMOUNT_CUR")
	private String settledAmountCur;
	
	/** 總已決金額 */
	@Column(name="TOTAL_SETTLED_AMOUNT")
	private Double totalSettledAmount;
	
	/** 審核狀態 */
	@Column(name="AUDIT_STATE")
	@Enumerated(EnumType.STRING)
	private AuditState auditState;
	
	/** 已決日期 */
	@Column(name="SETTLED_DATE")
	private LocalDateTime settledDate;
	
	/** 結案別 */
	@Column(name="CLOSED_TYPE")
	@Enumerated(EnumType.STRING)
	private ClosedType closedType;
	
	/** 是否追償 */
	@Column(name="RECOVERY")
	@Enumerated(EnumType.STRING)
	private SystemStatus recovery;
	
	/** 追償對象 */
	@Column(name="RECOVERY_OBJECT")
	@Enumerated(EnumType.STRING)
	private RecoveryObject recoveryObject;
	
	/** 追償人員 */
	@Column(name="RECOVERY_PERSON")
	@Enumerated(EnumType.STRING)
	private RecoveryPerson recoveryPerson;
	
	/** 簽賠次數 */
	@Column(name="PAYMENT_FREQUENCY")
	private Integer paymentFrequency;
	
	/** 保單號碼 */
	@Column(name="POLICY_CODE")
	private String policyCode;
	
	/** 險種 */
	@Column(name="INSURED_TYPE")
	private String insuredType;
	
	/** 受損標的所有權人 */
	@Column(name="DAMAGE_OBJECT_OWNER")
	private String damageObjectOwner;
	
	/** 幣別 */
	@Column(name="CURRENCY")
	private String currency;
	
	/** 預估金額 */
	@Column(name="ESTIMATE_AMOUNT")
	private Double estimateAmount;
	
	/** 已決金額 */
	@Column(name="SETTLED_AMOUNT")
	private Double settledAmount;
	
	/** 賠付序號 */
	@Column(name="PAYMENT_CODE")
	private String paymentCode;
	
	/** 對象 */
	@Column(name="PAYMENT_OBJECT")
	private String paymentObject;
	
	/** 金額 */
	@Column(name="PAYMENT_AMOUNT")
	private Double paymentAmount;

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

	public Integer getApplyBout() {
		return applyBout;
	}

	public void setApplyBout(Integer applyBout) {
		this.applyBout = applyBout;
	}

	public String getAccident() {
		return accident;
	}

	public void setAccident(String accident) {
		this.accident = accident;
	}

	public String getTotalAmountCur() {
		return totalAmountCur;
	}

	public void setTotalAmountCur(String totalAmountCur) {
		this.totalAmountCur = totalAmountCur;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getSettledAmountCur() {
		return settledAmountCur;
	}

	public void setSettledAmountCur(String settledAmountCur) {
		this.settledAmountCur = settledAmountCur;
	}

	public Double getTotalSettledAmount() {
		return totalSettledAmount;
	}

	public void setTotalSettledAmount(Double totalSettledAmount) {
		this.totalSettledAmount = totalSettledAmount;
	}

	public AuditState getAuditState() {
		return auditState;
	}

	public void setAuditState(AuditState auditState) {
		this.auditState = auditState;
	}

	public LocalDateTime getSettledDate() {
		return settledDate;
	}

	public void setSettledDate(LocalDateTime settledDate) {
		this.settledDate = settledDate;
	}

	public ClosedType getClosedType() {
		return closedType;
	}

	public void setClosedType(ClosedType closedType) {
		this.closedType = closedType;
	}

	public SystemStatus getRecovery() {
		return recovery;
	}

	public void setRecovery(SystemStatus recovery) {
		this.recovery = recovery;
	}

	public RecoveryObject getRecoveryObject() {
		return recoveryObject;
	}

	public void setRecoveryObject(RecoveryObject recoveryObject) {
		this.recoveryObject = recoveryObject;
	}

	public Integer getPaymentFrequency() {
		return paymentFrequency;
	}

	public void setPaymentFrequency(Integer paymentFrequency) {
		this.paymentFrequency = paymentFrequency;
	}

	public String getPolicyCode() {
		return policyCode;
	}

	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
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

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getEstimateAmount() {
		return estimateAmount;
	}

	public void setEstimateAmount(Double estimateAmount) {
		this.estimateAmount = estimateAmount;
	}

	public Double getSettledAmount() {
		return settledAmount;
	}

	public void setSettledAmount(Double settledAmount) {
		this.settledAmount = settledAmount;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getPaymentObject() {
		return paymentObject;
	}

	public void setPaymentObject(String paymentObject) {
		this.paymentObject = paymentObject;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public RecoveryPerson getRecoveryPerson() {
		return recoveryPerson;
	}

	public void setRecoveryPerson(RecoveryPerson recoveryPerson) {
		this.recoveryPerson = recoveryPerson;
	}

	

}
