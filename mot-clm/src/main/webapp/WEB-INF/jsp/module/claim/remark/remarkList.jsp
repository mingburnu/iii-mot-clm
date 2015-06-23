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
		<legend>處理記錄</legend>

		<%-- 搜尋區塊 --%>
		<s:form action="claim.remark.list" namespace="/crud" method="post"
			cssClass="form-search">
			<table class="form-table">
			<s:hidden name="registId" />
			<s:hidden name="entity.caseCode"/>
			<s:hidden name="caseId" />
				<tr>
					<td colspan="4" align="right">
						<div class="btn-group">
							<c:choose>
								<c:when test="${empty caseId}">
									<s:url namespace="/crud" action="claim.register.query" var="close">
										<s:param name="entity.id">${registId}</s:param>
									</s:url>
								</c:when>
								<c:otherwise>
									<s:url namespace="/crud" action="claim.case.query" var="close">
										<s:param name="entity.id">${registId}</s:param>
									</s:url>
								</c:otherwise>
							</c:choose>
							<input type="button" class="btn btn-inverse return" value="關閉"
								onclick="document.location='<s:property value="#close" />'" />
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
						<jsp:param name="action" value="claim.remark.list" />
						<jsp:param name="pager" value="${ds.pager}" />
					</jsp:include></td>
			</tr>
		</table>

		<%-- List區塊 --%>
		<table class="table table-hover" style="width: 100%">
			<thead>
				<tr class="list-head">
					<th>報案編號</th>
					<th>賠案編號</th>
					<th>被保險人ID</th>
					<th>被保險人姓名</th>
					<th>出險原因</th>
					<th>處理狀態</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${ds.results}" varStatus="status" >
					<c:set var="editPage">
						<s:url namespace="/crud" action="claim.remark.query">
							<s:param name="entity.id">${item.id}</s:param>
							<s:param name="registId">${registId}</s:param>
							<s:param name="caseId">${caseId}</s:param>
						</s:url>
					</c:set>
					<tr class="list-item${status.index mod 2}" onmouseup="document.location='${editPage}'">
						<td>${item.regCode}</td>
						<td>${item.caseCode}</td>
						<td>${item.insuredId}</td>
						<td>${item.insuredName}</td>
						<td>${item.mainReason}</td>
						<td>${item.remarkProcessStatus.remarkProcessStatus}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
</body>
</html>