package org.iii.module.setting.setAuthority.web;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.setting.setAuthority.entity.SetAuthority;
import org.iii.module.setting.setAuthority.service.SetAuthorityService;
import org.iii.module.setting.setClass.entity.SetClass;
import org.iii.module.setting.setClass.service.SetClassService;
import org.iii.module.setting.setOffice.entity.SetOffice;
import org.iii.module.setting.setOffice.service.SetOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * SetAuthorityAction 分層授權管理Action
 * @author Ruo Hsu
 * @version 2014/5/20
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SetAuthorityAction extends GenericCRUDAction<SetAuthority> {

	@Autowired
	private SetAuthorityService authorityService;
	
	@Autowired
	private SetClassService classService;
	
	@Autowired
	private DataSet<SetClass> dsClass;
	
	@Autowired
	private SetClass _class;
	
	@Autowired
	private SetOfficeService officeService;
	
	@Autowired
	private DataSet<SetOffice> dsOffice;	
	
	@Autowired
	private SetOffice _office;
	
//	private Integer minAmount = getEntity().getMinAmount();
//	private Integer maxAmount = getEntity().getMaxAmount();
	
	// 產品別不得為空值
	public void validateClassId() {
		if(getEntity() != null && getEntity().getClassId() == null){
			addActionError("請選擇產品別");
		}
	}
	
	// 單位別不得為空值
	public void validateOfficeId() {
		if(getEntity() != null && getEntity().getOfficeId() == null){
			addActionError("請選擇單位別");
		}
	}
	
	// 角色不得為空值
	public void validateRole(){
		if(StringUtils.isEmpty(getEntity().getRole())){
			addActionError("請輸入角色");
		}
	}
	
	// 業務來源不得為空值
	public void validateBusType() {
		if (getEntity() != null && getEntity().getBusType() == null) {
			addActionError("請選擇業務來源");
		}
	}
	
	// 審核類型不得為空值
	public void validateAuditType(){
		if(StringUtils.isEmpty(getEntity().getAuditType())){
			addActionError("請輸入審核類型");
		}
	}
	
	// 驗證核決金額(起)必須大於0
	public void validateMinAmount(){
		
//		Integer money = null;		
//		String minAmount = String.valueOf(getEntity().getMinAmount());
//		try {
//			money = Integer.parseInt(minAmount);
//		} catch (NumberFormatException e) {
//			
//		}
//		
//		if(money == null){
//			addActionError("金額必須是整數");
//		}
		
		
		if(getEntity().getMinAmount() != null){
			
			Integer money = null;		
			String minAmount = String.valueOf(getEntity().getMinAmount());
			try {				
				money = Integer.parseInt(minAmount);
			} catch (NumberFormatException e) {
				
			}			
			if(money == null){
				addActionError("金額必須是整數");
			}
		}
		
		
		
//		String minAmount = String.valueOf(getEntity().getMinAmount());
//		if(StringUtils.isNotEmpty(minAmount)){
//			if(!NumberUtils.isDigits(minAmount)){
//				addActionError("金額必須是整數");
//			}
//		}
//		if(getEntity().getMinAmount() != null){
//			if(getEntity().getMinAmount() < 0){
//				addActionError("核決金額(起)必須大於0");
//			}
//		}
	}
	
	// 驗證核決金額(迄)必須大於0，必須大於核決金額(起)
	public void validateMaxAmount(){
		if(getEntity().getMaxAmount() != null){
			if(getEntity().getMaxAmount() < 0){
				addActionError("核決金額(迄)必須大於0");
			}
			if(getEntity().getMinAmount() != null && getEntity().getMaxAmount() < getEntity().getMinAmount()){
				addActionError("核決金額(迄)必須大於核決金額(起)");
			}
		}
	}

	@Override
	public void validateSave() throws Exception {
		validateMinAmount();
		validateMaxAmount();
		validateClassId();
		validateOfficeId();
		validateRole();
		validateBusType();
		validateAuditType();		
		createDsClass();
		createDsOffice();
	}

	@Override
	public void validateUpdate() throws Exception {
		validateMinAmount();
		validateMaxAmount();
		validateClassId();
		validateOfficeId();
		validateRole();
		validateBusType();
		validateAuditType();		
		createDsClass();
		createDsOffice();
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
			if(getEntity().getId()!=null) {
				SetAuthority auth = authorityService.getById(getEntity().getId());
				setEntity(auth);
			}
			createDsClass();
			createDsOffice();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return EDIT;
	}

	@Override
	public String list() throws Exception {
		try {
			DataSet<SetAuthority> ds = authorityService.getByRestrictions(initDataSet());
			createDsClass();
			createDsOffice();
			List<SetAuthority> li = ds.getResults();
			for(SetAuthority s:li){
				_class=classService.getById(s.getClassId());
				s.setSetClass(_class);
//				_office=dsOffice.getEntity(officeService.getByRestrictions(s.getOfficeId()));
//				s.setSetOffice(_office);
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
			SetAuthority auth = authorityService.save(getEntity(), getLoginUser());
			setEntity(auth);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		createDsClass();
		createDsOffice();
		addActionMessage("儲存成功");
		return EDIT;
	}

	@Override
	public String update() throws Exception {
		try {
			SetAuthority auth = authorityService.update(getEntity(), getLoginUser());
			setEntity(auth);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		createDsClass();
		createDsOffice();
		addActionMessage("檔案已修改");
		return EDIT;
	}

	@Override
	public String delete() throws Exception {
		try {
			authorityService.deleteById(getEntity().getId());
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
	
	private void createDsOffice() throws Exception{
		dsOffice.setEntity(_office);
		dsOffice=officeService.getByRestrictions(dsOffice);
	}

	public DataSet<SetOffice> getDsOffice() {
		return dsOffice;
	}
	public void setDsOffice(DataSet<SetOffice> dsOffice) {
		this.dsOffice = dsOffice;
	}
	
}
