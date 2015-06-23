package org.iii.module.claim.estimate.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericWebAction;
import org.iii.module.claim.estimate.entity.Estimate;
import org.iii.module.claim.estimate.service.EstimateService;
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
public class EstimateWebAction extends GenericWebAction<Estimate> {
	

	private Map<String,Object> dataMap;
	
	@Autowired
	private EstimateService estimateService;
	
	private String caseCode;
	
	private long id;

	
	public String findEstimateCheckAjax() throws Exception {
		try {
			if(caseCode!=null){
				Estimate entity=getEntity();	
				entity.setEstimatedCheck(true);
				entity.setCaseCode(caseCode);
				DataSet<Estimate> ds=initDataSet();
				ds.setEntity(entity);				
				ds = estimateService.getByRestrictions(ds);
				dataMap=new HashMap<String,Object>();
				if(ds.getResults().size()>0){			  
					dataMap.put("data", "true");
				}else{
					dataMap.put("data", "false");
				}
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
