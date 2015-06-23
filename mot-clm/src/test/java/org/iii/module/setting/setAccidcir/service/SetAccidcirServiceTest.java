package org.iii.module.setting.setAccidcir.service;

import java.util.List;

import org.iii.core.GenericTest;
import org.iii.core.enums.SystemStatus;
import org.iii.core.model.DataSet;
import org.iii.core.security.secUser.entity.SecUser;
import org.iii.core.security.secUser.service.SecUserService;
import org.iii.module.setting.setAccidcir.entity.SetAccidcir;
import org.iii.module.setting.setAccidcir.enums.AccidcirType;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SetAccidcirServiceTest extends GenericTest {

	@Autowired
	private SecUserService service;
	
	@Autowired
	private SetAccidcirService acciService;
	
	@Autowired
	private DataSet<SetAccidcir> ds;
		
	@Test
	public void testCRUD() throws Exception {
		final String user1Code = "0123";
		final String acci1Code = "0456";
		
		//Save user 1
		SecUser user1 = new SecUser();
		user1.setUserCode(user1Code);
		user1.setUserName("Admin1");
		user1.setCreatedBy(1L);
		user1.setLastModifiedBy(1L);
		user1.setStatus(SystemStatus.Y);
		user1.setUserPassword("test");
		
		SecUser dbUser1 = service.save(user1, user1);
		Assert.assertEquals(user1Code, dbUser1.getUserCode());
				
		//建立物件
		SetAccidcir accidcir = new SetAccidcir();
		accidcir.setClassId((long)9999);
		accidcir.setCode(acci1Code);
		accidcir.setLocalName("xxx");
		accidcir.setEngName("ooo");
		accidcir.setAccidcirType(AccidcirType.ANY);
		
		//save
		accidcir = acciService.save(accidcir, user1);
		Assert.assertEquals(acci1Code, accidcir.getCode());
		
		//update
		final String updAcciName = "asdfgjkl";
		accidcir.setLocalName(updAcciName);
		accidcir = acciService.update(accidcir, user1);
		Assert.assertEquals(updAcciName, accidcir.getLocalName());
		
		//find
		SetAccidcir findAcci = new SetAccidcir();
		findAcci.setLocalName("asdf");
		ds.setEntity(findAcci);
		ds = acciService.getByRestrictions(ds);
		List<SetAccidcir> acci = ds.getResults();
		Assert.assertEquals(1, acci.size());
        Assert.assertEquals(updAcciName, acci.get(0).getLocalName());
        
		
		
		//delete by id
//		SetAccidcir dbUser2 = service.save(entity, user);
//		final Long user2Id = dbUser2.getId();
//		boolean deleted = true;
//		try {
//			acciService.deleteById(acci1Code);
//		} catch (Exception e) {
//			deleted = false;
//		}
//		Assert.assertTrue(deleted);
//		
		
		
	}

}
