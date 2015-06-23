package org.iii.module.setting.setCarType.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.setting.setCarType.dao.SetCarTypeDao;
import org.iii.module.setting.setCarType.entity.SetCarType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * SetCarTypeService 車種管理Service
 * @author Ruo Hsu
 * @version 2014/3/26
 */
@Service
public class SetCarTypeService extends GenericService<SetCarType> {

	@Autowired
	private SetCarTypeDao dao;

	@Override
	public DataSet<SetCarType> getByRestrictions(DataSet<SetCarType> ds)
			throws Exception {

		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());
		
		SetCarType entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		
		if(StringUtils.isNotEmpty(entity.getTypeCode())){
			restrictions.eq("typeCode", entity.getTypeCode());
		}
		if(StringUtils.isNotEmpty(entity.getTypeName())){
			restrictions.likeIgnoreCase("typeName", entity.getTypeName(),MatchMode.ANYWHERE);
		}
		
		return dao.findByRestrictions(restrictions, ds);
	}

	@Override
	protected GenericDao<SetCarType> getDao() {
		return dao;
	}
	

}
