package org.iii.module.setting.dao;

import static org.junit.Assert.*;

import org.iii.core.GenericTest;
import org.iii.module.setting.setAmountLimit.dao.SetAmountLimitDao;
import org.iii.module.setting.setAmountLimit.entity.SetAmountLimit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * SetAmountLimitDaoTest
 * 
 * @author Rodrigo Chun-Ming Chu
 * @version 2014/3/26
 */
public class SetAmountLimitDaoTest extends GenericTest {
	@Autowired
	private SetAmountLimitDao dao;

	@Test
	public void testCRUD() throws Exception {
		SetAmountLimit amountLimit1 = new SetAmountLimit();
		long classId = 1234;
		long officeId = 4321;
		long fromId = 5678;
		amountLimit1.setClassId(classId);
		amountLimit1.setOfficeId(officeId);
		dao.save(amountLimit1);
		System.out.println(amountLimit1.toString());
		SetAmountLimit amountLimit2 = dao.findById(amountLimit1.getId());
		assertEquals(amountLimit2.toString(), amountLimit1.toString());

		amountLimit2.setClassId(classId);
		amountLimit2.setFromId(fromId);
		;
		dao.update(amountLimit2);
		SetAmountLimit amountLimit3 = dao.findById(amountLimit2.getId());
		assertEquals(amountLimit3.toString(), amountLimit2.toString());

		dao.delete(amountLimit3);
		SetAmountLimit amountLimit4 = dao.findById(amountLimit3.getId());
		assertNull(amountLimit4);
	}
}
