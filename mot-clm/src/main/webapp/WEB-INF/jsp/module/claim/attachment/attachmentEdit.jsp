<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
				var deleteButton = $("button#delete").length;
				if (deleteButton == 0) {
					$("button[id='remove']").remove();
					$("button[id='clearSel']").remove();
					$("input:checkbox[name='markupHead']").remove();
				}
			});
	
	$(document).ready(function() {
				var regId =<%out.print(Long.parseLong(request.getParameter("entity.regId")));%>;
				var code="<%out.print(request.getParameter("entity.code"));%>";
				var count=$("input[name='markup']").length;
				var pager=$("div[class='pagination'] ul li").length;
				var pageLocation=pager-2;
				var offSet= (pageLocation-1)*10;
				if(count==0 && pager>=3){document.location = "<c:url value='/crud/claim.attachment.list.action?entity.regId='/>"+ regId+"&entity.code="+code+"&pager.offset="+ offSet+"&pager.currentPage="+pageLocation;}
			});
	
	$(document).ready(function() {
		var count=$("input[name='markup']").length;
		$("input:checkbox[name='markupHead']").click(function(){
			if($("input:checkbox[name='markupHead']").is(':checked'))
			  {$("input:checkbox[name='markup']").prop("checked", true);
			  		for(var i=0;i<count;i++){
					if($("input:checkbox[name='markup']:eq("+i+")").is(":checked")){
						var value=$("input:checkbox[name='markup']:eq("+i+")").val();
					$("input[name='selected']:eq("+i+")").val(value);
					}else{
						$("input[name='selected']:eq("+i+")").val('');
						}
					}
			}else{
			  $("input:checkbox[name='markup']").prop("checked", false);
					for(var i=0;i<count;i++){
					if($("input:checkbox[name='markup']:eq("+i+")").is(":checked")){
						var value=$("input:checkbox[name='markup']:eq("+i+")").val();
					$("input[name='selected']:eq("+i+")").val(value);
					}else{
						$("input[name='selected']:eq("+i+")").val('');
						}
					}
			}
		});
	});
	
	$(document).ready(function(){
	    $("table#pager tr td a").each(function(){
	        this.href = this.href.replace("XXX&?", "&");
	    });
	});
	
	$(document).ready(
			function() {
				$("#transfer").click(
						function() {
							$.post($("#claim_attachment_save").attr("action"),
									$("#claim_attachment_save").serialize(),
									function() {
										$("#claim_attachment_save").submit();
									});
						});
			});

	$(document).ready(
			function() {
				$("#display").click(
						function() {
							$.post($("#claim_attachment_list").attr("action"),
									$("#claim_attachment_list").serialize(),
									function() {
										$("#claim_attachment_list").submit();
									});
						});
			});

	$(document).ready(function() {
		$("#clearSel").click(
				function() {
					if($("input[name='markup']:checked").length!=0){
						$.post($("#claim_attachment_deleteSelected").attr(
						"action"), $("#claim_attachment_deleteSelected")
						.serialize(), function() {
					$("#claim_attachment_deleteSelected").submit();
						});
						multiRefresh();
						}
			});
	});
	
	$(document).ready(
			function() {
				$("#remove").click(
						function() {
							$.post($("#claim_attachment_deleteAll").attr(
									"action"), $("#claim_attachment_deleteAll")
									.serialize(), function() {
								$("#claim_attachment_deleteAll").submit();
							});
						});
				});
	
	$(document).ready(function() {
		var count=$("input[name='markup']").length;
		for(var i=0;i<count;i++){
		var selected = "<input type='text' name='selected' />";
		
		$("div[id='selectedItem'] form[name='claim_attachment_deleteSelected']").append(selected);
		}
		
		$("input:checkbox[name='markup']").change(function() 
				{
			for(var i=0;i<count;i++){
			if($("input:checkbox[name='markup']:eq("+i+")").is(":checked")){
				var value=$("input:checkbox[name='markup']:eq("+i+")").val();
			$("input[name='selected']:eq("+i+")").val(value);
			}else{ 
				$("input[name='selected']:eq("+i+")").val('');
				}
			}
			});
		});
	
	function uniRefresh() {
		setTimeout(function(){
			var regId =<%out.print(Long.parseLong(request.getParameter("entity.regId")));%>;
			var code="<%out.print(request.getParameter("entity.code"));%>";
			var site=window.location.href;
			var keyword="pager";
			if(site.indexOf(keyword) != -1){
				location.replace(location.href);}
			else {document.location = "<c:url value='/crud/claim.attachment.list.action?entity.regId='/>"+ regId+"&entity.code="+code+"&pager.offset=0&pager.currentPage=1";}
		}, 250);
	}
	
	function multiRefresh(){
		var count=$("input[name='markup']:checked").length;
		var times=count*250;
		setTimeout(function(){
			var regId =<%out.print(Long.parseLong(request.getParameter("entity.regId")));%>;
			var code="<%out.print(request.getParameter("entity.code"));%>";
			var site=window.location.href;
			var keyword="pager";
			if(site.indexOf(keyword) != -1){
				location.replace(location.href);}
			else {document.location = "<c:url value='/crud/claim.attachment.list.action?entity.regId='/>"+ regId+"&entity.code="+code+"&pager.offset=0&pager.currentPage=1";}
		}, times);
	}
	
	function back() {
		var regId =<%out.print(Long.parseLong(request.getParameter("entity.regId")));%>
	;
		document.location = "<c:url value='/crud/claim.case.query.action?entity.id='/>"
				+ regId;
	}
</script>
<body class="page-padding">
	<fieldset>
		<legend>
			報案編號<%
			out.print(request.getParameter("entity.code"));
		%>附件
		</legend>

		<%-- 上傳列 --%>
		<s:form action="claim.attachment.save" method="post" namespace="/crud"
			cssClass="form-horizontal" enctype="multipart/form-data">
			<s:file label="File" name="myFile" />
			<s:file label="File" name="myFile" />
			<s:file label="File" name="myFile" />
			<input type="hidden" name="entity.uploadUser"
				value="${login.userName}" />
			<input type="hidden" name="entity.regId"
				value="<%out.print(Long.parseLong(request.getParameter("entity.regId")));%>" />
			<input type="hidden" name="entity.code"
				value="<%out.print(request.getParameter("entity.code"));%>" />
		</s:form>

	</fieldset>

	<%-- 分頁 --%>
	<table style="width: 100%" id="pager">
		<tr>
			<td><jsp:include page="/WEB-INF/jsp/layout/pagination2.jsp">
					<jsp:param name="namespace" value="/crud" />
					<jsp:param name="action" value="claim.attachment.list" />
					<jsp:param name="code" value="${entity.code}" />
					<jsp:param name="regId" value="${entity.regId}" />
					<jsp:param name="pager" value="${ds.pager}" />
				</jsp:include></td>
		</tr>
	</table>

	<div class="divMark1">

		<%-- 搜尋區塊 --%>
		<s:form action="claim.attachment.list" namespace="/crud" method="post"
			cssClass="form-search">
			<input type="hidden" name="entity.regId"
				value="<%out.print(Long.parseLong(request.getParameter("entity.regId")));%>" />
			<input type="hidden" name="entity.code"
				value="<%out.print(request.getParameter("entity.code"));%>" readonly />
		</s:form>

		<%-- 按鈕區塊 --%>
		<table class="form-table">
			<tr>
				<td colspan="6" align="right">
					<div class="btn-group">
						<button id="transfer" class="btn btn-success">上傳</button>
						<button id="display" class="btn btn-info">清單</button>
						<button id="clearSel" class="btn btn-warning"
							style="color: #ffffff">選刪</button>
						<button id="remove" class="btn btn-danger">全刪</button>
						<button id="close" class="btn btn-inverse" onclick="back()">
							關閉</button>
					</div>
				</td>
			</tr>
		</table>
	</div>

	<%-- List區塊 --%>
	<table class="table table-hover" style="width: 100%">
		<thead>
			<tr class="list-head">
				<th><input type="checkbox" name="markupHead" /></th>
				<th>報案編號</th>
				<th>上傳者</th>
				<th>檔案名稱</th>
				<th>檔案郵件擴展</th>
				<th>檔案大小</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${ds.results}" varStatus="status">
				<tr class="list-item${status.index mod 2}">
					<td><input type="checkbox" name="markup" value="${item.id }" /></td>
					<td>${item.code}</td>
					<td>${item.uploadUser}</td>
					<td><a
						href="<c:url value="/resources/media/files/${item.fileUri}"/>">${item.fileName}</a></td>
					<td>${item.fileMime}</td>
					<td>${item.fileSize}&nbsp;kb</td>
					<td><form id="claim_attachment_delete"
							name="claim_attachment_delete"
							action="<c:url value='/crud/claim.attachment.delete.action'/>"
							method="post" target="iframe1">
							<div style="display: none">
								<input type="hidden" name="entity.id" value="${item.id}" /> <input
									type="hidden" name="entity.regId" value="${item.regId}" /><input
									type="hidden" name="entity.code" value="${item.code}" /> <input
									type="hidden" name="entity.uploadUser"
									value="${item.uploadUser}" /> <input type="hidden"
									name="entity.fileName" value="${item.fileName}" /> <input
									type="hidden" name="entity.fileMime" value="${item.fileMime}" />
								<input type="hidden" name="entity.fileSize"
									value="${item.fileSize}" /> <input type="hidden"
									name="entity.fileUri" value="${item.fileUri}" />
							</div>
							<div class="btn-group">
								<button id="delete" class="btn btn-primary"
									onclick="uniRefresh()">刪除</button>
							</div>
						</form></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div>
		<s:form action="claim.attachment.deleteAll" method="post"
			namespace="/crud">
			<input type="hidden" name="entity.regId"
				value="<%out.print(Long.parseLong(request.getParameter("entity.regId")));%>" />
			<input type="hidden" name="entity.code"
				value="<%out.print(request.getParameter("entity.code"));%>" />
		</s:form>
	</div>
	<div id="selectedItem" style="display: none">
		<s:form action="claim.attachment.deleteSelected" method="post"
			namespace="/crud" target="iframe1">
			<input type="hidden" name="entity.regId"
				value="<%out.print(Long.parseLong(request.getParameter("entity.regId")));%>" />
			<input type="hidden" name="entity.code"
				value="<%out.print(request.getParameter("entity.code"));%>" />
		</s:form>
	</div>

	<div id="tempIframe" style="display: none">
		<iframe name="iframe1"></iframe>
	</div>

</body>
</html>