package org.iii.module.setting.dao;

import org.iii.core.GenericTest;
import org.iii.module.setting.setClmItem.dao.SetClmItemDao;
import org.iii.module.setting.setClmItem.entity.SetClmItem;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SetClmItemDaoTest extends GenericTest {

	@Autowired
	private SetClmItemDao dao;
	
	@Test
	public void testCRUD() throws Exception{
		SetClmItem clmItem1 = new SetClmItem();
		
		clmItem1.setCode("xxx");
		clmItem1.setLocalName("yyy");
		
		
		dao.save(clmItem1);
	}

}
