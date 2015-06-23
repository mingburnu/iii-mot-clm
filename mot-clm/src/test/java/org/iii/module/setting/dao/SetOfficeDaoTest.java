package org.iii.module.setting.dao;

import org.iii.core.GenericTest;
import org.iii.module.setting.setOffice.dao.SetOfficeDao;
import org.iii.module.setting.setOffice.entity.SetOffice;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SetOfficeDaoTest extends GenericTest {

	@Autowired
	private SetOfficeDao dao;
	
	@Test
	public void testCRUD() throws Exception{
		SetOffice office1 = new SetOffice();
		
		office1.setCode("xxx");
		office1.setLocalName("yyy");
		
		
		dao.save(office1);
	}

}
