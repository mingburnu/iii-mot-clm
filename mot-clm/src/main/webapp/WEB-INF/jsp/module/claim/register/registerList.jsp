<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript"
	src='<c:url value="/resources/js/jquery.fancybox.js" />'></script>
<script type="text/javascript"
	src='<c:url value="/resources/js/jquery.fancybox.pack.js" />'></script>
<link href='<c:url value="/resources/css/ui-fancybox/jquery.fancybox.css"/>'
	rel="stylesheet" type="text/css" media="screen" />
	
<script type="text/javascript">
$(function(){
	
	$('input[name="entity.mainAccidentName"]').bind('click', function() {
		$.fancybox.open({
			href : '<c:url value="/append/claim/getAccidcir.jsp"/>',
			type : 'iframe',
			padding : 20,
			scrolling : 'auto'
		});
	});
	
	$('input[name="entity.processUserCode"]').bind('click', function() {
		$.fancybox.open({
			href : '<c:url value="/append/claim/getUser.jsp"/>',
			type : 'iframe',
			padding : 20,
			scrolling : 'auto'
		});
	});
	
});

	
	function setUserData(code,name,office){
		$('input[name="entity.processUserCode"]').val(code);		
	}
	
	function setMainAccident(code,name){
		$('input[name="entity.mainAccidentName"]').val(name);
	}

</script>


<style type="text/css">
.divMark1 {
	border: 1px solid #dddddd;
	width: 95%;
	background-color: #F4F2F2;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body class="page-padding">
	<fieldset>
		<legend>報案管理</legend>
		<div class="divMark1">
			<%-- 搜尋區塊 --%>
			<s:form action="claim.register.list" namespace="/crud" method="post"
				cssClass="form-search">
				<table class="form-table">
					<tr>
						<td class="form-label"><s:select name="selectKey"
								cssClass="input-medium"
								list="#{'entity.code':'報案編號','entity.caseCode':'賠案編號','entity.accidentPeopleId':'事故人ID','entity.insuredId':'被保險人ID','entity.policyCode':'保單號碼','entity.plate':'車牌號碼','entity.driver':'駕駛ID'}"
								listKey="key" listValue="value" /></td>
						<td><s:textfield name="selectValue" cssClass="input-medium" /></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td class="form-label">理賠經辦:</td>
						<td><s:textfield name="entity.processUserCode" cssClass="input-medium" style="background-color : F1E179"/></td>
						<td class="form-label">出險期間:</td>
						<td><s:textfield name="accidcirDateMin"
								cssClass="input-medium" />~<s:textfield name="accidcirDateMax"
								cssClass="input-medium" /></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td class="form-label">事故原因:</td>
						<td><s:textfield name="entity.mainAccidentName"
								cssClass="input-medium" style="background-color : F1E179"/></td>
						<td class="form-label">受理日期:</td>
						<td><s:textfield name="acceptedDateMin"
								cssClass="input-medium" />~<s:textfield name="acceptedDateMax"
								cssClass="input-medium" /></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="6" align="right">
							<div class="btn-group">
								<s:submit cssClass="btn btn-info" value="搜尋" />
								<input type="button" class="btn btn-success" value="新增"
									onclick="document.location='<s:url namespace="/crud" action="claim.register.query" />'" />
							</div>
						</td>
					</tr>
				</table>
			</s:form>
		</div>

		<%-- 分頁 --%>
		<table style="width: 100%">
			<tr>
				<td><jsp:include page="/WEB-INF/jsp/layout/pagination.jsp">
						<jsp:param name="namespace" value="/crud" />
						<jsp:param name="action" value="claim.register.list" />
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
					<th>車號</th>
					<th>事故人</th>
					<th>事故日</th>
					<th>事故原因</th>
					<th>處理狀態</th>
					<th>受理日</th>
					<th>立案日</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="item" items="${ds.results}" varStatus="status">
					<c:set var="editPage">
						<s:url namespace="/crud" action="claim.register.query">
							<s:param name="entity.id">${item.id}</s:param>
						</s:url>
					</c:set>
					<tr class="list-item${status.index mod 2}"
						onmouseup="document.location='${editPage}'">
						<td>${item.code}</td>
						<td>${item.caseCode}</td>
						<td>${item.plate}</td>
						<td>${item.accidentPeopleId}${item.accidentPeopleName}</td>
						<td><joda:format value="${item.accidcirDate}"  pattern="yyyy/MM/dd"/></td>
						<td>${item.mainAccidentName}</td>
						<td>${item.accidentStatus.processStatus}</td>
						<td><joda:format value="${item.acceptedDate}" pattern="yyyy/MM/dd" /></td>
						<td><joda:format value="${item.postDate}" pattern="yyyy/MM/dd" /></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</fieldset>
</body>
</html>