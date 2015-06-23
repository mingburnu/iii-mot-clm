package org.iii.module.claim.estimate.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.claim.estimate.dao.EstimateDao;
import org.iii.module.claim.estimate.entity.Estimate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * EstimateDao 理算賠款Service
 * @author Rodrigo Chun-Ming Chu
 * @version 2014/5/9
 */
@Service
public class EstimateService extends GenericService<Estimate>{
	@Autowired
	private EstimateDao dao;
	@Override
	public DataSet<Estimate> getByRestrictions(DataSet<Estimate> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());
		Estimate entity=ds.getEntity();
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		if(StringUtils.isNotEmpty(entity.getCaseCode())){
			restrictions.eq("caseCode", entity.getCaseCode());
		}
		if(entity.getEstimatedCheck()){
			restrictions.eq("estimatedCheck", true);
		}
		return dao.findByRestrictions(restrictions, ds);
	}

	@Override
	protected GenericDao<Estimate> getDao() {
		return dao;
	}
	
	public DataSet<Estimate> getCaseCodeByRestrictions(DataSet<Estimate> ds) throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());	

		HttpServletRequest request = ServletActionContext.getRequest();
		String caseCode = request.getParameter("entity.caseCode");
		System.out.println("CASECODE==" + caseCode);
		
		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		restrictions.eq("caseCode", caseCode);
	
		return dao.findByRestrictions(restrictions, ds);
	}
}
