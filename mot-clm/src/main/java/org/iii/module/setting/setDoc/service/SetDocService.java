package org.iii.module.setting.setDoc.service;

import org.apache.commons.lang3.StringUtils;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.setting.setDoc.dao.SetDocDao;
import org.iii.module.setting.setDoc.entity.SetDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * SetDocService
 * @author Gina Chen
 * @version 2014/3/28
 */
@Service
public class SetDocService extends GenericService<SetDoc>{

	@Autowired
	private SetDocDao dao;
	
	@Override
	protected GenericDao<SetDoc> getDao() {
		return dao;
	}

	@Override
	public DataSet<SetDoc> getByRestrictions(DataSet<SetDoc> ds) throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());
		SetDoc entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		if(StringUtils.isNotEmpty(entity.getCode())){
			restrictions.eq("code", entity.getCode());
		}
		if(StringUtils.isNotEmpty(entity.getLocalName())){
			restrictions.eq("localName", entity.getLocalName());
		}
		return dao.findByRestrictions(restrictions, ds);
		
	}

}
