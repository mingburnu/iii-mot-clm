package org.iii.module.setting.setCarType.web;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.setting.setCarType.entity.SetCarType;
import org.iii.module.setting.setCarType.service.SetCarTypeService;
import org.iii.module.setting.setClass.entity.SetClass;
import org.iii.module.setting.setClass.service.SetClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * SetCarTypeAction 車種管理Action
 * 
 * @author Ruo Hsu
 * @version 2014/5/20
 * 
 * @author Mark Huang
 * @version 2014/3/31
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetCarTypeAction extends GenericCRUDAction<SetCarType> {

	@Autowired
	private SetCarTypeService carTypeService;
	
	@Autowired
	private SetClassService classService;
	
	@Autowired
	private DataSet<SetClass> dsClass;
	
	@Autowired
	private SetClass _class;
	
	@Override
	public void validateSave() throws Exception {
		validateCarTypeTypeCode();
		validateCarTypeTypeName();
		validateCarTypeClassId();
		createDsClass();	
	}

	@Override
	public void validateUpdate() throws Exception {
		validateCarTypeTypeCode();
		validateCarTypeTypeName();
		validateCarTypeClassId();
		createDsClass();
	}

	// 車種代碼不得為空值
	public void validateCarTypeTypeCode() {
		if (StringUtils.isEmpty(getEntity().getTypeCode())) {
			addActionError("請輸入車種代碼");
		}
	}

	// 車種名稱不得為空值
	public void validateCarTypeTypeName() {
		if (StringUtils.isEmpty(getEntity().getTypeName())) {
			addActionError("請輸入車種名稱");
		}
	}

	// 產品別不得為空值
	public void validateCarTypeClassId() {
		if (getEntity() != null && getEntity().getClassId() == null) {
			addActionError("請選擇產品別");
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
				SetCarType carType = carTypeService.getById(getEntity().getId());
				setEntity(carType);
			}
			createDsClass();			
			
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return EDIT;
	}

	@Override
	public String list() throws Exception {
		try {
			DataSet<SetCarType> ds = carTypeService.getByRestrictions(initDataSet());
			createDsClass();
			List<SetCarType> li=ds.getResults();
			for(SetCarType s:li){
				_class=classService.getById(s.getClassId());
				s.setSetClass(_class);
			}
			ds.setResults(li);			
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
			SetCarType carType = carTypeService.save(getEntity(), getLoginUser());
			setEntity(carType);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		createDsClass();
		addActionMessage("儲存成功");
		return EDIT;
	}

	@Override
	public String update() throws Exception {
		try {
			SetCarType carType = carTypeService.update(getEntity(), getLoginUser());
			setEntity(carType);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		createDsClass();
		addActionMessage("檔案已修改");
		return EDIT;
	}

	@Override
	public String delete() throws Exception {
		try {
			carTypeService.deleteById(getEntity().getId());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		setEntity(null);
		disableAllInput();
		addActionMessage("檔案已刪除");
		return DELETE;
	}
	
	private void createDsClass() throws Exception{
		dsClass.setEntity(_class);
		dsClass=classService.getByRestrictions(dsClass);
	}

	public DataSet<SetClass> getDsClass() {
		return dsClass;
	}
	public void setDsClass(DataSet<SetClass> dsClass) {
		this.dsClass = dsClass;
	}
	

}
