package org.iii.module.setting.setAuthority.service;

import org.iii.core.GenericTest;
import org.iii.core.enums.SystemStatus;
import org.iii.core.model.DataSet;
import org.iii.core.security.secUser.entity.SecUser;
import org.iii.module.setting.setAuthority.entity.SetAuthority;
import org.iii.module.setting.setAuthority.enums.BusType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SetAuthorityTest 分層授權管理Test
 * @author Ruo Hsu
 * @version 2014/3/30
 */
public class SetAuthorityServiceTest extends GenericTest {

	@Autowired
	private SetAuthorityService service;
	
	@Autowired
	private DataSet<SetAuthority> ds;

	@Test
	public void testCRUD() throws Exception {
		
		SecUser user1 = new SecUser();
		user1.setUserCode("1234");
		user1.setUserName("Admin1");
		user1.setCreatedBy(1L);
		user1.setLastModifiedBy(1L);
		user1.setStatus(SystemStatus.Y);
		user1.setUserPassword("test");
				
		final String userRole = "role01";
		final String userAuditType = "type01";
				
		// save
		SetAuthority authority1 = new SetAuthority();
		authority1.setClassId(1L);
		authority1.setOfficeId("O10");
		authority1.setRole(userRole);
		authority1.setBusType(BusType.DIRECT);
		authority1.setAuditType(userAuditType);
		authority1.setMinAmount(10000);
		authority1.setMaxAmount(10000000);
		authority1.setDescript("Test");
		authority1.setId((long)12345789);
		
		SetAuthority dbUser1 = service.save(authority1, user1);
		final Long userId = dbUser1.getId();
		Assert.assertEquals(userRole, dbUser1.getRole());
		
		// query by role
		dbUser1 = service.getById(userId);
		Assert.assertEquals(userRole, dbUser1.getRole());
		
		// update role
		final String authority1UpdRole = "Role_test";
		dbUser1.setRole("Role_test");
		dbUser1 = service.update(dbUser1, user1);
		Assert.assertEquals(authority1UpdRole, dbUser1.getRole());
		
//		// query by condition
//		SetAuthority queryRole = new SetAuthority();
//		queryRole.setRole("roletest");
//		ds.setEntity(queryRole);
//		ds = service.getByRestrictions(ds);
//		List<SetAuthority> users = ds.getResults();
//		Assert.assertEquals(1, users.size());
//		Assert.assertEquals(authority1UpdRole, users.get(0).getRole());
		
		// delete by role
		boolean deleted = true;
		try {
			service.deleteById(userId);
		} catch (Exception e) {
			deleted = false;
		}
		Assert.assertTrue(deleted);
	}
}
