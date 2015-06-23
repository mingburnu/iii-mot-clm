package org.iii.module.claim.contact.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericWebAction;
import org.iii.module.claim.cases.entity.Case;
import org.iii.module.claim.cases.service.CaseService;
import org.iii.module.claim.contact.entity.Contact;
import org.iii.module.claim.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 報案 webAction
 * 
 * @author Mark Huang
 * @version 2014/5/1
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ContactWebAction extends GenericWebAction<Contact> {
	
	@Autowired
	private ContactService contactService;
	
	private List<Contact> contactlList;
	private Map<String,Object> dataMap;
	
	@Autowired
	private Case cases;
	
	@Autowired
	private CaseService casesService;
	
	@Autowired
	private DataSet<Case> dsCases;
	
	
	private String caseCode;
	
	private long id;

	
	public String findContactAjax() throws Exception {
		try {
			if(caseCode!=null){
				cases.setCaseCode(caseCode);
				dsCases.setEntity(cases);
				dsCases=casesService.getByRestrictions(dsCases);
				if(dsCases.getResults().size()!=0){
					cases=dsCases.getResults().get(0);
				}
				Contact entity=getEntity();
				if(cases.getCode()!=null){
					entity.setRegCode(cases.getCode());
				}
				entity.setCaseCode(caseCode);
				DataSet<Contact> ds=initDataSet();
				ds.setEntity(entity);
				ds = contactService.getByRestrictionsCaseCode(ds);
				contactlList=ds.getResults();
				dataMap=new HashMap<String,Object>();  
				dataMap.put("data", contactlList);
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return SUCCESS;
	}
	

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
	
}
