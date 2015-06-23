<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
.divMark1 {
	border: 1px solid #dddddd;
	width: 95%;
	background-color: #F4F2F2;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src='<c:url value="/resources/js/jquery.fancybox.js" />'></script>
<script type="text/javascript"
	src='<c:url value="/resources/js/jquery.fancybox.pack.js" />'></script>
<link href='<c:url value="/resources/css/ui-fancybox/jquery.fancybox.css"/>'
	rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript">

$(function(){	
	$('input[name="entity.claimStaff"]').bind('click', function() {
		$.fancybox.open({
			href : '<c:url value="/append/claim/getClaimStaff.jsp"/>',
			type : 'iframe',
			padding : 20,
			scrolling : 'auto'
		});
	});	
	
	$('#claimStaffRandom').bind('change', function(){
		if($(this).is(':checked')){
			$('input[name="entity.claimStaff"]').val('');
			$('input[name="entity.claimStaff"]').attr('disabled',true);
			$('input[name="entity.claimStaffUser.userName"]').val('');
		}else{
			$('input[name="entity.claimStaff"]').attr('disabled',false);
		}
	});
	
	
});
	
function setClaimStaffData(code,name,office){
	$('input[name="entity.claimStaff"]').val(code);
	$('input[name="entity.claimStaffUser.userName"]').val(name+"/"+office);
}
</script>	
	

</head>
<body class="page-padding">
	<fieldset>
		<legend>轉案</legend>
		<s:form namespace="/crud" action="claim.case.save" method="post"
			cssClass="form-horizontal">
			<s:hidden name="entity.id" />
			<s:hidden name="entity.officeId" />
			<s:hidden name="entity.accidcirLocalName" />
			<s:hidden name="entity.processType" />
			<s:hidden name="entity.insuredId" />
			<s:hidden name="entity.insuredName" />
			<s:hidden name="entity.insuredTelCty" />
			<s:hidden name="entity.insuredTelArea" />
			<s:hidden name="entity.insuredTel" />
			<s:hidden name="entity.insuredPhoneCty" />
			<s:hidden name="entity.insuredPhone" />
			<s:hidden name="entity.insuredMessage" />
			<s:hidden name="entity.insuredPostal" />
			<s:hidden name="entity.insuredAddress" />
			<s:hidden name="entity.plate" />
			<s:hidden name="entity.forcedCode" />
			<s:hidden name="entity.policyCode" />
			<s:hidden name="entity.carTypeId" />
			<s:hidden name="entity.engineCode" />
			<s:hidden name="entity.carYear" />
			<s:hidden name="entity.brandType" />
			<s:hidden name="entity.displacement" />
			<s:hidden name="entity.passengers" />
			<s:hidden name="entity.specialNote" />
			<s:hidden name="entity.driver" />
			<s:hidden name="entity.driversCode" />
			<s:hidden name="entity.licenseYear" />
			<s:hidden name="entity.birthday" />
			<s:hidden name="entity.sex" />
			<s:hidden name="entity.country" />
			<s:hidden name="entity.insuredRel" />
			<s:hidden name="entity.licenseType" />
			<s:hidden name="entity.marriage" />
			<s:hidden name="entity.driverTelCty" />
			<s:hidden name="entity.driverTelArea" />
			<s:hidden name="entity.driverTel" />
			<s:hidden name="entity.driverPhoneCty" />
			<s:hidden name="entity.driverPhone" />
			<s:hidden name="entity.driverMessage" />
			<s:hidden name="entity.driverPostal" />
			<s:hidden name="entity.driverAddress" />
			<s:hidden name="entity.mainAccident" />
			<s:hidden name="entity.otherAccident" />
			<s:hidden name="entity.mainAccidentName" />
			<s:hidden name="entity.otherAccidentName" />
			<s:hidden name="entity.accidentPeopleId" />
			<s:hidden name="entity.accidentPeopleName" />
			<s:hidden name="entity.accidcirDate" />
			<s:hidden name="entity.accidentCountry" />
			<s:hidden name="entity.accidentStatus" />
			<s:hidden name="entity.accidentAddressDes" />
			<s:hidden name="entity.accidentSecStatus" />
			<s:hidden name="entity.eventId" />
			<s:hidden name="entity.accidentDeal" />
			<s:hidden name="entity.accidentDes" />
			<s:hidden name="entity.accidentDealUnit" />
			<s:hidden name="entity.applyDate" />
			<s:hidden name="entity.acceptedDate" />
			<s:hidden name="entity.applicant" />
			<s:hidden name="entity.applicantInsuredRel" />
			<s:hidden name="entity.proxyProcessUserCode" />


			<hr>
			<table>
				<tr>
					<td><h5>報案號碼</h5></td>
					<td><s:textfield name="entity.code" cssClass="input-medium"
							readonly="true" /></td>
					<td><h5>受理單位</h5></td>
					<td><s:textfield name="entity.office.code"
							cssClass="input-small" readonly="true" /> <s:textfield
							name="entity.office.localName" cssClass="input-small"
							readonly="true" /></td>
				</tr>
			</table>
			<hr>
			<table>
				<tr>
					<td><h5>受理人員</h5></td>
					<td><s:textfield name="entity.processUserCode"
							cssClass="input-small" readonly="true" /></td>
					<td><h5>指派理賠人員</h5></td>
					<td><h5>
							<s:textfield name="entity.claimStaff" cssClass="input-small" style="background-color : F7FA00" disabled="true"/>
							<s:textfield name="entity.claimStaffUser.userName" cssClass="input-large"
								readonly="true" />
							<s:checkbox name="claimStaffRandom" onclick="unchecked" id="claimStaffRandom" value="true" />
							系統分派
						</h5></td>
				</tr>
			</table>

			<s:url namespace="/crud" action="claim.register.query" var="close">
				<s:param name="entity.id">${entity.id}</s:param>
			</s:url>
			<div class="btn-group" style="float: right">
				<s:submit cssClass="btn btn-success" value="儲存" />
				<input type="button" class="btn btn-inverse return" value="關閉"
					onclick="document.location='<s:property value="#close" />'" />
			</div>
		</s:form>
	</fieldset>
</body>
</html>