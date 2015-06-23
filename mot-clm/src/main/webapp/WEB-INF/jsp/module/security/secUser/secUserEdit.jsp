<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <s:set var="theme" value="'simple'" scope="page" /> --%>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />

<!DOCTYPE HTML>
<html>

<head>

<script type="text/javascript">
$(function(){
	
	$('#cool').bind('click', function(){
		
		$('input[name="entity.userCode"]').val("carol");
		$('input[name="entity.userPassword"]').val("sa123456");
		$('input[name="entity.userName"]').val("經辦5");
		$('input[name="entity.workOffice"]').val("台北總部");
		$('input[name="entity.telCty"]').val("886");
		$('input[name="entity.telArea"]').val("02");
		$('input[name="entity.tel"]').val("22316666");
		$("#select").val("PROCESS");
	})
	
})


</script>

	<title>車險系統</title>
</head>

<body class="page-padding">

	<fieldset><legend id="cool">使用者設定</legend>
		<s:form namespace="/crud" method="post" cssClass="form-horizontal">
			<table class="form-table">
				<s:hidden name="entity.id" />
				<tr>
					<td class="form-label"><label class="label label-important">使用者代號</label></td>
					<td><s:textfield name="entity.userCode" cssClass="input-medium" /></td>
				</tr>
				
				<%-- 新增頁增加密碼欄位 --%>
				<c:if test="${empty entity.id}">
				<tr>
					<td class="form-label"><label class="label label-important">使用者密碼</label></td>
					<td><s:password name="entity.userPassword" cssClass="input-medium" /></td>
				</tr>
				</c:if>
				
				<tr>
					<td class="form-label"><label class="label">使用者名稱</label></td>
					<td><s:textfield name="entity.userName" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">工作單位</label></td>
					<td><s:textfield name="entity.workOffice" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">電話(國碼)</label></td>
					<td><s:textfield name="entity.telCty" cssClass="input-small" maxlength="3" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">電話(區碼)</label></td>
					<td><s:textfield name="entity.telArea" cssClass="input-small" maxlength="2" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">電話</label></td>
					<td><s:textfield name="entity.tel" cssClass="input-medium" maxlength="8" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">類別</label></td>
					<td><s:select name="entity.userType" id="select"
								cssClass="input-medium"
								list="@org.iii.core.security.secUser.enums.UserType@values()"
								listKey="name()" listValue="userType" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">停用日</label></td>
					<td><s:textfield name="entity.stopDate" cssClass="input-medium" maxlength="8" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">狀態</label></td>
					<td>
						<s:select name="entity.status" cssClass="input-medium" list="@org.iii.core.enums.SystemStatus@values()" listKey="name()" listValue="localName2" />
					</td>
				</tr>
				<tr>
					<td colspan="2" class="form-button">
						<div class="btn-group">
							<c:choose>
								<c:when test="${empty entity.id}">
									<s:submit cssClass="btn btn-success" action="security.secUser.save" value="新增" />
								</c:when>
								<c:otherwise>
									<s:submit cssClass="btn btn-success" action="security.secUser.update" value="修改" />
								</c:otherwise>
							</c:choose>
							<s:submit cssClass="btn btn-danger" action="security.secUser.delete" value="刪除" />
							<input type="button" class="btn btn-inverse return" value="關閉" onclick="document.location='<s:url namespace="/crud" action="security.secUser.list" />'" />
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