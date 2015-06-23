package org.iii.module.claim.estimateDetail.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericWebAction;
import org.iii.module.claim.estimateDetail.entity.EstimateDetail;
import org.iii.module.claim.estimateDetail.service.EstimateDetailService;
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
public class EstimateDetailWebAction extends GenericWebAction<EstimateDetail> {
	
	// User
	@Autowired
	private EstimateDetail estimateDetail;
	@Autowired
	private EstimateDetailService estimateDetailService;
	@Autowired
	private DataSet<EstimateDetail> dsEstimateDetail;
	
	private List<EstimateDetail> estimateDetailList;
	private Map<String,Object> dataMap;
	
	
	private String caseCode;
	
	private long id;

	
	public String findEstimateDetailAjax() throws Exception {
		try {
			if(caseCode!=null){
				estimateDetail.setCaseCode(caseCode);
				dsEstimateDetail.setEntity(estimateDetail);
				dsEstimateDetail = estimateDetailService.getByRestrictions(dsEstimateDetail);
				estimateDetailList=dsEstimateDetail.getResults();
				dataMap=new HashMap<String,Object>();  
				dataMap.put("data", estimateDetailList);
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return SUCCESS;
	}
	
	public String deleteAjax() throws Exception {
		try {
				estimateDetailService.deleteById(id);
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
