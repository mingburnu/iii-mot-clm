package org.iii.module.setting.setEvent.dao;

import org.iii.core.GenericTest;
import org.iii.module.setting.setEvent.entity.SetEvent;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SetEventDaoTest
 * @author Gina Chen
 * @version 2014/3/24
 */
public class SetEventDaoTest extends GenericTest {

	@Autowired
	private SetEventDao dao;
	
	@Test
	public void test() throws Exception {
		SetEvent event1 = new SetEvent();
		
		event1.setCode("be97");
		event1.setDescript("test");
		
		dao.save(event1);
	}

}
