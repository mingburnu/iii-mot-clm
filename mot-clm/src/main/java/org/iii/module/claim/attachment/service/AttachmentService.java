package org.iii.module.claim.attachment.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.iii.core.dao.GenericDao;
import org.iii.core.dao.IiiRestrictions;
import org.iii.core.model.DataSet;
import org.iii.core.service.GenericService;
import org.iii.core.util.IiiBeanFactory;
import org.iii.module.claim.attachment.dao.AttachmentDao;
import org.iii.module.claim.attachment.entity.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 附件管理系統 Service
 * 
 * @author Rodrigo Chun-Ming Chu
 * @version 2014/5/20
 */
@Service
public class AttachmentService extends GenericService<Attachment> {
	@Autowired
	private AttachmentDao dao;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public DataSet<Attachment> getByRestrictions(DataSet<Attachment> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());

		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		Attachment attachment = ds.getEntity();

		String regIdString = String.valueOf(attachment.getRegId());

		if (StringUtils.isNotEmpty(regIdString) && attachment.getRegId() != 0
				&& StringUtils.isNotEmpty(attachment.getCode())) {
			restrictions.eq("regId", attachment.getRegId());
			restrictions.eq("code", attachment.getCode());
		}

		return dao.findByRestrictions(restrictions, ds);
	}

	public DataSet<Attachment> getByOrderDesc(DataSet<Attachment> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());

		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		Attachment attachment = ds.getEntity();
		
		String regIdString = String.valueOf(attachment.getRegId());

		if (StringUtils.isNotEmpty(regIdString) && attachment.getRegId() != 0
				&& StringUtils.isNotEmpty(attachment.getCode())) {
			restrictions.eq("regId", attachment.getRegId());
			restrictions.eq("code", attachment.getCode());
			restrictions.addOrderDesc("id");
		}

		return dao.findByRestrictions(restrictions, ds);
	}

	public DataSet<Attachment> getDsById(DataSet<Attachment> ds)
			throws Exception {
		Assert.notNull(ds);
		Assert.notNull(ds.getEntity());

		IiiRestrictions restrictions = IiiBeanFactory.getIiiRestrictions();
		restrictions.isNotNull("id");
		return dao.findByRestrictions(restrictions, ds);
	}
	
	@SuppressWarnings("rawtypes")
	public List<Attachment> selByIdByCriteria(List<Long> idList)
			throws Exception {
		List<Attachment> selList = new ArrayList<Attachment>();
		Session session = sessionFactory.getCurrentSession();
		
		int i = 0;
		while (i < idList.size()) {
			Criteria criteria = session.createCriteria(Attachment.class);
			criteria.add(Restrictions.eq("id", idList.get(i)));
			Iterator iterator = criteria.list().iterator();
			while (iterator.hasNext()) {
				Attachment attachment = (Attachment) iterator.next();
				selList.add(attachment);
			}
			i++;
		}
		return selList;
	}

	@SuppressWarnings("rawtypes")
	public List<Long> queryAllIdByCriteria() throws Exception {
		List<Long> idList = new ArrayList<Long>();
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Attachment.class);
		Iterator iterator = criteria.list().iterator();
		while (iterator.hasNext()) {
			Attachment attachment = (Attachment) iterator.next();
			idList.add(attachment.getId());
		}
		return idList;
	}

	@SuppressWarnings("rawtypes")
	public List<Long> queryAllIdByHql() throws Exception {
		List<Long> idList = new ArrayList<Long>();
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT A.id FROM Attachment A");
		List list = query.list();
		int i = 0;
		while (i < list.size()) {
			idList.add((Long) list.get(i));
			i++;
		}
		return idList;
	}

	public long queryMxaIdByJdbc() throws Exception {
		Connection conn = null;
		long maxId = -1;
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(
					"jdbc:h2:mem:iii;DB_CLOSE_DELAY=-1", "sa", "");
			Statement st = conn.createStatement();
			ResultSet idMaxResult = st
					.executeQuery("SELECT MAX(id) max_id FROM CLM_ATTACHMENT");
			while (idMaxResult.next()) {
				maxId = idMaxResult.getLong("max_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return maxId;
	}

	public List<Long> queryAllIdByJdbc() throws Exception {
		Connection conn = null;
		List<Long> idList = new ArrayList<Long>();
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection(
					"jdbc:h2:mem:iii;DB_CLOSE_DELAY=-1", "sa", "");
			Statement st = conn.createStatement();
			ResultSet idTotalResult = st
					.executeQuery("SELECT (id)id FROM CLM_ATTACHMENT");
			while (idTotalResult.next()) {
				idList.add(idTotalResult.getLong("id"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null)
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		return idList;
	}
	
	@Override
	protected GenericDao<Attachment> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
}
