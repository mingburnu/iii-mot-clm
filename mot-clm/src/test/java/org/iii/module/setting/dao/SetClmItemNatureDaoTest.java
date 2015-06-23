package org.iii.module.setting.dao;

import static org.junit.Assert.*;

import org.iii.core.GenericTest;
import org.iii.module.setting.setClmItemNature.dao.SetClmItemNatureDao;
import org.iii.module.setting.setClmItemNature.entity.SetClmItemNature;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 使用者
 * @author Mark Huang
 * @version 2014/3/21
 */
public class SetClmItemNatureDaoTest extends GenericTest {
	
	@Autowired
	private SetClmItemNatureDao dao;
	
	@Test
	public void testCRUD() throws Exception {
		SetClmItemNature clmItemNature=new SetClmItemNature();
		clmItemNature.setItemId((long) 123456789);
		clmItemNature.setNatureId((long) 987654321);
		
		dao.save(clmItemNature);
		SetClmItemNature clmItemNature2=dao.findById(clmItemNature.getId());
		assertEquals(clmItemNature2.toString(),clmItemNature.toString());
	
		clmItemNature2.setItemId((long)0000);
		clmItemNature2.setNatureId((long) 321);
		dao.update(clmItemNature2);
		SetClmItemNature clmItemNature3=dao.findById(clmItemNature2.getId());
		assertEquals(clmItemNature3.toString(),clmItemNature2.toString());
		
		dao.deleteById(clmItemNature2.getId());
		clmItemNature3=dao.findById(clmItemNature2.getId());
		assertNull(clmItemNature3);
	}
}
