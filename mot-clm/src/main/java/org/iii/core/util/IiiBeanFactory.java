package org.iii.core.util;

import java.util.HashSet;
import java.util.Set;

import org.iii.core.dao.HibernateQueryLanguage;
import org.iii.core.dao.HibernateRestrictions;
import org.iii.core.dao.IiiQueryLanguage;
import org.iii.core.dao.IiiRestrictions;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * IiiBeanFactory
 * @author David Hsu
 * @version 2014/3/11
 */
public class IiiBeanFactory {

	/**
	 * Get the IiiRestrictions
	 * @return IiiRestrictions
	 */
	public static IiiRestrictions getIiiRestrictions() {
		return new HibernateRestrictions();
	}
	
	/**
	 * Get the IiiQueryLanguage
	 * @return
	 */
	public static IiiQueryLanguage getIiiQueryLanguage() {
		return new HibernateQueryLanguage();
	}
	
	/**
	 * get all null property's name
	 * @param source
	 * @return
	 */
	public static String[] getNullPropertyNames(Object source) {
	    final BeanWrapper src = new BeanWrapperImpl(source);
	    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

	    Set<String> emptyNames = new HashSet<String>();
	    for(java.beans.PropertyDescriptor pd : pds) {
	        Object srcValue = src.getPropertyValue(pd.getName());
	        if (srcValue == null) emptyNames.add(pd.getName());
	    }
	    String[] result = new String[emptyNames.size()];
	    return emptyNames.toArray(result);
	}
	
}
