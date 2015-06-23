<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />

<c:choose>
	<c:when test="${not empty entity.id}">
		<script type="text/javascript">
		$(function(){			
			var name=$("input[name='entity.victimsName']").val();
			var price=$("input[name='entity.estimatedLossAmount']").val();
			var id = $("input[name='entity.id']").val();
			parent.saveDamageObjectOwner(name,price,id);
			parent.jQuery.fancybox.close();
		});
		</script>
	</c:when>
</c:choose>

<!DOCTYPE HTML>
<html>
<head>
<script type="text/javascript"
	src='<c:url value="/resources/js/jquery.fancybox.js" />'></script>
<script type="text/javascript"
	src='<c:url value="/resources/js/jquery.fancybox.pack.js" />'></script>
<link href='<c:url value="/resources/css/ui-fancybox/jquery.fancybox.css"/>'
	rel="stylesheet" type="text/css" media="screen" />
	
<style type="text/css">
.divBorder {
	border: 1px solid #dddddd;
	width: 95%;
}
.heading {
	width: 60px;
	margin-top: 0px;
	padding: 2px;
	border: 0px 1px 1px 0px solid silver;
	border-radius: 0px 0px 5px 0px;
	background-color: #dddddd;
}
</style>

<script type="text/javascript">

$(function(){
	$("input[name='entity.injuredBenefit']").on('change',function(){
		if(isNaN($(this).val())){
			 alert("請輸入數字");
			 $(this).val('');
		}
		calAmount();

	});
	$("input[name='entity.disabilityBenefit']").on('change',function(){
		if(isNaN($(this).val())){
			 alert("請輸入數字");
			 $(this).val('');
		}
		calAmount();
	});
	$("input[name='entity.deathBenefit']").on('change',function(){
		if(isNaN($(this).val())){
			 alert("請輸入數字");
			 $(this).val('');
		}
		calAmount();
	});
	
});

function calAmount(){
	var injuredBenefit=$("input[name='entity.injuredBenefit']").val();
	var disabilityBenefit=$("input[name='entity.disabilityBenefit']").val();
	var deathBenefit=$("input[name='entity.deathBenefit']").val();
	$("input[name='entity.estimatedLossAmount']").val(injuredBenefit*1+disabilityBenefit*1+deathBenefit*1);
}



function checkData(){
	var name=$("input[name='entity.victimsName']").val();
	var price=$("input[name='entity.estimatedLossAmount']").val();
	var errors='';
	if(name.length<=0){
		errors=errors+"名字要填";
	}
	if(price.length<=0){
		if(errors.length>0){
			errors=errors+",";
		}
		errors=errors+"損失金額要填";
	}
	if(errors.length<=0){
		 $("form").submit();
	}else{
		alert(errors);
	}
}

function getVictimsData(){	
	$.fancybox.open({
		href : '<c:url value="/append/contact/getContact.jsp"/>',
		type : 'iframe',
		padding : 20,
		scrolling : 'auto'
	});
}

function getCaseCode(){
	var caseCode=$("input[name='entity.caseCode']").val();
	return caseCode;
}

function setData(contact,homeTel,mobile,mail,address){
	$("input[name='entity.victimsName']").val(contact);
	$("input[name='entity.victimsTelHome']").val(homeTel);
	$("input[name='entity.victimsMobile']").val(mobile);
	$("input[name='entity.email']").val(mail);
	$("input[name='entity.victimsAddr']").val(address);
}

</script>

<title>受害人預估賠款</title>

</head>
<body>
	<fieldset>
		<legend>乘客責任險</legend>
		<s:form namespace="/edcrud" method="post" action="claim.estimateDetail.save.51" cssClass="form-horizontal">
			<s:hidden name="entity.id" />
			<s:hidden name="entity.insuredType" value="51.乘客責任險" />
			<table>
				<tr>
					<%-- 				
					<s:url namespace="/crud" action="claim.." var="contact">
 					<s:param name="registerId">${entity.id}</s:param> 
 					</s:url> 
 					--%>
					<td class="form-label"><h5>賠案編號</h5></td>
					<td><s:textfield name="entity.caseCode"
							cssClass="input-medium" readonly="true" /></td>
					<td class="form-label"><h5>出險日期</h5></td>
					<td><s:textfield name="entity.accidcirDate"
							cssClass="input-medium" disabled="true" /></td>
					<s:hidden name="entity.accidcirDate" />
				</tr>
				<tr>
					<td class="form-label"><h5>被保險人ID</h5></td>
					<td><s:textfield name="entity.insuredId"
							cssClass="input-medium" readonly="true" /></td>
					<td class="form-label"><h5>被保險人姓名</h5></td>
					<td><s:textfield name="entity.insuredName"
							cssClass="input-medium" readonly="true" /></td>
				</tr>
				<tr>
					<td class="form-label"><h5>車牌號碼</h5></td>
					<td><s:textfield name="entity.plate" cssClass="input-medium"
							readonly="true" /></td>
					<td class="form-label"><h5>申請次數</h5></td>
					<td><s:textfield name="entity.applyBout"
							cssClass="input-medium" readonly="true" /></td>
					<td class="form-label"><h5>事故原因</h5></td>
					<td><s:textfield name="entity.accident"
							cssClass="input-medium" readonly="true" /></td>
				</tr>
			</table>
			<div class="divBorder">
				<h5 class="heading">保單資料</h5>
				<table>
					<tr>
						<td><h5>**受害人</h5></td>
						<td><s:textfield name="entity.victimsName" 
								cssClass="input-medium" /></td>
						<td><h5>身份證號</h5></td>
						<td><s:textfield name="entity.victimsId" 
								cssClass="input-medium" /></td>	
						<td></td>
						<td><input type="button" class="btn btn-inverse return" 
							value="由賠案通訊錄複製" onclick="getVictimsData()"/></td>						
					</tr>					
					<tr>
						<td><h5>出生日期</h5></td>
						<td><s:textfield name="entity.birthday"
								cssClass="input-medium" /></td>
						<td><h5>性別</h5></td>
						<td class="form-label"><s:select name="entity.sex"
								cssClass="input-medium" list="@org.iii.core.enums.Sex@values()"
								listKey="name()" listValue="sex" /></td>
						<td><h5>職業</h5></td>
						<td class="form-label"><s:select name="entity.job"
								cssClass="input-medium"
								list="@org.iii.module.claim.register.enums.Job@values()"
								listKey="name()" listValue="job" /></td>
					</tr>
					<tr>
						<td><h5>婚姻</h5></td>
						<td><s:select name="entity.marriage" cssClass="input-medium"
								list="@org.iii.module.claim.register.enums.Marriage@values()"
								listKey="name()" listValue="marriage" /></td>
						<td><h5>國籍</h5></td>
						<td><s:select name="entity.country" cssClass="input-medium"
								list="@org.iii.core.enums.Country@values()" listKey="name()"
								listValue="country" /></td>
						<td><h5>電話</h5></td>
						<td><s:textfield name="entity.victimsTelHome"
								cssClass="input-small" placeholder="住家" /></td>
						<td><s:textfield name="entity.victimsMobile"
								cssClass="input-small" placeholder="手機 " /></td>
					</tr>
				</table>
				<table>
					<tr>
						<td><h5>E-mail</h5></td>
						<td><s:textfield name="entity.email" cssClass="input-large" /></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td><h5>地址</h5></td>
						<td><s:textfield name="entity.victimsAddr"
								cssClass="input-xxlarge" /></td>
						<td></td>
						<td></td>
					</tr>
				</table>
				<table>
					<tr>
						<td><h5>受傷部位</h5></td>
						<td><s:textfield name="entity.injuredArea"
								cssClass="input-small" /></td>
						<td><h5>傷勢</h5></td>
						<td><s:textfield name="entity.injured" cssClass="input-small" /></td>
					</tr>
				</table>
			</div>
			<hr>
			<div class="divBorder">
				<h5 class="heading">初步預估</h5>
				<table>
					<tr>
						<td><h5>體傷給付</h5></td>
						<td><s:textfield name="entity.injuredBenefit"
								cssClass="input-mini" /></td>
						<td><h5>殘廢給付</h5></td>
						<td><s:textfield name="entity.disabilityBenefit"
								cssClass="input-mini" /></td>
						<td><h5>死亡給付</h5></td>
						<td><s:textfield name="entity.deathBenefit"
								cssClass="input-mini" /></td>
						<td><h5>**預估損失金額</h5></td>
						<td><s:textfield name="entity.estimatedLossAmount"
								cssClass="input-mini" readonly="true" /></td>
					</tr>
				</table>
			</div>

			<table class="form-table">
				<tr>
					<td colspan="2" class="form-button">
						<div class="btn-group">

							<input type="button" class="btn btn-success" value="儲存"
								onclick="checkData()" />

							<input type="button" class="btn btn-inverse return" value="關閉"
								onclick="parent.jQuery.fancybox.close()" />
						</div>
					</td>
				</tr>
			</table>
		</s:form>
	</fieldset>
</body>
</html>