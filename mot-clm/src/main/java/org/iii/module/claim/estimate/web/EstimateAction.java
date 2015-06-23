package org.iii.module.claim.estimate.web;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.enums.Currency;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.claim.cases.entity.Case;
import org.iii.module.claim.cases.service.CaseService;
import org.iii.module.claim.estimate.entity.Estimate;
import org.iii.module.claim.estimate.service.EstimateService;
import org.iii.module.claim.estimateDetail.entity.EstimateDetail;
import org.iii.module.claim.estimateDetail.service.EstimateDetailService;
import org.iii.module.claim.remark.entity.Remark;
import org.iii.module.claim.remark.service.RemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 理算賠款Action
 * 
 * @author Rodrigo Chun-Ming Chu
 * @version 2014/4/12
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EstimateAction extends GenericCRUDAction<Estimate> {

	@Autowired
	private EstimateService estimateService;

	// 賠案
	@Autowired
	private Case cases;
	@Autowired
	private CaseService caseService;
	@Autowired
	private DataSet<Case> dsCase;

	// 賠案
	@Autowired
	private EstimateDetail estimateDetail;
	@Autowired
	private EstimateDetailService estimateDetailService;
	@Autowired
	private DataSet<EstimateDetail> dsEstimateDetail;

	private List<EstimateDetail> estimateDetailList;

	private String tempCurrency;

	private Currency[] currencyArray = Currency.values();

	private String estimatedLossAmount;

	private String correct;

	public Currency[] getCurrencyArray() {
		return currencyArray;
	}

	public void setCurrencyarray(Currency[] currencyArray) {
		this.currencyArray = currencyArray;
	}

	public int createApplyBout() throws Exception {

		Estimate entity = getEntity();
		DataSet<Estimate> ds = getDs();
		ds.setEntity(entity);
		ds = estimateService.getCaseCodeByRestrictions(ds);
		List<Estimate> result = ds.getResults();
		int applyBout = 1 + (result.size());
		System.out.println("Number" + applyBout);
		return applyBout;
	}

	// 處理記錄
	@Autowired
	private Remark remark;
	@Autowired
	private RemarkService remarkService;
	@Autowired
	private DataSet<Remark> dsRemark;

	@SuppressWarnings("static-access")
	private void saveRemark(Case cases) throws Exception {

		if (getEntity().getId() != null) {

			remark = remarkService.getById(getEntity().getId());
			dsRemark.setEntity(remark);
			dsRemark = remarkService.getByRestrictions(dsRemark);
			remark.setCaseCode(getEntity().getCaseCode());
			remark.setPostDate(getEntity().getCreatedDate());
			remark.setRemarkProcessStatus(remark.getRemarkProcessStatus().ADJUST);
			remarkService.save(remark, getLoginUser());
			

		}
	}

	@Override
	public void validateSave() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateUpdate() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void validateDelete() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public String query() throws Exception {
		try {
			Estimate entity = getEntity();
			DataSet<Estimate> ds = initDataSet();
			ds.setEntity(entity);
			ds = estimateService.getByRestrictions(ds);
			if (ds.getResults().size() == 0) {
				if (getEntity().getCaseCode() != null) {
					cases.setCaseCode(getEntity().getCaseCode());
					dsCase.setEntity(cases);
					dsCase = caseService.getByRestrictions(dsCase);
					List<Case> results = dsCase.getResults();
					if (results.size() == 1) {
						cases = results.get(0);
						entity.setId(null);
						entity.setAccidcirDate(cases.getAccidcirDate());
						entity.setInsuredId(cases.getInsuredId());
						entity.setInsuredName(cases.getInsuredName());
						entity.setPlate(cases.getPlate());
						entity.setAccident(cases.getMainAccident() + "."
								+ cases.getMainAccidentName());
						entity.setPolicyCode(cases.getPolicyCode());
						entity.setApplyBout(1);
					}
				}
			} else {
				entity = ds.getResults().get(0);
				setEntity(entity);
				if (entity.getEstimatedCheck()) {
					disableAllInput();
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
			DataSet<Estimate> ds = estimateService
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
			Estimate estimate = getEntity();
			estimate = estimateService.save(estimate, getLoginUser());
			setEntity(estimate);
			saveRemark(cases);
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
			Estimate estimate = getEntity();
			if (correct != null && correct.equals("true")) {
				if (estimate.getId() != null) {
					estimate = estimateService.getById(estimate.getId());
				}
				estimate.setEstimatedCheck(true);
				estimate = estimateService.update(estimate, getLoginUser());
				setEntity(estimate);
				disableAllInput();
			} else if (correct != null && correct.equals("false")) {
				if (estimate.getId() != null) {
					estimate = estimateService.getById(estimate.getId());
				}
				estimate.setEstimatedCheck(false);
				estimate = estimateService.update(estimate, getLoginUser());
				setEntity(estimate);
			} else {
				estimate = estimateService.update(getEntity(), getLoginUser());
				setEntity(estimate);
			}
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
			estimateService.deleteById(getEntity().getId());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		setEntity(null);
		disableAllInput();
		addActionMessage("檔案已刪除");
		return DELETE;
	}

	public List<EstimateDetail> getEstimateDetailList() {
		return estimateDetailList;
	}

	public void setEstimateDetailList(List<EstimateDetail> estimateDetailList) {
		this.estimateDetailList = estimateDetailList;
	}

	public DataSet<EstimateDetail> getDsEstimateDetail() {
		return dsEstimateDetail;
	}

	public void setDsEstimateDetail(DataSet<EstimateDetail> dsEstimateDetail) {
		this.dsEstimateDetail = dsEstimateDetail;
	}

	public String getTempCurrency() {
		return tempCurrency;
	}

	public void setTempCurrency(String tempCurrency) {
		this.tempCurrency = tempCurrency;
	}

	public String getEstimatedLossAmount() {
		return estimatedLossAmount;
	}

	public void setEstimatedLossAmount(String estimatedLossAmount) {
		this.estimatedLossAmount = estimatedLossAmount;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

}
