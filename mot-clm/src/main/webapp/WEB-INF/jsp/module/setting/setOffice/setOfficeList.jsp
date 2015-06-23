<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />

<!DOCTYPE HTML>
<html>

<head>
	<title>車險系統</title>
</head>

<body class="page-padding">

	<fieldset><legend>單位管理</legend>

		<%-- 搜尋區塊 --%>
		<s:form action="setting.setOffice.list" namespace="/crud" method="post" cssClass="form-search">
			<table class="form-table">
				<tr>
					<td class="form-label"><label class="label">單位代碼</label></td>
					<td><s:textfield name="entity.code" cssClass="input-medium search-query" /></td>
					<td class="form-label"><label class="label">單位全名</label></td>
					<td><s:textfield name="entity.localName" cssClass="input-medium search-query" /></td>
				</tr>
				<tr>
					<td colspan="4" align="right">
						<div class="btn-group">
							<s:submit cssClass="btn btn-info" value="搜尋" />
							<input type="button" class="btn btn-success" value="新增" onclick="document.location='<s:url namespace="/crud" action="setting.setOffice.query" />'" />
						</div>
					</td>
				</tr>
			</table>
		</s:form>

		<%-- 分頁 --%>
		<table style="width:100%">
			<tr>
				<td>
					<jsp:include page="/WEB-INF/jsp/layout/pagination.jsp">
						<jsp:param name="namespace" value="/crud" />
						<jsp:param name="action" value="setting.setOffice.list" />
						<jsp:param name="pager" value="${ds.pager}" />
					</jsp:include>
				</td>
			</tr>
		</table>
		
		<%-- List區塊 --%>
		<table class="table table-hover" style="width:100%">
			<thead>
				<tr class="list-head">
					<th>單位代碼</th><th>單位名稱</th><th>電話(國碼)</th><th>電話(區碼)</th><th>電話</th><th>負責人</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${ds.results}" varStatus="status">
					<c:set var="editPage">
						<s:url namespace="/crud" action="setting.setOffice.query">
							<s:param name="entity.id">${item.id}</s:param>
						</s:url>
					</c:set>
					<tr class="list-item${status.index mod 2}" onmouseup="document.location='${editPage}'">
						<td>${item.code}</td>
						<td>${item.localName}</td>
						<td>${item.telCty}</td>
						<td>${item.telArea}</td>
						<td>${item.tel}</td>
						<td>${item.manager}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
</body>
</html>