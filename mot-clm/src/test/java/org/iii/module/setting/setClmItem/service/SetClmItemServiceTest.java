package org.iii.module.setting.setClmItem.service;

import static org.junit.Assert.*;

import java.util.List;

import org.iii.core.GenericTest;
import org.iii.core.enums.SystemStatus;
import org.iii.core.model.DataSet;
import org.iii.core.security.secUser.entity.SecUser;
import org.iii.module.setting.setClmItem.entity.SetClmItem;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SetClmItemServiceTest extends GenericTest {


	@Autowired
	private SetClmItemService service;

	@Autowired
	private DataSet<SetClmItem> ds;
		
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
			SetClmItem clmItem1 = new SetClmItem();
			clmItem1.setCode("1234");
			clmItem1.setLocalName("123");
			clmItem1.setId((long)12345789);
			
			//save
			clmItem1=service.save(clmItem1, user1);
			assertEquals("1234", clmItem1.getCode());
			
			//update
			final String clmItem1UpdCode = "aabb";
			clmItem1.setCode("aabb");
			clmItem1=service.update(clmItem1, user1);
			assertEquals(clmItem1UpdCode, clmItem1.getCode());
			

			//find
			SetClmItem findClmItem = new SetClmItem();
			findClmItem.setCode("aabb");
			ds.setEntity(findClmItem);
			ds=service.getByRestrictions(ds);
			List<SetClmItem> clmItem = ds.getResults();
			Assert.assertEquals(1, clmItem.size());
			assertEquals(clmItem1UpdCode, clmItem.get(0).getCode());
			
			//delete
			SetClmItem clmItem3 = service.save(findClmItem, user1);
			final Long Office1Id = clmItem3.getId();
			boolean deleted = true;
			try {
				service.deleteById(Office1Id);
			} catch (Exception e) {
				deleted = false;
			}
			Assert.assertTrue(deleted);
			
	}

}
