package org.iii.module.claim.estimateDetail.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;
import org.iii.core.entity.GenericEntity;
import org.iii.core.enums.BrandType;
import org.iii.core.enums.Country;
import org.iii.core.enums.Sex;
import org.iii.module.claim.estimateDetail.enums.Repair;
import org.iii.module.claim.register.enums.Job;
import org.iii.module.claim.register.enums.Marriage;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * 受害人預估賠款 Entity
 * @author 黃小貓
 * @version 2014/5/12
 */
@Entity
@Table(name="CLM_ESTIMATE_DETAIL")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EstimateDetail extends GenericEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2470904600202986982L;
	
	/**
	 * 賠案號碼
	 */
	@Column(name="CASE_CODE")
	private String caseCode;
	
	/** 
	 * 出險日期
	 */
	@Column(name="ACCIDCIR_DATE")
	private LocalDateTime accidcirDate;
	
	/**
	 * 被保險人ID
	 */
	@Column(name="INSURED_ID")
	private String insuredId;
	
	/** 
	 * 被保險人姓名
	 */
	@Column(name="INSURED_NAME")
	private String insuredName;
	
	/**
	 * 車牌號碼
	 */
	@Column(name="PLATE")
	private String plate;
	
	/**
	 * 申請次數
	 */
	@Column(name="APPLY_BOUT")
	private Integer applyBout;
	
	/**
	 * 事故原因
	 */
	@Column(name="ACCIDENT")
	private String accident;
	
	/**
	 * 受害人
	 */
	@Column(name="VICTIMS_NAME")
	private String victimsName;
	
	/**
	 * 身份證號
	 */
	@Column(name="VICTIMS_ID")
	private String victimsId;
	
	/** 
	 * 出生日期
	 */
	@Column(name="BIRTHDAY")
	private LocalDateTime birthday;
	
	/** 
	 * 性別
	 */
	@Column(name="SEX")
	@Enumerated(EnumType.STRING)
	private Sex sex;
	
	/** 
	 * 職業 
	 */
	@Column(name="JOB")
	@Enumerated(EnumType.STRING)
	private Job job;
	
	/** 
	 * 婚姻
	 */
	@Column(name="MARRIAGE")
	@Enumerated(EnumType.STRING)
	private Marriage marriage;
	
	/** 
	 * 國籍 
	 */
	@Column(name="COUNTRY")
	@Enumerated(EnumType.STRING)
	private Country country;
	
	/**
	 * 電話(住家)
	 */
	@Column(name="VICTIMS_TEL_HOME")
	private String victimsTelHome;
	
	/**
	 * 電話(手機)
	 */
	@Column(name="VICTIMS_MOBILE")
	private String victimsMobile;
	
	/**
	 * E-mail
	 */
	@Column(name="EMAIL")
	private String email;
	
	/** 
	 * 地址 
	 */
	@Column(name="VICTIMS_ADDR")
	private String victimsAddr;
	
	/** 
	 * 受傷部位 
	 */
	@Column(name="INJURED_AREA")
	private String injuredArea;
	
	/** 
	 * 傷勢 
	 */
	@Column(name="INJURED")
	private String injured;
	
	/** 
	 * 體傷給付 
	 */
	@Column(name="INJURED_BENEFIT")
	private Double injuredBenefit;
	
	/** 
	 * 殘廢給付
	 */
	@Column(name="DISABILITY_BENEFIT")
	private Double disabilityBenefit;
	
	/** 
	 * 死亡給付
	 */
	@Column(name="DEATH_BENEFIT")
	private Double deathBenefit;
	
	/** 
	 * 預估損失金額
	 */
	@Column(name="ESTIMATED_LOSS_AMOUNT")
	private Double estimatedLossAmount;
	
	/** 
	 * 處理單位
	 */
	@Column(name="PROCESS_OFFICE")
	private String processOffice;
	
	/** 
	 * 理賠經辦
	 */
	@Column(name="CLAIMS_MANAGERS")
	private String claimsManagers;
	
	/** 
	 * 車主姓名
	 */
	@Column(name="OWNER_NAME")
	private String ownerName;
	
	/** 
	 * 車主電話(住家)
	 */
	@Column(name="OWNER_TEL_HOME")
	private String ownerTelHome;
	
	/** 
	 * 車主電話(手機)
	 */
	@Column(name="OWNER_MOBILE")
	private String ownerMobile;
	
	/** 
	 * 車主地址
	 */
	@Column(name="OWNER_ADDR")
	private String ownerAddr;
	
	/** 
	 * 駕照號碼
	 */
	@Column(name="DRIVERS_CODE")
	private String driversCode;
	
	/** 
	 * 駕駛人
	 */
	@Column(name="DRIVER")
	private String driver;
	
	/** 
	 * 駕駛電話(住家)
	 */
	@Column(name="DRIVER_TEL_HOME")
	private String driverTelHome;
	
	/** 
	 * 駕駛電話(手機)
	 */
	@Column(name="DRIVER_MOBILE")
	private String driverMobile;
	
	/** 
	 * 駕駛地址
	 */
	@Column(name="DRIVER_ADDR")
	private String driverAddr;
	
	/** 
	 * 廠牌型式
	 */
	@Column(name="BRAND_TYPE")
	@Enumerated(EnumType.STRING)
	private BrandType brandType;
	
	/** 
	 * 車輛年份
	 */
	@Column(name="CAR_YEAR")
	private Long carYear;
	
	/** 
	 * 排氣量
	 */
	@Column(name="DISPLACEMENT")
	private Integer displacement;
	
	/** 
	 * 引擎號碼
	 */
	@Column(name="ENGINE_CODE")
	private String engineCode;
	
	/** 
	 * 車身號碼
	 */
	@Column(name="CAR_CODE")
	private String carCode;
	
	/** 
	 * 修理廠
	 */
	@Column(name="REPAIR")
	@Enumerated(EnumType.STRING)
	private Repair repair;
	
	/** 
	 * 聯絡人
	 */
	@Column(name="CONTACT")
	private String contact;
	
	/** 
	 * 聯絡人電話(住家)
	 */
	@Column(name="CONTACT_TEL_HOME")
	private String contactTelHome;
	
	/** 
	 * 聯絡人電話(手機)
	 */
	@Column(name="CONTACT_MOBILE")
	private String contactMobile;
	
	/** 
	 * 修理廠統編
	 */
	@Column(name="REPAIR_ID")
	private String repairId;
	
	/** 
	 * 修理廠地址
	 */
	@Column(name="REPAIR_ADDR")
	private String repairAddr;
	
	/** 
	 * 險種
	 */
	@Column(name="INSURED_TYPE")
	private String insuredType;
	
	/** 
	 * 保額
	 */
	@Column(name="INSURED_AMOUNT")
	private Double insuredAmount;
	
	/** 
	 * 損失金額
	 */
	@Column(name="LOSS_AMOUNT")
	private Double lossAmount;
	
	/** 
	 * 自負額
	 */
	@Column(name="DEDUCTIBLE")
	private Double deductible ;

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	@JSON(serialize=false)
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

	public String getVictimsName() {
		return victimsName;
	}

	public void setVictimsName(String victimsName) {
		this.victimsName = victimsName;
	}

	public String getVictimsId() {
		return victimsId;
	}

	public void setVictimsId(String victimsId) {
		this.victimsId = victimsId;
	}
	
	@JSON(serialize=false)
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

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Marriage getMarriage() {
		return marriage;
	}

	public void setMarriage(Marriage marriage) {
		this.marriage = marriage;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getVictimsTelHome() {
		return victimsTelHome;
	}

	public void setVictimsTelHome(String victimsTelHome) {
		this.victimsTelHome = victimsTelHome;
	}

	public String getVictimsMobile() {
		return victimsMobile;
	}

	public void setVictimsMobile(String victimsMobile) {
		this.victimsMobile = victimsMobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVictimsAddr() {
		return victimsAddr;
	}

	public void setVictimsAddr(String victimsAddr) {
		this.victimsAddr = victimsAddr;
	}

	public String getInjuredArea() {
		return injuredArea;
	}

	public void setInjuredArea(String injuredArea) {
		this.injuredArea = injuredArea;
	}

	public String getInjured() {
		return injured;
	}

	public void setInjured(String injured) {
		this.injured = injured;
	}

	public Double getInjuredBenefit() {
		return injuredBenefit;
	}

	public void setInjuredBenefit(Double injuredBenefit) {
		this.injuredBenefit = injuredBenefit;
	}

	public Double getDisabilityBenefit() {
		return disabilityBenefit;
	}

	public void setDisabilityBenefit(Double disabilityBenefit) {
		this.disabilityBenefit = disabilityBenefit;
	}

	public Double getDeathBenefit() {
		return deathBenefit;
	}

	public void setDeathBenefit(Double deathBenefit) {
		this.deathBenefit = deathBenefit;
	}

	public Double getEstimatedLossAmount() {
		return estimatedLossAmount;
	}

	public void setEstimatedLossAmount(Double estimatedLossAmount) {
		this.estimatedLossAmount = estimatedLossAmount;
	}

	public String getProcessOffice() {
		return processOffice;
	}

	public void setProcessOffice(String processOffice) {
		this.processOffice = processOffice;
	}

	public String getClaimsManagers() {
		return claimsManagers;
	}

	public void setClaimsManagers(String claimsManagers) {
		this.claimsManagers = claimsManagers;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerTelHome() {
		return ownerTelHome;
	}

	public void setOwnerTelHome(String ownerTelHome) {
		this.ownerTelHome = ownerTelHome;
	}

	public String getOwnerMobile() {
		return ownerMobile;
	}

	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}

	public String getOwnerAddr() {
		return ownerAddr;
	}

	public void setOwnerAddr(String ownerAddr) {
		this.ownerAddr = ownerAddr;
	}

	public String getDriversCode() {
		return driversCode;
	}

	public void setDriversCode(String driversCode) {
		this.driversCode = driversCode;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getDriverTelHome() {
		return driverTelHome;
	}

	public void setDriverTelHome(String driverTelHome) {
		this.driverTelHome = driverTelHome;
	}

	public String getDriverMobile() {
		return driverMobile;
	}

	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}

	public String getDriverAddr() {
		return driverAddr;
	}

	public void setDriverAddr(String driverAddr) {
		this.driverAddr = driverAddr;
	}

	public BrandType getBrandType() {
		return brandType;
	}

	public void setBrandType(BrandType brandType) {
		this.brandType = brandType;
	}

	public Long getCarYear() {
		return carYear;
	}

	public void setCarYear(Long carYear) {
		this.carYear = carYear;
	}

	public Integer getDisplacement() {
		return displacement;
	}

	public void setDisplacement(Integer displacement) {
		this.displacement = displacement;
	}

	public String getEngineCode() {
		return engineCode;
	}

	public void setEngineCode(String engineCode) {
		this.engineCode = engineCode;
	}

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public Repair getRepair() {
		return repair;
	}

	public void setRepair(Repair repair) {
		this.repair = repair;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactTelHome() {
		return contactTelHome;
	}

	public void setContactTelHome(String contactTelHome) {
		this.contactTelHome = contactTelHome;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getRepairId() {
		return repairId;
	}

	public void setRepairId(String repairId) {
		this.repairId = repairId;
	}

	public String getRepairAddr() {
		return repairAddr;
	}

	public void setRepairAddr(String repairAddr) {
		this.repairAddr = repairAddr;
	}

	public String getInsuredType() {
		return insuredType;
	}

	public void setInsuredType(String insuredType) {
		this.insuredType = insuredType;
	}

	public Double getInsuredAmount() {
		return insuredAmount;
	}

	public void setInsuredAmount(Double insuredAmount) {
		this.insuredAmount = insuredAmount;
	}

	public Double getLossAmount() {
		return lossAmount;
	}

	public void setLossAmount(Double lossAmount) {
		this.lossAmount = lossAmount;
	}

	public Double getDeductible() {
		return deductible;
	}

	public void setDeductible(Double deductible) {
		this.deductible = deductible;
	}
	
	
	
}
