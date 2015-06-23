package org.iii.module.claim.remark.web;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.claim.remark.entity.Remark;
import org.iii.module.claim.remark.service.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 報案處理記錄 Action
 * @author Gina Chen
 * @version 2014/4/10
 */
@Controller
@SuppressWarnings("serial")
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RemarkAction extends GenericCRUDAction<Remark>{

	@Autowired
	private RemarkService remarkService;
	
	private Long registId;
	
	private String caseId;
	
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
			if(getEntity().getId() != null){
				Remark remark = remarkService.getById(getEntity().getId());
				setEntity(remark);
			}else{
				Remark entity = getEntity();
				if(getEntity().getRegCode() != null){
					entity.setRegCode(getEntity().getRegCode());
				}
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
			DataSet<Remark> ds = remarkService.getByRestrictions(initDataSet());
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
			Remark remark = remarkService.save(getEntity(), getLoginUser());
			setEntity(remark);
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
			Remark remark = remarkService.update(getEntity(), getLoginUser());
			setEntity(remark);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		addActionMessage("檔案已修改");
		return EDIT;
	}

	@Override
	public String delete() throws Exception {
		try {
			remarkService.deleteById(getEntity().getId());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		setEntity(null);
		disableAllInput();
		addActionMessage("檔案已刪除");
		return DELETE;
	}
	
	
	public Long getRegistId() {
		return registId;
	}

	public void setRegistId(Long registId) {
		this.registId = registId;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	
	

}
