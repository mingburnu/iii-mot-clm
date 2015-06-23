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

	<fieldset><legend>產品別設定</legend>
		<s:form namespace="/crud" method="post" cssClass="form-horizontal">
			<table class="form-table">
				<s:hidden name="entity.id" />
				<tr>
					<td class="form-label"><label class="label label-important">產品別代碼</label></td>
					<td><s:textfield name="entity.code" cssClass="input-medium" /></td>
				</tr>
		
				<tr>
					<td class="form-label"><label class="label">產品別全名</label></td>
					<td><s:textfield name="entity.localName" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">產品別英文</label></td>
					<td><s:textfield name="entity.engName" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">產品別描述</label></td>
					<td><s:textfield name="entity.descript" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td colspan="2" class="form-button">
						<div class="btn-group">
							<c:choose>
								<c:when test="${empty entity.id}">
									<s:submit cssClass="btn btn-success" action="setting.setClass.save" value="新增" />
								</c:when>
								<c:otherwise>
									<s:submit cssClass="btn btn-success" action="setting.setClass.update" value="修改" />
									<s:submit cssClass="btn btn-danger" action="setting.setClass.delete" value="刪除" />
								</c:otherwise>
							</c:choose>							
							<input type="button" class="btn btn-inverse return" value="關閉" onclick="document.location='<s:url namespace="/crud" action="setting.setClass.list" />'" />
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