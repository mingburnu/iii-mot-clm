<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <s:set var="theme" value="'simple'" scope="page" /> --%>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />

<!DOCTYPE HTML>
<html>

<head>
	<title>車險系統</title>
</head>

<body class="page-padding">

	<fieldset><legend>單位管理</legend>
		<s:form namespace="/crud" method="post" cssClass="form-horizontal">
			<table class="form-table">
				<s:hidden name="entity.id" />
				<tr>
					<td class="form-label"><label class="label label-important">單位代碼</label></td>
					<td><s:textfield name="entity.code" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label label-important">單位名稱</label></td>
					<td><s:textfield name="entity.localName" cssClass="input-medium" /></td>
				</tr>				
				<tr>
					<td class="form-label"><label class="label">電話(國碼)</label></td>
					<td><s:textfield name="entity.telCty" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">電話(區碼)</label></td>
					<td><s:textfield name="entity.telArea" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">電話</label></td>
					<td><s:textfield name="entity.tel" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label label-important">負責人</label></td>
					<td><s:textfield name="entity.manager" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td colspan="2" class="form-button">
						<div class="btn-group">
							<c:choose>
								<c:when test="${empty entity.id}">
									<s:submit cssClass="btn btn-success" action="setting.setOffice.save" value="新增" />
								</c:when>
								<c:otherwise>
									<s:submit cssClass="btn btn-success" action="setting.setOffice.update" value="修改" />
								</c:otherwise>
							</c:choose>
							<s:submit cssClass="btn btn-danger" action="setting.setOffice.delete" value="刪除" />
							<input type="button" class="btn btn-inverse return" value="關閉" onclick="document.location='<s:url namespace="/crud" action="setting.setOffice.list" />'" />
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