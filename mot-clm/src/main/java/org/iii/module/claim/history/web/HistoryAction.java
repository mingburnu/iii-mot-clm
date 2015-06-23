package org.iii.module.claim.history.web;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.claim.history.entity.History;
import org.iii.module.claim.history.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * HistoryAction 狀態記錄/備註Action
 * @author Ruo Hsu
 * @version 2014/5/9
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HistoryAction extends GenericCRUDAction<History> {

	@Autowired
	private HistoryService historyService;	
	
	private String othId;
	
	private String casedCode;
	
	@Override
	public void validateSave() throws Exception {
	
	}

	@Override
	public void validateUpdate() throws Exception {
	
	}

	@Override
	public void validateDelete() throws Exception {
	
	}

	@Override
	public String query() throws Exception {
		
		try {
			History history = null;
			if (getEntity().getId() != null) {
				history = historyService.getById(getEntity().getId());
				setEntity(history);
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return EDIT;
	}

	@Override
	public String list() throws Exception {
		
		try {
			DataSet<History> ds = historyService.getByRestrictions(initDataSet());
			setDs(ds);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return LIST;
	}
	
	@Override
	public String save() throws Exception {
		
		try {
			History history = historyService.save(getEntity(), getLoginUser());
			setEntity(history);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		addActionMessage("儲存成功");
		return EDIT;
	}

	@Override
	public String update() throws Exception {
		
		try {
			
			History history = historyService.update(getEntity(), getLoginUser());
			setEntity(history);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		addActionMessage("修改成功");
		return EDIT;
	}

	@Override
	public String delete() throws Exception {
		
		return null;
	}

	public String getOthId() {
		return othId;
	}

	public void setOthId(String othId) {
		this.othId = othId;
	}

	public String getCasedCode() {
		return casedCode;
	}

	public void setCasedCode(String casedCode) {
		this.casedCode = casedCode;
	}

	//新增備註用
	public String savedescript() throws Exception {
		
		try {
			
			History history = historyService.update(getEntity(), getLoginUser());
			setEntity(history);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		addActionMessage("儲存成功");
		return EDIT;
	}

}
