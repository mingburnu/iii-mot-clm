package org.iii.module.setting.dao;

import static org.junit.Assert.*;

import org.iii.core.GenericTest;
import org.iii.module.setting.setClass.dao.SetClassDao;
import org.iii.module.setting.setClass.entity.SetClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * SetClassDaoTest
 * @author Mark Huang
 * @version 2014/3/22
 */
public class SetClassDaoTest extends GenericTest  {

	@Autowired
	private SetClassDao dao;
	
	@Test
	public void testCRUD() throws Exception {
		
		SetClass class1 = new SetClass();		
		class1.setCode("xxx");
		class1.setLocalName("yyy");	
		
		dao.save(class1);
		System.out.println(class1.toString());
		SetClass class2=dao.findById(class1.getId());
		assertEquals(class2.toString(),class1.toString());
		
		class2.setCode("ggg");
		class2.setLocalName("HHH");
		dao.update(class2);
		SetClass class3=dao.findById(class2.getId());
		assertEquals(class3.toString(),class2.toString());
		
		dao.delete(class2);
		class3=dao.findById(class2.getId());
		assertNull(class3);
	}

}
