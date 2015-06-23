package org.iii.module.claim.contact.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;
import org.iii.module.claim.contact.enums.Type;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * 報案通訊錄 Entity
 * @author 黃小貓
 * @version 2014/4/9
 */
@Entity
@Table(name="CLM_GI_CONTACT")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Contact extends GenericEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2470904600202986982L;
	
	/**
	 * 報案編號
	 */
	@Column(name="REG_CODE")
	private String regCode;
	
	/**
	 * 賠案編號
	 */
	@Column(name="CASE_CODE")
	private String caseCode;
	
	/**
	 * 類別
	 */
	@Column(name="TYPE")
	@Enumerated(EnumType.STRING)
	private Type type;
	
	/**
	 * 客戶編號
	 */
	@Column(name="CUSTOMER_ID")
	private String customerId;
	
	/**
	 * 事故車號
	 */
	@Column(name="ACCIDENT_PLATE")
	private String accidentPlate;
	
	/**
	 * 聯絡人
	 */
	@Column(name="CONTACT")
	private String contact;
	
	/**
	 * 辦公室電話(國碼)
	 */
	@Column(name="OFFICE_TEL_CTY")
	private String officeTelCty;
	
	/**
	 * 辦公室電話(區碼)
	 */
	@Column(name="OFFICE_TEL_AREA")
	private String officeTelArea;
	
	/**
	 * 辦公室電話(電話號碼)
	 */
	@Column(name="OFFICE_TEL")
	private String officeTel;
	
	/**
	 * 辦公室電話(分機)
	 */
	@Column(name="OFFICE_TEL_EXT")
	private String officeTelExt;
	
	/**
	 * 傳真電話(國碼)
	 */
	@Column(name="FAX_CTY")
	private String faxCty;
	
	/**
	 * 傳真電話(區碼)
	 */
	@Column(name="FAX_AREA")
	private String faxArea;
	
	/**
	 * 傳真電話(電話號碼)
	 */
	@Column(name="FAX")
	private String fax;
	
	/**
	 * 電子郵件
	 */
	@Column(name="MAIL")
	private String mail;
	
	/**
	 * 住宅電話(國碼)
	 */
	@Column(name="HOME_TEL_CTY")
	private String homeTelCty;
	
	/**
	 * 住宅電話(區碼)
	 */
	@Column(name="HOME_TEL_AREA")
	private String homeTelArea;
	
	/**
	 * 住宅電話(電話號碼)
	 */
	@Column(name="HOME_TEL")
	private String homeTel;
	
	/**
	 * 行動電話(國碼)
	 */
	@Column(name="MOBILE_CTY")
	private String mobileCty;
	
	/**
	 * 行動電話(電話號碼)
	 */
	@Column(name="MOBILE")
	private String mobile;
	
	/**
	 * 簡訊
	 */
	@Column(name="MESSAGE")
	private Boolean message;
	
	/**
	 * 地址
	 */
	@Column(name="ADDRESS")
	private String address;

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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAccidentPlate() {
		return accidentPlate;
	}

	public void setAccidentPlate(String accidentPlate) {
		this.accidentPlate = accidentPlate;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getOfficeTelCty() {
		return officeTelCty;
	}

	public void setOfficeTelCty(String officeTelCty) {
		this.officeTelCty = officeTelCty;
	}

	public String getOfficeTelArea() {
		return officeTelArea;
	}

	public void setOfficeTelArea(String officeTelArea) {
		this.officeTelArea = officeTelArea;
	}

	public String getOfficeTel() {
		return officeTel;
	}

	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}

	public String getOfficeTelExt() {
		return officeTelExt;
	}

	public void setOfficeTelExt(String officeTelExt) {
		this.officeTelExt = officeTelExt;
	}

	public String getFaxCty() {
		return faxCty;
	}

	public void setFaxCty(String faxCty) {
		this.faxCty = faxCty;
	}

	public String getFaxArea() {
		return faxArea;
	}

	public void setFaxArea(String faxArea) {
		this.faxArea = faxArea;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getHomeTelCty() {
		return homeTelCty;
	}

	public void setHomeTelCty(String homeTelCty) {
		this.homeTelCty = homeTelCty;
	}

	public String getHomeTelArea() {
		return homeTelArea;
	}

	public void setHomeTelArea(String homeTelArea) {
		this.homeTelArea = homeTelArea;
	}

	public String getHomeTel() {
		return homeTel;
	}

	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}

	public String getMobileCty() {
		return mobileCty;
	}

	public void setMobileCty(String mobileCty) {
		this.mobileCty = mobileCty;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Boolean getMessage() {
		return message;
	}

	public void setMessage(Boolean message) {
		this.message = message;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
//	@Override
//	public String toString() {
//		
//		return "Contact [regCode=" + regCode + ", caseCode=" + caseCode
//				+ ", type=" + type + ", customerId=" + customerId
//				+ ", accidentPlate=" + accidentPlate + ", contact=" + contact
//				+ ", officeTelCty=" + officeTelCty + ", officeTelArea=" + officeTelArea
//				+ ", officeTel=" + officeTel + ", officeTelExt=" + officeTelExt
//				+ ", faxCty=" + faxCty + ", faxArea=" + faxArea
//				+ ", fax=" + fax + ", mail=" + mail
//				+ ", homeTelCty=" + homeTelCty + ", homeTelArea=" + homeTelArea
//				+ ", homeTel=" + homeTel + ", mobileCty=" + mobileCty
//				+ ", mobile=" + mobile + ", message="
//				+ message + ", address=" + address + "]";
//	}

	
}
