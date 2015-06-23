package org.iii.core.web;

import org.iii.core.entity.Entity;

/**
 * Action
 * @author David Hsu
 * @version 2014/3/14
 */
public interface Action<T extends Entity> {

	public final static String INDEX = "index";

	public final static String SECTION = "section";
	
	public final static String EDIT = "edit";
	
	public final static String DELETE = "delete";
	
	public final static String LIST = "list";

}
