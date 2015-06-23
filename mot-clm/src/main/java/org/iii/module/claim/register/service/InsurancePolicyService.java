package org.iii.module.claim.register.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.claim.register.dao.InsurancePolicyDao;
import org.iii.module.claim.register.entity.InsurancePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 保單  Service
 * @author Mark Huang
 * @version 2014/5/17
 */
@Service
public class InsurancePolicyService extends GenericService<InsurancePolicy>{
	
	@Autowired
	private InsurancePolicyDao dao;
	
	@Override
	public DataSet<InsurancePolicy> getByRestrictions(DataSet<InsurancePolicy> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());	
		
		InsurancePolicy entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		if(StringUtils.isNotEmpty(entity.getPlate())) {
			restrictions.eq("plate", entity.getPlate());
		}
		
		return dao.findByRestrictions(restrictions, ds);
	}
	

	public List<InsurancePolicy> getByRestrictionsNoSamePlate(InsurancePolicy entity)
			throws Exception {
		Assert.notNull(entity);

		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		
		return dao.findByRestrictionsNoSamePlate(restrictions);
	}

	@Override
	protected GenericDao<InsurancePolicy> getDao() {		
		return dao;
	}
	
}
