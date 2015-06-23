package org.iii.module.claim.register.dao;



import static org.junit.Assert.*;

import org.iii.core.GenericTest;
import org.iii.module.claim.register.entity.Register;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * RegisterDaoTest
 * @author Mark Huang
 * @version 2014/4/7
 */
public class RegisterDaoTest extends GenericTest {

	@Autowired
	private RegisterDao dao;
	
	@Test
	public void testCRUD() throws Exception {
		
		//save
		Register register1=new Register();
		register1.setCode("aaa");
		
		Register register2=dao.save(register1);
		assertEquals(register1.toString(),register2.toString());
		
		//update 
		register2.setCode("bbb");
		dao.update(register2);		
		assertEquals("bbb",register2.getCode());
		
		//findbyid
		register1=dao.findById(register2.getId());
		assertEquals(register2.getCode(),register1.getCode());
		
		//delete
		dao.delete(register1);
		register1=dao.findById(register1.getId());
		assertNull(register1);
		
	}

}
