package org.iii.module.setting.dao;

import static org.junit.Assert.*;

import org.iii.core.GenericTest;
import org.iii.module.setting.setClmNature.dao.SetClmNatureDao;
import org.iii.module.setting.setClmNature.entity.SetClmNature;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * SetClmNatureDaoTest
 * @author Mark Huang
 * @version 2014/3/22
 */
public class SetClmNatureDaoTest extends GenericTest {
	@Autowired
	private SetClmNatureDao dao;
	@Test	
	public void testCRUD() throws Exception {
		
		SetClmNature clmNature1=new SetClmNature();
		clmNature1.setCode("12345");
		clmNature1.setLocalName("ssss");
		clmNature1.setEngName("ss");	
		
		dao.save(clmNature1);			
		SetClmNature clmNature2=dao.findById(clmNature1.getId());
		assertEquals(clmNature1.getCode(), clmNature2.getCode());
		assertEquals(clmNature1.getLocalName(), clmNature2.getLocalName());
		
		clmNature2.setCode("7788");
		dao.update(clmNature2);
		SetClmNature clmNature3=dao.findById(clmNature2.getId());
		assertEquals("7788", clmNature3.getCode());
		
		dao.delete(clmNature2);
		clmNature3=dao.findById(clmNature2.getId());
		assertNull(clmNature3);
	}

}
