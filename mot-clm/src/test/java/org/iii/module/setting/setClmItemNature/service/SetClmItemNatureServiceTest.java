package org.iii.module.setting.setClmItemNature.service;

import static org.junit.Assert.*;

import org.iii.core.GenericTest;
import org.iii.core.enums.SystemStatus;
import org.iii.core.model.DataSet;
import org.iii.core.security.secUser.entity.SecUser;
import org.iii.module.setting.setClmItemNature.entity.SetClmItemNature;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * SetClassServiceTest
 * @author Mark Huang
 * @version 2014/3/28
 */
public class SetClmItemNatureServiceTest extends GenericTest {

	@Autowired
	private SetClmItemNatureService service;
	
	@Autowired
	private DataSet<SetClmItemNature> ds;
	
	@Test
	public void testCRUD() throws Exception {
		//建立使用者
		SecUser user1 = new SecUser();
		user1.setUserCode("1234");
		user1.setUserName("Admin1");
		user1.setCreatedBy(1L);
		user1.setLastModifiedBy(1L);
		user1.setStatus(SystemStatus.Y);
		user1.setUserPassword("test");
		
		//建立物件
		SetClmItemNature clmItemNature = new SetClmItemNature();
		clmItemNature.setItemId(1L);
		clmItemNature.setNatureId(1L);
		
		//save
		SetClmItemNature clmItemNature2= service.save(clmItemNature, user1);
		assertEquals(clmItemNature.getItemId(),clmItemNature2.getItemId());
		
		//update
		clmItemNature2.setItemId(100L);
		clmItemNature =service.update(clmItemNature2, user1);
		assertEquals(clmItemNature2.getItemId(),clmItemNature.getItemId());
		
		//delete
		service.deleteById(clmItemNature.getId());
		
		//find
		ds.setEntity(clmItemNature);
		ds = service.getByRestrictions(ds);
		assertNull(ds.getEntity());
	}
}
