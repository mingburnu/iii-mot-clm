package org.iii.module.claim.payments.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;
import org.iii.core.enums.AuditState;
import org.iii.core.enums.Currency;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;


/**
 * Payment 賠付內容Entity
 * @author Ruo Hsu
 * @version 2014/5/15
 */
@Entity
@Table(name="CLM_PAYMENTS")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Payments extends GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3881911111676648253L;

	/** 賠案號碼  */
	@Column(name="CASE_CODE")
	private String caseCode;
	
	/** 出險日期  */
	@Column(name="ACCIDCIR_DATE")
	private LocalDateTime accidcirDate;
	
	/** 被保險人ID  */
	@Column(name="INSURED_ID")
	private String insuredId;
	
	/** 被保險人姓名  */
	@Column(name="INSURED_NAME")
	private String insuredName;
	
	/** 保單號碼  */
	@Column(name="POLICY_CODE")
	private String policyCode;
	
	/** 車牌號碼  */
	@Column(name="PLATE")
	private String plate;
	
	/** 申請次數  */
	@Column(name="APPLY_BOUT")
	private Integer applyBout;
	
	/** 事故原因  */
	@Column(name="ACCIDENT")
	private String accident;
	
	/** 審核狀態  */
	@Column(name="AUDIT_STATE")
	@Enumerated(EnumType.STRING)
	private AuditState auditState;
	
	/** 已決日期  */
	@Column(name="DECIDE_DATE")
	private LocalDateTime decideDate;
	
	/** 賠付對象  */
	@Column(name="PAYMENT_OBJECT")
	private String paymentObject;
	
	/** 統一編號/ID  */
	@Column(name="PAYMENT_OBJECT_ID")
	private String paymentObjectId;
	
	/** 通知人  */
	@Column(name="NOTICE_PERSON")
	private String noticePerson;
	
	/** 電子郵件  */
	@Column(name="EMAIL")
	private String email;
	
	/** 電話一-國碼  */
	@Column(name="TEL_CTY_FIR")
	private String telCtyFir;
	
	/** 電話一-區碼  */
	@Column(name="TEL_AREA_FIR")
	private String telAreaFir;
	
	/** 電話一  */
	@Column(name="TEL_FIR")
	private String telFir;
	
	/** 電話二-國碼  */
	@Column(name="TEL_CTY_SEC")
	private String telCtySec;
	
	/** 電話二-區碼  */
	@Column(name="TEL_AREA_SEC")
	private String telAreaSec;
	
	/** 電話二  */
	@Column(name="TEL_SEC")
	private String telSec;
	
	/** 通訊地址  */
	@Column(name="ADDRESS")
	private String address;
	
	/** 行動電話-國碼  */
	@Column(name="PHONE_CTY")
	private String phoneCty;
	
	/** 行動電話  */
	@Column(name="PHONE")
	private String phone;
	
	/** 簡訊  */
	@Column(name="PHONE_MESSAGE")
	private boolean phoneMessage;
	
	/** 付款金額幣別  */
	@Column(name="PAYMENT_CURRENCY")
	private String paymentCurrency;
	
	/** 付款金額  */
	@Column(name="PAYMENT_AMOUNT")
	private Double paymentAmount;
	
	/** 付款單位  */
	@Column(name="PAYMENT_OFFICE")
	private String paymentOffice;
	
	/** 帳務單位  */
	@Column(name="ACCOUNT_OFFICE")
	private String accountOffice;
	
	/** 憑證號碼  */
	@Column(name="PROOF_NUMBER")
	private String proofNumber;
	
	/** 傳票號碼  */
	@Column(name="SUMMONS_NUMBER")
	private String summonsNumber;
	
	/** 賠案付款日  */
	@Column(name="PAY_DATE")
	private LocalDateTime payDate;
	
	/** 現金幣別  */
	@Column(name="CASH_CURRENCY")
	@Enumerated(EnumType.STRING)
	private Currency cashCurrency;
	
	/** 現金金額  */
	@Column(name="CASH")
	private Double cash;
	
	/** 現金兌換率  */
	@Column(name="CASH_RATE")
	private Double cashRate;
	
	/** 零用金付迄  */
	@Column(name="PETTY_CASH")
	private boolean pettyCash;
	
	/** 支票幣別  */
	@Column(name="CHECK_CURRENCY")
	@Enumerated(EnumType.STRING)
	private Currency checkCurrency;
	
	/** 支票金額  */
	@Column(name="CHECKED")
	private Double checked;
	
	/** 支票兌換率  */
	@Column(name="CHECK_RATE")
	private Double checkRate;
	
	/** 支票姓名  */
	@Column(name="CHECK_NAME")
	private String checkName;
	
	/** 支票電話  */
	@Column(name="CHECK_TEL")
	private String checkTel;
	
	/** 合併支票  */
	@Column(name="CHECK_MERGE")
	private boolean checkMerge;
	
	/** 代領票人  */
	@Column(name="CHECK_SUB")
	private boolean checkSub;
	
	/** 匯款幣別  */
	@Column(name="REMIT_CURRENCY")
	@Enumerated(EnumType.STRING)
	private Currency remitCurrency;
	
	/** 匯款金額  */
	@Column(name="REMIT")
	private Double remit;
	
	/** 匯款兌換率  */
	@Column(name="REMIT_RATE")
	private Double remitRate;
	
	/** 匯款銀行帳號資料序號  */
	@Column(name="REMIT_BANK_ACCOUNT_NO")
	private String remitBankAccountNo;
	
	/** 匯款銀行帳號  */
	@Column(name="REMIT_BANK_ACCOUNT")
	private String remitBankAccount;
	
	/** 匯款銀行代號  */
	@Column(name="REMIT_BANK_CODE")
	private String remitBankCode;
	
	/** 匯款銀行簡稱  */
	@Column(name="REMIT_BANK_NAME")
	private String remitBankName;
	
	/** 預定匯款日  */
	@Column(name="REMIT_PRE_DATE")
	private LocalDateTime remitPreDate;
	
	/** 匯費由受款人負擔  */
	@Column(name="REMIT_BY_PAYEE")
	private boolean remitByPayee;
	
	/** 郵寄匯款通知書  */
	@Column(name="REMIT_SEND_NOTICE")
	private boolean remitSendNotice;
	
	/** 外幣支票幣別  */
	@Column(name="FOREIGN_CHECK_CURRENCY")
	@Enumerated(EnumType.STRING)
	private Currency foreignCheckCurrency;
	
	/** 外幣支票金額  */
	@Column(name="FOREIGN_CHECK")
	private Double foreignCheck;
	
	/** 外幣支票兌換率  */
	@Column(name="FOREIGN_CHECK_RATE")
	private Double foreignCheckRate;
	
	/** 外幣合併支票  */
	@Column(name="FOREIGN_CHECK_MERGE")
	private boolean foreignCheckMerge;
	
	/** 外幣匯款幣別  */
	@Column(name="FOREIGN_REMIT_CURRENCY")
	@Enumerated(EnumType.STRING)
	private Currency foreignRemitCurrency;
	
	/** 外幣匯款金額  */
	@Column(name="FOREIGN_REMIT")
	private Double foreignRemit;
	
	/** 外幣匯款兌換率  */
	@Column(name="FOREIGN_REMIT_RATE")
	private Double foreignRemitRate;
	
	/** 外幣匯款銀行帳號資料序號  */
	@Column(name="FOREIGN_REMIT_BANK_ACCOUNT_NO")
	private String foreignRemitBankAccountNo;
	
	/** 外幣匯款銀行帳號  */
	@Column(name="FOREIGN_REMIT_BANK_ACCOUNT")
	private String foreignRemitBankAccount;
	
	/** 外幣匯款銀行代號  */
	@Column(name="FOREIGN_REMIT_BANK_CODE")
	private String foreignRemitBankCode;
	
	/** 外幣匯款銀行名稱  */
	@Column(name="FOREIGN_REMIT_BANK_NAME")
	private String foreignRemitBankName;
	
	/** 外幣匯款swift code  */
	@Column(name="FOREIGN_REMIT_SWIFT_CODE")
	private String foreignRemitSwiftCode;
	
	/** 外幣匯款IBAN code  */
	@Column(name="FOREIGN_REMIT_IBAN_CODE")
	private String foreignRemitIbanCode;
	
	/** 外幣匯款國別  */
	@Column(name="FOREIGN_REMIT_CTY")
	private String foreignRemitCty;
	
	/** 外幣匯款城市  */
	@Column(name="FOREIGN_REMIT_TOWN")
	private String foreignRemitTown;

	/** 外幣匯費由受款人負擔  */
	@Column(name="FOREIGN_REMIT_BY_PAYEE")
	private String foreignRemitByPayee;
	
	/** 賠付序號  */
	@Column(name="PAY_NUMBER")
	private String payNumber;

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

	public String getPolicyCode() {
		return policyCode;
	}

	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
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

	public AuditState getAuditState() {
		return auditState;
	}

	public void setAuditState(AuditState auditState) {
		this.auditState = auditState;
	}

	public LocalDateTime getDecideDate() {
		return decideDate;
	}

	public void setDecideDate(LocalDateTime decideDate) {
		this.decideDate = decideDate;
	}

	public String getPaymentObject() {
		return paymentObject;
	}

	public void setPaymentObject(String paymentObject) {
		this.paymentObject = paymentObject;
	}

	public String getPaymentObjectId() {
		return paymentObjectId;
	}

	public void setPaymentObjectId(String paymentObjectId) {
		this.paymentObjectId = paymentObjectId;
	}

	public String getNoticePerson() {
		return noticePerson;
	}

	public void setNoticePerson(String noticePerson) {
		this.noticePerson = noticePerson;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelCtyFir() {
		return telCtyFir;
	}

	public void setTelCtyFir(String telCtyFir) {
		this.telCtyFir = telCtyFir;
	}

	public String getTelAreaFir() {
		return telAreaFir;
	}

	public void setTelAreaFir(String telAreaFir) {
		this.telAreaFir = telAreaFir;
	}

	public String getTelFir() {
		return telFir;
	}

	public void setTelFir(String telFir) {
		this.telFir = telFir;
	}

	public String getTelCtySec() {
		return telCtySec;
	}

	public void setTelCtySec(String telCtySec) {
		this.telCtySec = telCtySec;
	}

	public String getTelAreaSec() {
		return telAreaSec;
	}

	public void setTelAreaSec(String telAreaSec) {
		this.telAreaSec = telAreaSec;
	}

	public String getTelSec() {
		return telSec;
	}

	public void setTelSec(String telSec) {
		this.telSec = telSec;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneCty() {
		return phoneCty;
	}

	public void setPhoneCty(String phoneCty) {
		this.phoneCty = phoneCty;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isPhoneMessage() {
		return phoneMessage;
	}

	public void setPhoneMessage(boolean phoneMessage) {
		this.phoneMessage = phoneMessage;
	}

	public String getPaymentCurrency() {
		return paymentCurrency;
	}

	public void setPaymentCurrency(String paymentCurrency) {
		this.paymentCurrency = paymentCurrency;
	}

	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentOffice() {
		return paymentOffice;
	}

	public void setPaymentOffice(String paymentOffice) {
		this.paymentOffice = paymentOffice;
	}

	public String getAccountOffice() {
		return accountOffice;
	}

	public void setAccountOffice(String accountOffice) {
		this.accountOffice = accountOffice;
	}

	public String getProofNumber() {
		return proofNumber;
	}

	public void setProofNumber(String proofNumber) {
		this.proofNumber = proofNumber;
	}

	public String getSummonsNumber() {
		return summonsNumber;
	}

	public void setSummonsNumber(String summonsNumber) {
		this.summonsNumber = summonsNumber;
	}

	public LocalDateTime getPayDate() {
		return payDate;
	}

	public void setPayDate(LocalDateTime payDate) {
		this.payDate = payDate;
	}

	public Currency getCashCurrency() {
		return cashCurrency;
	}

	public void setCashCurrency(Currency cashCurrency) {
		this.cashCurrency = cashCurrency;
	}

	public Double getCash() {
		return cash;
	}

	public void setCash(Double cash) {
		this.cash = cash;
	}

	public Double getCashRate() {
		return cashRate;
	}

	public void setCashRate(Double cashRate) {
		this.cashRate = cashRate;
	}

	public boolean isPettyCash() {
		return pettyCash;
	}

	public void setPettyCash(boolean pettyCash) {
		this.pettyCash = pettyCash;
	}

	public Currency getCheckCurrency() {
		return checkCurrency;
	}

	public void setCheckCurrency(Currency checkCurrency) {
		this.checkCurrency = checkCurrency;
	}



	public Double getChecked() {
		return checked;
	}

	public void setChecked(Double checked) {
		this.checked = checked;
	}

	public Double getCheckRate() {
		return checkRate;
	}

	public void setCheckRate(Double checkRate) {
		this.checkRate = checkRate;
	}

	public String getCheckName() {
		return checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public String getCheckTel() {
		return checkTel;
	}

	public void setCheckTel(String checkTel) {
		this.checkTel = checkTel;
	}

	public boolean isCheckMerge() {
		return checkMerge;
	}

	public void setCheckMerge(boolean checkMerge) {
		this.checkMerge = checkMerge;
	}

	public boolean isCheckSub() {
		return checkSub;
	}

	public void setCheckSub(boolean checkSub) {
		this.checkSub = checkSub;
	}

	public Currency getRemitCurrency() {
		return remitCurrency;
	}

	public void setRemitCurrency(Currency remitCurrency) {
		this.remitCurrency = remitCurrency;
	}

	public Double getRemit() {
		return remit;
	}

	public void setRemit(Double remit) {
		this.remit = remit;
	}

	public Double getRemitRate() {
		return remitRate;
	}

	public void setRemitRate(Double remitRate) {
		this.remitRate = remitRate;
	}

	public String getRemitBankAccountNo() {
		return remitBankAccountNo;
	}

	public void setRemitBankAccountNo(String remitBankAccountNo) {
		this.remitBankAccountNo = remitBankAccountNo;
	}

	public String getRemitBankAccount() {
		return remitBankAccount;
	}

	public void setRemitBankAccount(String remitBankAccount) {
		this.remitBankAccount = remitBankAccount;
	}

	public String getRemitBankCode() {
		return remitBankCode;
	}

	public void setRemitBankCode(String remitBankCode) {
		this.remitBankCode = remitBankCode;
	}

	public String getRemitBankName() {
		return remitBankName;
	}

	public void setRemitBankName(String remitBankName) {
		this.remitBankName = remitBankName;
	}

	public LocalDateTime getRemitPreDate() {
		return remitPreDate;
	}

	public void setRemitPreDate(LocalDateTime remitPreDate) {
		this.remitPreDate = remitPreDate;
	}

	public boolean isRemitByPayee() {
		return remitByPayee;
	}

	public void setRemitByPayee(boolean remitByPayee) {
		this.remitByPayee = remitByPayee;
	}

	public boolean isRemitSendNotice() {
		return remitSendNotice;
	}

	public void setRemitSendNotice(boolean remitSendNotice) {
		this.remitSendNotice = remitSendNotice;
	}

	public Currency getForeignCheckCurrency() {
		return foreignCheckCurrency;
	}

	public void setForeignCheckCurrency(Currency foreignCheckCurrency) {
		this.foreignCheckCurrency = foreignCheckCurrency;
	}

	public Double getForeignCheck() {
		return foreignCheck;
	}

	public void setForeignCheck(Double foreignCheck) {
		this.foreignCheck = foreignCheck;
	}

	public Double getForeignCheckRate() {
		return foreignCheckRate;
	}

	public void setForeignCheckRate(Double foreignCheckRate) {
		this.foreignCheckRate = foreignCheckRate;
	}

	public boolean isForeignCheckMerge() {
		return foreignCheckMerge;
	}

	public void setForeignCheckMerge(boolean foreignCheckMerge) {
		this.foreignCheckMerge = foreignCheckMerge;
	}

	public Currency getForeignRemitCurrency() {
		return foreignRemitCurrency;
	}

	public void setForeignRemitCurrency(Currency foreignRemitCurrency) {
		this.foreignRemitCurrency = foreignRemitCurrency;
	}

	public Double getForeignRemit() {
		return foreignRemit;
	}

	public void setForeignRemit(Double foreignRemit) {
		this.foreignRemit = foreignRemit;
	}

	public Double getForeignRemitRate() {
		return foreignRemitRate;
	}

	public void setForeignRemitRate(Double foreignRemitRate) {
		this.foreignRemitRate = foreignRemitRate;
	}

	public String getForeignRemitBankAccountNo() {
		return foreignRemitBankAccountNo;
	}

	public void setForeignRemitBankAccountNo(String foreignRemitBankAccountNo) {
		this.foreignRemitBankAccountNo = foreignRemitBankAccountNo;
	}

	public String getForeignRemitBankAccount() {
		return foreignRemitBankAccount;
	}

	public void setForeignRemitBankAccount(String foreignRemitBankAccount) {
		this.foreignRemitBankAccount = foreignRemitBankAccount;
	}

	public String getForeignRemitBankCode() {
		return foreignRemitBankCode;
	}

	public void setForeignRemitBankCode(String foreignRemitBankCode) {
		this.foreignRemitBankCode = foreignRemitBankCode;
	}

	public String getForeignRemitBankName() {
		return foreignRemitBankName;
	}

	public void setForeignRemitBankName(String foreignRemitBankName) {
		this.foreignRemitBankName = foreignRemitBankName;
	}

	public String getForeignRemitSwiftCode() {
		return foreignRemitSwiftCode;
	}

	public void setForeignRemitSwiftCode(String foreignRemitSwiftCode) {
		this.foreignRemitSwiftCode = foreignRemitSwiftCode;
	}

	public String getForeignRemitIbanCode() {
		return foreignRemitIbanCode;
	}

	public void setForeignRemitIbanCode(String foreignRemitIbanCode) {
		this.foreignRemitIbanCode = foreignRemitIbanCode;
	}

	public String getForeignRemitCty() {
		return foreignRemitCty;
	}

	public void setForeignRemitCty(String foreignRemitCty) {
		this.foreignRemitCty = foreignRemitCty;
	}

	public String getForeignRemitTown() {
		return foreignRemitTown;
	}

	public void setForeignRemitTown(String foreignRemitTown) {
		this.foreignRemitTown = foreignRemitTown;
	}

	public String getForeignRemitByPayee() {
		return foreignRemitByPayee;
	}

	public void setForeignRemitByPayee(String foreignRemitByPayee) {
		this.foreignRemitByPayee = foreignRemitByPayee;
	}

	public String getPayNumber() {
		return payNumber;
	}

	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}
	
	

}
