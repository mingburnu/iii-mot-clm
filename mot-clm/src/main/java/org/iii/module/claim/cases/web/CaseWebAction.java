package org.iii.module.claim.cases.web;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.web.GenericWebAction;
import org.iii.module.claim.cases.entity.Case;
import org.iii.module.claim.cases.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 報案 webAction
 * 
 * @author Rodrigo Chun-Ming Chu
 * @version 2014/5/10
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CaseWebAction extends GenericWebAction<Case> {

	private String plate;
	
	
	@Autowired
	private CaseService caseService;
	
	public String findUserByUserCodeAjax() throws Exception {
		try {

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return NONE;
	}

	// case轉檔 to estimate
	public String convert() throws Exception {
		try {
			if (getEntity().getId() != null) {
				Case cases = caseService.getById(getEntity().getId());
				setEntity(cases);
			} else {
				addActionMessage("Error");
			}
			
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return "convert";
	}
	
	// case保單資料
	public String policy() throws Exception {
		try {
			if (getEntity().getPlate() != null) {
				setPlate(getEntity().getPlate());
			} 
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return "policy";
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}
	
	
	
}
