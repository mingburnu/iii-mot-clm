<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<ul class="nav nav-pills">

	<s:url namespace="/crud" action="claim.attachment.list"
		var="attachment">
		<s:param name="entity.regId">${entity.id}</s:param>
		<s:param name="entity.code">${entity.code}</s:param>
	</s:url>
	<li><a href="<s:property value='#attachment' escape='false'/>">附件管理系統</a></li>

	<s:url namespace="/crud" action="claim.contact.list" var="contact">
		<s:param name="registerId">${entity.id}</s:param>
		<s:param name="entity.regCode">${entity.code}</s:param>
		<s:param name="entity.caseCode">${entity.caseCode}</s:param>
		<s:param name="cased">cased</s:param>

	</s:url>
	<li><a href="<s:property value='#contact' escape='false'/>">賠案通訊錄</a></li>


	<s:url namespace="/crud" action="claim.remark.list" var="remark">
		<s:param name="registId">${entity.id}</s:param>
		<s:param name="caseId">caseId</s:param>
		<s:param name="entity.regCode">${entity.code}</s:param>
		<s:param name="entity.caseCode">${entity.caseCode}</s:param>
		<s:param name="entity.insuredId">${entity.insuredId}</s:param>
		<s:param name="entity.insuredName">${entity.insuredName}</s:param>
		<s:param name="entity.accidcirDate">${entity.accidcirDate}</s:param>
		<s:param name="entity.mainReason">${entity.mainAccident}</s:param>
		<s:param name="entity.acceptedDate">${entity.acceptedDate}</s:param>
		<s:param name="entity.postDate">${entity.postDate}</s:param>
	</s:url>
	<li><a href="<s:property value="#remark" escape="false" />">賠案處理記錄</a></li>

	<s:url namespace="/crud" action="claim.history.list" var="history">
		<s:param name="entity.code">${entity.code}</s:param>
		<s:param name="othId">${entity.id}</s:param>
		<s:param name="casedCode">casedCode</s:param>
	</s:url>
	<li><a href="<s:property value="#history" escape="false" />">狀態記錄/備註</a></li>

</ul>