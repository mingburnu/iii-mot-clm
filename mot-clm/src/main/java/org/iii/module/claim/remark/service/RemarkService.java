package org.iii.module.claim.remark.service;

import org.apache.commons.lang3.StringUtils;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.claim.remark.dao.RemarkDao;
import org.iii.module.claim.remark.entity.Remark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 報案處理記錄 Service
 * @author Gina Chen
 * @version 2014/4/8
 */
@Service
public class RemarkService extends GenericService<Remark>{

	@Autowired
	private RemarkDao dao;
	
	@Override
	public DataSet<Remark> getByRestrictions(DataSet<Remark> ds) throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());
		Remark entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		if(StringUtils.isNotEmpty(entity.getRegCode())){
			restrictions.eq("regCode", entity.getRegCode());
		}
		return dao.findByRestrictions(restrictions, ds);
		
	}

	@Override
	protected GenericDao<Remark> getDao() {
		return dao;
	}

}
