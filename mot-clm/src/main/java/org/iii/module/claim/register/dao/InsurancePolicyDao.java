package org.iii.module.claim.register.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.iii.core.dao.IiiRestrictions;
import org.iii.module.claim.register.entity.InsurancePolicy;
import org.iii.module.commons.dao.CECJ03Dao;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

/**
 * 保單 dao
 * @author Mark Huang
 * @version 2014/5/17
 */
@Repository
public class InsurancePolicyDao extends CECJ03Dao<InsurancePolicy>{

	@SuppressWarnings("unchecked")
	public List<InsurancePolicy> findByRestrictionsNoSamePlate(IiiRestrictions restrictions) throws Exception {
		Assert.notNull(restrictions);
		Criteria dataCri = getSession().createCriteria(InsurancePolicy.class);
		
//		DataSet<T> results = new DataSet<>();
	
		//add restrictions
		List<Criterion> crions = (List<Criterion>) restrictions.getCriterions();
		for(Criterion crion : crions) {
			dataCri.add(crion);
		}
		
		//add orders
		List<Order> orders = (List<Order>) restrictions.getOrders();
		for(Order order : orders) {
			dataCri.addOrder(order);
		}
		List<InsurancePolicy> li=dataCri.list();
		List<InsurancePolicy> li2=new ArrayList<InsurancePolicy>();
		String temp;
		for(Iterator<InsurancePolicy> it = li.iterator();it.hasNext();){
			InsurancePolicy i = (InsurancePolicy)it.next();  
			temp=i.getPlate();
			int s=0;
			for (Iterator<InsurancePolicy> it2 = li.iterator();it2.hasNext();){   
				InsurancePolicy i2 = (InsurancePolicy)it2.next();  
			    if (i2.getPlate().equals(temp)){  
			    	s++;
			    	if(s>=2){
			    		li2.add(i2);
			    	}
			    }  
			} 			
		}
		li.removeAll(li2);

		return li;
	}

}
