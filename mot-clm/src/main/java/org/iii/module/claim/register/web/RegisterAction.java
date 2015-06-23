package org.iii.module.claim.register.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.security.secUser.entity.SecUser;
import org.iii.core.security.secUser.service.SecUserService;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.claim.history.entity.History;
import org.iii.module.claim.history.service.HistoryService;
import org.iii.module.claim.register.entity.Register;
import org.iii.module.claim.register.service.RegisterService;
import org.iii.module.claim.remark.entity.Remark;
import org.iii.module.claim.remark.enums.RemarkProcessStatus;
import org.iii.module.claim.remark.service.RemarkService;
import org.iii.module.setting.setAccidcir.entity.SetAccidcir;
import org.iii.module.setting.setAccidcir.service.SetAccidcirService;
import org.iii.module.setting.setCarType.entity.SetCarType;
import org.iii.module.setting.setCarType.service.SetCarTypeService;
import org.iii.module.setting.setEvent.entity.SetEvent;
import org.iii.module.setting.setEvent.service.SetEventService;
import org.iii.module.setting.setOffice.entity.SetOffice;
import org.iii.module.setting.setOffice.service.SetOfficeService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 * 報案 action
 * 
 * @author Mark Huang
 * @version 2014/4/7
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RegisterAction extends GenericCRUDAction<Register> {

	@Autowired
	private RegisterService registerService;

	// Office
	@Autowired
	private SetOffice office;
	@Autowired
	private SetOfficeService officeService;
	@Autowired
	private DataSet<SetOffice> dsOffice;

	// 出險型態
	@Autowired
	private SetAccidcir accidcir;
	@Autowired
	private SetAccidcirService accidcirService;
	@Autowired
	private DataSet<SetAccidcir> dsAccidcir;

	// 事件
	@Autowired
	private SetEvent event;
	@Autowired
	private SetEventService eventService;
	@Autowired
	private DataSet<SetEvent> dsEvent;

	// 車種
	@Autowired
	private SetCarType carType;
	@Autowired
	private SetCarTypeService carTypeService;
	@Autowired
	private DataSet<SetCarType> dsCarType;

	// 備註
	@Autowired
	private History history;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private DataSet<History> dsHistory;

	// User
	@Autowired
	private SecUser user;
	@Autowired
	private SecUser user2;
	@Autowired
	private SecUserService userService;
	@Autowired
	private DataSet<SecUser> dsUser;

	// 處理記錄
	@Autowired
	private Remark remark;
	@Autowired
	private RemarkService remarkService;
	@Autowired
	private DataSet<Remark> dsRemark;

	private void saveRemark(Register register) throws Exception {

		//Register
		remark.setRegCode(register.getCode());
		dsRemark.setEntity(remark);
		dsRemark = remarkService.getByRestrictions(dsRemark);
		remark.setCaseCode(register.getCaseCode());
		remark.setInsuredId(register.getInsuredId());
		remark.setInsuredName(register.getInsuredName());
		remark.setAccidcirDate(register.getAccidcirDate());
		remark.setMainReason(register.getMainAccident());
		remark.setAcceptedDate(register.getAcceptedDate());
		remark.setRemarkProcessStatus(RemarkProcessStatus.RECEIVE);
		remarkService.save(remark, getLoginUser());
		
		
	}

	// accidcirType 取得checkbox資料
	private String[] accidcirType;

	// 取得 select 資料
	private String selectKey;
	private String selectValue;
	private LocalDateTime accidcirDateMin;
	private LocalDateTime accidcirDateMax;
	private LocalDateTime acceptedDateMin;
	private LocalDateTime acceptedDateMax;

	// 接 是否同被保險人地址
	private boolean driverAddressBoolean;
	
	private String registerTable_length;

	@Override
	public void validateSave() throws Exception {		
		if (StringUtils.isEmpty(getEntity().getPlate())) {
			addActionError("車牌號碼");
		}
		if (StringUtils.isEmpty(getEntity().getMainAccident())) {
			addActionError("主要事故原因");
		}
		if (StringUtils.isEmpty(getEntity().getPolicyCode())) {
			addActionError("保單號碼");
		}
		if (getEntity()!=null && getEntity().getApplyDate()==null) {
			addActionError("申請日期");
		}
		if (StringUtils.isEmpty(getEntity().getApplicant())) {
			addActionError("申請人");
		}
		if (StringUtils.isEmpty(getEntity().getProcessUserCode())) {
			addActionError("受理經辦");
		}
		if (StringUtils.isEmpty(getEntity().getProxyProcessUserCode())) {
			addActionError("代辦受理經辦");
		}
		createDsCarType();
		createDsOffice();
		createDsAccidcir();
		createDsEvent();
	}

	@Override
	public void validateUpdate() throws Exception {
		if (StringUtils.isEmpty(getEntity().getPlate())) {
			addActionError("車牌號碼");
		}
		if (StringUtils.isEmpty(getEntity().getMainAccident())) {
			addActionError("主要事故原因");
		}
		if (StringUtils.isEmpty(getEntity().getPolicyCode())) {
			addActionError("保單號碼");
		}
		if (getEntity()!=null && getEntity().getApplyDate()==null) {
			addActionError("申請日期");
		}
		if (StringUtils.isEmpty(getEntity().getApplicant())) {
			addActionError("申請人");
		}
		if (StringUtils.isEmpty(getEntity().getProcessUserCode())) {
			addActionError("受理經辦");
		}
		if (StringUtils.isEmpty(getEntity().getProxyProcessUserCode())) {
			addActionError("代辦受理經辦");
		}
		createDsCarType();
		createDsOffice();
		createDsAccidcir();
		createDsEvent();
	}

	@Override
	public void validateDelete() throws Exception {
	}

	@Override
	public String query() throws Exception {
		try {
			if (getEntity().getId() != null) {
				Register register = registerService
						.getById(getEntity().getId());
				setAccidcirType(accidcirTypeConvertToList(register
						.getAccidcirLocalName()));
				createUserData(register);				
				createProxyUserData(register);				
				setEntity(register);
			} else {
				Register register = getEntity();
				register.setAcceptedDate(LocalDateTime.now());
				register.setCode(createCode());
			}
			createDsCarType();
			createDsOffice();
			createDsAccidcir();
			createDsEvent();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return EDIT;
	}

	@Override
	public String list() throws Exception {
		try {
			DataSet<Register> ds = initDataSet();
			Map<String, Object> datas = ds.getDatas();
			Register entity = getEntity();
			if (selectKey != null) {
				if (selectKey.equals("entity.code")) {
					entity.setCode(selectValue);
				} else if (selectKey.equals("entity.insuredId")) {
					entity.setInsuredId(selectValue);
				} else if (selectKey.equals("entity.policyCode")) {
					entity.setPolicyCode(selectValue);
				} else if (selectKey.equals("entity.plate")) {
					entity.setPlate(selectValue);
				} else if (selectKey.equals("entity.driver")) {
					entity.setDriver(selectValue);
				} else if (selectKey.equals("entity.caseCode")) {
					entity.setCaseCode(selectValue);
				} else if (selectKey.equals("entity.accidentPeopleId")) {
					entity.setAccidentPeopleId(selectValue);
				}
			}
			if (accidcirType != null) {
				if (accidcirTypeConvertToString() != null) {
					entity.setAccidcirLocalName(accidcirTypeConvertToString());
				}
			}
			if (accidcirDateMin != null) {
				datas.put("accidcirDateMin", accidcirDateMin);
			}
			if (accidcirDateMax != null) {
				datas.put("accidcirDateMax", accidcirDateMax);
			}
			if (acceptedDateMin != null) {
				datas.put("acceptedDateMin", acceptedDateMin);
			}
			if (acceptedDateMax != null) {
				datas.put("acceptedDateMax", acceptedDateMax);
			}
			ds.setDatas(datas);
			ds.setEntity(entity);
			ds = registerService.getByRestrictions(ds);
			setDs(ds);
			createDsAccidcir();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return LIST;
	}

	@Override
	public String save() throws Exception {
		try {
			Register register = getEntity();
			if (driverAddressBoolean) {
				register.setDriverPostal(register.getInsuredPostal());
				register.setDriverAddress(register.getInsuredAddress());
			}
			register.setAccidcirLocalName(accidcirTypeConvertToString());
			register = registerService.save(register, getLoginUser());
			createUserData(register);
			createProxyUserData(register);
			setEntity(register);		
			saveRemark(register);
			
			createDsOffice();
			createDsAccidcir();
			createDsEvent();
			createDsCarType();
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
			Register register = getEntity();
			if (driverAddressBoolean) {
				register.setDriverPostal(register.getInsuredPostal());
				register.setDriverAddress(register.getInsuredAddress());
			}
			register.setAccidcirLocalName(accidcirTypeConvertToString());
			register = registerService.update(register, getLoginUser());
			createUserData(register);
			createProxyUserData(register);
			setEntity(register);
			createHistory(register);

			createDsOffice();
			createDsAccidcir();
			createDsEvent();
			createDsCarType();
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
			registerService.deleteById(getEntity().getId());
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		setEntity(null);
		disableAllInput();
		addActionMessage("檔案已刪除");
		return DELETE;
	}

	/**
	 * 取得DsOffice
	 * 
	 * @throws Exception
	 */
	public void createDsOffice() {
		try {
			dsOffice.setEntity(office);
			dsOffice = officeService.getByRestrictions(dsOffice);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得DsAccidcir
	 * 
	 * @throws Exception
	 */
	public void createDsAccidcir() {
		try {
			dsAccidcir.setEntity(accidcir);
			dsAccidcir = accidcirService.getByRestrictions(dsAccidcir);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得DsEvent
	 * 
	 * @throws Exception
	 */
	public void createDsEvent() {
		try {
			dsEvent.setEntity(event);
			dsEvent = eventService.getByRestrictions(dsEvent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得DsCarType
	 * 
	 * @throws Exception
	 */
	public void createDsCarType() {
		try {
			dsCarType.setEntity(carType);
			dsCarType = carTypeService.getByRestrictions(dsCarType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 存入紀錄
	 * 
	 * @throws Exception
	 */
	public void createHistory(Register register) {
		try {
			history.setCode(register.getCode());
			dsHistory.setEntity(history);
			dsHistory = historyService.getByRestrictions(dsHistory);
			history.setNumber(dsHistory.getResults().size() + 1);
			history.setModifyDate(LocalDateTime.now());
			history.setAccidentStatus(register.getAccidentStatus());
			history.setAccidentSecStatus(register.getAccidentSecStatus());
			history.setRemarkProcessStatus(RemarkProcessStatus.RECEIVE);
			history.setPerson(getLoginUser().getUserCode());
			historyService.save(history, getLoginUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得USER明細
	 * 
	 * @throws Exception
	 */
	public void createUserData(Register register) {
		try {
			if (StringUtils.isNotEmpty(register.getProcessUserCode())) {	
				user.setUserCode(register.getProcessUserCode());
				dsUser.setEntity(user);
				dsUser = userService.getByRestrictions(dsUser);
				user = dsUser.getResults().get(0);
				if (user.getWorkOffice() != null) {
					user.setUserName(user.getUserName() + "/"
							+ user.getWorkOffice());
				} else {
					user.setUserName(user.getUserName() + "/");
				}
				register.setProcessUser(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 取得USER明細
	 * 
	 * @throws Exception
	 */
	public void createProxyUserData(Register register) {
		try {
			if (StringUtils.isNotEmpty(register.getProxyProcessUserCode())) {
				user2.setUserCode(register.getProxyProcessUserCode());
				dsUser.setEntity(user2);
				dsUser = userService.getByRestrictions(dsUser);
				user2 = dsUser.getResults().get(0);
				if (user2.getWorkOffice() != null) {
					user2.setUserName(user2.getUserName() + "/"
							+ user2.getWorkOffice());
				} else {
					user2.setUserName(user2.getUserName() + "/");
				}
				register.setProxyProcessUser(user2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 產生報案編號
	 * 
	 * @throws Exception
	 */
	public String createCode() throws Exception {
		java.util.Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String code = sdf.format(today);
		code = code.substring(2);
		Register entity = getEntity();
		entity.setCode(code);
		DataSet<Register> ds = getDs();
		ds.setEntity(entity);
		ds = registerService.getCodeByRestrictions(ds);
		List<Register> result = ds.getResults();
		if (result.size() == 0) {
			code = code + "0001";
		} else {
			code = result.get((result.size() - 1)).getCode();
			int temp = Integer.parseInt(code.substring(6));
			code = code.substring(0, 6);
			if ((temp + 1) / 1000 < 1) {
				code = code + "0";
				if ((temp + 1) / 100 < 1) {
					code = code + "0";
					if ((temp + 1) / 10 < 1) {
						code = code + "0";
					}
				}
			}
			code = code + (temp + 1);
		}
		return code;
	}

	/** 出險型態 從checkbox接值成String[] 轉換成String */
	public String accidcirTypeConvertToString() {
		String result = "";
		int num = 0;
		int count = accidcirType.length;
		for (String s : accidcirType) {
			num = num + 1;
			if (num == count) {
				result = result + s;
			} else {
				result = result + s + ",";
			}
		}
		return result;
	}

	/** 出險型態 從資料庫接值String轉換成String[] */
	public String[] accidcirTypeConvertToList(String temp) {
		String[] result = temp.split(",");
		return result;
	}

	public DataSet<SetOffice> getDsOffice() {
		return dsOffice;
	}

	public void setDsOffice(DataSet<SetOffice> dsOffice) {
		this.dsOffice = dsOffice;
	}

	public DataSet<SetAccidcir> getDsAccidcir() {
		return dsAccidcir;
	}

	public void setDsAccidcir(DataSet<SetAccidcir> dsAccidcir) {
		this.dsAccidcir = dsAccidcir;
	}

	public void setDsEvent(DataSet<SetEvent> dsEvent) {
		this.dsEvent = dsEvent;
	}

	public DataSet<SetEvent> getDsEvent() {
		return dsEvent;
	}

	public DataSet<SetCarType> getDsCarType() {
		return dsCarType;
	}

	public void setDsCarType(DataSet<SetCarType> dsCarType) {
		this.dsCarType = dsCarType;
	}

	public String[] getAccidcirType() {
		return accidcirType;
	}

	public void setAccidcirType(String[] accidcirType) {
		this.accidcirType = accidcirType;
	}

	public String getSelectKey() {
		return selectKey;
	}

	public void setSelectKey(String selectKey) {
		this.selectKey = selectKey;
	}

	public String getSelectValue() {
		return selectValue;
	}

	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}

	public LocalDateTime getAccidcirDateMin() {
		return accidcirDateMin;
	}

	public void setAccidcirDateMin(LocalDateTime accidcirDateMin) {
		this.accidcirDateMin = accidcirDateMin;
	}

	public LocalDateTime getAccidcirDateMax() {
		return accidcirDateMax;
	}

	public void setAccidcirDateMax(LocalDateTime accidcirDateMax) {
		this.accidcirDateMax = accidcirDateMax;
	}

	public LocalDateTime getAcceptedDateMin() {
		return acceptedDateMin;
	}

	public void setAcceptedDateMin(LocalDateTime acceptedDateMin) {
		this.acceptedDateMin = acceptedDateMin;
	}

	public LocalDateTime getAcceptedDateMax() {
		return acceptedDateMax;
	}

	public void setAcceptedDateMax(LocalDateTime acceptedDateMax) {
		this.acceptedDateMax = acceptedDateMax;
	}

	public boolean isDriverAddressBoolean() {
		return driverAddressBoolean;
	}

	public void setDriverAddressBoolean(boolean driverAddressBoolean) {
		this.driverAddressBoolean = driverAddressBoolean;
	}

	public String getRegisterTable_length() {
		return registerTable_length;
	}

	public void setRegisterTable_length(String registerTable_length) {
		this.registerTable_length = registerTable_length;
	}
	
}
