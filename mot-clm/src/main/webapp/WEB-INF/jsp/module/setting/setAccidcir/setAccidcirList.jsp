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

	<fieldset><legend>出險原因管理</legend>

		<%-- 搜尋區塊 --%>
		<s:form action="setting.setAccidcir.list" namespace="/crud" method="post" cssClass="form-search">
			<table class="form-table">
				<tr>
					<td class="form-label"><label class="label">出險原因代號</label></td>
					<td><s:textfield name="entity.code" cssClass="input-medium search-query" /></td>
					<td class="form-label"><label class="label">出險原因名稱</label></td>
					<td><s:textfield name="entity.localName" cssClass="input-medium search-query" /></td>
				</tr>
				<tr>
					<td colspan="4" align="right">
						<div class="btn-group">
							<s:submit cssClass="btn btn-info" value="搜尋" />
							<input type="button" class="btn btn-success" value="新增" onclick="document.location='<s:url namespace="/crud" action="setting.setAccidcir.query" />'" />
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
						<jsp:param name="action" value="setting.setAccidcir.list" />
						<jsp:param name="pager" value="${ds.pager}" />
					</jsp:include>
				</td>
			</tr>
		</table>
		
		<%-- List區塊 --%>
		<table class="table table-hover" style="width:100%">
			<thead>
				<tr class="list-head">
					<th>產品別</th><th>出險原因代號</th><th>出險原因中文全名</th><th>出險原因英文名稱</th><th>事故分類</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${ds.results}" varStatus="status">
					<c:set var="editPage" scope="page">
						<s:url namespace="/crud" action="setting.setAccidcir.query">
							<s:param name="entity.id">${item.id}</s:param>
						</s:url>
					</c:set>
					<tr class="list-item${status.index mod 2}" onmouseup="document.location='${editPage}'">
						<td>${item.classId}</td>
						<td>${item.code}</td>
						<td>${item.localName}</td>
						<td>${item.engName}</td>
						<td>${item.accidcirType.accidcirType}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
</body>
</html>