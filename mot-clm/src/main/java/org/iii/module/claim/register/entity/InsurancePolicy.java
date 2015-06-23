package org.iii.module.claim.register.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;
import org.slf4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * 保單
 * @author Mark Huang
 * @version 2014/5/17
 */
@Entity
@Table(name="INSURANCE_POLICY")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InsurancePolicy extends GenericEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8808846502632415317L;

	/** 保單號碼  */
	@Column(name="CODE")
	private String code;
	
	/** 保險起迄日  */
	@Column(name="INSURANCE_DATE")
	private String insuranceDate;
	
	/** 繳費情形  */
	@Column(name="PAY_STATU")
	private String payStatu;
	
	/** 狀態  */
	@Column(name="STATU")
	private String statu;
	
	/** 幣別  */
	@Column(name="MONEY_TYPE")
	private String moneyType;
	
	/** 險別 */
	@Column(name="Insurance_TYPE")
	private String insuranceType;
	
	/** 保額 */
	@Column(name="INSURANCE_VALUE")
	private String insuranceValue;
	
	/** 保費 */
	@Column(name="POLICY_PAID")
	private String policyPaid;
	
	/** 未收保費 */
	@Column(name="POLICY_NOT_PAID")
	private String policyNotPaid;
	
	/** 專案別 */
	@Column(name="PROJECT_TYPE")
	private String projectType;
	
	/** 繳費別 */
	@Column(name="PAY_TYPE")
	private String payType;
	
	/** 被保險人ID  */
	@Column(name="INSURED_ID")
	private String insuredId;
	
	/** 被保險人姓名  */
	@Column(name="INSURED_NAME")
	private String insuredName;
	
	/** 車牌  */
	@Column(name="PLATE")
	private String plate;
	
	/** 強制證號  */
	@Column(name="FORCED_CODE")
	private String forcedCode;
	
	/** 車輛種類 */
	@Column(name="CAR_TYPE")
	private long carTypeId;
	
	/** 引擎號碼 */
	@Column(name="ENGINE_CODE")
	private String engineCode;
	
	/** 車輛年份 */
	@Column(name="CAR_YEAR")
	private Integer carYear;
	
	/** 廠牌型式 */
	@Column(name="BRAND_TYPE")
	private String brandType;
	
	/** 排氣量 */
	@Column(name="DISPLACEMENT")
	private Integer displacement;
	
	/** 載客人數 */
	@Column(name="PASSENGERS")
	private Integer passengers;
	
	/** 特殊註記 */
	@Column(name="SPECIAL_NOTE")
	private String specialNote;
	
	/** 營業經辦 */
	@Column(name = "PROCESS_USER_CODE")
	private String processUserCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInsuranceDate() {
		return this.insuranceDate;
	}

	public void setInsuranceDate(String insuranceDate) {
		this.insuranceDate = insuranceDate;
	}

	public String getPayStatu() {
		return payStatu;
	}

	public void setPayStatu(String payStatu) {
		this.payStatu = payStatu;
	}

	public String getStatu() {
		return statu;
	}

	public void setStatu(String statu) {
		this.statu = statu;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
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

	public String getForcedCode() {
		return forcedCode;
	}

	public void setForcedCode(String forcedCode) {
		this.forcedCode = forcedCode;
	}

	public long getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(long carTypeId) {
		this.carTypeId = carTypeId;
	}
	
	public String getEngineCode() {
		return engineCode;
	}

	public void setEngineCode(String engineCode) {
		this.engineCode = engineCode;
	}

	public Integer getCarYear() {
		return carYear;
	}

	public void setCarYear(Integer carYear) {
		this.carYear = carYear;
	}

	public String getBrandType() {
		return brandType;
	}

	public void setBrandType(String brandType) {
		this.brandType = brandType;
	}

	public Integer getDisplacement() {
		return displacement;
	}

	public void setDisplacement(Integer displacement) {
		this.displacement = displacement;
	}

	public Integer getPassengers() {
		return passengers;
	}

	public void setPassengers(Integer passengers) {
		this.passengers = passengers;
	}

	public String getSpecialNote() {
		return specialNote;
	}

	public void setSpecialNote(String specialNote) {
		this.specialNote = specialNote;
	}

	public Logger getLog() {
		return log;
	}

	public String getInsuranceValue() {
		return insuranceValue;
	}

	public void setInsuranceValue(String insuranceValue) {
		this.insuranceValue = insuranceValue;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getPolicyPaid() {
		return policyPaid;
	}

	public void setPolicyPaid(String policyPaid) {
		this.policyPaid = policyPaid;
	}

	public String getPolicyNotPaid() {
		return policyNotPaid;
	}

	public void setPolicyNotPaid(String policyNotPaid) {
		this.policyNotPaid = policyNotPaid;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getProcessUserCode() {
		return processUserCode;
	}

	public void setProcessUserCode(String processUserCode) {
		this.processUserCode = processUserCode;
	}
	
	
	
}
