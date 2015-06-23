package org.iii.module.claim.register.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.iii.core.entity.GenericEntity;
import org.iii.core.enums.Country;
import org.iii.core.enums.ProcessStatus;
import org.iii.core.enums.Sex;
import org.iii.core.security.secUser.entity.SecUser;
import org.iii.module.claim.register.enums.AccidentDeal;
import org.iii.module.claim.register.enums.AccidentSecStatus;
import org.iii.module.claim.register.enums.InsuredRelation;
import org.iii.module.claim.register.enums.LicenseType;
import org.iii.module.claim.register.enums.Marriage;
import org.iii.module.claim.remark.enums.RemarkProcessStatus;
import org.iii.module.setting.setAccidcir.entity.SetAccidcir;
import org.iii.module.setting.setCarType.entity.SetCarType;
import org.iii.module.setting.setEvent.entity.SetEvent;
import org.iii.module.setting.setOffice.entity.SetOffice;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * 報案
 * @author Mark Huang
 * @version 2014/4/6
 */
@Entity
@Table(name="CLM_REG")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Register extends GenericEntity {
	
	private static final long serialVersionUID = 6370437332826017113L;

	/** 報案編號  */
	@Column(name="CODE")
	private String code;
	
	/** 處理單位  */
	@Column(name="OFFICE_ID")
	private Long officeId;
	
	/** 處理單位entity  */
	@Transient
	private SetOffice office;
	
	/** 賠案號碼  */
	@Column(name="CASE_CODE")
	private String caseCode;
	
	/** 立案日  */
	@Column(name="POST_DATE")
	private LocalDateTime postDate;
	
	/** 出險型態  */
	@Column(name="ACCIDCIR_LOCALNAME")
	private String accidcirLocalName;
	
	/** 出險型態entity  */
	@Transient
	private SetAccidcir accidcir;
	
	/** 受理類型  */
	@Column(name="PROCESS_TYPE")
	private String processType;
	
	/** 被保險人ID  */
	@Column(name="INSURED_ID")
	private String insuredId;
	
	/** 被保險人姓名  */
	@Column(name="INSURED_NAME")
	private String insuredName;
	
	/** 出險日期  */
	@Column(name="ACCIDCIR_DATE")
	private LocalDateTime accidcirDate;
	
	/** 被保險人電話(國碼) */
	@Column(name="INSURED_TEL_CTY")
	private String insuredTelCty;
	
	/** 被保險人電話(區碼) */
	@Column(name="INSURED_TEL_AREA")
	private String insuredTelArea;
	
	/** 被保險人電話(電話號碼) */
	@Column(name="INSURED_TEL")
	private String insuredTel;
	
	/** 被保險人電話(手機國碼) */
	@Column(name="INSURED_PHONE_CTY")
	private String insuredPhoneCty;
	
	/** 被保險人電話(手機號碼) */
	@Column(name="INSURED_PHONE")
	private String insuredPhone;
	
	/** 被保險人是否收簡訊 */
	@Column(name="INSURED_MESSAGE")
	private boolean insuredMessage;
	
	/** 被保險人郵遞區號 */
	@Column(name="INSURED_POSTAL")
	private String insuredPostal;
	
	/** 被保險人地址 */
	@Column(name="INSURED_ADDRESS")
	private String insuredAddress;
	
	/** 車牌  */
	@Column(name="PLATE")
	private String plate;
	
	/** 強制證號  */
	@Column(name="FORCED_CODE")
	private String forcedCode;
	
	/** 車輛種類 */
	@Column(name="CAR_TYPE")
	private String carTypeId;
	
	/** 車輛種類entity  */
	@Transient
	private SetCarType carType;
	
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
	
	/** 駕駛人 */
	@Column(name="DRIVER")
	private String driver;
	
	/** 駕照號碼 */
	@Column(name="DRIVERS_CODE")
	private String driversCode;
	
	/** 領(換)照年度 */
	@Column(name="LICENSE_YEAR")
	private Integer licenseYear;
	
	/** 出生日期 */
	@Column(name="BIRTHDAY")
	private LocalDateTime birthday;
		
	/** 性別 */
	@Column(name="SEX")
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	/** 國籍 */
	@Column(name="COUNTRY")
	@Enumerated(EnumType.STRING)
	private Country country;
	
	/** 與被保人關係 */
	@Column(name="INSURED_REL")
	@Enumerated(EnumType.STRING)
	private InsuredRelation insuredRel;
	
	/** 駕照種類 */
	@Column(name="LICENSE_TYPE")
	@Enumerated(EnumType.STRING)
	private LicenseType licenseType;
	
	/** 婚姻狀況 */
	@Column(name="MARRIAGE")
	@Enumerated(EnumType.STRING)
	private Marriage marriage;
	
	/** 駕駛人電話(國碼) */
	@Column(name="DRIVER_TEL_CTY")
	private String driverTelCty;
	
	/** 駕駛人電話(區碼) */
	@Column(name="DRIVER_TEL_AREA")
	private String driverTelArea;
	
	/** 駕駛人電話(電話號碼) */
	@Column(name="DRIVER_TEL")
	private String driverTel;
	
	/** 駕駛人電話(手機國碼) */
	@Column(name="DRIVER_PHONE_CTY")
	private String driverPhoneCty;
	
	/** 駕駛人電話(手機號碼) */
	@Column(name="DRIVER_PHONE")
	private String driverPhone;
	
	/** 駕駛人是否收簡訊 */
	@Column(name="DRIVER_MESSAGE")
	private boolean driverMessage;
	
	/** 駕駛人郵遞區號 */
	@Column(name="DRIVER_POSTAL")
	private String driverPostal;
	
	/** 駕駛人地址 */
	@Column(name="DRIVER_ADDRESS")
	private String driverAddress;
	
	/** 案件狀態 */
	@Column(name="REG_STATUS")
	@Enumerated(EnumType.STRING)
	private ProcessStatus regStatus;
	
	/** 主要事故原因 */
	@Column(name="MAIN_ACCIDENT")
	private String mainAccident;
	
	/** 主要事故原因名 */
	@Column(name="MAIN_ACCIDENT_NAME")
	private String mainAccidentName;
	
	/** 其他事故原因 */
	@Column(name="OTHER_ACCIDENT")
	private String otherAccident;
	
	/** 其他事故原因名 */
	@Column(name="OTHER_ACCIDENT_NAME")
	private String otherAccidentName;
	
	/** 事故人ID */
	@Column(name="ACCIDENT_PEOPLE_ID")
	private String accidentPeopleId;
	
	/** 事故人姓名 */
	@Column(name="ACCIDENT_PEOPLE_NAME")
	private String accidentPeopleName;
	
	/** 事故日期*/
	@Column(name="ACCIDENT_DATE")
	private LocalDateTime accidentDate;
	
	/** 事故地點(國家) */
	@Column(name="ACCIDENT_COUNTRY")
	private String accidentCountry;
	
	/** 事故地點(縣市) */
	@Column(name="ACCIDENT_COUNTY")
	private String accidentCounty;
	
	/** 事故地點(郵遞區號) */
	@Column(name="ACCIDENT_POSTAL_CODE")
	private String accidentPostalCode;
	
	/** 事故地點(地址) */
	@Column(name="ACCIDENT_ADDRESS")
	private String accidentAddress;
	
	/** 事故地點補述 */
	@Column(name="ACCIDENT_ADDRESS_DES")
	private String accidentAddressDes;
	
	/** 事件 */
	@Column(name="EVENT")
	private long eventId;
	
	/** 事故entity */
	@Transient
	private SetEvent event;
	
	/** 事故概述 */
	@Column(name="ACCIDENT_DES")
	private String accidentDes;
	
	/** 狀態  */
	@Column(name="ACCIDENT_STATUS")
	@Enumerated(EnumType.STRING)
	private ProcessStatus accidentStatus;
	
	/** 次狀態  */
	@Column(name="ACCIDENT_SEC_STATUS")
	@Enumerated(EnumType.STRING)
	private AccidentSecStatus accidentSecStatus;
	
	/** 事故處理  */
	@Column(name="ACCIDENT_DEAL")
	@Enumerated(EnumType.STRING)
	private AccidentDeal accidentDeal;
	
	/** 事故處理單位  */
	@Column(name="ACCIDENT_DEAL_UNIT")
	private String accidentDealUnit;
	
	/** 申請日  */
	@Column(name="APPLY_DATE")
	private LocalDateTime applyDate;
	
	/** 受理日期  */
	@Column(name="ACCEPTED_DATE")
	private LocalDateTime acceptedDate;
	
	/** 申請人  */
	@Column(name="APPLICANT")
	private String applicant;
	
	/** 申請人與被保人關係  */
	@Column(name="APPLICANT_INSURED_REL")
	@Enumerated(EnumType.STRING)
	private InsuredRelation applicantInsuredRel;
	
	/** 受理經辦  */
	@Column(name="PROCESS_USER_CODE")
	private String processUserCode;
	
	/** 受理經辦entity */
	@Transient
	private SecUser processUser;
	
	/** 代處理受理經辦  */
	@Column(name="PROXY_PROCESS_USER_CODE")
	private String proxyProcessUserCode;
	
	/** 代處理受理經辦entity */
	@Transient
	private SecUser proxyProcessUser;
	
	/** 保單號碼  */
	@Column(name="POLICY_CODE")
	private String policyCode;
	
	/** 處理狀態  */
	@Column(name="REMARK_PROCESS_STATUS")
	@Enumerated(EnumType.STRING)
	private RemarkProcessStatus remarkProcessStatus;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getOfficeId() {
		return officeId;
	}

	public void setOfficeId(Long officeId) {
		this.officeId = officeId;
	}

	public SetOffice getOffice() {
		return office;
	}

	public void setOffice(SetOffice office) {
		this.office = office;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public LocalDateTime getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDateTime postDate) {
		this.postDate = postDate;
	}

	public String getAccidcirLocalName() {
		return accidcirLocalName;
	}

	public void setAccidcirLocalName(String accidcirLocalName) {
		this.accidcirLocalName = accidcirLocalName;
	}

	public SetAccidcir getAccidcir() {
		return accidcir;
	}

	public void setAccidcir(SetAccidcir accidcir) {
		this.accidcir = accidcir;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
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

	public String getInsuredTelCty() {
		return insuredTelCty;
	}

	public void setInsuredTelCty(String insuredTelCty) {
		this.insuredTelCty = insuredTelCty;
	}

	public String getInsuredTelArea() {
		return insuredTelArea;
	}

	public void setInsuredTelArea(String insuredTelArea) {
		this.insuredTelArea = insuredTelArea;
	}

	public String getInsuredTel() {
		return insuredTel;
	}

	public void setInsuredTel(String insuredTel) {
		this.insuredTel = insuredTel;
	}

	public String getInsuredPhoneCty() {
		return insuredPhoneCty;
	}

	public void setInsuredPhoneCty(String insuredPhoneCty) {
		this.insuredPhoneCty = insuredPhoneCty;
	}

	public String getInsuredPhone() {
		return insuredPhone;
	}

	public void setInsuredPhone(String insuredPhone) {
		this.insuredPhone = insuredPhone;
	}

	public boolean isInsuredMessage() {
		return insuredMessage;
	}

	public void setInsuredMessage(boolean insuredMessage) {
		this.insuredMessage = insuredMessage;
	}

	public String getInsuredPostal() {
		return insuredPostal;
	}

	public void setInsuredPostal(String insuredPostal) {
		this.insuredPostal = insuredPostal;
	}

	public String getInsuredAddress() {
		return insuredAddress;
	}

	public void setInsuredAddress(String insuredAddress) {
		this.insuredAddress = insuredAddress;
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

	public String getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(String carTypeId) {
		this.carTypeId = carTypeId;
	}

	public SetCarType getCarType() {
		return carType;
	}

	public void setCarType(SetCarType carType) {
		this.carType = carType;
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

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDriversCode() {
		return driversCode;
	}

	public void setDriversCode(String driversCode) {
		this.driversCode = driversCode;
	}

	public Integer getLicenseYear() {
		return licenseYear;
	}

	public void setLicenseYear(Integer licenseYear) {
		this.licenseYear = licenseYear;
	}

	public LocalDateTime getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDateTime birthday) {
		this.birthday = birthday;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public InsuredRelation getInsuredRel() {
		return insuredRel;
	}

	public void setInsuredRel(InsuredRelation insuredRel) {
		this.insuredRel = insuredRel;
	}

	public LicenseType getLicenseType() {
		return licenseType;
	}

	public void setLicenseType(LicenseType licenseType) {
		this.licenseType = licenseType;
	}

	public Marriage getMarriage() {
		return marriage;
	}

	public void setMarriage(Marriage marriage) {
		this.marriage = marriage;
	}

	public String getDriverTelCty() {
		return driverTelCty;
	}

	public void setDriverTelCty(String driverTelCty) {
		this.driverTelCty = driverTelCty;
	}

	public String getDriverTelArea() {
		return driverTelArea;
	}

	public void setDriverTelArea(String driverTelArea) {
		this.driverTelArea = driverTelArea;
	}

	public String getDriverTel() {
		return driverTel;
	}

	public void setDriverTel(String driverTel) {
		this.driverTel = driverTel;
	}

	public String getDriverPhoneCty() {
		return driverPhoneCty;
	}

	public void setDriverPhoneCty(String driverPhoneCty) {
		this.driverPhoneCty = driverPhoneCty;
	}

	public String getDriverPhone() {
		return driverPhone;
	}

	public void setDriverPhone(String driverPhone) {
		this.driverPhone = driverPhone;
	}

	public boolean isDriverMessage() {
		return driverMessage;
	}

	public void setDriverMessage(boolean driverMessage) {
		this.driverMessage = driverMessage;
	}

	public String getDriverPostal() {
		return driverPostal;
	}

	public void setDriverPostal(String driverPostal) {
		this.driverPostal = driverPostal;
	}

	public String getDriverAddress() {
		return driverAddress;
	}

	public void setDriverAddress(String driverAddress) {
		this.driverAddress = driverAddress;
	}

	public ProcessStatus getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(ProcessStatus regStatus) {
		this.regStatus = regStatus;
	}

	public String getMainAccident() {
		return mainAccident;
	}

	public void setMainAccident(String mainAccident) {
		this.mainAccident = mainAccident;
	}

	public String getOtherAccident() {
		return otherAccident;
	}

	public void setOtherAccident(String otherAccident) {
		this.otherAccident = otherAccident;
	}

	public String getAccidentPeopleId() {
		return accidentPeopleId;
	}

	public void setAccidentPeopleId(String accidentPeopleId) {
		this.accidentPeopleId = accidentPeopleId;
	}

	public String getAccidentPeopleName() {
		return accidentPeopleName;
	}

	public void setAccidentPeopleName(String accidentPeopleName) {
		this.accidentPeopleName = accidentPeopleName;
	}

	public String getAccidentCountry() {
		return accidentCountry;
	}

	public void setAccidentCountry(String accidentCountry) {
		this.accidentCountry = accidentCountry;
	}

	public String getAccidentCounty() {
		return accidentCounty;
	}

	public void setAccidentCounty(String accidentCounty) {
		this.accidentCounty = accidentCounty;
	}

	public String getAccidentPostalCode() {
		return accidentPostalCode;
	}

	public void setAccidentPostalCode(String accidentPostalCode) {
		this.accidentPostalCode = accidentPostalCode;
	}

	public String getAccidentAddress() {
		return accidentAddress;
	}

	public void setAccidentAddress(String accidentAddress) {
		this.accidentAddress = accidentAddress;
	}

	public String getAccidentAddressDes() {
		return accidentAddressDes;
	}

	public void setAccidentAddressDes(String accidentAddressDes) {
		this.accidentAddressDes = accidentAddressDes;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public SetEvent getEvent() {
		return event;
	}

	public void setEvent(SetEvent event) {
		this.event = event;
	}

	public String getAccidentDes() {
		return accidentDes;
	}

	public void setAccidentDes(String accidentDes) {
		this.accidentDes = accidentDes;
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

	public AccidentDeal getAccidentDeal() {
		return accidentDeal;
	}

	public void setAccidentDeal(AccidentDeal accidentDeal) {
		this.accidentDeal = accidentDeal;
	}

	public String getAccidentDealUnit() {
		return accidentDealUnit;
	}

	public void setAccidentDealUnit(String accidentDealUnit) {
		this.accidentDealUnit = accidentDealUnit;
	}

	public LocalDateTime getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(LocalDateTime applyDate) {
		this.applyDate = applyDate;
	}

	public LocalDateTime getAcceptedDate() {
		return acceptedDate;
	}

	public void setAcceptedDate(LocalDateTime acceptedDate) {
		this.acceptedDate = acceptedDate;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public InsuredRelation getApplicantInsuredRel() {
		return applicantInsuredRel;
	}

	public void setApplicantInsuredRel(InsuredRelation applicantInsuredRel) {
		this.applicantInsuredRel = applicantInsuredRel;
	}

	public String getProcessUserCode() {
		return processUserCode;
	}

	public void setProcessUserCode(String processUserCode) {
		this.processUserCode = processUserCode;
	}

	public SecUser getProcessUser() {
		return processUser;
	}

	public void setProcessUser(SecUser processUser) {
		this.processUser = processUser;
	}

	public String getProxyProcessUserCode() {
		return proxyProcessUserCode;
	}

	public void setProxyProcessUserCode(String proxyProcessUserCode) {
		this.proxyProcessUserCode = proxyProcessUserCode;
	}

	public SecUser getProxyProcessUser() {
		return proxyProcessUser;
	}

	public void setProxyProcessUser(SecUser proxyProcessUser) {
		this.proxyProcessUser = proxyProcessUser;
	}

	public String getPolicyCode() {
		return policyCode;
	}

	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}
	
	public LocalDateTime getAccidentDate() {
		return accidentDate;
	}

	public void setAccidentDate(LocalDateTime accidentDate) {
		this.accidentDate = accidentDate;
	}

	public RemarkProcessStatus getRemarkProcessStatus() {
		return remarkProcessStatus;
	}

	public void setRemarkProcessStatus(RemarkProcessStatus remarkProcessStatus) {
		this.remarkProcessStatus = remarkProcessStatus;
	}

	public String getMainAccidentName() {
		return mainAccidentName;
	}

	public void setMainAccidentName(String mainAccidentName) {
		this.mainAccidentName = mainAccidentName;
	}

	public String getOtherAccidentName() {
		return otherAccidentName;
	}

	public void setOtherAccidentName(String otherAccidentName) {
		this.otherAccidentName = otherAccidentName;
	}
	
}