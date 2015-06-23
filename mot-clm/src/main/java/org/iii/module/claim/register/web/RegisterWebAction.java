package org.iii.module.claim.register.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.security.secUser.entity.SecUser;
import org.iii.core.security.secUser.enums.UserType;
import org.iii.core.security.secUser.service.SecUserService;
import org.iii.core.web.GenericWebAction;
import org.iii.module.claim.contact.entity.Contact;
import org.iii.module.claim.contact.service.ContactService;
import org.iii.module.claim.register.entity.InsurancePolicy;
import org.iii.module.claim.register.entity.InsurancePolicyItem;
import org.iii.module.claim.register.entity.Register;
import org.iii.module.claim.register.service.InsurancePolicyItemService;
import org.iii.module.claim.register.service.InsurancePolicyService;
import org.iii.module.claim.register.service.RegisterService;
import org.iii.module.setting.setAccidcir.entity.SetAccidcir;
import org.iii.module.setting.setAccidcir.service.SetAccidcirService;
import org.iii.module.setting.setOffice.entity.SetOffice;
import org.iii.module.setting.setOffice.service.SetOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 報案 webAction
 * 
 * @author Mark Huang
 * @version 2014/5/1
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RegisterWebAction extends GenericWebAction<Register> {
	
	// Office
	@Autowired
	private SetOffice office;
	@Autowired
	private SetOfficeService officeService;
	@Autowired
	private DataSet<SetOffice> dsOffice;
	
	// SetAccidcir
	@Autowired
	private SetAccidcir accidcir;
	@Autowired
	private SetAccidcirService accidcirService;
	@Autowired
	private DataSet<SetAccidcir> dsAccidcir;
	
	// InsurancePolicy
	@Autowired
	private InsurancePolicy insurancePolicy;
	@Autowired
	private InsurancePolicyService insurancePolicyService;
	@Autowired
	private DataSet<InsurancePolicy> dsInsurancePolicy;
	
	// InsurancePolicyItem
	@Autowired
	private InsurancePolicyItem insurancePolicyItem;
	@Autowired
	private InsurancePolicyItemService insurancePolicyItemService;
	@Autowired
	private DataSet<InsurancePolicyItem> dsInsurancePolicyItem;
	
	// User
	@Autowired
	private SecUser user;
	@Autowired
	private SecUserService userService;
	@Autowired
	private DataSet<SecUser> dsUser;
	
	private List<SecUser> userList;
	private Map<String,Object> dataMap;
	
	@Autowired
	private RegisterService registerService;
	
	private String plate;
	
	private String policyCode;
	
	
	public String findUserAjax() throws Exception {
		try {
			user.setUserType(UserType.PROCESS);
			dsUser.setEntity(user);
			dsUser = userService.getByRestrictions(dsUser);
			userList=dsUser.getResults();
			dataMap=new HashMap<String,Object>();  
			dataMap.put("data", userList);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return SUCCESS;
	}
	
	public String findClaimStaffAjax() throws Exception {
		try {
			user.setUserType(UserType.CALIMSTAFF);
			dsUser.setEntity(user);
			dsUser = userService.getByRestrictions(dsUser);
			userList=dsUser.getResults();
			dataMap=new HashMap<String,Object>();  
			dataMap.put("data", userList);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return SUCCESS;
	}
	
	public String findLawStaffAjax() throws Exception {
		try {
			user.setUserType(UserType.LAWSTAFF);
			dsUser.setEntity(user);
			dsUser = userService.getByRestrictions(dsUser);
			userList=dsUser.getResults();
			dataMap=new HashMap<String,Object>();  
			dataMap.put("data", userList);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return SUCCESS;
	}
	
	public String findAccidcirAjax() throws Exception {
		try {
			dsAccidcir.setEntity(accidcir);
			dsAccidcir = accidcirService.getByRestrictions(dsAccidcir);
			List<SetAccidcir> accidcirList=dsAccidcir.getResults();
			dataMap=new HashMap<String,Object>();  
			dataMap.put("data", accidcirList);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return SUCCESS;
	}
	
	public String findPlateAjax() throws Exception {
		try {
			if(StringUtils.isNotEmpty(plate)){
				insurancePolicy.setPlate(plate);
			}
			dsInsurancePolicy.setEntity(insurancePolicy);
			dsInsurancePolicy = insurancePolicyService.getByRestrictions(dsInsurancePolicy);
			List<InsurancePolicy> insurancePolicyList=dsInsurancePolicy.getResults();
			dataMap=new HashMap<String,Object>();  
			dataMap.put("data", insurancePolicyList);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return SUCCESS;
	}
	
	
	public String findPlateNoSameAjax() throws Exception {
		try {
			List<InsurancePolicy> insurancePolicyList = insurancePolicyService.getByRestrictionsNoSamePlate(insurancePolicy);
			dataMap=new HashMap<String,Object>();  			
			dataMap.put("data", insurancePolicyList);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return SUCCESS;
	}
	
	public String findPolicyItemAjax() throws Exception {
		try {
			if(StringUtils.isNotEmpty(plate)){
				insurancePolicyItem.setPlate(plate);
			}
			if(StringUtils.isNotEmpty(policyCode)){
				insurancePolicyItem.setCode(policyCode);;
			}
			dsInsurancePolicyItem.setEntity(insurancePolicyItem);
			dsInsurancePolicyItem = insurancePolicyItemService.getByRestrictions(dsInsurancePolicyItem);
			List<InsurancePolicyItem> insurancePolicyItemList=dsInsurancePolicyItem.getResults();
			dataMap=new HashMap<String,Object>();  
			dataMap.put("data", insurancePolicyItemList);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return SUCCESS;
	}
	
	public String findPolicyByPlateAjax() throws Exception {
		try {
			if(StringUtils.isNotEmpty(policyCode)){
				insurancePolicy.setCode(policyCode);;
			}
			dsInsurancePolicy.setEntity(insurancePolicy);
			dsInsurancePolicy = insurancePolicyService.getByRestrictions(dsInsurancePolicy);
			List<InsurancePolicy> insurancePolicyList=dsInsurancePolicy.getResults();
			dataMap=new HashMap<String,Object>();  
			dataMap.put("data", insurancePolicyList);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return SUCCESS;
	}
	
	
	
	// Register保單資料
	public String policy() throws Exception {
		try {
			if (getEntity().getPlate() != null) {
				setPlate(getEntity().getPlate());
			} 
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return "policy";
	}
	
	//通訊錄
	@Autowired
	private Contact contact;
	@Autowired
	private ContactService contactService;
	@Autowired
	private DataSet<Contact> dsContact;
	
	public void saveContact() throws Exception{
		
	}
	

	// Register轉檔 to case
	public String convert() throws Exception {
		try {
			if (getEntity().getId() != null) {
				Register register = registerService
						.getById(getEntity().getId());
				System.out.println(getEntity().getId());
				System.out.println(register.getOfficeId());
				register.setOffice(officeService.getById(register.getOfficeId()));
				setEntity(register);
				
				
			} else {
				addActionMessage("Error");
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return "convert";
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getPolicyCode() {
		return policyCode;
	}

	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}

	

}
