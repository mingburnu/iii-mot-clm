package org.iii.module.claim.contact.service;

import org.apache.commons.lang3.StringUtils;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.claim.contact.dao.ContactDao;
import org.iii.module.claim.contact.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;


/**
 * 報案通訊錄 Service
 * @author 黃小貓
 * @version 2014/4/9
 */
@Service
public class ContactService extends GenericService<Contact>{

	@Autowired
	private ContactDao dao;
	
	@Override
	public DataSet<Contact> getByRestrictions(DataSet<Contact> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());	
		
		Contact entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		if(StringUtils.isNotEmpty(entity.getRegCode())) {
			restrictions.eq("regCode",entity.getRegCode());
		}
		if(StringUtils.isEmpty(entity.getCaseCode())) {
			restrictions.eq("caseCode","");
		}
		return dao.findByRestrictions(restrictions, ds);
	}
	
	public DataSet<Contact> getByRestrictionsCaseCode(DataSet<Contact> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());	
		
		Contact entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		if(StringUtils.isNotEmpty(entity.getRegCode())) {
			restrictions.eq("regCode",entity.getRegCode());
		}
		return dao.findByRestrictions(restrictions, ds);
	}

	@Override
	protected GenericDao<Contact> getDao() {
		return dao;
	}
	
	
}
