<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />
<!DOCTYPE HTML>
<html>
<head>

<title>車險系統</title>
</head>
<body class="page-padding">
	<fieldset>
		<legend>通訊錄</legend>
		<%-- 搜尋區塊 --%>
		<s:form action="claim.contact.list" namespace="/crud" method="post" cssClass="form-search">
			<table class="form-table">
			<s:hidden name="registerId" />
			<s:hidden name="cased" />
				
				<tr>
					<td colspan="4" align="right">
						<div class="btn-group">
							
							<s:url namespace="/crud" action="claim.contact.query"
								var="add">
								<s:param name="registerId">${registerId}</s:param>
								<s:param name="entity.regCode">${entity.regCode}</s:param>
								<s:param name="entity.caseCode">${entity.caseCode}</s:param>
								<s:param name="cased">${cased}</s:param>
							</s:url>
							
							<input type="button" class="btn btn-success" value="新增"
								onclick="document.location='<s:property value="#add" escape='false'/>'"  />
							
							<s:hidden name="cased" />
							<c:choose>							
								<c:when test="${empty cased}">
								<s:url namespace="/crud" action="claim.register.query"
								var="close">
								<s:param name="entity.id">${registerId}</s:param>		
								</s:url>
								<input type="button" class="btn btn-inverse return" value="關閉"
								onclick="document.location='<s:property value="#close" />'" />
									
								</c:when>
								<c:otherwise>
								<s:url namespace="/crud" action="claim.case.query"
								var="close">
								<s:param name="entity.id">${registerId}</s:param>		
								</s:url>
								<input type="button" class="btn btn-inverse return" value="關閉"
								onclick="document.location='<s:property value="#close" />'" />
								</c:otherwise>
							</c:choose>
							
						</div>
					</td>
				</tr>
			</table>
		</s:form>

		<%-- 分頁 --%>
		<table style="width: 100%">
			<tr>
				<td><jsp:include page="/WEB-INF/jsp/layout/pagination.jsp">
						<jsp:param name="namespace" value="/crud" />
						<jsp:param name="action" value="claim.contact.list" />
						<jsp:param name="pager" value="${ds.pager}" />
					</jsp:include></td>
			</tr>
		</table>

		<%-- List區塊 --%>
		<table class="table table-hover" style="width: 100%">
			<thead>
				<tr class="list-head">
					<th>類別</th>
					<th>客戶編號</th>
					<th>事故車號</th>
					<th>辦公室電話(區碼)</th>
					<th>辦公室電話</th>
					<th>聯絡人</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${ds.results}" varStatus="status" >
					<c:set var="editPage">
						<s:url namespace="/crud" action="claim.contact.query">
							<s:param name="entity.id">${item.id}</s:param>
							<s:param name="registerId">${registerId}</s:param>
							<s:param name="cased">${cased}</s:param>
							<s:param name="entity.regCode">${entity.regCode}</s:param>
							<s:param name="entity.caseCode">${entity.caseCode}</s:param>
						</s:url>
					</c:set>
					<tr class="list-item${status.index mod 2}"
						onmouseup="document.location='${editPage}'">
						<td>${item.type.type}</td>
						<td>${item.customerId}</td>
						<td>${item.accidentPlate}</td>
						<td>${item.officeTelArea}</td>
						<td>${item.officeTel}</td>
						<td>${item.contact}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
</body>
</html>