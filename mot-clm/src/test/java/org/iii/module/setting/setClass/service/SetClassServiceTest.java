package org.iii.module.setting.setClass.service;

import static org.junit.Assert.assertEquals;

import org.iii.core.GenericTest;
import org.iii.core.enums.SystemStatus;
import org.iii.core.model.DataSet;
import org.iii.core.security.secUser.entity.SecUser;
import org.iii.module.setting.setClass.entity.SetClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * SetClassServiceTest
 * @author Mark Huang
 * @version 2014/3/26
 */
public class SetClassServiceTest extends GenericTest {
	
	@Autowired
	private SetClassService service;
	
	@Autowired
	private DataSet<SetClass> ds;
	
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
		SetClass _class = new SetClass();
		_class.setCode("1234");
		_class.setLocalName("123");
		_class.setId((long)12345789);
		
		//save
		_class=service.save(_class, user1);
		assertEquals("1234", _class.getCode());
		
		//update
		_class.setCode("aabb");
		_class=service.update(_class, user1);
		assertEquals("aabb", _class.getCode());
		
		//find
		ds.setEntity(_class);
		ds=service.getByRestrictions(ds);
		assertEquals(_class.getCode(), ds.getEntity().getCode());
		
		//delete
		System.out.println(ds.getEntity().getId());
		service.deleteById(ds.getEntity().getId());
		ds.setEntity(_class);
		ds=service.getByRestrictions(ds);
		assertEquals(0, ds.getResults().size());
		
		
		
	}

}
