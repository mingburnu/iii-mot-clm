<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <s:set var="theme" value="'simple'" scope="page" /> --%>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />

<!DOCTYPE HTML>
<html>

<head>
<title>車險系統</title>
</head>

<body class="page-padding">

	<fieldset>
		<legend>分層授權管理</legend>
		<s:form namespace="/crud" method="post" cssClass="form-horizontal">
			<table class="form-table">
				<s:hidden name="entity.id" />
				<tr>
					<td class="form-label"><label class="label label-important">產品別</label></td>
					<td>
						<s:select name="entity.classId" cssClass="input-medium" list="dsClass.results" listKey="id" listValue="localName" />
					</td>
				</tr>
				<tr>
					<td class="form-label"><label class="label label-important">單位別</label></td>
					<td>
						<s:select name="entity.officeId" cssClass="input-medium" list="dsOffice.results" listKey="id" listValue="localName" />
					</td>
				</tr>
				<tr>
					<td class="form-label"><label class="label label-important">角色</label></td>
					<td><s:textfield name="entity.role" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label label-important">業務來源</label></td>
					<td>
						<s:select name="entity.busType" cssClass="input-medium" list="@org.iii.module.setting.setAuthority.enums.BusType@values()" listKey="name()" listValue="busType" />
					</td>
				</tr>
				<tr>
					<td class="form-label"><label class="label label-important">審核類型</label></td>
					<td><s:textfield name="entity.auditType" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">核決金額(起)</label></td>
					<td><s:textfield name="entity.minAmount" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">核決金額(迄)</label></td>
					<td><s:textfield name="entity.maxAmount" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">備註說明</label></td>
					<td><s:textfield name="entity.descript" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td colspan="2" class="form-button">
						<div class="btn-group">
							<c:choose>
								<c:when test="${empty entity.id}">
									<s:submit cssClass="btn btn-success"
										action="setting.setAuthority.save" value="新增" />
								</c:when>
								<c:otherwise>
									<s:submit cssClass="btn btn-success"
										action="setting.setAuthority.update" value="修改" />
								</c:otherwise>
							</c:choose>
							<s:submit cssClass="btn btn-danger"
								action="setting.setAuthority.delete" value="刪除" />
							<input type="button" class="btn btn-inverse return" value="關閉"
								onclick="document.location='<s:url namespace="/crud" action="setting.setAuthority.list" />'" />
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