package org.iii.module.setting.setOffice.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.iii.core.GenericTest;
import org.iii.core.enums.SystemStatus;
import org.iii.core.model.DataSet;
import org.iii.core.security.secUser.entity.SecUser;
import org.iii.module.setting.setOffice.entity.SetOffice;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SetOfficeServiceTest extends GenericTest {
	
	@Autowired
	private SetOfficeService service;
	@Autowired
	private DataSet<SetOffice> ds;
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
			SetOffice office1 = new SetOffice();
			office1.setCode("1234");
			office1.setLocalName("123");
			office1.setId((long)12345789);
			
			//save
			office1=service.save(office1, user1);
			assertEquals("1234", office1.getCode());
			
			//update id
			final String office1UpdCode = "aabb";
			office1.setCode("aabb");
			office1=service.update(office1, user1);
			assertEquals(office1UpdCode, office1.getCode());
			//update name
			final String office1UpdName = "aabb";
			office1.setLocalName("aabb");
			office1=service.update(office1, user1);
			assertEquals(office1UpdName, office1.getLocalName());
			//find id
			SetOffice findOffice = new SetOffice();
			findOffice.setCode("aabb");
			ds.setEntity(findOffice);
			ds=service.getByRestrictions(ds);
			List<SetOffice> Office = ds.getResults();
			Assert.assertEquals(1, Office.size());
			assertEquals(office1UpdCode, Office.get(0).getCode());
			
			//find name
			SetOffice findOfficeName = new SetOffice();
			findOfficeName.setLocalName("ab");
			ds.setEntity(findOfficeName);
			ds=service.getByRestrictions(ds);
			List<SetOffice> Office2 = ds.getResults();
			Assert.assertEquals(1, Office2.size());
			assertEquals(office1UpdName, Office.get(0).getLocalName());
			
			//delete
			SetOffice Office3 = service.save(findOffice, user1);
			final Long Office1Id = Office3.getId();
			boolean deleted = true;
			try {
				service.deleteById(Office1Id);
			} catch (Exception e) {
				deleted = false;
			}
			Assert.assertTrue(deleted);
			
	}

}
