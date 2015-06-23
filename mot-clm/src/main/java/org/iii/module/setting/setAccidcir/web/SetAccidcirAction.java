package org.iii.module.setting.setAccidcir.web;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.setting.setAccidcir.entity.SetAccidcir;
import org.iii.module.setting.setAccidcir.service.SetAccidcirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * SetAccidcirAction
 * @author Gina Chen
 * @version 2014/3/26
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetAccidcirAction extends GenericCRUDAction<SetAccidcir> {

	@Autowired
	private SetAccidcirService acciService;
	

	@Override
	public void validateSave() throws Exception {
		validateAccidcirClassId();
		validateAccidcirCode();
		validateAccidcirLocalName();
		
	}

	private void validateAccidcirClassId() {
		
		if(getEntity().getClassId() == null && !NumberUtils.isDigits(String.valueOf(getEntity().getClassId()))){
			addActionError("請輸入產品別(需為數值)");
		}
		clearMessages();
	}

	private void validateAccidcirCode() {
		if(StringUtils.isEmpty(getEntity().getCode())){
			addActionError("請輸入產品代碼");
		}
		
	}
	
	private void validateAccidcirLocalName() {
		if(StringUtils.isEmpty(getEntity().getLocalName())){
			addActionError("請輸入中文名稱");
		}
		
	}

	@Override
	public void validateUpdate() throws Exception {
		validateAccidcirCode();
	}

	@Override
	public void validateDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String query() throws Exception {
		try {
			if(getEntity().getId() != null){
				SetAccidcir code = acciService.getById(getEntity().getId());
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
			DataSet<SetAccidcir> ds = acciService.getByRestrictions(initDataSet());
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
			SetAccidcir code = acciService.save(getEntity(), getLoginUser());
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
			SetAccidcir code = acciService.update(getEntity(), getLoginUser(), "userPassword");
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
			acciService.deleteById(getEntity().getId());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		disableAllInput();
		addActionMessage("檔案已刪除");
		return DELETE;
	}
	

}
