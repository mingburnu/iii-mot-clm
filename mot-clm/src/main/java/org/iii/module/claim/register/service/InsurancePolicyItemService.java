package org.iii.module.claim.register.service;

import org.apache.commons.lang3.StringUtils;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.claim.register.dao.InsurancePolicyItemDao;
import org.iii.module.claim.register.entity.InsurancePolicyItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 保單  Service
 * @author Mark Huang
 * @version 2014/5/17
 */
@Service
public class InsurancePolicyItemService extends GenericService<InsurancePolicyItem>{
	
	@Autowired
	private InsurancePolicyItemDao dao;
	
	@Override
	public DataSet<InsurancePolicyItem> getByRestrictions(DataSet<InsurancePolicyItem> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());	
		
		InsurancePolicyItem entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		if(StringUtils.isNotEmpty(entity.getPlate())) {
			restrictions.eq("plate", entity.getPlate());
		}
		
		if(StringUtils.isNotEmpty(entity.getCode())) {
			restrictions.eq("code", entity.getCode());
		}
		
		return dao.findByRestrictions(restrictions, ds);
	}
	

	@Override
	protected GenericDao<InsurancePolicyItem> getDao() {		
		return dao;
	}
	
}
