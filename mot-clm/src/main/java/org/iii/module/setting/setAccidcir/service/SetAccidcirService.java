package org.iii.module.setting.setAccidcir.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.setting.setAccidcir.dao.SetAccidcirDao;
import org.iii.module.setting.setAccidcir.entity.SetAccidcir;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * SetAccidcirService
 * @author Gina Chen
 * @version 2014/3/26
 */
@Service
public class SetAccidcirService extends GenericService<SetAccidcir>{

	@Autowired
	private SetAccidcirDao dao;
	
	@Override
	protected GenericDao<SetAccidcir> getDao() {
		return dao;
	}
	
	@Override
	public DataSet<SetAccidcir> getByRestrictions(DataSet<SetAccidcir> ds) throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());
		SetAccidcir entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		if(StringUtils.isNotEmpty(entity.getCode())){
			restrictions.eq("code", entity.getCode());
		}
		if(StringUtils.isNotEmpty(entity.getLocalName())){
			restrictions.likeIgnoreCase("localName", entity.getLocalName(), MatchMode.ANYWHERE);
		}
		
		return dao.findByRestrictions(restrictions, ds);
	}

	

}
