package org.iii.module.setting.setClmNature.web;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.setting.setClmNature.entity.SetClmNature;
import org.iii.module.setting.setClmNature.service.SetClmNatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 賠款性質 action
 * 
 * @author Mark Huang
 * @version 2014/3/28
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetClmNatureAction extends GenericCRUDAction<SetClmNature> {

	@Autowired
	private SetClmNatureService clmNatureService;

	public void validateSave() throws Exception {
		validateClmNatureCode();
		validateClmNatureLocalName();
	}

	@Override
	public void validateUpdate() throws Exception {
		validateClmNatureCode();
		validateClmNatureLocalName();
	}

	// 賠款性質代碼不得為空值
	public void validateClmNatureCode() {
		if (StringUtils.isEmpty(getEntity().getCode())) {
			addActionError("賠款性質代碼");
		}
	}

	// 賠款性質全名不得為空值
	public void validateClmNatureLocalName() {
		if (StringUtils.isEmpty(getEntity().getLocalName())) {
			addActionError("賠款性質全名");
		}
	}

	@Override
	public void validateDelete() throws Exception {
		// check id
		if (getEntity() != null && getEntity().getId() == null) {
			addActionError("沒有東西可以刪除");
		}
	}

	@Override
	public String query() throws Exception {
		try {
			if (getEntity().getId() != null) {
				SetClmNature clmNature = clmNatureService.getById(getEntity()
						.getId());
				setEntity(clmNature);
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
			DataSet<SetClmNature> ds = clmNatureService
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
			SetClmNature clmNature = clmNatureService.save(getEntity(),
					getLoginUser());
			setEntity(clmNature);
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
			SetClmNature clmNature = clmNatureService.update(getEntity(),
					getLoginUser());
			setEntity(clmNature);
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
			clmNatureService.deleteById(getEntity().getId());
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
