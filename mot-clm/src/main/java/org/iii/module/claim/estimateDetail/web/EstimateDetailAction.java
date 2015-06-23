package org.iii.module.claim.estimateDetail.web;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.security.secUser.entity.SecUser;
import org.iii.core.security.secUser.service.SecUserService;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.claim.cases.entity.Case;
import org.iii.module.claim.cases.service.CaseService;
import org.iii.module.claim.estimate.entity.Estimate;
import org.iii.module.claim.estimate.service.EstimateService;
import org.iii.module.claim.estimateDetail.entity.EstimateDetail;
import org.iii.module.claim.estimateDetail.service.EstimateDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


/**
 * 受害人預估賠款 Action
 * 
 * @author 黃小貓
 * @version 2014/5/14
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EstimateDetailAction extends GenericCRUDAction<EstimateDetail>{

	@Autowired
	private EstimateDetailService estimateDetailService;
	
	private String caseCode;

	private boolean data;
	
	// 事件
	@Autowired
	private Case cases;
	@Autowired
	private CaseService caseService;
	@Autowired
	private DataSet<Case> dsCase;
	
	// 事件
	@Autowired
	private Estimate estimate;
	@Autowired
	private EstimateService estimateService;
	@Autowired
	private DataSet<Estimate> dsEstimate;
	
	
	// User
	@Autowired
	private SecUser user;
	@Autowired
	private SecUserService userService;
	@Autowired
	private DataSet<SecUser> dsUser;
	
	@Override
	public void validateSave() throws Exception {
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
			if (caseCode!=null && caseCode!="") {
				cases.setCaseCode(caseCode);
				dsCase.setEntity(cases);
				dsCase=caseService.getByRestrictions(dsCase);
				cases=dsCase.getResults().get(0);
				EstimateDetail entity =getEntity();
				entity.setCaseCode(caseCode);
				entity.setAccidcirDate(cases.getAccidcirDate());
				entity.setInsuredId(cases.getInsuredId());
				entity.setInsuredName(cases.getInsuredName());
				entity.setPlate(cases.getPlate());
				entity.setApplyBout(1);
				entity.setAccident(cases.getMainAccident()+"."+cases.getMainAccidentName());
				
				user.setUserCode(cases.getClaimStaff());
				dsUser.setEntity(user);
				dsUser=userService.getByRestrictions(dsUser);
				user=dsUser.getResults().get(0);
				
				entity.setProcessOffice(user.getWorkOffice());
				entity.setClaimsManagers(user.getUserCode()+"/"+user.getUserName());
				entity.setInsuredAmount(40000D);
				setEntity(entity);
			}

			if(data){
				EstimateDetail entity =getEntity();
				cases.setCaseCode(entity.getCaseCode());
				dsCase.setEntity(cases);
				dsCase=caseService.getByRestrictions(dsCase);
				cases=dsCase.getResults().get(0);

				entity.setCaseCode(cases.getCaseCode());
				entity.setAccidcirDate(cases.getAccidcirDate());
				entity.setInsuredId(cases.getInsuredId());
				entity.setInsuredName(cases.getInsuredName());
				entity.setPlate(cases.getPlate());
				entity.setApplyBout(1);
				entity.setAccident(cases.getMainAccident()+"."+cases.getMainAccidentName());
				entity.setOwnerName(cases.getInsuredName());
				entity.setOwnerTelHome(cases.getInsuredTel());
				entity.setOwnerMobile(cases.getInsuredPhone());
				entity.setOwnerAddr(cases.getInsuredAddress());
				entity.setDriver(cases.getDriver());
				entity.setDriversCode(cases.getDriversCode());
				entity.setDriverTelHome(cases.getDriverTel());
				entity.setDriverMobile(cases.getDriverPhone());
				entity.setDriverAddr(cases.getDriverAddress());
				entity.setCarYear((long)cases.getCarYear());
				entity.setDisplacement(cases.getDisplacement());
				entity.setEngineCode(cases.getEngineCode());
				entity.setCarCode(cases.getForcedCode());			
				
				
				user.setUserCode(cases.getClaimStaff());
				dsUser.setEntity(user);
				dsUser=userService.getByRestrictions(dsUser);
				user=dsUser.getResults().get(0);
				
				entity.setProcessOffice(user.getWorkOffice());
				entity.setClaimsManagers(user.getUserCode()+"/"+user.getUserName());
				entity.setInsuredAmount(40000D);
				
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

			DataSet<EstimateDetail> ds = initDataSet();

			ds.setEntity(getEntity());

			ds = estimateDetailService.getByRestrictions(ds);
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
			
			EstimateDetail estimateDetail = estimateDetailService.save(getEntity(), getLoginUser());
			setEntity(estimateDetail);
		
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
			EstimateDetail estimateDetail= estimateDetailService
					.update(getEntity(), getLoginUser());
			setEntity(estimateDetail);
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
			estimateDetailService.deleteById(getEntity().getId());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		setEntity(null);
		disableAllInput();
		addActionMessage("檔案已刪除");
		return DELETE;

	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public boolean isData() {
		return data;
	}

	public void setData(boolean data) {
		this.data = data;
	}
	
	

}
