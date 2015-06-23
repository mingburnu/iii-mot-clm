package org.iii.module.setting.setAuthority.dao;

import org.iii.core.GenericTest;
import org.iii.module.setting.setAuthority.entity.SetAuthority;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 使用者
 * @author Ruo Hsu
 * @version 2014/3/25
 */
public class SetAuthorityDaoTest extends GenericTest {

	@Autowired
	private SetAuthorityDao dao;

	@Test
	public void testCRUD() throws Exception {

		SetAuthority authority1 = new SetAuthority();

		authority1.setRole("people");
		authority1.setAuditType("other");

		dao.save(authority1);
	}

}
