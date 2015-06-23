package org.iii.module.claim.register.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * 保單項目
 * @author Mark Huang
 * @version 2014/5/20
 */
@Entity
@Table(name="INSURANCE_POLICY_ITEM")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InsurancePolicyItem extends GenericEntity {
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
	
	/** 承保項目  */
	@Column(name="INSURANCE_ITEM")
	private String insuranceItem;
	
	/** 每一事故  */
	@Column(name="EACH_ACCIDENT")
	private String eachAccident;
	
	/** 每一體傷  */
	@Column(name="EACH_HURT")
	private String eachHurt;
	
	/** 每一死亡  */
	@Column(name="EACH_DEAD")
	private String eachDead;
	
	/** 自負額  */
	@Column(name="PAY_SELF")
	private String paySelf;
	
	/** 幣別  */
	@Column(name="MONEY_TYPE")
	private String moneyType;
	
	/** 險別 */
	@Column(name="Insurance_TYPE")
	private String insuranceType;
	
	/** 保額 */
	@Column(name="INSURANCE_VALUE")
	private String insuranceValue;
	
	/** 車牌  */
	@Column(name="PLATE")
	private String plate;

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInsuranceDate() {
		return insuranceDate;
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

	public String getInsuranceItem() {
		return insuranceItem;
	}

	public void setInsuranceItem(String insuranceItem) {
		this.insuranceItem = insuranceItem;
	}

	public String getEachAccident() {
		return eachAccident;
	}

	public void setEachAccident(String eachAccident) {
		this.eachAccident = eachAccident;
	}

	public String getEachHurt() {
		return eachHurt;
	}

	public void setEachHurt(String eachHurt) {
		this.eachHurt = eachHurt;
	}

	public String getEachDead() {
		return eachDead;
	}

	public void setEachDead(String eachDead) {
		this.eachDead = eachDead;
	}

	public String getPaySelf() {
		return paySelf;
	}

	public void setPaySelf(String paySelf) {
		this.paySelf = paySelf;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getInsuranceValue() {
		return insuranceValue;
	}

	public void setInsuranceValue(String insuranceValue) {
		this.insuranceValue = insuranceValue;
	}
	
	
	
}
