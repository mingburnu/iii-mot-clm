package org.iii.module.claim.settle.service;

import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.claim.settle.dao.SettleDetailDao;
import org.iii.module.claim.settle.entity.SettleDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 理算簽結 理賠金額 detail Entity
 * @author Mark Huang
 * @version 2014/6/2
 */
@Service
public class SettleDetailService extends GenericService<SettleDetail>{

	@Autowired
	private SettleDetailDao dao;
	
	@Override
	public DataSet<SettleDetail> getByRestrictions(DataSet<SettleDetail> ds) throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());
		SettleDetail entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		
		if(entity.getEstimateDetailId()!=0L){
			restrictions.eq("estimateDetailId", entity.getEstimateDetailId());
		}

		return dao.findByRestrictions(restrictions, ds);
	}

	@Override
	protected GenericDao<SettleDetail> getDao() {
		return dao;
	}

}
