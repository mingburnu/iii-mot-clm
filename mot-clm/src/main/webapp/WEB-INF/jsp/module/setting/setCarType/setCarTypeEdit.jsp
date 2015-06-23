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

	<fieldset><legend>車種管理設定</legend>
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
					<td class="form-label"><label class="label label-important">車種代碼</label></td>
					<td><s:textfield name="entity.typeCode" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label label-important">車種名稱</label></td>
					<td><s:textfield name="entity.typeName" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">使用方式</label></td>
					<td>
						<s:select name="entity.usage" cssClass="input-medium" list="@org.iii.module.setting.setCarType.enums.CarUsage@values()" listKey="name()" listValue="carUsage" />
					</td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">乘載人數上限</label></td>
					<td><s:textfield name="entity.peLimit" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">乘載噸數上限</label></td>
					<td><s:textfield name="entity.toLimit" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">是否需要車籍資料</label></td>
					<td>
						<s:select name="entity.needCarinfo" cssClass="input-medium" list="@org.iii.core.enums.SystemStatus@values()" listKey="name()" listValue="localName3" />
					</td>
				</tr>
				<tr>
					<td colspan="2" class="form-button">
						<div class="btn-group">
							<c:choose>
								<c:when test="${empty entity.id}">
									<s:submit cssClass="btn btn-success" action="setting.setCarType.save" value="新增" />
								</c:when>
								<c:otherwise>
									<s:submit cssClass="btn btn-success" action="setting.setCarType.update" value="修改" />
									<s:submit cssClass="btn btn-danger" action="setting.setCarType.delete" value="刪除" />
								</c:otherwise>
							</c:choose>							
							<input type="button" class="btn btn-inverse return" value="關閉" onclick="document.location='<s:url namespace="/crud" action="setting.setCarType.list" />'" />
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