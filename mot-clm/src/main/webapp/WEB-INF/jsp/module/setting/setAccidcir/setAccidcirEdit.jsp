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

	<fieldset><legend>出險原因管理設定</legend>
		<s:form namespace="/crud" method="post" cssClass="form-horizontal">
			<table class="form-table">
				<s:hidden name="entity.id" />
				<tr>
					<td class="form-label"><label class="label label-important">產品別</label></td>
					<td><s:textfield name="entity.classId" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label label-important">出險原因代碼</label></td>
					<td><s:textfield name="entity.code" cssClass="input-medium" /></td>
				</tr>
				
				<tr>
					<td class="form-label"><label class="label label-important">出險原因中文全名</label></td>
					<td><s:textfield name="entity.localName" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">出險原因英文名稱</label></td>
					<td><s:textfield name="entity.engName" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">事故分類</label></td>
					<td>
						<s:select name="entity.accidcirType" cssClass="input-medium" list="@org.iii.module.setting.setAccidcir.enums.AccidcirType@values()" listKey="name()" listValue="accidcirType" />
					</td>
				</tr>
				<tr>
					<td colspan="2" class="form-button">
						<div class="btn-group">
							<c:choose>
								<c:when test="${empty entity.id}">
									<s:submit cssClass="btn btn-success" action="setting.setAccidcir.save" value="新增" />
								</c:when>
								<c:otherwise>
									<s:submit cssClass="btn btn-success" action="setting.setAccidcir.update" value="修改" />
								</c:otherwise>
							</c:choose>
							<s:submit cssClass="btn btn-danger" action="setting.setAccidcir.delete" value="刪除" />
							<input type="button" class="btn btn-inverse return" value="關閉" onclick="document.location='<s:url namespace="/crud" action="setting.setAccidcir.list" />'" />
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