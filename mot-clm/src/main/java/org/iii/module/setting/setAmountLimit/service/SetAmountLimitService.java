package org.iii.module.setting.setAmountLimit.service;

import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.setting.setAmountLimit.dao.SetAmountLimitDao;
import org.iii.module.setting.setAmountLimit.entity.SetAmountLimit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 使用者
 * 
 * @author Rodrigo Chun-Ming Chu
 * @version 2014/3/26
 */
@Service
public class SetAmountLimitService extends GenericService<SetAmountLimit> {
	@Autowired
	private SetAmountLimitDao dao;

	@Override
	protected GenericDao<SetAmountLimit> getDao() {
		return dao;
	}

	@Override
	public DataSet<SetAmountLimit> getByRestrictions(DataSet<SetAmountLimit> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());

		SetAmountLimit entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		
		if (entity.getClassId() != 0 && entity.getOfficeId() != 0) {
			restrictions.eq("classId", entity.getClassId());
			restrictions.eq("officeId", entity.getOfficeId());
		} else if (entity.getClassId() != 0 && entity.getOfficeId() == 0) {
			restrictions.eq("classId", entity.getClassId());
		} else if (entity.getOfficeId() != 0 && entity.getClassId() == 0) {
			restrictions.eq("officeId", entity.getOfficeId());
		} else if (entity.getClassId() == 0 && entity.getOfficeId() == 0) {
		}
		return dao.findByRestrictions(restrictions, ds);
	}
}
