package org.iii.module.setting.setOffice.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.setting.setOffice.dao.SetOfficeDao;
import org.iii.module.setting.setOffice.entity.SetOffice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 單位管理 Service
 * @author 黃小貓
 * @version 2014/3/26
 */
@Service
public class SetOfficeService extends GenericService<SetOffice>{
	
	@Autowired
	private SetOfficeDao dao;
	
	@Override
	protected GenericDao<SetOffice> getDao() {
		return dao;
	}

	@Override
	public DataSet<SetOffice> getByRestrictions(DataSet<SetOffice> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());
		
		SetOffice entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		if(StringUtils.isNotEmpty(entity.getCode())) {
			restrictions.eq("code", entity.getCode());
		}
		if(StringUtils.isNotEmpty(entity.getLocalName())) {
			restrictions.likeIgnoreCase("localName", entity.getLocalName(), MatchMode.ANYWHERE);
		}
		
		return dao.findByRestrictions(restrictions, ds);
	}

	

}
