package org.iii.module.setting.setClass.web;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.setting.setClass.entity.SetClass;
import org.iii.module.setting.setClass.service.SetClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 產品別 action
 * 
 * @author Mark Huang
 * @version 2014/3/26
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetClassAction extends GenericCRUDAction<SetClass> {

	@Autowired
	private SetClassService classService;

	@Override
	public void validateSave() throws Exception {
		validateClassCode();
		validateClassLocalName();
	}

	@Override
	public void validateUpdate() throws Exception {
		validateClassCode();
		validateClassLocalName();
	}

	// 產品別代碼不得為空值
	public void validateClassCode() {
		if (StringUtils.isEmpty(getEntity().getCode())) {
			addActionError("產品別代碼");
		}
	}

	// 產品別全名不得為空值
	public void validateClassLocalName() {
		if (StringUtils.isEmpty(getEntity().getLocalName())) {
			addActionError("產品別全名");
		}
	}	
	
	@Override
	public void validateDelete() throws Exception {
		//check id
		if (getEntity()!=null && getEntity().getId() == null) {
			addActionError("沒有東西可以刪除");
		}
	}

	@Override
	public String query() throws Exception {
		try {
			if (getEntity().getId() != null) {
				SetClass _class = classService.getById(getEntity().getId());
				setEntity(_class);
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
			DataSet<SetClass> ds = classService
					.getByRestrictions(initDataSet());
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
			SetClass _class = classService.save(getEntity(), getLoginUser());
			setEntity(_class);
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
			SetClass _class = classService.update(getEntity(), getLoginUser());
			setEntity(_class);
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
			classService.deleteById(getEntity().getId());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		setEntity(null);
		disableAllInput();
		addActionMessage("檔案已刪除");
		return DELETE;
	}

}
