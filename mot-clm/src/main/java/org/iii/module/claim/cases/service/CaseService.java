package org.iii.module.claim.cases.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.claim.cases.dao.CaseDao;
import org.iii.module.claim.cases.entity.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 賠案一般資料 Service
 * 
 * @author Rodrigo Chun-Ming Chu
 * @version 2014/4/12
 */
@Service
public class CaseService extends GenericService<Case> {
	@Autowired
	private CaseDao dao;

	@Override
	public DataSet<Case> getByRestrictions(DataSet<Case> ds) throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());	
		
		Case entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		if(StringUtils.isNotEmpty(entity.getCode())) {
			restrictions.eq("code", entity.getCode());
		}
		if(StringUtils.isNotEmpty(entity.getInsuredId())) {
			restrictions.eq("insuredId", entity.getInsuredId());
		}
		if(StringUtils.isNotEmpty(entity.getPolicyCode())) {
			restrictions.eq("policyCode", entity.getPolicyCode());
		}
		if(StringUtils.isNotEmpty(entity.getPlate())) {
			restrictions.eq("plate", entity.getPlate());
		}
		if(StringUtils.isNotEmpty(entity.getDriver())) {
			restrictions.eq("driver", entity.getDriver());
		}
		if(StringUtils.isNotEmpty(entity.getCaseCode())) {
			restrictions.eq("caseCode", entity.getCaseCode());
		}
		if(StringUtils.isNotEmpty(entity.getAccidentPeopleId())) {
			restrictions.eq("accidentPeopleId", entity.getAccidentPeopleId());
		}
		if(StringUtils.isNotEmpty(entity.getAccidcirLocalName())){
			restrictions.likeIgnoreCase("accidcirLocalName", entity.getAccidcirLocalName(), MatchMode.ANYWHERE);;
		}
		
		if(StringUtils.isNotEmpty(entity.getProcessUserCode())) {
			restrictions.eq("processUserCode", entity.getProcessUserCode());
		}
		
		Map<String,Object> datas=ds.getDatas();
		if(datas.get("accidcirDateMin")!=null){
			restrictions.ge("accidcirDate", datas.get("accidcirDateMin"));
		}
		if(datas.get("accidcirDateMax")!=null){
			restrictions.le("accidcirDate", datas.get("accidcirDateMax"));
		}
		if(datas.get("acceptedDateMin")!=null){
			restrictions.ge("acceptedDate", datas.get("acceptedDateMin"));
		}
		if(datas.get("acceptedDateMax")!=null){
			restrictions.le("acceptedDate", datas.get("acceptedDateMax"));
		}
		
		if(StringUtils.isNotEmpty(entity.getMainAccidentName())){
			restrictions.eq("mainAccidentName", entity.getMainAccidentName());;
		}
		
		
		return dao.findByRestrictions(restrictions, ds);
	}
	
	public DataSet<Case> getCaseCodeByRestrictions(DataSet<Case> ds) throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());	
		
		Case entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		if(StringUtils.isNotEmpty(entity.getCaseCode())) {
			restrictions.likeIgnoreCase("caseCode", entity.getCaseCode(), MatchMode.START);
		}
	
		return dao.findByRestrictions(restrictions, ds);
	}

	@Override
	protected GenericDao<Case> getDao() {
		return dao;
	}

}
