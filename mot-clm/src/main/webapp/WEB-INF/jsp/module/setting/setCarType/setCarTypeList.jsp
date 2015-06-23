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

	<fieldset><legend>車種管理</legend>

		<%-- 搜尋區塊 --%>
		<s:form action="setting.setCarType.list" namespace="/crud" method="post" cssClass="form-search">
			<table class="form-table">
				<tr>
					<td class="form-label"><label class="label">車種代碼</label></td>
					<td><s:textfield name="entity.typeCode" cssClass="input-medium search-query" /></td>
					<td class="form-label"><label class="label">車種名稱</label></td>
					<td><s:textfield name="entity.typeName" cssClass="input-medium search-query" /></td>
				</tr>
				<tr>
					<td colspan="4" align="right">
						<div class="btn-group">
							<s:submit cssClass="btn btn-info" value="搜尋" />
							<input type="button" class="btn btn-success" value="新增" onclick="document.location='<s:url namespace="/crud" action="setting.setCarType.query" />'" />
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
						<jsp:param name="action" value="setting.setCarType.list" />
						<jsp:param name="pager" value="${ds.pager}" />
					</jsp:include>
				</td>
			</tr>
		</table>
		
		<%-- List區塊 --%>
		<table class="table table-hover" style="width:100%">
			<thead>
				<tr class="list-head">
					<th>產品別 </th><th>車種代碼</th><th>車種名稱</th><th>使用方式</th><th>乘載人數上限</th><th>乘載噸數上限</th><th>是否需要車籍資料</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${ds.results}" varStatus="status">
					<c:set var="editPage">
						<s:url namespace="/crud" action="setting.setCarType.query">
							<s:param name="entity.id">${item.id}</s:param>
						</s:url>
					</c:set>
					<tr class="list-item${status.index mod 2}" onmouseup="document.location='${editPage}'">
						<td>${item.setClass.localName}</td>
						<td>${item.typeCode}</td>
						<td>${item.typeName}</td>
						<td>${item.usage.carUsage}</td>
						<td>${item.peLimit}</td>
						<td>${item.toLimit}</td>
						<td>${item.needCarinfo.localName3}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
</body>
</html>