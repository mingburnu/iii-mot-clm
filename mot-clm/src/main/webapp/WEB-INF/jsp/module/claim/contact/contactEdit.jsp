<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />
<!DOCTYPE HTML>
<html>
<head>

<script type="text/javascript">

$(function(){
	
	$('#cool').bind('click', function(){
		
		$('input[name="entity.customerId"]').val("CU01");
		$('input[name="entity.accidentPlate"]').val("ACC-1234");
		$('input[name="entity.contact"]').val("林夆");
		$('input[name="entity.mail"]').val("zlin@gmail.com");
		$('input[name="entity.officeTelCty"]').val("886");
		$('input[name="entity.officeTelArea"]').val("02");
		$('input[name="entity.officeTel"]').val("86664321");
		$('input[name="entity.faxCty"]').val("886");
		$('input[name="entity.faxArea"]').val("02");
		$('input[name="entity.fax"]').val("66391111");
		$('input[name="entity.mobileCty"]').val("886");
		$('input[name="entity.mobile"]').val("0928754632");
		$('input[name="entity.address"]').val("新北市板橋區中山路一段100號");
		
		
	})
	
	
})


</script>

<title>車險系統</title>
</head>
<body class="page-padding">
	<fieldset>
		<legend id="cool">通訊錄設定</legend>
		<s:form namespace="/crud" method="post" cssClass="form-horizontal">
			<s:hidden name="entity.id" />
			<s:hidden name="registerId" />
			<s:hidden name="cased" />
			<table>
				<tr>
					<s:url namespace="/crud" action="claim.contact.list" var="contact">
						<s:param name="registerId">${entity.id}</s:param>
					</s:url>
					<td class="form-label" ><h5>報案編號</h5></td>
					<td><s:textfield name="entity.regCode" cssClass="input-medium" readonly="true"
							/></td>
					<td class="form-label"><h5>賠案編號</h5></td>
					<td><s:textfield name="entity.caseCode"
							cssClass="input-medium" readonly="true" /></td>
				</tr>
			</table>
			<table>
				<tr>
					<td class="form-label"><h5>類別</h5></td>
					<td class="form-label"><s:select name="entity.type"
							cssClass="input-medium"
							list="@org.iii.module.claim.contact.enums.Type@values()"
							listKey="name()" listValue="type" /></td>
				</tr>
				<tr>
					<td><h5>客戶編號</h5></td>
					<td><s:textfield name="entity.customerId"
							cssClass="input-medium" /></td>
					<td><h5>事故車號</h5></td>
					<td><s:textfield name="entity.accidentPlate"
							cssClass="input-medium" /></td>
					<td><h5>聯絡人</h5></td>
					<td class="form-label"><s:textfield name="entity.contact"
							cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td><h5>電子郵件</h5></td>
					<td><s:textfield name="entity.mail" cssClass="input-large" /></td>
				</tr>
			</table>
			<table>
				<tr>
					<td><h5>辦公室電話</h5></td>
					<td><s:textfield name="entity.officeTelCty"
							cssClass="input-mini" placeholder="886" />-</td>
					<td><s:textfield name="entity.officeTelArea"
							cssClass="input-mini" />-</td>
					<td><s:textfield name="entity.officeTel"
							cssClass="input-medium" /></td>
					<td><h5>分機</h5></td>
					<td><s:textfield name="entity.officeTelExt"
							cssClass="input-mini" /></td>
				</tr>
				<tr>
					<td><h5>傳真電話</h5></td>
					<td><s:textfield name="entity.faxCty" cssClass="input-mini"
							placeholder="886" />-</td>
					<td><s:textfield name="entity.faxArea" cssClass="input-mini" />-</td>
					<td><s:textfield name="entity.fax" cssClass="input-medium" /></td>
				</tr>
				<tr>
					<td><h5>住宅電話</h5></td>
					<td><s:textfield name="entity.homeTelCty"
							cssClass="input-mini" placeholder="886" />-</td>
					<td><s:textfield name="entity.homeTelArea"
							cssClass="input-mini" />-</td>
					<td><s:textfield name="entity.homeTel" cssClass="input-medium" /></td>
				</tr>
			</table>
			<table>
				<tr>
					<td><h5>行動電話</h5></td>
					<td><s:textfield name="entity.mobileCty" cssClass="input-mini"
							placeholder="886" />-</td>
					<td><s:textfield name="entity.mobile" cssClass="input-medium" /></td>
					<td><s:checkbox name="entity.message" onclick="unchecked" /></td>
					<td><h5>簡訊</h5></td>
				</tr>
			</table>
			<table class="form-table">
				<tr>
					<td><h5>地址</h5></td>
					<td><s:textfield name="entity.address"
							cssClass="input-xxlarge" /></td>
				</tr>
				<tr>
					<td colspan="2" class="form-button">
						<div class="btn-group">
							<c:choose>
								<c:when test="${empty entity.id}">
									<s:submit cssClass="btn btn-success"
										action="claim.contact.save" value="新增" />
								</c:when>
								<c:otherwise>
								
									<s:submit cssClass="btn btn-success"
										action="claim.contact.update" value="修改" />
									
									<s:submit cssClass="btn btn-danger" action="claim.contact.delete"
								value="刪除" />
								</c:otherwise>
							</c:choose>
							
							<s:url namespace="/crud" action="claim.contact.list"
								var="close">
								<s:param name="registerId">${registerId}</s:param>
								<s:param name="entity.regCode">${entity.regCode}</s:param>
								<s:param name="entity.caseCode">${entity.caseCode}</s:param>
								<s:param name="cased">${cased}</s:param>
							</s:url>
							<input type="button" class="btn btn-inverse return" value="關閉"
								onclick="document.location='<s:property value="#close" escape='false'/>'" />
						</div>
					</td>
				</tr>
			</table>

			<%-- 創建者資訊 --%>
			<jsp:include page="/WEB-INF/jsp/layout/creatorInfo.jsp" />
		</s:form>
	</fieldset>
</body>
</html>