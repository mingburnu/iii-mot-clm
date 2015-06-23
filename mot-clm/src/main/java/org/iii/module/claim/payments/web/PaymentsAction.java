package org.iii.module.claim.payments.web;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.iii.core.enums.AuditState;
import org.iii.core.model.DataSet;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.claim.estimate.entity.Estimate;
import org.iii.module.claim.estimate.service.EstimateService;
import org.iii.module.claim.payments.entity.Payments;
import org.iii.module.claim.payments.service.PaymentsService;
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
 * PaymentAction 賠付內容Action
 * @author Ruo Hsu
 * @version 2014/5/20
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PaymentsAction extends GenericCRUDAction<Payments> {

	@Autowired
	private PaymentsService paymentService;
	
	private String caseCode;
	
	@Autowired
	private Estimate estimate;
	
	@Autowired
	private EstimateService estimateService;
	
	@Autowired
	private DataSet<Estimate> dsEstimate;
	
	@Autowired
	private Settle settle;
	
	@Autowired
	private SettleService settleService;
	
	@Autowired
	private DataSet<Settle> dsSettle;
	
	private String num;

	private String[] numArrray;
	
	private boolean likeInsured;
	
	public void convertNum(){
		if(num!=null && num!=""){
			numArrray=num.split(",");
		}
	}
	
	//已決賠款
	@Autowired
	private SettleDetail settleDetail;
	@Autowired
	private SettleDetailService settleDetailService;
	@Autowired
	private DataSet<SettleDetail> dsSettleDetail;
	
	public void updateSettleDetail(String[] ids,String id){
		if(ids!=null && ids.length!=0)
		for(String s:ids){
			try {
					System.out.println("Long.valueOf(s)===="+Long.valueOf(s));
					settleDetail=settleDetailService.getById(Long.valueOf(s));
					settleDetail.setPayId(id);
					settleDetailService.update(settleDetail, getLoginUser());				
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
		
	}
	
	
	// 驗證金額
		public void validateMoney(){
			
			if(NumberUtils.isDigits(getEntity().getCash().toString()) == false){
				addActionError("金額必須是整數");
			}
			
			
//			if(getEntity().getMinAmount() != null || getEntity().getMaxAmount() != null){
//				try {
//					Integer.parseInt(getEntity().getMinAmount().toString());
//					Integer.parseInt(getEntity().getMaxAmount().toString());
//				} catch (NumberFormatException e) {
//					addActionError("金額必須是整數");
//				}
//			}
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
			if(caseCode!=null){
				estimate.setCaseCode(caseCode);
				dsEstimate.setEntity(estimate);
				dsEstimate=estimateService.getByRestrictions(dsEstimate);
				estimate=dsEstimate.getResults().get(0);
				
				settle.setCaseCode(caseCode);
				dsSettle.setEntity(settle);
				dsSettle=settleService.getByRestrictions(dsSettle);
				if(dsSettle.getResults().size()!=0){
					settle=dsSettle.getResults().get(0);
				}else{
					settle=null;
				}
				
			}
			Payments payment = getEntity();
			
			payment.setCaseCode(estimate.getCaseCode());
			payment.setAccidcirDate(estimate.getAccidcirDate());
			payment.setInsuredId(estimate.getInsuredId());
			payment.setInsuredName(estimate.getInsuredName());
			payment.setPolicyCode(estimate.getPolicyCode());
			payment.setPlate(estimate.getPlate());
			payment.setApplyBout(estimate.getApplyBout());
			payment.setAccident(estimate.getAccident());
			
			if(settle==null){
				payment.setAuditState(AuditState.PROCESS);			
				payment.setDecideDate(LocalDateTime.now());			
				payment.setPaymentCurrency("TWD");				
			}else{
				payment.setAuditState(settle.getAuditState());			
				payment.setDecideDate(settle.getSettledDate());			
				payment.setPaymentCurrency(settle.getSettledAmountCur());
				}
			
						
			
			setEntity(payment);

		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return EDIT;
	}

	@Override
	public String list() throws Exception {
		return null;
	}

	@Override
	public String save() throws Exception {		
		try {
			numArrray = num.split(",");			
			Payments payment = getEntity();
			DataSet<Payments> dsPayments = initDataSet();
			dsPayments.setEntity(payment);
			dsPayments=paymentService.getByRestrictions(dsPayments);
			if(dsPayments.getResults()!=null && dsPayments.getResults().size()==0){
				payment.setPayNumber("1");
			}else{
				payment.setPayNumber(""+(dsPayments.getResults().size()+1));
			}			
			System.out.println("payment.getPayNumber()=="+payment.getPayNumber());
			System.out.println("payment="+payment);
			paymentService.save(payment, getLoginUser());
			setEntity(payment);
			updateSettleDetail(numArrray,payment.getPayNumber());
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
			Payments	payment = paymentService.update(getEntity(), getLoginUser());
			setEntity(payment);
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		addActionMessage("修改成功");
		return EDIT;

	}

	@Override
	public String delete() throws Exception {
		try {
			if (getEntity().getId() != null) {
				paymentService.deleteById(getEntity().getId());
			}					
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		disableAllInput();
		
		addActionMessage("刪除成功");
		return DELETE;
	}

	public String getCaseCode() {
		return caseCode;
	}

	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}


	public boolean isLikeInsured() {
		return likeInsured;
	}


	public void setLikeInsured(boolean likeInsured) {
		this.likeInsured = likeInsured;
	}
	
	
	

}
