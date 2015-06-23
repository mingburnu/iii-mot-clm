package org.iii.module.setting.setClmItemNature.service;

import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.setting.setClmItemNature.dao.SetClmItemNatureDao;
import org.iii.module.setting.setClmItemNature.entity.SetClmItemNature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
/**
 * 理賠項目對應賠款性質 Service
 * @author Mark Huang
 * @version 2014/3/28
 */
@Service
public class SetClmItemNatureService extends GenericService<SetClmItemNature>  {
	
	@Autowired
	private SetClmItemNatureDao dao;
	
	@Override
	public DataSet<SetClmItemNature> getByRestrictions(DataSet<SetClmItemNature> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());	
		
		SetClmItemNature entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		if(entity != null && entity.getItemId() != null) {
			restrictions.eq("itemId", entity.getItemId());
		}
		if(entity != null && entity.getNatureId() != null) {
			restrictions.eq("natureId", entity.getNatureId());
		}
			
		return dao.findByRestrictions(restrictions, ds);
	}
	@Override
	protected GenericDao<SetClmItemNature> getDao() {		
		return dao;
	}

}
