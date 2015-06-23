package org.iii.module.setting.setClass.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.setting.setClass.dao.SetClassDao;
import org.iii.module.setting.setClass.entity.SetClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
/**
 * 產品別 Service
 * @author Mark Huang
 * @version 2014/3/26
 */
@Service
public class SetClassService extends GenericService<SetClass> {
	@Autowired
	private SetClassDao dao;
	@Override
	public DataSet<SetClass> getByRestrictions(DataSet<SetClass> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());	
		
		SetClass entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		if(StringUtils.isNotEmpty(entity.getCode())) {
			restrictions.eq("code", entity.getCode());
		}
		if(StringUtils.isNotEmpty(entity.getLocalName())) {
			restrictions.likeIgnoreCase("localName", entity.getLocalName(), MatchMode.ANYWHERE);
		}
		
		return dao.findByRestrictions(restrictions, ds);
	}
	@Override
	protected GenericDao<SetClass> getDao() {		
		return dao;
	}
}
