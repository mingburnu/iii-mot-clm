package org.iii.module.claim.settle.service;

import org.apache.commons.lang3.StringUtils;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.claim.settle.dao.SettleDao;
import org.iii.module.claim.settle.entity.Settle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 理算簽結 Service
 * @author Gina Chen
 * @version 2014/5/13
 */
@Service
public class SettleService extends GenericService<Settle>{

	@Autowired
	private SettleDao dao;
	
	@Override
	public DataSet<Settle> getByRestrictions(DataSet<Settle> ds) throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());
		Settle entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		if(StringUtils.isNotEmpty(entity.getCaseCode())){
			restrictions.eq("caseCode", entity.getCaseCode());
		}
		return dao.findByRestrictions(restrictions, ds);
	}

	@Override
	protected GenericDao<Settle> getDao() {
		return dao;
	}

}
