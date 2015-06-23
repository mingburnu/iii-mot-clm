<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />

<!DOCTYPE HTML>

<html>
<head>
<title>車險系統</title>

<script type="text/javascript"
	src='<c:url value="/resources/js/jquery-1.10.2.js" />'></script>
<script type="text/javascript"
	src='<c:url value="/resources/js/jquery-ui-1.10.4.custom.js" />'></script>

<script type="text/javascript">
	$(function() {

		//控制頁面的input是否disable
		<c:if test="${formModel.disableAllInput}">
		$("input:not(.return), select").each(function() {
			$(this).attr("disabled", true);
		});
		</c:if>

	});
</script>

<%-- show error message --%>
<s:if test="hasActionErrors()">
	<div class="alert alert-error">
		<button type="button" class="close" data-dismiss="alert"
			onclick="$('.alert-error').hide();">&times;</button>
		<strong>錯誤訊息</strong>
		<s:actionerror />
	</div>
</s:if>

<%-- show message --%>
<s:if test="hasActionMessages()">
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert"
			onclick="$('.alert-success').hide();">&times;</button>
		<strong>系統訊息</strong>
		<s:actionmessage />
	</div>
</s:if>

</head>

<body class="page-padding">

	<fieldset>
		<legend>狀態記錄/備註</legend>


		<s:form namespace="/crud" method="post" cssClass="form-horizontal">
			<table class="form-table">
				<s:hidden name="entity.id" />
				<s:hidden name="othId" />
				<s:hidden name="casedCode" />
				<s:hidden name="entity.code" />
				<tr>
					<td class="form-label"><label class="label">序號</label></td>
					<td><s:textfield name="entity.number" cssClass="input-medium" disabled="true" />
						<s:hidden name="entity.number" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">修改日期</label></td>
					<td><s:textfield name="entity.modifyDate" cssClass="input-medium" disabled="true" />
						<s:hidden name="entity.modifyDate" cssClass="input-medium"/></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">狀態</label></td>
					<td><s:textfield name="entity.accidentStatus.processStatus"	cssClass="input-medium" disabled="true" />
						<s:hidden name="entity.accidentStatus" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">次狀態</label></td>
					<td><s:textfield name="entity.accidentSecStatus.accidentSecStatus" cssClass="input-medium" disabled="true" />
						<s:hidden name="entity.accidentSecStatus" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">處理狀態</label></td>
					<td><s:textfield name="entity.remarkProcessStatus.remarkProcessStatus" cssClass="input-medium" disabled="true" />
						<s:hidden name="entity.remarkProcessStatus" cssClass="input-medium"/></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">備註</label></td>
					<td><s:textarea name="entity.descript" cssClass="input-medium"
							rows="8" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">修改人員</label></td>
					<td><s:textfield name="entity.person" cssClass="input-medium" disabled="true" />
						<s:hidden name="entity.person" cssClass="input-medium"/></td>
				</tr>
				<tr>
					<td colspan="2" class="form-button">
						<div class="btn-group">
							<c:choose>
								<c:when test="${empty entity.descript}">
									<s:submit cssClass="btn btn-success"
										action="claim.history.savedescript" value="新增" />
								</c:when>
								<c:otherwise>
									<s:submit cssClass="btn btn-success"
										action="claim.history.update" value="修改" />
								</c:otherwise>
							</c:choose>	
							<s:url namespace="/crud" action="claim.history.list" var="close">
								<s:param name="entity.code">${entity.code}</s:param>
								<s:param name="othId">${othId}</s:param>
								<s:param name="casedCode">${casedCode}</s:param>
							</s:url>
							<input type="button" class="btn btn-inverse return" value="關閉"
								onclick="document.location='<s:property value="#close" escape="false" />'" />
						</div>
					</td>
				</tr>
			</table>

			<%-- 創建者資訊 --%>
			<jsp:include page="/WEB-INF/jsp/layout/creatorInfo.jsp" />

		</s:form>
	</fieldset>

</body>
</html>