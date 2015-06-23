package org.iii.module.claim.payments.service;

import org.apache.commons.lang3.StringUtils;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.claim.payments.dao.PaymentsDao;
import org.iii.module.claim.payments.entity.Payments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * PaymentService 賠付內容Service
 * @author Ruo Hsu
 * @version 2014/5/15
 */
@Service
public class PaymentsService extends GenericService<Payments> {

	@Autowired
	private PaymentsDao dao;

	@Override
	public DataSet<Payments> getByRestrictions(DataSet<Payments> ds)
			throws Exception {
		
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());
		Payments entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		
		if(StringUtils.isNotEmpty(entity.getCaseCode())){
			restrictions.eq("caseCode", entity.getCaseCode());
		}
		
		return dao.findByRestrictions(restrictions, ds);
	}

	@Override
	protected GenericDao<Payments> getDao() {
		return dao;
	}
	
}
