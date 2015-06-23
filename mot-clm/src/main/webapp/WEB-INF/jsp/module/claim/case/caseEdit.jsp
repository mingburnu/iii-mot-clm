<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />
<!DOCTYPE HTML>
<html>
<head>
<style type="text/css">
.checkboxLabel {
	display: inline-table;
	margin-bottom: 5px;
}

.radioLabel {
	display: inline-table;
	margin-bottom: 5px;
}

.divBorder {
	border: 1px solid #dddddd;
	width: 95%;
}
</style>

<script type="text/javascript"
	src='<c:url value="/resources/js/jquery.fancybox.js" />'></script>
<script type="text/javascript"
	src='<c:url value="/resources/js/jquery.fancybox.pack.js" />'></script>
<link href='<c:url value="/resources/css/ui-fancybox/jquery.fancybox.css"/>'
	rel="stylesheet" type="text/css" media="screen" />
	
	
<script type="text/javascript">
	$(function() {
		
		var caseCode='${entity.caseCode}';
		//取得保單資料
		$.ajax({
			  url: '<s:url namespace="/json" action="claim.estimate.findEstimateCheckAjax?caseCode='+caseCode+'" />',
			  type: "GET",
			  dataType: "json",
			  async: false,
			  success: function(Jdata) {
				var checked=Jdata["data"];
				if(checked=="true"){
					$("#topMenu").find("li").eq(2).removeAttr("style");
				}
			  },
			  error: function() {
			    alert("ERROR!!!");
			  }			
		});
		
		
		$('input[name="entity.processUserCode"]').bind('click', function() {
			$.fancybox.open({
				href : '<c:url value="/append/claim/getUser.jsp"/>',
				type : 'iframe',
				padding : 20,
				scrolling : 'auto'
			});
		});
		
		$('input[name="entity.proxyProcessUserCode"]').bind('click', function() {
			$.fancybox.open({
				href : '<c:url value="/append/claim/getProxyUser.jsp"/>',
				type : 'iframe',
				padding : 20,
				scrolling : 'auto'
			});
		});
		
		$('input[name="entity.lawStaff"]').bind('click', function() {
			$.fancybox.open({
				href : '<c:url value="/append/claim/getLawStaff.jsp"/>',
				type : 'iframe',
				padding : 20,
				scrolling : 'auto'
			});
		});
		
		$('input[name="entity.proxyLawStaff"]').bind('click', function() {
			$.fancybox.open({
				href : '<c:url value="/append/claim/getProxyLawStaff.jsp"/>',
				type : 'iframe',
				padding : 20,
				scrolling : 'auto'
			});
		});
		
		$('input[name="entity.mainAccidentName"]').bind('click', function() {
			$.fancybox.open({
				href : '<c:url value="/append/claim/getAccidcir.jsp"/>',
				type : 'iframe',
				padding : 20,
				scrolling : 'auto'
			});
		});
		
		$('input[name="entity.otherAccidentName"]').bind('click', function() {
			$.fancybox.open({
				href : '<c:url value="/append/claim/getOtherAccidcir.jsp"/>',
				type : 'iframe',
				padding : 20,
				scrolling : 'auto'
			});
		});
		
		$('#driverAddressBoolean').bind('change', function(){
			if($(this).is(':checked')){
				$('input[name="entity.driverPostal"]').val('');
				$('input[name="entity.driverAddress"]').val('');
				$('input[name="entity.driverPostal"]').attr('disabled',true);
				$('input[name="entity.driverAddress"]').attr('disabled',true);				
			}else{
				$('input[name="entity.driverPostal"]').attr('disabled',false);
				$('input[name="entity.driverAddress"]').attr('disabled',false);
			}
		});
		
	});
	
	function checkAccident(code){
		var result=false;
		if($('input[name="entity.mainAccident"]').val()==code){
			result=true;
		}
		if($('input[name="entity.otherAccident"]').val()==code){
			result=true;
		}
		return result;
	}	
	
	function setMainAccident(code,name){
		$('input[name="entity.mainAccident"]').val(code);
		$('input[name="entity.mainAccidentName"]').val(name);
	}
	
	function setOtherAccident(code,name){
		$('input[name="entity.otherAccident"]').val(code);
		$('input[name="entity.otherAccidentName"]').val(name);
	}	
	
	
	
	
	function checkLawStaffData(code){
		var result=false;
		if($('input[name="entity.lawStaff"]').val()==code){
			result=true;
		}
		if($('input[name="entity.proxyLawStaff"]').val()==code){
			result=true;
		}
		return result;
	}	
	
	function setLawStaffData(code,name){
		$('input[name="entity.lawStaff"]').val(code);		
	}
	
	function setProxyLawStaffData(code){
		$('input[name="entity.proxyLawStaff"]').val(code);
	}
	
	
	
	function checkUserData(code){
		var result=false;
		if($('input[name="entity.processUserCode"]').val()==code){
			result=true;
		}
		if($('input[name="entity.proxyProcessUserCode"]').val()==code){
			result=true;
		}
		return result;
	}	
	
	function setUserData(code,name,office){
		$('input[name="entity.processUserCode"]').val(code);
		$('input[name="entity.processUser.userName"]').val(name+"/"+office);
	}
	
	function setProxyUserData(code,name,office){
		$('input[name="entity.proxyProcessUserCode"]').val(code);
		$('input[name="entity.proxyProcessUser.userName"]').val(name+"/"+office);
	}
</script>
<title>車險系統</title>
</head>
<body class="page-padding">
	<fieldset>
		<legend>賠案管理</legend>
		<c:choose>
			<c:when test="${not empty entity.id}">
				<%-- 選單 --%>
				<jsp:include
					page="/WEB-INF/jsp/module/claim/case/layout/caseTopMenu.jsp" />
			</c:when>
		</c:choose>

		<s:form namespace="/crud" method="post" cssClass="form-horizontal">
			<s:hidden name="entity.id" />
			<table>
				<tr>
					<td><h5>報案編號</h5></td>
					<td><s:textfield name="entity.code" cssClass="input-medium"
							readonly="true" /></td>
					<td><h5>處理單位</h5></td>
					<td><s:select name="entity.officeId" cssClass="input-medium"
							list="dsOffice.results" listKey="id" listValue="localName" /></td>
					<td><h5>賠案號碼</h5></td>
					<td><s:textfield name="entity.caseCode" cssClass="input-medium"
							readonly="true" /></td>
					<td><h5>立案日</h5></td>
					<td><s:textfield name="entity.postDate" cssClass="input-small"
							disabled="true" /></td>
					<s:hidden name="entity.postDate" />
				</tr>
			</table>
			<div class="divBorder">
				<h5 class="heading">出險型態</h5>
				<s:checkboxlist name="accidcirType" cssClass="input-medium"
					list="{'不明受損','人員傷亡','天災','道路救援','自行撞損','墜落物','泡水車'}" />
			</div>
			<hr>
			<div class="divBorder">
				<h5 class="heading">受理類型</h5>
				<s:radio cssClass="checkboxLabel"
					list="{'7-11','保戶親自','VIP網站','修理廠'}" name="entity.processType" />
				<hr>
					<table>
					<tr>
						<td><h5>被保險人ID</h5></td>
						<td><s:textfield name="entity.insuredId"
								cssClass="input-small" readonly="true" /></td>
						<td><h5>姓名</h5></td>
						<td><s:textfield name="entity.insuredName"
								cssClass="input-small" readonly="true" /></td>
						<td><h5>出險日期</h5></td>
						<td><s:textfield name="entity.accidcirDate"
								cssClass="input-small" disabled="true" /></td>
								<s:hidden name="entity.accidcirDate" />
					</tr>
				</table>
				<table>
					<tr>
						<td><h5>電話</h5></td>
						<td><s:textfield name="entity.insuredTelCty"
								cssClass="input-mini" placeholder="國碼" /></td>
						<td><s:textfield name="entity.insuredTelArea"
								cssClass="input-mini" placeholder="區碼" /></td>
						<td><s:textfield name="entity.insuredTel"
								cssClass="input-small" /></td>
						<td><h5>分機</h5></td>
						<td><s:textfield name="entity.insuredPhoneCty"
								cssClass="input-mini" /></td>
						<td><h5>手機</h5></td>
						<td><s:textfield name="entity.insuredPhone"
								cssClass="input-small" /></td>
						<td><s:checkbox name="entity.insuredMessage"
								onclick="unchecked" /></td>
						<td><h5>簡訊</h5></td>
					</tr>
				</table>
				<table>
					<tr>
						<td><h5>郵遞區號</h5></td>
						<td><s:textfield name="entity.insuredPostal"
								cssClass="input-mini" /></td>
						<td><h5>地址</h5></td>
						<td><s:textfield name="entity.insuredAddress"
								cssClass="input-xxlarge" /></td>
					</tr>
				</table>
				<table>
					<tr>
						<td><h5>車牌</h5></td>
						<td><s:textfield name="entity.plate" cssClass="input-small"
								style="background-color : F1E179" readonly="true"/></td>
						<td><h5>強制證號</h5></td>
						<td><s:textfield name="entity.forcedCode"
								cssClass="input-small" readonly="true" /></td>
						<td><h5>車輛種類</h5></td>
						<td><s:textfield name="entity.carTypeId"
								cssClass="input-small" readonly="true" /></td>
					</tr>
				</table>
				<table>
					<tr>
						<td><h5>引擎號碼</h5></td>
						<td><s:textfield name="entity.engineCode"
								cssClass="input-small" readonly="true" /></td>
						<td><h5>車輛年份</h5></td>
						<td><s:textfield name="entity.carYear" cssClass="input-small"
								readonly="true" /></td>
						<td><h5>廠牌型式</h5></td>
						<td><s:textfield name="entity.brandType"
								cssClass="input-small" readonly="true" /></td>
					</tr>
				</table>
				<table>
					<tr>
						<td><h5>排氣量</h5></td>
						<td><s:textfield name="entity.displacement"
								cssClass="input-small" readonly="true" /></td>
						<td><h5>載客人數</h5></td>
						<td><s:textfield name="entity.passengers"
								cssClass="input-small" readonly="true" /></td>
						<td><h5>特殊註記</h5></td>
						<td><s:textfield name="entity.specialNote"
								cssClass="input-small" readonly="true" /></td>
					</tr>
				</table>
				<hr>
				<table>
					<tr>
						<td><h5>駕駛人</h5></td>
						<td><s:textfield name="entity.driver" cssClass="input-small" /></td>
						<td><h5>駕照號碼</h5></td>
						<td><s:textfield name="entity.driversCode"
								cssClass="input-small" /></td>
						<td><h5>領(換)照年度</h5></td>
						<td><s:textfield name="entity.licenseYear"
								cssClass="input-small" /></td>
					</tr>
					<tr>
						<td><h5>出生日期</h5></td>
						<td><s:textfield name="entity.birthday"
								cssClass="input-small" /></td>
						<td><h5>性別</h5></td>
						<td><s:select name="entity.sex" cssClass="input-medium"
								list="@org.iii.core.enums.Sex@values()" listKey="name()"
								listValue="sex" /></td>
						<td><h5>國籍</h5></td>
						<td><s:select name="entity.country" cssClass="input-medium"
								list="@org.iii.core.enums.Country@values()" listKey="name()"
								listValue="country" /></td>
					</tr>
					<tr>
						<td><h5>與被保人關係</h5></td>
						<td><s:select name="entity.insuredRel"
								cssClass="input-medium"
								list="@org.iii.module.claim.register.enums.InsuredRelation@values()"
								listKey="name()" listValue="insuredRelation" /></td>
						<td><h5>駕照種類</h5></td>
						<td><s:select name="entity.licenseType"
								cssClass="input-medium"
								list="@org.iii.module.claim.register.enums.LicenseType@values()"
								listKey="name()" listValue="licenseType" /></td>
						<td><h5>婚姻狀況</h5></td>
						<td><s:select name="entity.marriage" cssClass="input-medium"
								list="@org.iii.module.claim.register.enums.Marriage@values()"
								listKey="name()" listValue="marriage" /></td>
					</tr>
				</table>
				<table>
					<tr>
						<td><h5>電話</h5></td>
						<td><s:textfield name="entity.driverTelCty"
								cssClass="input-mini" placeholder="國碼" /></td>
						<td><s:textfield name="entity.driverTelArea"
								cssClass="input-mini" placeholder="區碼" /></td>
						<td><s:textfield name="entity.driverTel"
								cssClass="input-small" /></td>
						<td><h5>分機</h5></td>
						<td><s:textfield name="entity.driverPhoneCty"
								cssClass="input-mini" /></td>
						<td><h5>手機</h5></td>
						<td><s:textfield name="entity.driverPhone"
								cssClass="input-small" /></td>
						<td><s:checkbox name="entity.driverMessage"
								onclick="unchecked" /></td>
						<td><h5>簡訊</h5></td>
					</tr>
				</table>
				<table>
					<tr>
						<td><h5>郵遞區號</h5></td>
						<td><s:textfield name="entity.driverPostal"
								cssClass="input-mini" /></td>
						<td><h5>地址</h5></td>
						<td><s:textfield name="entity.driverAddress"
								cssClass="input-xxlarge" /></td>
						<td><s:checkbox name="driverAddressBoolean" value="false" id="driverAddressBoolean"/></td>
						<td><h5>同被保險人</h5></td>
					</tr>
				</table>
			</div>
			<hr>
			<s:hidden name="entity.policyCode" />
			<jsp:include page="/WEB-INF/jsp/module/claim/case/layout/insuredPolicy.jsp" />
			<hr>
			<div class="divBorder">
				<h5 class="heading">事故資料</h5>
				<h5>
					*主要事故原因
					<s:hidden name="entity.mainAccident" />
					<s:textfield name="entity.mainAccidentName" cssClass="input-mideum"
						style="background-color : F1E179" />
				</h5>
				<h5>
					其他事故原因
					<s:hidden name="entity.otherAccident" />
					<s:textfield name="entity.otherAccidentName" cssClass="input-mideum"
						style="background-color : F1E179" />
				</h5>
				<hr>
				<table>
					<tr>
						<td><h5>事故人ID</h5></td>
						<td><h5>
								<s:textfield name="entity.accidentPeopleId"
									cssClass="input-small" />
								事故人姓名
								<s:textfield name="entity.accidentPeopleName"
									cssClass="input-small" />
							</h5></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td><h5>事故地點</h5></td>
						<td><s:textfield name="entity.accidentCountry"
								cssClass="input-mini" placeholder="國家" /> <s:textfield
								name="entity.accidentCounty" cssClass="input-mini"
								placeholder="城市" /> <s:textfield
								name="entity.accidentPostalCode" cssClass="input-mini"
								placeholder="區號" /> <s:textfield name="entity.accidentAddress"
								cssClass="input-large" /></td>
						<td><h5>狀態</h5></td>
						<td><s:select name="entity.accidentStatus"
								cssClass="input-medium"
								list="@org.iii.core.enums.ProcessStatus@values()"
								listKey="name()" listValue="processStatus" /></td>
					</tr>
					<tr>
						<td><h5>事故地點補述</h5></td>
						<td><s:textfield name="entity.accidentAddressDes"
								cssClass="input-large" /></td>
						<td><h5>次狀態</h5></td>
						<td><s:select name="entity.accidentSecStatus"
								cssClass="input-large"
								list="@org.iii.module.claim.register.enums.AccidentSecStatus@values()"
								listKey="name()" listValue="accidentSecStatus" /></td>

					</tr>
					<tr>
						<td><h5>事件</h5></td>
						<td><s:select name="entity.eventId" cssClass="input-xlarge"
								list="dsEvent.results" listKey="id" listValue="code" /></td>
						<td><h5>事故處理</h5></td>
						<td><s:select name="entity.accidentDeal"
								cssClass="input-large"
								list="@org.iii.module.claim.register.enums.AccidentDeal@values()"
								listKey="name()" listValue="accidentDeal" /></td>

					</tr>
					<tr>
						<td><h5>事故概述</h5></td>
						<td><s:textarea name="entity.accidentDes"
								cssClass="input-xlarge" style="resize:none;" ></s:textarea></td>
						<td><h5>事故處理單位</h5></td>
						<td><s:textfield name="entity.accidentDealUnit"
								cssClass="input-large" /></td>
					</tr>
				</table>
			</div>
			<hr>
			<div class="divBorder">
				<h5 class="heading">其他資料</h5>
				<table>
					<tr>
						<td><h5>申請日期</h5></td>
						<td><s:textfield name="entity.applyDate"
								cssClass="input-small" disabled="true" /></td>
						<s:hidden name="entity.applyDate" />
						<td><h5>受理日期</h5></td>
						<td><s:textfield name="entity.acceptedDate"
								cssClass="input-small" disabled="true" /></td>
						<s:hidden name="entity.acceptedDate" />
						<td><h5>申請人</h5></td>
						<td><s:textfield name="entity.applicant"
								cssClass="input-small"  readonly="true"/></td>
						<td><h5>與被保人關係</h5></td>
						<td><s:select name="entity.applicantInsuredRel"
								cssClass="input-large"
								list="@org.iii.module.claim.register.enums.InsuredRelation@values()"
								listKey="name()" listValue="insuredRelation" /></td>
					</tr>
				</table>
				<hr>
				<table>
					<tr>
						<td><s:checkbox name="entity.coinsurance"
								onclick="unchecked" /></td>
						<td><h5>共保</h5></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td>
						<td><s:checkbox name="entity.stakeholders"
								onclick="unchecked" /></td>
						<td><h5>利害關係人</h5></td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td>
						<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<td>
						<td><s:checkbox name="entity.customerNote"
								onclick="unchecked" /></td>
						<td><h5>註記</h5></td>
					</tr>
				</table>
				<hr>
				<table>
					<tr>
						<td><h5>**受理經辦</h5></td>
						<td><s:textfield name="entity.processUserCode" cssClass="input-small" style="background-color : F1E179"  /></td>
						<td></td>
						<td><s:textfield name="entity.processUser.userName"
								cssClass="input-medium" readonly="true" /></td>
						<td>**法務人員</td>
						<td><s:textfield name="entity.lawStaff"
								cssClass="input-small" style="background-color : F1E179"/></td>
					</tr>
					<tr>
						<td><h5>**代辦受理經辦</h5></td>
						<td><s:textfield name="entity.proxyProcessUserCode"
								cssClass="input-small" style="background-color : F1E179"/></td>
						<td></td>
						<td><s:textfield name="entity.proxyProcessUser.userName"
								cssClass="input-medium" readonly="true" /></td>
						<td>**代理法務人員</td>
						<td><s:textfield name="entity.proxyLawStaff"
								cssClass="input-small" style="background-color : F1E179"/></td>						
					</tr>
				</table>
			</div>
			<c:choose>
				<c:when test="${not empty entity.id}">
					<%-- 創建者資訊 --%>
					<jsp:include page="/WEB-INF/jsp/layout/creatorInfo.jsp" />
					<%-- 底下的選單 --%>
					<jsp:include
						page="/WEB-INF/jsp/module/claim/case/layout/caseFootMenu.jsp" />
				</c:when>
			</c:choose>

			<div class="btn-group" style="float: right">
				<s:submit cssClass="btn btn-success" action="claim.case.update"
					value="修改" />
				<input type="button" class="btn btn-inverse return" value="關閉"
					onclick="document.location='<s:url namespace="/crud" action="claim.case.list" />'" />
			</div>
		</s:form>
	</fieldset>
</html>