package org.iii.module.setting.setEvent.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.iii.core.entity.GenericEntity;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * 事件管理
 * @author Gina Chen
 * @version 2014/3/21
 */
@Entity
@Table(name="SET_EVENT")
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetEvent extends GenericEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2677387123173871194L;

	/** The code. */
	@Column(name="code")
	private String code;
	
	/** The event_date. */
	@Column(name="event_date")
	private LocalDateTime eventDate;
	
	/** The descript. */
	@Column(name="descript")
	private String descript;

	@Override
	public String toString() {
		return "SetEvent [code=" + code + ", eventDate=" + eventDate
				+ ", descript=" + descript + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	
}
