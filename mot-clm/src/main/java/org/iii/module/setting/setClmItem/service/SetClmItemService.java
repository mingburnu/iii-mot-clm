package org.iii.module.setting.setClmItem.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.setting.setClmItem.dao.SetClmItemDao;
import org.iii.module.setting.setClmItem.entity.SetClmItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 理賠項目管理 Service
 * @author 黃小貓
 * @version 2014/3/26
 */
@Service
public class SetClmItemService extends GenericService<SetClmItem>{

	@Autowired
	private SetClmItemDao dao;
	
	@Override
	public DataSet<SetClmItem> getByRestrictions(DataSet<SetClmItem> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());
		
		SetClmItem entity = ds.getEntity();
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
	protected GenericDao<SetClmItem> getDao() {
		
		return dao;
	}

}
