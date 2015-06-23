package org.iii.module.claim.register.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.claim.register.dao.RegisterDao;
import org.iii.module.claim.register.entity.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 報案 Service
 * @author Mark Huang
 * @version 2014/4/7
 */
@Service
public class RegisterService extends GenericService<Register>{
	
	@Autowired
	private RegisterDao dao;
	
	@Override
	public DataSet<Register> getByRestrictions(DataSet<Register> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());	
		
		Register entity = ds.getEntity();
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
	
	public DataSet<Register> getCodeByRestrictions(DataSet<Register> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());	
		
		Register entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		if(StringUtils.isNotEmpty(entity.getCode())) {
			restrictions.likeIgnoreCase("code", entity.getCode() , MatchMode.START);		
		}
		
		return dao.findByRestrictions(restrictions, ds);
	}

	@Override
	protected GenericDao<Register> getDao() {		
		return dao;
	}
	
}
