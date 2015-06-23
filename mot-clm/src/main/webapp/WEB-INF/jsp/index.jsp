<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />

<!DOCTYPE HTML>
<html>

<script type="text/javascript">
	$(function() {
		// 		$("#contentTr").css("height", screen.height - 270);
		// 		$("#contentTd").css("width", screen.width - 180);
		$("#contentTd").mouseover(function() {
			$("#menu").menu("collapseAll", null, true);
		});
	});
</script>

<head>
<title>車險理賠系統</title>
</head>

<body class="index-background">
	<div align="center" style="width: 100%; height: 768px;">
		<table
			style="border-spacing: 0px; border-collapse: collapse; width: 1024px; height: 100%;"
			class="index-content">
			<tr style="height: 50px;">
				<td style="width: 135px;" class="index-padding-left">
					<h2 style="display : inline">車險理賠</h2>
					<jsp:include page="/WEB-INF/jsp/layout/header.jsp" />
				</td>
				<td></td>
			</tr>
			<tr style="height: 50px;">
				<td valign="top" class="index-padding-menu">
				<jsp:include page="/WEB-INF/jsp/layout/menu.jsp" /></td>
				<td></td>
			</tr>
			<tr id="contentTr" style="height: 700px;">
				<td valign="top" id="contentTd" class="index-padding-center">
					<iframe
						name="content" height="100%" width="100%"
						style="border: 0px; position: relative;"
						src='<c:url value="/page/section.action" />' class="index-radius"></iframe>
				</td>
			</tr>
			<tr>
				<td><jsp:include page="/WEB-INF/jsp/layout/footer.jsp" /></td>
				<td></td>
			</tr>
		</table>
	</div>

</body>
</html>
