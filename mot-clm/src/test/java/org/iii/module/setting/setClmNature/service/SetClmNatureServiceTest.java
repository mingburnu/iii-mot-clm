package org.iii.module.setting.setClmNature.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.iii.core.GenericTest;
import org.iii.core.enums.SystemStatus;
import org.iii.core.model.DataSet;
import org.iii.core.security.secUser.entity.SecUser;
import org.iii.module.setting.setClmNature.entity.SetClmNature;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SetClassServiceTest
 * 
 * @author Mark Huang
 * @version 2014/3/28
 */
public class SetClmNatureServiceTest extends GenericTest {

	@Autowired
	private SetClmNatureService service;

	@Autowired
	private DataSet<SetClmNature> ds;

	@Test
	public void testCRUD() throws Exception {
		// 建立使用者
		SecUser user1 = new SecUser();
		user1.setUserCode("1234");
		user1.setUserName("Admin1");
		user1.setCreatedBy(1L);
		user1.setLastModifiedBy(1L);
		user1.setStatus(SystemStatus.Y);
		user1.setUserPassword("test");

		// 建立物件
		SetClmNature clmNature = new SetClmNature();
		clmNature.setCode("aaa");
		clmNature.setLocalName("bbb");

		// save
		SetClmNature clmNature2 = service.save(clmNature, user1);
		assertEquals(clmNature.getCode(), clmNature2.getCode());
		assertEquals(clmNature.getLocalName(), clmNature2.getLocalName());

		// update
		clmNature2.setCode("ccc");
		clmNature = service.update(clmNature2, user1);
		assertEquals(clmNature2.getCode(), clmNature.getCode());

		// query by condition
		SetClmNature clmNature3 = new SetClmNature();
		clmNature3.setLocalName("b");
		ds.setEntity(clmNature3);
		ds = service.getByRestrictions(ds);
		List<SetClmNature> clmNatures = ds.getResults();
		Assert.assertEquals(1, clmNatures.size());
		assertEquals("bbb", clmNatures.get(0).getLocalName());

		// find By Id
		clmNature = service.getById(clmNature2.getId());
		if (clmNature.getId() != clmNature2.getId())
			fail("findById error");

		// delete
		service.deleteById(clmNature2.getId());

	}

}
