package org.iii.module.setting.setAmountLimit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.iii.core.entity.GenericEntity;
import org.iii.module.setting.setClass.entity.SetClass;
import org.iii.module.setting.setOffice.entity.SetOffice;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * 使用者
 * 
 * @author Rodrigo Chun-Ming Chu
 * @version 2014/3/21
 */
@Entity
@Table(name = "SET_AMOUNT_LIMIT")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetAmountLimit extends GenericEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5860146482911095125L;
	/**
	 * 產品別
	 */
	@Column(name = "CLASS_ID")
	private long classId;

	@Transient
	private SetClass setClass;

	/**
	 * 單位別
	 */
	@Column(name = "OFFICE_ID")
	private long officeId;
	/**
	 * 轉入單位
	 */
	@Column(name = "FROM_ID")
	private long fromId;

	@Transient
	private SetOffice setOffice;

	@Transient
	private SetOffice setOfficeFrom;
	/**
	 * 金額
	 */
	@Column(name = "AMOUNT")
	private int amount;
	/**
	 * 備註說明
	 */
	@Column(name = "DESCRIPT")
	private String descript;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SetAmountLimit [classId=" + classId + ", setClass=" + setClass
				+ ", officeId=" + officeId + ", fromId=" + fromId
				+ ", setOffice=" + setOffice + ", setOfficeFrom="
				+ setOfficeFrom + ", amount=" + amount + ", descript="
				+ descript + "]";
	}

	/**
	 * @return the setClass
	 */
	public SetClass getSetClass() {
		return setClass;
	}

	/**
	 * @param setClass
	 *            the setClass to set
	 */
	public void setSetClass(SetClass setClass) {
		this.setClass = setClass;
	}

	/**
	 * @return the setOffice
	 */
	public SetOffice getSetOffice() {
		return setOffice;
	}

	/**
	 * @param setOffice
	 *            the setOffice to set
	 */
	public void setSetOffice(SetOffice setOffice) {
		this.setOffice = setOffice;
	}

	/**
	 * @return the setOfficeFrom
	 */
	public SetOffice getSetOfficeFrom() {
		return setOfficeFrom;
	}

	/**
	 * @param setOfficeFrom
	 *            the setOfficeFrom to set
	 */
	public void setSetOfficeFrom(SetOffice setOfficeFrom) {
		this.setOfficeFrom = setOfficeFrom;
	}

	/**
	 * @return the classId
	 */
	public long getClassId() {
		return classId;
	}

	/**
	 * @param classId
	 *            the classId to set
	 */
	public void setClassId(long classId) {
		this.classId = classId;
	}

	/**
	 * @return the officeId
	 */
	public long getOfficeId() {
		return officeId;
	}

	/**
	 * @param officeId
	 *            the officeId to set
	 */
	public void setOfficeId(long officeId) {
		this.officeId = officeId;
	}

	/**
	 * @return the fromId
	 */
	public long getFromId() {
		return fromId;
	}

	/**
	 * @param fromId
	 *            the fromId to set
	 */
	public void setFromId(long fromId) {
		this.fromId = fromId;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @return the descript
	 */
	public String getDescript() {
		return descript;
	}

	/**
	 * @param descript
	 *            the descript to set
	 */
	public void setDescript(String descript) {
		this.descript = descript;
	}
}
