package org.iii.module.claim.estimateDetail.service;

import org.apache.commons.lang3.StringUtils;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.claim.estimateDetail.dao.EstimateDetailDao;
import org.iii.module.claim.estimateDetail.entity.EstimateDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 受害人預估賠款 Service
 * @author 黃小貓
 * @version 2014/5/14
 */
@Service
public class EstimateDetailService extends GenericService<EstimateDetail>{

	@Autowired
	private EstimateDetailDao dao;
	
	@Override
	public DataSet<EstimateDetail> getByRestrictions(DataSet<EstimateDetail> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());	
		
		EstimateDetail entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		if(StringUtils.isNotEmpty(entity.getCaseCode())) {
			restrictions.eq("caseCode", entity.getCaseCode());
		}
		restrictions.addOrderAsc("insuredType");
		
		return dao.findByRestrictions(restrictions, ds);
	}

	@Override
	protected GenericDao<EstimateDetail> getDao() {
		return dao;
	}

}
