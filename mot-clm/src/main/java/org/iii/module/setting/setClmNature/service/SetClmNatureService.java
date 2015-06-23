package org.iii.module.setting.setClmNature.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.setting.setClmNature.dao.SetClmNatureDao;
import org.iii.module.setting.setClmNature.entity.SetClmNature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
/**
 * 賠款性質 Service
 * @author Mark Huang
 * @version 2014/3/29
 */
@Service
public class SetClmNatureService extends GenericService<SetClmNature>  {
	
	@Autowired
	private SetClmNatureDao dao;
	
	@Override
	public DataSet<SetClmNature> getByRestrictions(DataSet<SetClmNature> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());	
		
		SetClmNature entity = ds.getEntity();
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
	protected GenericDao<SetClmNature> getDao() {		
		return dao;
	}

}
