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
			var name=$("input[name='entity.ownerName']").val();
			var price=$("input[name='entity.lossAmount']").val();
			var id = $("input[name='entity.id']").val();

			parent.saveOwnerName(name,price,id);
			parent.jQuery.fancybox.close();
		});
		</script>
	</c:when>
</c:choose>
<!DOCTYPE HTML>
<html>
<head>
<style type="text/css">
.heading {
	width: 60px;
	margin-top: 0px;
	padding: 2px;
	border: 0px 1px 1px 0px solid silver;
	border-radius: 0px 0px 5px 0px;
	background-color: #dddddd;
}
</style>
<s:url namespace="/edcrud" action="claim.estimateDetail.query.01" escapeAmp="false"	var="query">
			<s:param name="data">true</s:param>
			<s:param name="entity.caseCode">${entity.caseCode}</s:param>
</s:url>
<script type="text/javascript">

function checkData(){
	var name=$("input[name='entity.ownerName']").val();
	var price=$("#total").val();
	var errors='';
	if(name.length<=0){
		errors=errors+"車主姓名尚未輸入";
	}
	if(price.length<=0){
		if(errors.length>0){
			errors=errors+",";
		}
		errors=errors+"損失金額尚未輸入";
	}
	if(errors.length<=0){
		 $("form").submit();
	}else{
		alert(errors);
	}
}

$(function(){
	$("input[name='entity.lossAmount']").on("change",function(){
		var lossAmount=$("input[name='entity.lossAmount']").val();
		if(isNaN(lossAmount)){
			 $("input[name='entity.lossAmount']").val("");
			 lossAmount=$("input[name='entity.lossAmount']").val();
		}
		$("#total").val(lossAmount);
	});
});

function getCaseData(){
	document.location="<s:property value='#query' escape='false'/>";
}

</script>
<title>受害人預估賠款</title>
</head>
<body>
	<fieldset>
		<legend>甲式車體險</legend>
		<s:form namespace="/edcrud" method="post" action="claim.estimateDetail.save.01" cssClass="form-horizontal">
			<s:hidden name="entity.id" />
			<s:hidden name="entity.caseCode" />
			<s:hidden name="entity.insuredType" value="01.甲式車體險" />
			<table>
				<tr>

					<td class="form-label"><h5>處理單位</h5></td>
					<td><s:textfield name="entity.processOffice"
							cssClass="input-medium" readonly="true" /></td>
					<td class="form-label"><h5>理賠經辦</h5></td>
					<td><s:textfield name="entity.claimsManagers"
							cssClass="input-medium" readonly="true" /></td>
					<td></td>
				</tr>
			</table>
			<hr>
			<div class="divBorder">
				<h4>車輛資料</h4>
				<table>
					<tr>
						<td class="form-label"><h5>車牌號碼</h5></td>
						<td><s:textfield name="entity.plate" cssClass="input-medium" /></td>
						<td class="form-label"><h5>**車主姓名</h5></td>
						<td><s:textfield name="entity.ownerName"
								cssClass="input-medium" /></td>
						<td><input type="button" class="btn btn-inverse return" 
							value="同賠案資料" onclick="getCaseData()"/> </td>
					</tr>
				</table>
				<table>
					<tr>
						<td class="form-label"><h5>車主電話</h5></td>
						<td><s:textfield name="entity.ownerTelHome"
								cssClass="input-medium" placeholder="住家" /></td>
						<td><s:textfield name="entity.ownerMobile"
								cssClass="input-medium" placeholder="手機 " /></td>
						<td class="form-label"><h5>車主地址</h5></td>
						<td><s:textfield name="entity.ownerAddr"
								cssClass="input-xlarge" /></td>
					</tr>
				</table>
				<br>
				<table>
					<tr>
						<td class="form-label"><h5>駕照號碼</h5></td>
						<td><s:textfield name="entity.driversCode"
								cssClass="input-medium" /></td>
						<td class="form-label"><h5>駕駛人</h5></td>
						<td><s:textfield name="entity.driver" cssClass="input-medium" /></td>
					</tr>
				</table>
				<table>
					<tr>
						<td class="form-label"><h5>駕駛電話</h5></td>
						<td><s:textfield name="entity.driverTelHome"
								cssClass="input-medium" placeholder="住家" /></td>
						<td><s:textfield name="entity.driverMobile"
								cssClass="input-medium" placeholder="手機 " /></td>
						<td class="form-label"><h5>駕駛地址</h5></td>
						<td><s:textfield name="entity.driverAddr"
								cssClass="input-xlarge" /></td>
					</tr>
				</table>
				<br>
				<table>
					<tr>
						<td><h5>廠牌型式</h5></td>
						<td class="form-label"><s:select name="entity.brandType"
								cssClass="input-large"
								list="@org.iii.core.enums.BrandType@values()" listKey="name()"
								listValue="brandType" /></td>
						<td class="form-label"><h5>車輛年份</h5></td>
						<td><s:textfield name="entity.carYear"
								cssClass="input-medium" /></td>
						<td class="form-label"><h5>排氣量</h5></td>
						<td><s:textfield name="entity.displacement"
								cssClass="input-medium" /></td>
					</tr>
					<tr>
						<td class="form-label"><h5>引擎號碼</h5></td>
						<td><s:textfield name="entity.engineCode"
								cssClass="input-medium" /></td>
						<td class="form-label"><h5>車身號碼</h5></td>
						<td><s:textfield name="entity.carCode"
								cssClass="input-medium" /></td>
					</tr>
				</table>
			</div>
			<hr>
			<div class="divBorder">
				<h4>修理廠</h4>
				<table>
					<tr>
						<td><h5>修理廠</h5></td>
						<td class="form-label"><s:select name="entity.repair"
								cssClass="input-medium"
								list="@org.iii.module.claim.estimateDetail.enums.Repair@values()"
								listKey="name()" listValue="repair" /></td>
						<td><h5>聯絡人</h5></td>
						<td><s:textfield name="entity.contact"
								cssClass="input-medium" /></td>
						<td class="form-label"><h5>聯絡人電話</h5></td>
						<td><s:textfield name="entity.contactTelHome"
								cssClass="input-medium" placeholder="住家" /></td>
						<td><s:textfield name="entity.contactMobile"
								cssClass="input-medium" placeholder="手機 " /></td>
					</tr>
				</table>
				<table>
					<tr>
						<td><h5>修理廠統編</h5></td>
						<td><s:textfield name="entity.repairId"
								cssClass="input-medium" /></td>
						<td><h5>修理廠地址</h5></td>
						<td><s:textfield name="entity.repairAddr"
								cssClass="input-medium" /></td>
					</tr>
				</table>
				</div>
				<hr>
			<div class="divBorder">
				<h4>初步預估</h4>
				<table>
					<tr>
					<td></td>
						</tr>
						</table>
						<table>
						<tr>
						<td><h5>險種</h5></td>
						<td><h5>保額</h5></td>
						<td><h5>**損失金額</h5></td>
						<td><h5>自負額</h5></td>
						</tr>
						<tr>
						<td>01</td>
						<td><s:textfield name="entity.insuredAmount"
								cssClass="input-medium" readonly="true"/></td>
						
						<td><s:textfield name="entity.lossAmount"
								cssClass="input-medium"/></td>
						
						<td><s:textfield name="entity.deductible"
								cssClass="input-medium" /></td>
					</tr>
					<tr>
					<td>合計</td>
					<td><s:textfield id="total"
								cssClass="input-medium"  readonly="true" /></td>
					<td></td>
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