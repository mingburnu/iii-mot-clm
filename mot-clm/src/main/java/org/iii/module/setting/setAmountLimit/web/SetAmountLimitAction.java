package org.iii.module.setting.setAmountLimit.web;

import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.setting.setAmountLimit.entity.SetAmountLimit;
import org.iii.module.setting.setAmountLimit.service.SetAmountLimitService;
import org.iii.module.setting.setClass.entity.SetClass;
import org.iii.module.setting.setClass.service.SetClassService;
import org.iii.module.setting.setOffice.entity.SetOffice;
import org.iii.module.setting.setOffice.service.SetOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetAmountLimitAction extends GenericCRUDAction<SetAmountLimit> {

	@Autowired
	private SetClass classes;

	@Autowired
	private SetOffice office;

	@Autowired
	private SetClassService classService;

	@Autowired
	private SetOfficeService officeService;

	@Autowired
	private DataSet<SetClass> dsClass;

	@Autowired
	private DataSet<SetOffice> dsOffice;

	@Autowired
	private SetAmountLimitService amountLimitService;

	@Override
	public void validateSave() throws Exception {
		validateClassId();
		validateOfficeId();
		validateAmount();
		validateFromId();
		createDsClassAndDsOffice();
	}

	private void validateAmount() {
		String amount = String.valueOf(getEntity().getAmount());
		if (NumberUtils.isDigits(amount) && getEntity().getAmount() > 0) {
			System.out.print("true");
		} else {
			addActionError("請輸入正整數");
		}
	}

	public void validateClassId() {

		if (getEntity().getClassId() == 0) {
			addActionError("請輸入產品別");
		}
	}

	public void validateOfficeId() {
		if (getEntity().getOfficeId() == 0) {
			addActionError("請輸入單位別");
		}
	}

	public void validateFromId() {
		if (getEntity().getFromId() == 0) {
			addActionError("請輸入轉案單位");
		}
	}

	@Override
	public void validateUpdate() throws Exception {
		validateClassId();
		validateOfficeId();
		validateAmount();
		validateFromId();
		createDsClassAndDsOffice();
	}

	@Override
	public void validateDelete() throws Exception {
		if (getEntity() != null && getEntity().getId() == null) {
			addActionError("沒有東西可以刪除");
		}
	}

	@Override
	public String query() throws Exception {
		try {
			if (getEntity().getId() != null) {
				SetAmountLimit amountLimit = amountLimitService
						.getById(getEntity().getId());
				setEntity(amountLimit);
			}
			createDsClassAndDsOffice();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return EDIT;
	}

	@Override
	public String list() throws Exception {
		try {
			DataSet<SetAmountLimit> ds = amountLimitService
					.getByRestrictions(initDataSet());
			List<SetAmountLimit> results = ds.getResults();

			for (int i = 0; i < results.size(); i++) {
				SetClass setClass = classService.getById(results.get(i)
						.getClassId());
				SetOffice setOffice = officeService.getById(results.get(i)
						.getOfficeId());
				SetOffice setOfficeFrom = officeService.getById(results.get(i)
						.getFromId());
				results.get(i).setSetClass(setClass);
				results.get(i).setSetOffice(setOffice);
				results.get(i).setSetOfficeFrom(setOfficeFrom);
			}

			ds.setResults(results);

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
			SetAmountLimit amountLimit = amountLimitService.save(getEntity(),
					getLoginUser());
			setEntity(amountLimit);
			createDsClassAndDsOffice();
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
			SetAmountLimit amountLimit = amountLimitService.update(getEntity(),
					getLoginUser());
			setEntity(amountLimit);
			createDsClassAndDsOffice();
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
			amountLimitService.deleteById(getEntity().getId());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		setEntity(null);
		disableAllInput();
		addActionMessage("檔案已刪除");
		return DELETE;
	}

	private void createDsClassAndDsOffice() throws Exception {
		dsClass.setEntity(classes);
		dsClass = classService.getByRestrictions(dsClass);

		dsOffice.setEntity(office);
		dsOffice = officeService.getByRestrictions(dsOffice);
	}

	/**
	 * @return the classes
	 */
	public SetClass getClasses() {
		return classes;
	}

	/**
	 * @param classes
	 *            the classes to set
	 */
	public void setClasses(SetClass classes) {
		this.classes = classes;
	}

	/**
	 * @return the office
	 */
	public SetOffice getOffice() {
		return office;
	}

	/**
	 * @param office
	 *            the office to set
	 */
	public void setOffice(SetOffice office) {
		this.office = office;
	}

	/**
	 * @return the classService
	 */
	public SetClassService getClassService() {
		return classService;
	}

	/**
	 * @param classService
	 *            the classService to set
	 */
	public void setClassService(SetClassService classService) {
		this.classService = classService;
	}

	/**
	 * @return the officeService
	 */
	public SetOfficeService getOfficeService() {
		return officeService;
	}

	/**
	 * @param officeService
	 *            the officeService to set
	 */
	public void setOfficeService(SetOfficeService officeService) {
		this.officeService = officeService;
	}

	/**
	 * @return the dsClass
	 */
	public DataSet<SetClass> getDsClass() {
		return dsClass;
	}

	/**
	 * @param dsClass
	 *            the dsClass to set
	 */
	public void setDsClass(DataSet<SetClass> dsClass) {
		this.dsClass = dsClass;
	}

	/**
	 * @return the dsOffice
	 */
	public DataSet<SetOffice> getDsOffice() {
		return dsOffice;
	}

	/**
	 * @param dsOffice
	 *            the dsOffice to set
	 */
	public void setDsOffice(DataSet<SetOffice> dsOffice) {
		this.dsOffice = dsOffice;
	}
}