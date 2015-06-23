package org.iii.module.setting.setEvent.web;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.setting.setEvent.entity.SetEvent;
import org.iii.module.setting.setEvent.service.SetEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * SetEventAction
 * @author Gina Chen
 * @version 2014/3/28
 */
@Controller
@SuppressWarnings("serial")
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetEventAction extends GenericCRUDAction<SetEvent>{

	@Autowired
	private SetEventService eventService;
	
	@Override
	public void validateSave() throws Exception {
		validateEventCode();
		validateEventDescript();
		
	}

	private void validateEventCode() {
		if(StringUtils.isEmpty(getEntity().getCode())){
			addActionError("請輸入代號");
		}
		
	}
	
	private void validateEventDescript() {
		if(StringUtils.isEmpty(getEntity().getDescript())){
			addActionError("事件敘述");
		}
		
	}

	

	@Override
	public void validateUpdate() throws Exception {
		validateEventCode();
		
	}

	@Override
	public void validateDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String query() throws Exception {
		try {
			if(getEntity().getId() != null){
				SetEvent code = eventService.getById(getEntity().getId());
				setEntity(code);			
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
			DataSet<SetEvent> ds = eventService.getByRestrictions(initDataSet()) ;
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
			SetEvent code = eventService.save(getEntity(), getLoginUser());
			setEntity(code);
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
			SetEvent code = eventService.update(getEntity(), getLoginUser(), "userPassword");
			setEntity(code);
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
			eventService.deleteById(getEntity().getId());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		disableAllInput();
		addActionMessage("檔案已刪除");
		return DELETE;
	}

}
