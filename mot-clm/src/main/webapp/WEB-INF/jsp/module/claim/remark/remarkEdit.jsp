<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp"/>
<jsp:include page="/WEB-INF/jsp/layout/css.jsp"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>車險系統</title>
</head>
<body class="page-padding">
	<fieldset>
		<legend>處理記錄設定</legend>
		<s:form namespace="/crud" method="post" cssClass="form-horizontal">
			<table class="form-table">
				<s:hidden name="entity.id" />
				<s:hidden name="registId" />
				<s:hidden name="caseId" />
				<tr>
					<s:url namespace="/crud" action="claim.remark.list" var="remark">
						<s:param name="registId">${entity.id}</s:param>
						
					</s:url>
					<td class="form-label"><label class="label label-important">報案編號</label></td>
					<td><s:textfield name="entity.regCode" cssClass="input-medium" readonly="true" /></td>
				</tr>
				<tr>	
					<td class="form-label"><label class="label label-important">賠案編號</label></td>
					<td><s:textfield name="entity.caseCode" cssClass="input-medium" readonly="true" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">被保險人ID</label></td>
					<td><s:textfield name="entity.insuredId" cssClass="input-medium" readonly="true" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">被保險人姓名</label></td>
					<td><s:textfield name="entity.insuredName" cssClass="input-medium" readonly="true" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">出險日期</label></td>
					<td><s:textfield name="entity.accidcirDate" cssClass="input-medium" readonly="true"></s:textfield></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">出險原因</label></td>
					<td><s:textfield name="entity.mainReason" cssClass="input-medium" readonly="true"></s:textfield></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">處理狀態</label></td>
					<td><s:select name="entity.remarkProcessStatus" cssClass="input-medium" list="@org.iii.module.claim.remark.enums.RemarkProcessStatus@values()" listKey="name()" listValue="remarkProcessStatus" readonly="true" /></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">受理日期</label></td>
					<td><s:textfield name="entity.acceptedDate" cssClass="input-medium" readonly="true"></s:textfield></td>
				</tr>
				<tr>
					<td class="form-label"><label class="label">立案日</label>
					<td><s:textfield name="entity.postDate" cssClass="input-medium" readonly="true" /></td>
				</tr>
				<tr>
					<td colspan="2" class="form-button">
						<div class="btn-group">
							<c:choose>
								<c:when test="${empty registId}${empty caseId}">
									<s:submit cssClass="btn btn-success" action="claim.remark.save" value="新增" />
								</c:when>
								<c:otherwise>
									<s:submit cssClass="btn btn-success" action="claim.remark.update" value="修改" />
								</c:otherwise>
							</c:choose>
<%-- 							<s:submit cssClass="btn btn-danger" action="claim.remark.delete" value="刪除" /> --%>
							
								<s:url namespace="/crud" action="claim.remark.list" var="close">
									<s:param name="registId">${registId}</s:param>
									<s:param name="caseId">${caseId}</s:param>
									<s:param name="entity.id">${entity.id}</s:param>
									<s:param name="entity.regCode">${entity.regCode}</s:param>
								    <s:param name="entity.caseCode">${entity.caseCode}</s:param>
									<s:param name="entity.insuredId">${entity.insuredId}</s:param>
									<s:param name="entity.insuredName">${entity.insuredName}</s:param>
									<s:param name="entity.accidcirDate">${entity.accidcirDate}</s:param>
									<s:param name="entity.mainReason">${entity.mainReason}</s:param>
									<s:param name="entity.acceptedDate">${entity.acceptedDate}</s:param>
									<s:param name="entity.postDate">${entity.postDate}</s:param>
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