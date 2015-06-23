package org.iii.core.security.secUser.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.annotations.Type;
import org.iii.core.entity.GenericEntity;
import org.iii.core.security.secUser.enums.UserType;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
/**
 * 使用者
 * @author David Hsu
 * @version 2014/3/7
 */
@Entity
@Table(name="SEC_USER")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SecUser extends GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5804311089729989927L;

	/**
	 * 使用者代號
	 */
	@Column(name="USER_CODE")
	private String userCode;
	
	/**
	 * 使用者名稱
	 */
	@Column(name="USER_NAME")
	private String userName;
	
	/**
	 * 使用者密碼
	 */
	@Column(name="USER_PASSWORD")
	private String userPassword;
	
	/**
	 * 工作單位
	 */
	@Column(name="WORK_OFFICE")
	private String workOffice;
	
	/**
	 * 電話(國碼)
	 */
	@Column(name="TEL_CTY")
	private String telCty;
	
	/**
	 * 電話(區碼)
	 */
	@Column(name="TEL_AREA")
	private String telArea;
	
	/**
	 * 電話
	 */
	@Column(name="TEL")
	private String tel;
	
	/**
	 * 停用日
	 */
	@Column(name="STOP_DATE")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	private LocalDateTime stopDate;
	
	/**
	 * User類別
	 */
	@Column(name="USER_TYPE")
	@Enumerated(EnumType.STRING)
	private UserType userType;
	

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@JSON(serialize=false)
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getWorkOffice() {
		return workOffice;
	}

	public void setWorkOffice(String workOffice) {
		this.workOffice = workOffice;
	}

	public String getTelCty() {
		return telCty;
	}

	public void setTelCty(String telCty) {
		this.telCty = telCty;
	}

	public String getTelArea() {
		return telArea;
	}

	public void setTelArea(String telArea) {
		this.telArea = telArea;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@JSON(serialize=false) 
	public LocalDateTime getStopDate() {
		return stopDate;
	}

	public void setStopDate(LocalDateTime stopDate) {
		this.stopDate = stopDate;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	
}
