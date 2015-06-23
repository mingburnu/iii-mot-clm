<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<ul class="nav nav-pills">
	<s:url namespace="/crud" action="claim.attachment.query" var="attachment"></s:url>
	<li><a href="<s:property value='#attachment' escape='false'/>">賠款資料更正</a></li>
	<s:url namespace="/crud" action="claim.contact.list" var="contact"></s:url>
	<li><a href="<s:property value='#contact' escape='false'/>">補印匯款通知</a></li>
	<s:url namespace="/crud" action="claim.remark.list" var="remark"></s:url>
	<li><a href="<s:property value="#remark" escape="false" />">補印賠付通知單</a></li>
</ul>