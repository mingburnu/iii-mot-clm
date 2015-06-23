package org.iii.module.claim.contact.web;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.claim.contact.entity.Contact;
import org.iii.module.claim.contact.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 報案通訊錄 Action
 * 
 * @author 黃小貓
 * @version 2014/4/10
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ContactAction extends GenericCRUDAction<Contact> {

	@Autowired
	private ContactService contactService;

	private Long registerId;
	
	private String cased;

	@Override
	public void validateSave() throws Exception {
		validateContact();
		if(StringUtils.isEmpty(getEntity().getMobile())) {
			addActionError("請輸入行動電話");
		}
	}
	public void validateContact() {
		if(StringUtils.isEmpty(getEntity().getContact())) {
			addActionError("請輸入聯絡人");
		}
	}

	@Override
	public void validateUpdate() throws Exception {

	}

	@Override
	public void validateDelete() throws Exception {

	}

	@Override
	public String query() throws Exception {
		try {
			if (getEntity().getId() != null) {
				Contact contact = contactService.getById(getEntity().getId());
				setEntity(contact);
		}else{
			Contact entity = getEntity();
			if(getEntity().getRegCode() != null){
				entity.setRegCode(getEntity().getRegCode());
			}
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
			DataSet<Contact> ds = contactService.getByRestrictions(initDataSet());
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
			if(getEntity().getCaseCode()==null && getEntity().getCaseCode()==""){
				getEntity().setCaseCode("");
			}
			Contact contact = contactService.save(getEntity(), getLoginUser());
			setEntity(contact);
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
			Contact contact = contactService
					.update(getEntity(), getLoginUser());
			setEntity(contact);
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
			if (getEntity().getId() != null) {
				contactService.deleteById(getEntity().getId());
			}					
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		disableAllInput();
		
		addActionMessage("檔案已刪除");
		return DELETE;

	}

	public Long getRegisterId() {
		return registerId;
	}

	public void setRegisterId(Long registerId) {
		this.registerId = registerId;
	}

	public String getCased() {
		return cased;
	}

	public void setCased(String cased) {
		this.cased = cased;
	}
	

}
