package org.iii.module.setting.setClmItemNature.web;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.setting.setClmItem.entity.SetClmItem;
import org.iii.module.setting.setClmItem.service.SetClmItemService;
import org.iii.module.setting.setClmItemNature.entity.SetClmItemNature;
import org.iii.module.setting.setClmItemNature.service.SetClmItemNatureService;
import org.iii.module.setting.setClmNature.entity.SetClmNature;
import org.iii.module.setting.setClmNature.service.SetClmNatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 理賠項目對應賠款性質 action
 * 
 * @author Mark Huang
 * @version 2014/3/28
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetClmItemNatureAction extends GenericCRUDAction<SetClmItemNature> {

	@Autowired
	private SetClmItem clmItem;
	
	@Autowired
	private SetClmNature clmNature;	

	@Autowired
	private SetClmItemService clmItemService;
	
	@Autowired
	private DataSet<SetClmItem> dsClmItem;
	
	@Autowired
	private DataSet<SetClmNature> dsClmNature;
	
	@Autowired
	private SetClmNatureService clmNatureService;

	@Autowired
	private SetClmItemNatureService clmItemNatureService;

	public void validateSave() throws Exception {
		validateClmItemNatureCode();
		validateClmItemNatureLocalName();
	}

	@Override
	public void validateUpdate() throws Exception {
		validateClmItemNatureCode();
		validateClmItemNatureLocalName();
	}

	// 理賠項目不得為空值
	public void validateClmItemNatureCode() {
		if (getEntity() != null && getEntity().getItemId() == null) {
			addActionError("理賠項目");
		}
	}

	// 賠款性質不得為空值
	public void validateClmItemNatureLocalName() {
		if (getEntity() != null && getEntity().getNatureId() == null) {
			addActionError("賠款性質");
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
				SetClmItemNature clmItemNature = clmItemNatureService
						.getById(getEntity().getId());
				setEntity(clmItemNature);			
			}			
			
			createDsClmItemAndDsClmNature();

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return EDIT;
	}

	@Override
	public String list() throws Exception {
		try {
			DataSet<SetClmItemNature> ds = clmItemNatureService
					.getByRestrictions(initDataSet());
			
			createDsClmItemAndDsClmNature();
			
			List<SetClmItemNature> li=ds.getResults();
			for(SetClmItemNature entity:li){
				entity.setClmItem(clmItem);
				clmItem = clmItemService
						.getById(entity.getItemId());
				entity.setClmItem(clmItem);

				clmNature = clmNatureService
						.getById(entity.getNatureId());
				entity.setClmNature(clmNature);
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
			SetClmItemNature clmItemNature = clmItemNatureService.save(
					getEntity(), getLoginUser());
			setEntity(clmItemNature);
			
			createDsClmItemAndDsClmNature();
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
			SetClmItemNature clmItemNature = clmItemNatureService.update(
					getEntity(), getLoginUser());
			setEntity(clmItemNature);
			
			createDsClmItemAndDsClmNature();
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
			clmItemNatureService.deleteById(getEntity().getId());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		setEntity(null);
		disableAllInput();
		addActionMessage("檔案已刪除");
		return DELETE;
	}
	
	/** 取得理賠項目和賠款性質 的dataset 
	 * @throws Exception */
	private void createDsClmItemAndDsClmNature() throws Exception{
		dsClmItem.setEntity(clmItem);
		dsClmItem = clmItemService
				.getByRestrictions(dsClmItem);
		
		dsClmNature.setEntity(clmNature);
		dsClmNature = clmNatureService
				.getByRestrictions(dsClmNature);
	}
	
	public SetClmItem getClmItem() {
		return clmItem;
	}

	public void setClmItem(SetClmItem clmItem) {
		this.clmItem = clmItem;
	}

	public SetClmNature getClmNature() {
		return clmNature;
	}

	public void setClmNature(SetClmNature clmNature) {
		this.clmNature = clmNature;
	}

	public DataSet<SetClmItem> getDsClmItem() {
		return dsClmItem;
	}

	public void setDsClmItem(DataSet<SetClmItem> dsClmItem) {
		this.dsClmItem = dsClmItem;
	}

	public DataSet<SetClmNature> getDsClmNature() {
		return dsClmNature;
	}

	public void setDsClmNature(DataSet<SetClmNature> dsClmNature) {
		this.dsClmNature = dsClmNature;
	}
	
}
