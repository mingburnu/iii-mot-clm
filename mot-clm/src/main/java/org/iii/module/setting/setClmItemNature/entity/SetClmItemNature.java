package org.iii.module.setting.setClmItemNature.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.iii.core.entity.GenericEntity;
import org.iii.module.setting.setClmItem.entity.SetClmItem;
import org.iii.module.setting.setClmNature.entity.SetClmNature;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

/**
 * 理賠項目對應賠款性質
 * @author Mark Huang
 * @version 2014/3/22
 */
@Entity
@Table(name="SET_CLM_ITEM_NATURE")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetClmItemNature extends GenericEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3453133697264121646L;
	
	/** 理賠項目  */
	@Column(name="ITEM_ID")
	private Long itemId;
	
	/** 理賠項目  data*/
	@Transient
	private SetClmItem clmItem;
	
	/** 賠款性質 */
	@Column(name="NATURE_ID")	
	private Long natureId;
	
	/** 賠款性質 data*/
	@Transient
	private SetClmNature clmNature;	
	
	@Override
	public String toString() {
		return "SetClmItemNature [itemId=" + itemId + ", natureId=" + natureId
				+ "]";
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Long getNatureId() {
		return natureId;
	}
	public void setNatureId(Long natureId) {
		this.natureId = natureId;
	}
	public SetClmItem getClmItem() {
		return clmItem;
	}
	public void setClmItem(SetClmItem clmItem) {
		this.clmItem = clmItem;
	}
	public SetClmNature getClmNature() {
		return clmNature;
	}
	public void setClmNature(SetClmNature clmNature) {
		this.clmNature = clmNature;
	}	
	
}
