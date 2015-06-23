package org.iii.core.dao;

import org.apache.log4j.Logger;
import org.iii.core.entity.GenericEntity;

/**
 * GenericDao
 * @author David Hsu
 * @version 2014/3/11
 */
public abstract class GenericDao<T extends GenericEntity> implements Dao<T> {
	
	protected final transient Logger log = Logger.getLogger(getClass());

}
