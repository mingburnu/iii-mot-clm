package org.iii.module.setting.setAuthority.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.MatchMode;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.setting.setAuthority.dao.SetAuthorityDao;
import org.iii.module.setting.setAuthority.entity.SetAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * SetAuthorityService 分層授權管理Service
 * @author Ruo Hsu
 * @version 2014/4/3
 */
@Service
public class SetAuthorityService extends GenericService<SetAuthority>{

	@Autowired
	private SetAuthorityDao dao;
	
	@Override
	protected GenericDao<SetAuthority> getDao() {
		return dao;
	}
	
	@Override
	public DataSet<SetAuthority> getByRestrictions(DataSet<SetAuthority> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());
		
		SetAuthority entity = ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		
		if(StringUtils.isNotEmpty(entity.getRole())){
			restrictions.eq("role", entity.getRole());
		}
		if(StringUtils.isNotEmpty(entity.getAuditType())){
			restrictions.likeIgnoreCase("auditType", entity.getAuditType(),MatchMode.ANYWHERE);
		}
		
		return dao.findByRestrictions(restrictions, ds);
	}
	
}
