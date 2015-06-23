package org.iii.module.claim.contact.dao;

import static org.junit.Assert.*;

import org.iii.core.GenericTest;
import org.iii.module.claim.contact.entity.Contact;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ContactDaoTest
 * @author 黃小貓
 * @version 2014/4/10
 */
public class ContactDaoTest extends GenericTest {

	@Autowired
	private ContactDao dao;
	
	@Test
	public void testCRUD()  throws Exception {
		//save
		Contact contact1=new Contact();
		contact1.setCustomerId("aaa");
				
		Contact contact2=dao.save(contact1);
				assertEquals(contact1.toString(),contact2.toString());
				
				//update 
				contact2.setCustomerId("bbb");
				dao.update(contact2);		
				assertEquals("bbb",contact2.getCustomerId());
				
				//findbyid
				contact1=dao.findById(contact2.getId());
				assertEquals(contact2.getCustomerId(),contact1.getCustomerId());
				
				//delete
				dao.delete(contact1);
				contact1=dao.findById(contact1.getId());
				assertNull(contact1);
	}

}
