package org.iii.core.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * Hibernate Restrictions
 * @author David Hsu
 * @version 2014/3/11
 */
public class HibernateRestrictions implements IiiRestrictions {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1634807264759350521L;

	private List<Criterion> criterions;
	
	private List<Order> orders;
	
	public HibernateRestrictions() {
		criterions = new ArrayList<>();
		orders = new ArrayList<>();
	}
	
	@Override
	public List<Criterion> getCriterions() {
		return criterions;
	}
	
	@Override
	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public void eq(String propertyName, Object value) {
		criterions.add(Restrictions.eq(propertyName, value));
	}
	
	@Override
	public void ne(String propertyName, Object value) {
		criterions.add(Restrictions.ne(propertyName, value));
	}
	
	@Override
	public void like(String propertyName, Object value) {
		criterions.add(Restrictions.like(propertyName, value));
	}
	
	@Override
	public void like(String propertyName, String value, MatchMode matchMode) {
		criterions.add(Restrictions.like(propertyName, value, matchMode));
	}
	
	@Override
	public void likeIgnoreCase(String propertyName, Object value) {
		criterions.add(Restrictions.ilike(propertyName, value));
	}
	
	@Override
	public void likeIgnoreCase(String propertyName, String value, MatchMode matchMode) {
		criterions.add(Restrictions.ilike(propertyName, value, matchMode));
	}
	
	@Override
	public void gt(String propertyName, Object value) {
		criterions.add(Restrictions.gt(propertyName, value));
	}
	
	@Override
	public void lt(String propertyName, Object value) {
		criterions.add(Restrictions.lt(propertyName, value));
	}
	
	@Override
	public void ge(String propertyName, Object value) {
		criterions.add(Restrictions.ge(propertyName, value));
	}
	
	@Override
	public void le(String propertyName, Object value) {
		criterions.add(Restrictions.le(propertyName, value));
	}
	
	@Override
	public void between(String propertyName, Object leftValue, Object rightValue) {
		criterions.add(Restrictions.between(propertyName, leftValue, rightValue));
	}
	
	@Override
	public void in(String propertyName, Object[] values) {
		criterions.add(Restrictions.in(propertyName, values));
	}
	
	@Override
	public void in(String propertyName, Collection<?> values) {
		criterions.add(Restrictions.in(propertyName, values));
	}
	
	@Override
	public void isNull(String propertyName) {
		criterions.add(Restrictions.isNull(propertyName));
	}
	
	@Override
	public void isNotNull(String propertyName) {
		criterions.add(Restrictions.isNotNull(propertyName));
	}
	
	@Override
	public void eqProperty(String propertyName, String otherPropertyName) {
		criterions.add(Restrictions.eqProperty(propertyName, otherPropertyName));
	}
	
	@Override
	public void neProperty(String propertyName, String otherPropertyName) {
		criterions.add(Restrictions.neProperty(propertyName, otherPropertyName));
	}
	
	@Override
	public void gtProperty(String propertyName, String otherPropertyName) {
		criterions.add(Restrictions.gtProperty(propertyName, otherPropertyName));
	}
	
	@Override
	public void ltProperty(String propertyName, String otherPropertyName) {
		criterions.add(Restrictions.ltProperty(propertyName, otherPropertyName));
	}
	
	@Override
	public void geProperty(String propertyName, String otherPropertyName) {
		criterions.add(Restrictions.geProperty(propertyName, otherPropertyName));
	}
	
	@Override
	public void leProperty(String propertyName, String otherPropertyName) {
		criterions.add(Restrictions.leProperty(propertyName, otherPropertyName));
	}

	@Override
	public void addOrderAsc(String propertyName) {
		orders.add(Order.asc(propertyName));
	}

	@Override
	public void addOrderDesc(String propertyName) {
		orders.add(Order.desc(propertyName));
	}

}
