package org.iii.module.setting.setDoc.dao;

import org.iii.core.GenericTest;
import org.iii.module.setting.setDoc.entity.SetDoc;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SetDocDaoTest
 * @author Gina Chen
 * @version 2014/3/24
 */
public class SetDocDaoTest extends GenericTest {

	@Autowired
	private SetDocDao dao;
	
	@Test
	public void test() throws Exception {
		SetDoc doc1 = new SetDoc();
		
		doc1.setCode("di973d");
		doc1.setLocalName("xxye");
		
		dao.save(doc1);
	}

}
