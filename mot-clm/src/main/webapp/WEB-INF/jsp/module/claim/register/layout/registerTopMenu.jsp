<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<ul class="nav nav-tabs" id="topMenu">
	<li class="active"><s:url namespace="/crud"	action="claim.register.query" 
		var="register">
			<s:param name="entity.id">${entity.id}</s:param>
		</s:url> <a href="<s:property value="#register" />">報案</a></li>
	<li><s:url namespace="/web" action="claim.register.policy"
			var="policy">
			<s:param name="entity.id">${entity.id}</s:param>
			<s:param name="entity.insuredId">${entity.insuredId}</s:param>
			<s:param name="entity.insuredName">${entity.insuredName}</s:param>
			<s:param name="entity.plate">${entity.plate}</s:param>
		</s:url> <a href="<s:property value="#policy" escape='false' />">保單資料</a></li>
</ul>