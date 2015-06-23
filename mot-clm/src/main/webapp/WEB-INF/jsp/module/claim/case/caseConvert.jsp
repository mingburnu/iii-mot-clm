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
<title>理算賠款</title>
</head>
<body class="page-padding">
	<fieldset>
		<legend>理算賠款</legend>
		<s:form namespace="/crud" action="claim.estimate.save" method="post"
			cssClass="form-horizontal">
			<s:hidden name="entity.id" />
			<s:hidden name="entity.insuredId" />
			<s:hidden name="entity.insuredName" />
			<s:hidden name="entity.accidcirDate" />
			<s:hidden name="entity.plate" />
			<s:hidden name="entity.accident" />

			<!-- initial value -->
			<s:hidden name="entity.feeStatus"></s:hidden>
			<s:hidden name="entity.insuredPeriod"></s:hidden>
			<s:hidden name="entity.processUserCode"></s:hidden>
			<s:hidden name="entity.insuredAmount" value="800"></s:hidden>
			<s:hidden name="entity.insuredType" value="乘客責任險"></s:hidden>
			<s:hidden name="entity.damageObjectOwner" value="陳水扁"></s:hidden>
			<s:hidden name="entity.currency"></s:hidden>
			<s:hidden name="entity.estimatedAmount" value="15000"></s:hidden>
			<s:hidden name="entity.totalEstimatedAmount" value="15000"></s:hidden>
			<hr>
			<table>
				<tbody>
				<tr>
					<td><h5>賠案號碼</h5></td>
					<td><s:textfield name="entity.caseCode" cssClass="input-small"
							readonly="true" /></td>

					<td><h5>保單號碼</h5></td>
					<td><s:textfield name="entity.policyCode"
							cssClass="input-small" readonly="true" /></td>
				</tr>
				</tbody>
			</table>
			
			<s:url namespace="/crud" action="claim.case.query" var="close">
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