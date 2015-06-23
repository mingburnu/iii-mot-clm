package org.iii.core.service;

import org.iii.core.entity.Entity;
import org.iii.core.model.DataSet;
import org.iii.core.security.secUser.entity.SecUser;

/**
 * Service
 * @author David Hsu
 * @version 2014/3/11
 */
public interface Service<T extends Entity> {

	public T save(T entity, SecUser user)  throws Exception;
	
	public T getById(Long id) throws Exception;
	
	public DataSet<T> getByRestrictions(DataSet<T> ds) throws Exception;
	
	public T update(T entity, SecUser user) throws Exception;
	
	public T update(T entity, SecUser user, String... ignoreProperties) throws Exception;
	
	public void deleteById(Long id) throws Exception;
	
}
