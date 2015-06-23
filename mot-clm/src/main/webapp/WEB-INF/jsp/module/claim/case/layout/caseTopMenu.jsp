<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
</head>
<ul class="nav nav-tabs"  id="topMenu">
	<li class="active"><s:url namespace="/crud"	action="claim.case.query" 
		var="case">
			<s:param name="entity.id">${entity.id}</s:param>
			<s:param name="entity.caseCode">${entity.caseCode}</s:param>
		</s:url> <a href="<s:property value="#case"  escape='false'/>">賠案處理</a></li>
	<li><s:url namespace="/crud" action="claim.estimate.query"	var="query">
			<s:param name="entity.id">${entity.id}</s:param>
			<s:param name="entity.caseCode">${entity.caseCode}</s:param>
		</s:url> <a href="<s:property value="#query" escape='false'/>">理算賠款</a></li>

	<li style="display:none">
			<s:url namespace="/crud" action="claim.settle.query" var="settle">
			<s:param name="entity.caseCode">${entity.caseCode}</s:param>
			</s:url>
		<a href="<s:property value="#settle" escape="false" />">理算簽結</a>	
	</li>

	
	<li><s:url namespace="/web" action="claim.case.policy"
			var="policy">
			<s:param name="entity.id">${entity.id}</s:param>
			<s:param name="entity.insuredId">${entity.insuredId}</s:param>
			<s:param name="entity.insuredName">${entity.insuredName}</s:param>
			<s:param name="entity.plate">${entity.plate}</s:param>
			<s:param name="entity.caseCode">${entity.caseCode}</s:param>
		</s:url> <a href="<s:property value="#policy" escape='false' />">保單資料</a></li>
</ul>
</html>