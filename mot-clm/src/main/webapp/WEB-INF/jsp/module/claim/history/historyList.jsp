<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>


<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />

<!DOCTYPE HTML>
<html>

<head>
<title>車險系統</title>
<script type="text/javascript" src='<c:url value="/resources/js/jquery-1.10.2.js" />'></script>
<script type="text/javascript" src='<c:url value="/resources/js/jquery-ui-1.10.4.custom.js" />'></script>

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
		<button type="button" class="close" data-dismiss="alert" onclick="$('.alert-error').hide();">&times;</button>
		<strong>錯誤訊息</strong>
		<s:actionerror />
	</div>
</s:if>

<%-- show message --%>
<s:if test="hasActionMessages()">
	<div class="alert alert-success">
		<button type="button" class="close" data-dismiss="alert" onclick="$('.alert-success').hide();">&times;</button>
		<strong>系統訊息</strong>
		<s:actionmessage/>
	</div>
</s:if>

</head>

<body class="page-padding">

	<fieldset>
		<legend>狀態記錄/備註</legend>

		<%-- 關閉區塊 --%>
		<s:form action="claim.history.list" namespace="/crud" method="post"
			cssClass="form-search">
			<table class="form-table">
				<tr>
					<td colspan="4" align="right">
					<s:hidden name="casedCode" />
						<c:choose>
							<c:when test="${empty casedCode}">
								<s:url namespace="/crud" action="claim.register.query" var="close">
									<s:param name="entity.code">${entity.code}</s:param>
									<s:param name="entity.id">${othId}</s:param>
								</s:url>
								<div class="btn-group">
									<input type="button" class="btn btn-inverse return" value="關閉" onclick="document.location='<s:property value="#close" escape="false" />'" />
								</div>
							</c:when>
							<c:otherwise>
								<s:url namespace="/crud" action="claim.case.query" var="close">
									<s:param name="entity.code">${entity.code}</s:param>
									<s:param name="entity.id">${othId}</s:param>
								</s:url>
								<div class="btn-group">
									<input type="button" class="btn btn-inverse return" value="關閉" onclick="document.location='<s:property value="#close" escape="false" />'" />
								</div>
							</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</table>
		</s:form>

		<%-- 分頁 --%>
		<table style="width: 100%">
			<tr>
				<td><jsp:include page="/WEB-INF/jsp/layout/pagination.jsp">
						<jsp:param name="namespace" value="/crud" />
						<jsp:param name="action" value="claim.history.list" />
						<jsp:param name="pager" value="${ds.pager}" />
					</jsp:include></td>
			</tr>
		</table>

		<%-- List區塊 --%>
		<table class="table table-hover" style="width: 100%">
			<thead>
				<tr class="list-head">
					<th>序號</th>
					<th>修改日期</th>
					<th>狀態</th>
					<th>次狀態</th>
					<th>處理狀態</th>
					<th>備註</th>
					<th>修改人員</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${ds.results}" varStatus="status">
					<tr class="list-item${status.index mod 2}">
						<td>${item.number}</td>
						<td><joda:format value="${item.modifyDate}" pattern="yyyy/MM/dd" /></td>
						<td>${item.accidentStatus.processStatus}</td>
						<td>${item.accidentSecStatus.accidentSecStatus}</td>
						<td>${item.remarkProcessStatus.remarkProcessStatus}</td>
						<td><c:choose>
								<c:when test="${empty item.descript}">
									<s:url namespace="/crud" action="claim.history.query" var="add">
										<s:param name="entity.id">${item.id}</s:param>
										<s:param name="entity.code">${entity.code}</s:param>
										<s:param name="othId">${othId}</s:param>
										<s:param name="casedCode">${casedCode}</s:param>
									</s:url>
									<input type="button" class="btn btn-success" value="新增"
										onclick="document.location='<s:property value="#add" escape="false" />'" />
								</c:when>
								<c:otherwise>
									<s:url namespace="/crud" action="claim.history.query"
										var="update">
										<s:param name="entity.id">${item.id}</s:param>
										<s:param name="entity.code">${entity.code}</s:param>
										<s:param name="othId">${othId}</s:param>
										<s:param name="casedCode">${casedCode}</s:param>
									</s:url>
									<input type="button" class="btn btn-success" value="修改"
										onclick="document.location='<s:property value="#update" escape="false" />'" />
								</c:otherwise>
							</c:choose></td>
						<td>${item.person}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>

</body>
</html>