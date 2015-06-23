package org.iii.module.commons.web;

import org.iii.core.security.secUser.entity.SecUser;
import org.iii.core.web.GenericWebAction;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * CommonPageAction
 * @author David Hsu
 * @version 2014/3/15
 */
@Controller
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SuppressWarnings("serial")
public class CommonPageAction extends GenericWebAction<SecUser> {
	
}
