package org.iii.module.claim.exchangeRate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;
import org.iii.core.enums.Currency;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * ExchangeRate 外匯匯率Entity
 * @author Ruo Hsu
 * @version 2014/5/13
 */
@Entity
@Table(name="EXCHANGE_RATE")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ExchangeRate extends GenericEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1451577847946325562L;

	/** 幣別  */
	@Column(name="CURRENCY")
	@Enumerated(EnumType.STRING)
	private Currency currency;
	
	/** 兌換率  */
	@Column(name="RATE")
	private double rate;

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
}
