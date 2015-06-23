package org.iii.module.claim.settle.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.enums.AuditState;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.claim.cases.entity.Case;
import org.iii.module.claim.cases.service.CaseService;
import org.iii.module.claim.estimate.entity.Estimate;
import org.iii.module.claim.estimate.service.EstimateService;
import org.iii.module.claim.estimateDetail.entity.EstimateDetail;
import org.iii.module.claim.estimateDetail.service.EstimateDetailService;
import org.iii.module.claim.payments.entity.Payments;
import org.iii.module.claim.payments.service.PaymentsService;
import org.iii.module.claim.remark.entity.Remark;
import org.iii.module.claim.remark.enums.RemarkProcessStatus;
import org.iii.module.claim.remark.service.RemarkService;
import org.iii.module.claim.settle.entity.Settle;
import org.iii.module.claim.settle.entity.SettleDetail;
import org.iii.module.claim.settle.service.SettleDetailService;
import org.iii.module.claim.settle.service.SettleService;
import org.joda.time.LocalDateTime;
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
public class SettleAction extends GenericCRUDAction<Settle> {

	@Autowired
	private SettleService settleService;

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

	private List<SettleDetail> liSettleDetail;

	private String check;
	
	//賠付資料
	@Autowired
	private Payments payments;
	@Autowired
	private PaymentsService paymentslService;
	@Autowired
	private DataSet<Payments> dsPayments;
	
	public void findPayments(String caseCode) throws Exception{
		System.out.println("caseCode=="+caseCode);
		payments.setCaseCode(caseCode);
		dsPayments.setEntity(payments);
		dsPayments=paymentslService.getByRestrictions(dsPayments);
		System.out.println("dsPayments.result.size=="+dsPayments.getResults().size());
	}
	
	//已決賠款
	@Autowired
	private SettleDetail settleDetail;
	@Autowired
	private SettleDetailService settleDetailService;
	@Autowired
	private DataSet<SettleDetail> dsSettleDetail;

	public void findSettleDetail(DataSet<EstimateDetail> dsEstimateDetail)
			throws Exception {
		if (dsEstimateDetail != null && dsEstimateDetail.getResults() != null
				&& dsEstimateDetail.getResults().size() != 0) {
			List<EstimateDetail> li = dsEstimateDetail.getResults();
			liSettleDetail = new ArrayList<>();
			List<Long> li2 = new ArrayList<>();
			for (EstimateDetail ed : li) {
				if (li2.contains(ed.getId())) {
				} else {
					li2.add(ed.getId());
				}
			}
			if (li2 != null) {
				for (Long id : li2) {
					if (id != 0L) {
						settleDetail.setEstimateDetailId(id);
						dsSettleDetail.setEntity(settleDetail);
						dsSettleDetail = settleDetailService
								.getByRestrictions(dsSettleDetail);
						liSettleDetail.addAll(dsSettleDetail.getResults());
					}
				}
			}
		}
	}

	public void saveSettleDetail(List<SettleDetail> liSettleDetail)
			throws Exception {
		if (liSettleDetail != null) {
			if (liSettleDetail.size() > 0) {
				for (int i = 0; i < liSettleDetail.size(); i++) {
					settleDetailService.save(liSettleDetail.get(i),
							getLoginUser());
				}
			}
		}
	}

	public void updateSettleDetail(List<SettleDetail> liSettleDetail)
			throws Exception {
		SettleDetail settleDetail;
		if (liSettleDetail != null) {
			if (liSettleDetail.size() > 0) {
				for (int i = 0; i < liSettleDetail.size(); i++) {
					settleDetail = liSettleDetail.get(i);
					if (settleDetail.getId() == null
							|| settleDetail.getId() == 0L) {
						settleDetailService.save(settleDetail, getLoginUser());
					} else {
						settleDetailService
								.update(settleDetail, getLoginUser(),"payId");
					}
				}
			}
		}
	}

	@Autowired
	private EstimateDetail estimateDetail;
	@Autowired
	private EstimateDetailService estimateDetailService;
	@Autowired
	private DataSet<EstimateDetail> dsEstimateDetail;

	public void findEstimateDetail(String caseCode) throws Exception {
		estimateDetail.setCaseCode(caseCode);
		dsEstimateDetail.setEntity(estimateDetail);
		dsEstimateDetail = estimateDetailService
				.getByRestrictions(dsEstimateDetail);
	}

	// 處理記錄
	@Autowired
	private Remark remark;
	@Autowired
	private RemarkService remarkService;
	@Autowired
	private DataSet<Remark> dsRemark;

	private void saveRemark(Case cases) throws Exception {

		if (getEntity().getId() != null) {

			remark = remarkService.getById(getEntity().getId());
			dsRemark.setEntity(remark);
			dsRemark = remarkService.getByRestrictions(dsRemark);
			remark.setCaseCode(getEntity().getCaseCode());
			remark.setPostDate(getEntity().getCreatedDate());
			remark.setRemarkProcessStatus(RemarkProcessStatus.SIGNED);
			remarkService.save(remark, getLoginUser());

		}
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
			Settle entity = getEntity();
			if (entity.getCaseCode() != null && entity.getCaseCode() != "") {
				DataSet<Settle> ds = initDataSet();
				ds.setEntity(entity);
				ds = settleService.getByRestrictions(ds);

				if (ds.getResults().size() == 0) {
					cases.setCaseCode(entity.getCaseCode());
					dsCase.setEntity(cases);
					dsCase = caseService.getByRestrictions(dsCase);
					cases = dsCase.getResults().get(0);

					entity.setCaseCode(cases.getCaseCode());
					entity.setAccidcirDate(cases.getAccidcirDate());
					entity.setInsuredId(cases.getInsuredId());
					entity.setInsuredName(cases.getInsuredName());
					entity.setPlate(cases.getPlate());
					entity.setPolicyCode(cases.getPolicyCode());

					estimate.setCaseCode(entity.getCaseCode());
					dsEstimate.setEntity(estimate);
					dsEstimate = estimateService.getByRestrictions(dsEstimate);
					estimate = dsEstimate.getResults().get(0);

					entity.setCurrency(estimate.getCurrency());
					entity.setSettledAmountCur(estimate.getCurrency());
					entity.setApplyBout(estimate.getApplyBout());
					entity.setAccident(estimate.getAccident());
					entity.setTotalAmountCur(estimate.getCurrency());
					entity.setTotalAmount(estimate.getTotalEstimatedAmount());
				} else {
					entity = ds.getResults().get(0);
					setEntity(entity);
					System.out.println(entity.getAuditState());
					if (entity.getAuditState() != null
							&& (entity.getAuditState().equals(
									AuditState.PENDING) || entity
									.getAuditState().equals(AuditState.SIGNOFF))) {
						disableAllInput();
						check = "true";
					}
				}
				findEstimateDetail(entity.getCaseCode());
				findPayments(entity.getCaseCode());
				findSettleDetail(dsEstimateDetail);
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
			DataSet<Settle> ds = settleService.getByRestrictions(initDataSet());
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
			Settle settle = getEntity();
			settle.setSettledDate(LocalDateTime.now());
			settleService.save(settle, getLoginUser());
			setEntity(settle);
			findEstimateDetail(settle.getCaseCode());
			findPayments(settle.getCaseCode());
			saveSettleDetail(liSettleDetail);
			saveRemark(cases);
			findSettleDetail(dsEstimateDetail);
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
			Settle settle = getEntity();
			settle.setSettledDate(LocalDateTime.now());
			settleService.update(settle, getLoginUser());
			setEntity(settle);
			findEstimateDetail(settle.getCaseCode());
			findPayments(settle.getCaseCode());
			updateSettleDetail(liSettleDetail);
			findSettleDetail(dsEstimateDetail);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		addActionMessage("檔案已修改");
		return EDIT;
	}

	public String confirmUpdate() throws Exception {
		try {
			Settle settle = getEntity();
			System.out.println("ID===" + settle.getId());
			settle.setAuditState(AuditState.PENDING);
			settleService.update(settle, getLoginUser());
			setEntity(settle);
			findEstimateDetail(settle.getCaseCode());
			findPayments(settle.getCaseCode());
			updateSettleDetail(liSettleDetail);
			findSettleDetail(dsEstimateDetail);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		addActionMessage("檔案已送審");
		check = "true";
		disableAllInput();
		return EDIT;
	}

	public String cancelUpdate() throws Exception {
		try {
			Settle settle = getEntity();
			if (settle.getId() != null && settle.getId() != 0L) {
				settle = settleService.getById(settle.getId());
			}
			settle.setAuditState(AuditState.PROCESS);
			settleService.update(settle, getLoginUser());
			setEntity(settle);
			findEstimateDetail(settle.getCaseCode());
			findPayments(settle.getCaseCode());
			findSettleDetail(dsEstimateDetail);

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		addActionMessage("送審已取消");
		return EDIT;
	}

	@Override
	public String delete() throws Exception {
		try {
			settleService.deleteById(getEntity().getId());
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

	public List<SettleDetail> getLiSettleDetail() {
		return liSettleDetail;
	}

	public void setLiSettleDetail(List<SettleDetail> liSettleDetail) {
		this.liSettleDetail = liSettleDetail;
	}

	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getCheckbox2() {
		return checkbox2;
	}

	public void setCheckbox2(String checkbox2) {
		this.checkbox2 = checkbox2;
	}

	public DataSet<Payments> getDsPayments() {
		return dsPayments;
	}

	public void setDsPayments(DataSet<Payments> dsPayments) {
		this.dsPayments = dsPayments;
	}
	
	

}
