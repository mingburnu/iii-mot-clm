package org.iii.module.claim.contact.service;

import static org.junit.Assert.*;

import org.iii.core.GenericTest;
import org.iii.core.enums.SystemStatus;
import org.iii.core.model.DataSet;
import org.iii.core.security.secUser.entity.SecUser;
import org.iii.module.claim.contact.entity.Contact;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ContactServiceTest
 * 
 * @author 黃小貓
 * @version 2014/4/10
 */
public class ContactServiceTest extends GenericTest {

	@Autowired
	private ContactService service;

	@Autowired
	private DataSet<Contact> ds;

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
		Contact contact1 = new Contact();
		contact1.setCustomerId("123");

		// save
		Contact contact2 = service.save(contact1, user1);
		assertEquals("123", contact1.getCustomerId());

		// update
		contact2.setCustomerId("123");
		contact1 = service.update(contact2, user1);
		assertEquals(contact2.getCustomerId(), contact1.getCustomerId());

		// delete
		service.deleteById(contact1.getId());

		// find
		ds.setEntity(contact1);
		ds = service.getByRestrictions(ds);
		assertNull(ds.getEntity());
	}

}
