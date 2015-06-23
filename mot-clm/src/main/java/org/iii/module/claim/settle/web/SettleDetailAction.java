package org.iii.module.claim.settle.web;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.claim.cases.entity.Case;
import org.iii.module.claim.cases.service.CaseService;
import org.iii.module.claim.estimate.entity.Estimate;
import org.iii.module.claim.estimate.service.EstimateService;
import org.iii.module.claim.estimateDetail.entity.EstimateDetail;
import org.iii.module.claim.estimateDetail.service.EstimateDetailService;
import org.iii.module.claim.settle.entity.SettleDetail;
import org.iii.module.claim.settle.service.SettleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 理算簽結 Action
 * 
 * @author Gina Chen
 * @version 2014/5/13
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SettleDetailAction extends GenericCRUDAction<SettleDetail> {

	@Autowired
	private SettleDetailService settleDetailService;
	
	// 賠案
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
	
	private String checkbox;
	private String checkbox2;
	private List<String> money;
	private List<SettleDetail> liSettleDetail;
	
	
	@Autowired
	private EstimateDetail estimateDetail;
	@Autowired
	private EstimateDetailService estimateDetailService;
	@Autowired
	private DataSet<EstimateDetail> dsEstimateDetail;	
	
	public void findEstimateDetail(String caseCode) throws Exception{
		estimateDetail.setCaseCode(caseCode);
		dsEstimateDetail.setEntity(estimateDetail);
		dsEstimateDetail=estimateDetailService.getByRestrictions(dsEstimateDetail);
	}

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
	
			
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return EDIT;
	}

	@Override
	public String list() throws Exception {
		try {
			DataSet<SettleDetail> ds = settleDetailService.getByRestrictions(initDataSet());
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
			SettleDetail entity = getEntity();
			settleDetailService.save(entity, getLoginUser());		
			setEntity(entity);
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
			SettleDetail entity = getEntity();
			settleDetailService.update(entity, getLoginUser());
			setEntity(entity);
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
			settleDetailService.deleteById(getEntity().getId());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		setEntity(null);
		disableAllInput();
		addActionMessage("檔案已刪除");
		return DELETE;
	}

	public DataSet<EstimateDetail> getDsEstimateDetail() {
		return dsEstimateDetail;
	}

	public void setDsEstimateDetail(DataSet<EstimateDetail> dsEstimateDetail) {
		this.dsEstimateDetail = dsEstimateDetail;
	}

	public String getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(String checkbox) {
		this.checkbox = checkbox;
	}

	public String getCheckbox2() {
		return checkbox2;
	}

	public void setCheckbox2(String checkbox2) {
		this.checkbox2 = checkbox2;
	}

	public List<String> getMoney() {
		return this.money;
	}

	public void setMoney(List<String> money) {
		this.money = money;
	}

	public List<SettleDetail> getLiSettleDetail() {
		return liSettleDetail;
	}

	public void setLiSettleDetail(List<SettleDetail> liSettleDetail) {
		this.liSettleDetail = liSettleDetail;
	}

	
	

}
