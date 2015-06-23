package org.iii.module.claim.register.Service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.iii.core.GenericTest;
import org.iii.core.enums.SystemStatus;
import org.iii.core.model.DataSet;
import org.iii.core.security.secUser.entity.SecUser;
import org.iii.module.claim.register.entity.Register;
import org.iii.module.claim.register.service.RegisterService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * RegisterServiceTest
 * @author Mark Huang
 * @version 2014/4/7
 */
public class RegisterServiceTest extends GenericTest {

	@Autowired
	private RegisterService service;
	
	@Autowired
	private DataSet<Register> ds;
	
	@Test
	public void testCRUD() throws Exception{
		//建立使用者
		SecUser user1 = new SecUser();
		user1.setUserCode("1234");
		user1.setUserName("Admin1");
		user1.setCreatedBy(1L);
		user1.setLastModifiedBy(1L);
		user1.setStatus(SystemStatus.Y);
		user1.setUserPassword("test");
		
		//建立物件
		Register register1 = new Register();
		register1.setCode("aaa");
		
		//save
		Register register2= service.save(register1, user1);
		assertEquals("aaa",register1.getCode());
		
		//update
		register2.setCode("bbb");
		register1 =service.update(register2, user1);
		assertEquals(register2.getCode(),register1.getCode());
		
		//delete
		service.deleteById(register1.getId());
		
		//find
		ds.setEntity(register1);
		ds = service.getByRestrictions(ds);
		assertNull(ds.getEntity());
	}

}
