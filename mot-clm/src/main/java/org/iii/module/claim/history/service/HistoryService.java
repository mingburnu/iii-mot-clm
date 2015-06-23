package org.iii.module.claim.history.service;

import org.apache.commons.lang3.StringUtils;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.claim.history.dao.HistoryDao;
import org.iii.module.claim.history.entity.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * HistoryService 狀態記錄/備註Service
 * @author Ruo Hsu
 * @version 2014/5/15
 */
@Service
public class HistoryService extends GenericService<History> {

	public HistoryService() {
		super();
	}

	@Autowired
	private HistoryDao dao;
	
	@Override
	public DataSet<History> getByRestrictions(DataSet<History> ds) throws Exception {
		
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());
		
		History entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		
		if(StringUtils.isNotEmpty(entity.getCode())){
			restrictions.eq("code", entity.getCode());
		}
		
		return dao.findByRestrictions(restrictions, ds);
	}

	@Override
	protected GenericDao<History> getDao() {
		return dao;
	}

}
