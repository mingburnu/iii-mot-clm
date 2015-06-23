package org.iii.module.setting.setEvent.service;

import org.apache.commons.lang3.StringUtils;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.setting.setEvent.dao.SetEventDao;
import org.iii.module.setting.setEvent.entity.SetEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * SetEventService
 * @author Gina Chen
 * @version 2014/3/28
 */
@Service
public class SetEventService extends GenericService<SetEvent>{

	@Autowired
	private SetEventDao dao;
	
	@Override
	protected GenericDao<SetEvent> getDao() {
		return dao;
	}
	
	@Override
	public DataSet<SetEvent> getByRestrictions(DataSet<SetEvent> ds) throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());
		SetEvent entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		if(StringUtils.isNotEmpty(entity.getCode())){
			restrictions.eq("code", entity.getCode());
		}
		if(StringUtils.isNotEmpty(entity.getDescript())){
			restrictions.eq("descript", entity.getDescript());
		}
		return dao.findByRestrictions(restrictions, ds);
	}


}
