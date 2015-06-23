package org.iii.module.setting.setDoc.web;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.setting.setDoc.entity.SetDoc;
import org.iii.module.setting.setDoc.service.SetDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


/**
 * SetDocAction
 * @author Gina Chen
 * @version 2014/3/28
 */
@Controller
@SuppressWarnings("serial")
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetDocAction extends GenericCRUDAction<SetDoc>{

	@Autowired
	private SetDocService docService;
	
	@Override
	public void validateSave() throws Exception {
		validateDocClassId();
		validateDocCode();
		validateDocLocalName();
		
	}

	private void validateDocClassId() {
		String classID = String.valueOf(getEntity().getClassId());
		if(StringUtils.isEmpty(classID)){
			addActionError("請輸入產品別");
		}
		if(!NumberUtils.isDigits(classID)){
			addActionError("ID須為數值");
		}
		
	}

	private void validateDocCode() {
		if(StringUtils.isEmpty(getEntity().getCode())){
			addActionError("請輸入代號");
		}
		
	}

	private void validateDocLocalName() {
		if(StringUtils.isEmpty(getEntity().getLocalName())){
			addActionError("請輸入名稱");
		}
		
	}
	
	@Override
	public void validateUpdate() throws Exception {
		validateDocCode();
		
	}

	@Override
	public void validateDelete() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String query() throws Exception {
		try {
			if(getEntity().getId() != null){
				SetDoc code = docService.getById(getEntity().getId());
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
			DataSet<SetDoc> ds = docService.getByRestrictions(initDataSet());
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
			SetDoc code = docService.save(getEntity(), getLoginUser());
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
			SetDoc code = docService.update(getEntity(), getLoginUser(), "userPassword");
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
			docService.deleteById(getEntity().getId());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		disableAllInput();
		addActionMessage("檔案已刪除");
		return DELETE;
	}

}
