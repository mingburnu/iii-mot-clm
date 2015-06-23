<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />

<!DOCTYPE HTML>
<html>

<head>
<title>車險系統</title>
</head>

<body class="page-padding">

	<fieldset>
		<legend>轉案管理</legend>

		<%-- 搜尋區塊 --%>
		<s:form action="setting.setAmountLimit.list" namespace="/crud"
			method="post" cssClass="form-search">
			<table class="form-table">
				<tr>
					<td class="form-label"><label class="label">產品別</label></td>
					<td><s:select headerValue="--Please select--" headerKey="0"
							name="entity.classId" cssClass="input-medium"
							list="dsClass.results" listKey="id" listValue="localName" /></td>
					<td class="form-label"><label class="label">單位別</label></td>
					<td><s:select headerValue="--Please select--" headerKey="0"
							name="entity.officeId" cssClass="input-medium"
							list="dsOffice.results" listKey="id" listValue="localName" /></td>
				</tr>
				<tr>
					<td colspan="4" align="right">
						<div class="btn-group">
							<s:submit cssClass="btn btn-info" value="搜尋" />
							<input type="button" class="btn btn-success" value="新增"
								onclick="document.location='<s:url namespace="/crud" action="setting.setAmountLimit.query" />'" />
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
						<jsp:param name="action" value="setting.setAmountLimit.list" />
						<jsp:param name="pager" value="${ds.pager}" />
					</jsp:include></td>
			</tr>
		</table>

		<%-- List區塊 --%>
		<table class="table table-hover" style="width: 100%">
			<thead>
				<tr class="list-head">
					<th>產品別</th>
					<th>單位別</th>
					<th>轉案單位</th>
					<th>金額</th>
					<th>描述</th>
				</tr>
			</thead>
			<tbody>
				<%--<c:forEach var="item" items="${ds.results}" varStatus="status">
					<c:set var="editPage">
						<s:url namespace="/crud" action="setting.setAmountLimit.query">
							<s:param name="entity.id">${item.id}</s:param>
						</s:url>
					</c:set>
					<sql:setDataSource var="datasource" driver="org.h2.Driver"
						url="jdbc:h2:mem:iii;DB_CLOSE_DELAY=-1" user="sa" password="" />

					<sql:query dataSource="${datasource}" var="result1">
SELECT LOCAL_NAME
FROM SET_CLASS
WHERE ID = ${item.classId};
</sql:query>
					<sql:query dataSource="${datasource}" var="result2">
SELECT LOCAL_NAME
FROM SET_OFFICE
WHERE ID = ${item.officeId};
</sql:query>
					<sql:query dataSource="${datasource}" var="result3">
SELECT LOCAL_NAME
FROM SET_OFFICE
WHERE ID = ${item.fromId};
</sql:query>
					<tr class="list-item${status.index mod 2}"
						onmouseup="document.location='${editPage}'">
						<c:forEach var="row" items="${result1.rows}">
							<td><c:out value="${row.LOCAL_NAME}" /></td>
						</c:forEach>
						<c:forEach var="row" items="${result2.rows}">
							<td><c:out value="${row.LOCAL_NAME}" /></td>
						</c:forEach>
						<c:forEach var="row" items="${result3.rows}">
							<td><c:out value="${row.LOCAL_NAME}" /></td>
						</c:forEach>
						<td>${item.amount}</td>
						<td>${item.descript}</td>
					</tr>
				</c:forEach> --%>
				<c:forEach var="item" items="${ds.results}" varStatus="status">
					<c:set var="editPage">
						<s:url namespace="/crud" action="setting.setAmountLimit.query">
							<s:param name="entity.id">${item.id}</s:param>
						</s:url>
					</c:set>
					<tr class="list-item${status.index mod 2}"
						onmouseup="document.location='${editPage}'">
						<td>${item.setClass.localName}</td>
						<td>${item.setOffice.localName}</td>
						<td>${item.setOfficeFrom.localName}</td>
						<td>${item.amount}</td>
						<td>${item.descript}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
</body>
</html>