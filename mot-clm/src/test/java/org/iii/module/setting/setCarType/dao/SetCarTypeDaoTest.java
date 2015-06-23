package org.iii.module.setting.setCarType.dao;

import org.iii.core.GenericTest;
import org.iii.module.setting.setCarType.dao.SetCarTypeDao;
import org.iii.module.setting.setCarType.entity.SetCarType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 使用者
 * @author Ruo Hsu
 * @version 2014/3/25
 * 
 */
public class SetCarTypeDaoTest extends GenericTest {
	
	@Autowired
	private SetCarTypeDao dao;

	@Test
	public void testCRUD() throws Exception {

		SetCarType carType1 = new SetCarType();

		carType1.setTypeCode("0001");
		carType1.setTypeName("TOYOTA");

		dao.save(carType1);
	}

}
