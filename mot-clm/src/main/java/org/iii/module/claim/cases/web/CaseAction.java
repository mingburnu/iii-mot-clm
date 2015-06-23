package org.iii.module.claim.cases.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.iii.core.model.DataSet;
import org.iii.core.security.secUser.entity.SecUser;
import org.iii.core.security.secUser.enums.UserType;
import org.iii.core.security.secUser.service.SecUserService;
import org.iii.core.web.GenericCRUDAction;
import org.iii.module.claim.cases.entity.Case;
import org.iii.module.claim.cases.service.CaseService;
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
 * 賠案 Action
 * 
 * @author Rodrigo Chun-Ming Chu
 * @version 2014/4/12
 */
@Controller
@SuppressWarnings("serial")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CaseAction extends GenericCRUDAction<Case> {

	@Autowired
	private Case cases;

	@Autowired
	private CaseService caseService;

	private String regstId;

	public String getRegstId() {
		return regstId;
	}

	public void setRegstId(String regstId) {
		this.regstId = regstId;
	}

	// 事件
	@Autowired
	private Register register;
	@Autowired
	private RegisterService registerService;
	@Autowired
	private DataSet<Register> dsRegister;

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

	// User
	@Autowired
	private SecUser user;
	@Autowired
	private SecUser user2;
	@Autowired
	private SecUserService userService;
	@Autowired
	private DataSet<SecUser> dsUser;

	// accidcirType 取得checkbox資料
	private String[] accidcirType;

	// 理賠人員系統指派 true or false
	private boolean claimStaffRandom;

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
		if (getEntity() != null && getEntity().getApplyDate() == null) {
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
		if (StringUtils.isEmpty(getEntity().getLawStaff())) {
			addActionError("法務人員");
		}
		if (StringUtils.isEmpty(getEntity().getProxyLawStaff())) {
			addActionError("代辦法務人員");
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
			if (getEntity().getCaseCode() != null) {
				System.out.println("getEntity().getCaseCode() != null");
				cases.setCaseCode(getEntity().getCaseCode());
				DataSet<Case> ds = initDataSet();
				ds.setEntity(cases);
				ds = caseService.getByRestrictions(ds);
				cases = ds.getResults().get(0);
				setAccidcirType(accidcirTypeConvertToList(cases
						.getAccidcirLocalName()));
				createUserData(cases);
				createProxyUserData(cases);
				setEntity(cases);
			} else if (getEntity().getId() != null) {
				System.out.println("getEntity().getId() != null");
				cases = caseService.getById(getEntity().getId());
				setAccidcirType(accidcirTypeConvertToList(cases
						.getAccidcirLocalName()));
				createUserData(cases);
				createProxyUserData(cases);
				setEntity(cases);
			}
			createDsOffice();
			createDsAccidcir();
			createDsEvent();
			createDsCarType();
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
			throw new Exception(e);
		}
		return EDIT;
	}

	@Override
	public String list() throws Exception {
		try {
			DataSet<Case> ds = caseService.getByRestrictions(initDataSet());
			Map<String, Object> datas = ds.getDatas();
			Case entity = getEntity();
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
			ds = caseService.getByRestrictions(ds);
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
			Case cases = getEntity();
			cases.setClaimStaff(createRandomClaimStaff());
			cases.setPostDate(LocalDateTime.now());
			cases.setCaseCode(createCaseCode());
			cases = caseService.save(cases, getLoginUser());
			updateRegister(cases);
			saveRemark(cases);

			setAccidcirType(accidcirTypeConvertToList(cases
					.getAccidcirLocalName()));
			createUserData(cases);
			createProxyUserData(cases);
			setEntity(cases);
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
			Case cases = getEntity();
			if (driverAddressBoolean) {
				cases.setDriverPostal(cases.getInsuredPostal());
				cases.setDriverAddress(cases.getInsuredAddress());
			}
			cases.setAccidcirLocalName(accidcirTypeConvertToString());
			cases = caseService.update(getEntity(), getLoginUser());
			createUserData(cases);
			createProxyUserData(cases);
			setEntity(cases);
			createDsOffice();
			createDsAccidcir();
			createDsEvent();
			createDsCarType();

			updateRegisterTable(cases);
			// 產生狀態記錄/備註
			createHistory(cases);

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
			caseService.deleteById(getEntity().getId());
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
	public String createRandomClaimStaff() {
		try {
			if (claimStaffRandom) {
				user.setUserType(UserType.CALIMSTAFF);
				dsUser.setEntity(user);
				dsUser = userService.getByRestrictions(dsUser);
				List<SecUser> results = dsUser.getResults();
				int a = (int) (Math.random() * results.size());
				user = results.get(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user.getUserCode();
	}

	/**
	 * 系統指派理賠人員
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
	 * 取得USER明細
	 * 
	 * @throws Exception
	 */
	public void createUserData(Case cases) {
		try {
			if (StringUtils.isNotEmpty(cases.getProcessUserCode())) {
				user.setUserName(null);
				user.setUserType(null);
				user.setUserCode(cases.getProcessUserCode());
				dsUser.setEntity(user);
				dsUser = userService.getByRestrictions(dsUser);
				user = dsUser.getResults().get(0);
				if (user.getWorkOffice() != null) {
					user.setUserName(user.getUserName() + "/"
							+ user.getWorkOffice());
				} else {
					user.setUserName(user.getUserName() + "/");
				}
				cases.setProcessUser(user);
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
	public void createProxyUserData(Case cases) {
		try {
			if (StringUtils.isNotEmpty(cases.getProxyProcessUserCode())) {
				user2.setUserCode(cases.getProxyProcessUserCode());
				dsUser.setEntity(user2);
				dsUser = userService.getByRestrictions(dsUser);
				user2 = dsUser.getResults().get(0);
				if (user2.getWorkOffice() != null) {
					user2.setUserName(user2.getUserName() + "/"
							+ user2.getWorkOffice());
				} else {
					user2.setUserName(user2.getUserName() + "/");
				}
				cases.setProxyProcessUser(user2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新報案
	 * 
	 * @throws Exception
	 */
	public void updateRegister(Case cases) throws Exception {
		register.setCode(cases.getCode());
		dsRegister.setEntity(register);
		dsRegister = registerService.getByRestrictions(dsRegister);
		register = dsRegister.getResults().get(0);
		register.setPostDate(cases.getAcceptedDate());
		register.setCaseCode(cases.getCaseCode());
		registerService.update(register, getLoginUser());
	}

	/**
	 * 更新報案
	 * 
	 * @throws Exception
	 */
	public void updateRegisterTable(Case cases) throws Exception {
		register.setCode(cases.getCode());
		dsRegister.setEntity(register);
		dsRegister = registerService.getByRestrictions(dsRegister);
		register = dsRegister.getResults().get(0);
		register.setAccidentStatus(cases.getAccidentStatus());
		register.setAccidentSecStatus(cases.getAccidentSecStatus());
		registerService.update(register, getLoginUser());
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
			remark.setRegCode(register.getCode());
			dsRemark.setEntity(remark);
			dsRemark = remarkService.getByRestrictions(dsRemark);
			remark.setCaseCode(cases.getCaseCode());
			remark.setPostDate(cases.getPostDate());
			remark.setRemarkProcessStatus(RemarkProcessStatus.CONVERT);
			remarkService.save(remark, getLoginUser());

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
	 * 產生賠案號碼
	 * 
	 * @throws Exception
	 */
	public String createCaseCode() throws Exception {
		java.util.Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String code = sdf.format(today);
		code = "C" + code.substring(3);
		Case entity = getEntity();
		entity.setCaseCode(code);
		DataSet<Case> ds = getDs();
		ds.setEntity(entity);
		ds = caseService.getCaseCodeByRestrictions(ds);
		List<Case> result = ds.getResults();
		if (result.size() == 0) {
			code = code + "0001";
		} else {
			code = result.get((result.size() - 1)).getCaseCode();
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

	/** 出險型態 從checkbox接值成list轉換成String */
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

	/** 出險型態 從資料庫接值String轉換成list */
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

	public boolean isClaimStaffRandom() {
		return claimStaffRandom;
	}

	public void setClaimStaffRandom(boolean claimStaffRandom) {
		this.claimStaffRandom = claimStaffRandom;
	}

	public String getRegisterTable_length() {
		return registerTable_length;
	}

	public void setRegisterTable_length(String registerTable_length) {
		this.registerTable_length = registerTable_length;
	}

	// 狀態記錄/備註
	@Autowired
	private History history;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private DataSet<History> dsHistory;

	/**
	 * 存入狀態記錄/備註
	 * 
	 * @throws Exception
	 */
	public void createHistory(Case cases) {
		try {
			history.setCode(cases.getCode());
			dsHistory.setEntity(history);
			dsHistory = historyService.getByRestrictions(dsHistory);
			history.setNumber(dsHistory.getResults().size() + 1);
			history.setModifyDate(LocalDateTime.now());
			history.setAccidentStatus(cases.getAccidentStatus());
			history.setAccidentSecStatus(cases.getAccidentSecStatus());
			history.setRemarkProcessStatus(RemarkProcessStatus.RECEIVE);
			history.setPerson(getLoginUser().getUserCode());
			historyService.save(history, getLoginUser());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
