package org.iii.module.setting.setAccidcir.dao;

import org.iii.core.GenericTest;
import org.iii.module.setting.setAccidcir.entity.SetAccidcir;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SetAccidcirDaoTest
 * @author Gina Chen
 * @version 2014/3/24
 */
public class SetAccidcirDaoTest extends GenericTest {

	
	@Autowired
	private SetAccidcirDao dao;
	
	@Test
	public void test() throws Exception {
		SetAccidcir accidcir1 = new SetAccidcir();
		
		accidcir1.setCode("d8kd");
		accidcir1.setLocalName("xxx");
		accidcir1.setEngName("yyy");
		
		dao.save(accidcir1);
	}

}
