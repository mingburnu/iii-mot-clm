package org.iii.module.setting.setOffice.web;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.setting.setOffice.entity.SetOffice;
import org.iii.module.setting.setOffice.service.SetOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 單位管理
 * @author 黃小貓
 * @version 2014/3/26
 */

@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)

public class SetOfficeAction extends GenericCRUDAction<SetOffice>{

	@Autowired
	private SetOfficeService officeService;
	
	@Override
	public void validateSave() throws Exception {
		validateCode();
		if(StringUtils.isEmpty(getEntity().getLocalName())) {
			addActionError("請輸入單位名稱");
		}
		if(StringUtils.isEmpty(getEntity().getManager())) {
			addActionError("請輸入負責人名稱");
		}
		
	}

	@Override
	public void validateUpdate() throws Exception {
		validateCode();
		
	}

	@Override
	public void validateDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void validateCode() {
		if(StringUtils.isEmpty(getEntity().getCode())) {
			addActionError("請輸入單位代碼");
		}
	}
	
	@Override
	public String query() throws Exception {
		try {
//			String id = getRequest().getParameter("id");
			if(getEntity().getId()!=null) {
				SetOffice code = officeService.getById(getEntity().getId());
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
			DataSet<SetOffice> ds=officeService.getByRestrictions(initDataSet());
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
			SetOffice code = officeService.save(getEntity(), getLoginUser());
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
			SetOffice code = officeService.update(getEntity(), getLoginUser(), "userPassword");
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
			officeService.deleteById(getEntity().getId());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		disableAllInput();
		addActionMessage("檔案已刪除");
		return DELETE;
	}


}
