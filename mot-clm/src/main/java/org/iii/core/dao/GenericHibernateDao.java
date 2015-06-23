package org.iii.core.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.iii.core.entity.GenericEntity;
import org.iii.core.model.DataSet;
import org.iii.core.model.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * GenericHibernateDao
 * @author David Hsu
 * @version 2014/3/7
 */
public abstract class GenericHibernateDao<T extends GenericEntity> extends GenericDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public GenericHibernateDao() {
		this.entityClass = null;
		Class<?> c = getClass();
		Type t = c.getGenericSuperclass();
		if(t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.entityClass = (Class<T>) p[0];
		}
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T findById(Long id) throws Exception {
		Assert.notNull(id);
		return (T) getSession().byId(entityClass).load(id);
	}

	@Override
	public List<T> findByRestrictions(IiiRestrictions restrictions) throws Exception {
		DataSet<T> results = findByRestrictions(restrictions, null);
		return results.getResults();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public DataSet<T> findByRestrictions(IiiRestrictions restrictions, DataSet<T> ds) throws Exception {
		Assert.notNull(restrictions);
		Criteria dataCri = getSession().createCriteria(entityClass);
		Criteria countCri = getSession().createCriteria(entityClass);
//		DataSet<T> results = new DataSet<>();
		
		//add restrictions
		List<Criterion> crions = (List<Criterion>) restrictions.getCriterions();
		for(Criterion crion : crions) {
			countCri.add(crion);
			dataCri.add(crion);
		}
		
		//add orders
		List<Order> orders = (List<Order>) restrictions.getOrders();
		for(Order order : orders) {
			dataCri.addOrder(order);
		}
		
		if(ds != null && ds.getPager() != null) { //分頁
			Pager pager = ds.getPager();
			
			//count total records
			countCri.setProjection(Projections.rowCount());
			Long totalRecord = (Long) countCri.list().get(0);
			log.debug("totalRecord:" + totalRecord);
			pager.setTotalRecord(totalRecord);
			
			dataCri.setFirstResult(pager.getOffset());
			dataCri.setMaxResults(pager.getRecordPerPage());
		} else {
			ds = new DataSet<>();
		}		
		
		ds.setResults(dataCri.list());
		
		return ds;
	}

	@Override
	public T save(T entity) throws Exception {
		Assert.notNull(entity);
		Serializable id = getSession().save(entity);
		try {
			BeanUtils.setProperty(entity, "id", id);
		} catch (IllegalAccessException | InvocationTargetException e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return entity;
	}

	@Override
	public void update(T entity) throws Exception {
		Assert.notNull(entity);
		getSession().update(entity);
	}

	@Override
	public void deleteById(Long id) throws Exception {
		Assert.notNull(id);
		T t = findById(id);
		getSession().delete(t);
	}

	@Override
	public void delete(T entity) throws Exception {
		Assert.notNull(entity);
		getSession().delete(entity);
	}
	
	@Override
	public List<?> findByQL(IiiQueryLanguage iQL) {
		Assert.notNull(iQL);
		Assert.notNull(iQL.getSql());
		
		Query query = getSession().createQuery(iQL.getSql());
		for(Entry<String, Object> keyValue : iQL.getParameters().entrySet()) {
			query.setParameter(keyValue.getKey(), keyValue.getValue());
		}
		
		return query.list();
	}
	
}
