<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />


<c:choose>
	<c:when test="${not empty entity.id}">
		<script type="text/javascript">		
		$(function(){			
			var name=$("input[name='entity.paymentObject']").val();
			var price=$("input[name='entity.cash']").val();
			var id = $("input[name='entity.payNumber']").val();
			if(id==null && id==""){
				id=1;
			}			
			parent.paymentAdd(id,name,price);
			parent.jQuery.fancybox.close();
		});
		</script>
	</c:when>
</c:choose>

<!DOCTYPE HTML>

<html>
<script type="text/javascript">
$(function(){
	$("#quickProduce").bind("click",function(){
		$("input[name='entity.paymentObject']").val("張學友");
		$("input[name='entity.paymentObjectId']").val("J234567890");
		$("input[name='entity.paymentObjectId']").val("J234567890");
		$("input[name='entity.noticePerson']").val("劉德華");
		$("input[name='entity.email']").val("mot-clm@hotmail.com");
		$("input[name='entity.telCtyFir']").val("080");
		$("input[name='entity.telAreaFir']").val("02");
		$("input[name='entity.telFir']").val("25252525");
		$("input[name='entity.telCtySec']").val("080");
		$("input[name='entity.telAreaSec']").val("03");
		$("input[name='entity.telSec']").val("4949494");
		$("input[name='entity.address']").val("台北市忠孝東路9段99號9樓");
		$("input[name='entity.phoneCty']").val("080");
		$("input[name='entity.phone']").val("0989888999");
		$("input[name='entity.paymentOffice']").val("華西銀行財務部");
		$("input[name='entity.accountOffice']").val("資策會會計部");
		$("input[name='entity.proofNumber']").val("DD-554");
		$("input[name='entity.summonsNumber']").val("020488995");
		$("input[name='entity.payDate']").val("2014/06/18");
	});
	
	$("#currency").bind("change",function(){
		var ty=$(this).val();
		if(ty=="TWD"){
			$("input[name='chan']").val("1");
		}else if(ty=="USD"){
			$("input[name='chan']").val("29.645");
		}else if(ty=="CNY"){
			$("input[name='chan']").val("4.7");
		}else if(ty=="JPY"){
			$("input[name='chan']").val("0.285");
		}else if(ty=="EUR"){
			$("input[name='chan']").val("40.1");
		}else if(ty=="HKD"){
			$("input[name='chan']").val("3.73");
		}else if(ty=="GBP"){
			$("input[name='chan']").val("49.3");
		}else{
			$("input[name='chan']").val("27.81");
		}
		
		var ch=$("input[name='chan']").val();
		var cash=$("input[name='entity.cash']").val();
		
		$("#money").val((cash/ch));
		
	});
});



</script>
<head>
<style type="text/css">
.tableLeft {
	margin-left: 20px;
}
</style>

<title>車險系統</title>
</head>

<body>

	<fieldset>
		<legend id="quickProduce">賠付內容</legend>
	
		<s:form namespace="/crud" method="post" cssClass="form-horizontal">
			<s:hidden name="num" />
			<s:hidden name="entity.id" />
			<s:hidden name="entity.payNumber"/>
			<table>
				<tr>
					<td><h5>賠案號碼</h5></td>
					<td><s:textfield name="entity.caseCode" cssClass="input-medium" readonly="true" /></td>
					<td><h5>出險日期</h5></td>
					<td><s:textfield name="entity.accidcirDate" cssClass="input-medium" disabled="true" /></td>
					<td><h5>被保險人ID</h5></td>
					<td><s:textfield name="entity.insuredId" cssClass="input-medium" disabled="true" /></td>
					<td><h5>被保險人姓名</h5></td>
					<td><s:textfield name="entity.insuredName" cssClass="input-lamediumrge" disabled="true" /></td>
				</tr>
				<tr>
					<td><h5>保單號碼</h5></td>
					<td><s:textfield name="entity.policyCode" cssClass="input-medium" disabled="true" /></td>
					<td><h5>車牌號碼</h5></td>
					<td><s:textfield name="entity.plate" cssClass="input-medium" disabled="true" /></td>
					<td align="right"><h5>申請次數</h5></td>
					<td><s:textfield name="entity.applyBout" cssClass="input-small" disabled="true" /></td>
					<td align="right"><h5>事故原因</h5></td>
					<td><s:textfield name="entity.accident" cssClass="input-medium" disabled="true" /></td>
				</tr>
				<tr>
					<td><h5>審核狀態</h5></td>
					<td><s:textfield name="entity.auditState.auditState" cssClass="input-small" disabled="true" /></td>
					<td><h5>已決日期</h5></td>
					<td><s:textfield name="entity.decideDate" cssClass="input-medium" disabled="true" /></td>
				</tr>
			</table>
			<hr>
			<div class="divBorder">
				<h5 class="heading">賠付對象</h5>
				<table class="tableLeft">
					<tr>
						<td><h5>賠付對象</h5></td>
						<td colspan="2"><s:textfield name="entity.paymentObject" cssClass="input-small" />
						</td>
						<td align="right"><h5>統一編號/ID</h5></td>
						<td colspan="2"><s:textfield name="entity.paymentObjectId" cssClass="input-medium" /></td>
						<td></td>
						<td align="right"><s:checkbox name="likeInsured"  value="false" id="likeInsured" /></td>
						<td><h5>同被保險人</h5></td>
					</tr>
					<tr>
						<td align="right"><h5>電話一</h5></td>
						<td colspan="8"><s:textfield name="entity.telCtyFir" cssClass="input-mini" />-
						<s:textfield name="entity.telAreaFir" cssClass="input-mini" />-
						<s:textfield name="entity.telFir" cssClass="input-small" /></td>
					</tr>
					<tr>
						<td align="right"><h5>通知人</h5></td>
						<td colspan="2"><s:textfield name="entity.noticePerson" cssClass="input-small" />
						</td>
						<td align="right"><h5>電子郵件</h5></td>
						<td colspan="5"><s:textfield name="entity.email" cssClass="input-large" /></td>
					</tr>
					<tr>
						<td align="right"><h5>電話二</h5></td>
						<td colspan="3"><s:textfield name="entity.telCtySec" cssClass="input-mini" />-
						<s:textfield name="entity.telAreaSec" cssClass="input-mini" />-
						<s:textfield name="entity.telSec" cssClass="input-small" /></td>
					</tr>
					<tr>
						<td><h5>通訊地址</h5></td>
						<td colspan="8"><s:textfield name="entity.address" cssClass="input-xxlarge" /></td>
					</tr>
					<tr>
						<td><h5>行動電話</h5></td>
						<td><s:textfield name="entity.phoneCty" cssClass="input-mini" />-
						<s:textfield name="entity.phone" cssClass="input-small" /></td>
						<td align="right"><s:checkbox name="entity.phoneMessage" value="false" id="" /></td>
						<td><h5>簡訊通知</h5></td>
					</tr>
					<tr>
						<td><h5>付款金額</h5></td>
						<td><s:textfield name="entity.paymentCurrency" cssClass="input-mini" readonly="true" />
						<s:textfield name="entity.cash" cssClass="input-small" readonly="true" />
						<s:hidden name="" cssClass="input-small"/></td>
						<td align="right"><h5>付款單位</h5></td>
						<td><s:textfield name="entity.paymentOffice" cssClass="input-small" /></td>
						<td></td>
						<td align="right"><h5>帳務單位</h5></td>
						<td><s:textfield name="entity.accountOffice" cssClass="input-small" /></td>
					</tr>
					<tr>
						<td><h5>憑證號碼</h5></td>
						<td><s:textfield name="entity.proofNumber" cssClass="input-small" /></td>
						<td><h5>傳票號碼</h5></td>
						<td><s:textfield name="entity.summonsNumber" cssClass="input-small" /></td>
						<td></td>
						<td align="right"><h5>賠案付款日</h5></td>
						<td><s:textfield name="entity.payDate" cssClass="input-small" /></td>
					</tr>
				</table>
			</div>
			<hr>
			<div class="divBorder">
				<h5 class="heading">付款方式</h5>
				<table class="tableLeft">
					<tr>
						<td align="right"><input id="radiobutton" name="radiobutton" type="radio" value="radiobutton"></td>
						<td><h5>現金</h5></td>
						<td width="30px"></td>
						<td align="right"><h5>金額</h5></td>
						<td><s:select id="currency" name="entity.cashCurrency" cssClass="input-small" list="@org.iii.core.enums.Currency@values()" listKey="name()" listValue="currency" /></td>
						<td colspan="2"><s:textfield id="money" cssClass="input-small" disabled="true"/></td>
						<td align="right"><h5>兌換率</h5></td>
						<td><s:textfield name="chan" cssClass="input-small" disabled="true" /></td>
						<td align="right"><input type="checkbox" name="checkbox" value="checkbox"></td>
						<td colspan="5"><h5>零用金付迄</h5></td>
					</tr>
					<tr>
						<td align="right"><input name="radiobutton" type="radio" value="radiobutton"></td>
						<td><h5>支票</h5></td>
						<td></td>
						<td align="right"><h5>金額</h5></td>
						<td><s:select name="entity.cashCurrency" cssClass="input-small" list="@org.iii.core.enums.Currency@values()" listKey="name()" listValue="currency" /></td>
						<td colspan="2"><s:textfield name="" cssClass="input-small" /></td>
						<td align="right"><h5>兌換率</h5></td>
						<td><s:textfield name="" cssClass="input-small" disabled="true" /></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td align="right"><input type="checkbox" name="checkbox" value="checkbox"></td>
						<td><h5>合併支票</h5></td>
						<td align="right" width="30px"><input type="checkbox" name="checkbox" value="checkbox"></td>
						<td><h5>代領票人</h5></td>
						<td align="right"><h5>姓名</h5></td>
						<td><s:textfield name="" cssClass="input-small" /></td>
						<td width="10px"></td>
						<td align="right"><h5>電話</h5></td>
						<td><s:textfield name="" cssClass="input-small" /></td>
					</tr>
					<tr>
						<td align="right"><input name="radiobutton" type="radio" value="radiobutton"></td>
						<td><h5>匯款</h5></td>
						<td></td>
						<td align="right"><h5>金額</h5></td>
						<td><s:select name="entity.cashCurrency" cssClass="input-small" list="@org.iii.core.enums.Currency@values()" listKey="name()" listValue="currency" /></td>
						<td colspan="2"><s:textfield name="" cssClass="input-small" /></td>
						<td align="right"><h5>兌換率</h5></td>
						<td><s:textfield name="" cssClass="input-small" disabled="true" /></td>
					</tr>
					<tr>
						<td colspan="5" align="right"><h5>銀行帳號資料序號</h5></td>
						<td colspan="2"><s:textfield name="" cssClass="input-small" /></td>
						<td align="right"><h5>帳號</h5></td>
						<td><s:textfield name="" cssClass="input-small" /></td>
						<td align="right"><input type="checkbox" name="checkbox" value="checkbox"></td>
						<td colspan="3"><h5>匯費由受款人負擔</h5></td>
					</tr>
					<tr>
						<td colspan="5" align="right"><h5>銀行代號</h5></td>
						<td colspan="2"><s:textfield name="" cssClass="input-small" /></td>
						<td align="right"><h5>預定匯款日</h5></td>
						<td><s:textfield name="" cssClass="input-small" disabled="true" /></td>
						<td align="right"><input type="checkbox" name="checkbox" value="checkbox"></td>
						<td colspan="3"><h5>郵寄匯款通知書</h5></td>
					</tr>
					<tr>
						<td colspan="5" align="right"><h5>匯款銀行簡稱</h5></td>
						<td colspan="2"><s:textfield name="" cssClass="input-small" disabled="true" /></td>
					</tr>
				<%-- 	<tr>
						<td align="right"><input name="radiobutton" type="radio" value="radiobutton"></td>
						<td><h5>外幣支票</h5></td>
						<td></td>
						<td align="right"><h5>金額</h5></td>
						<td><s:select name="entity.cashCurrency" cssClass="input-small" list="@org.iii.core.enums.Currency@values()" listKey="name()" listValue="currency" /></td>
						<td colspan="2"><s:textfield name="" cssClass="input-small" /></td>
						<td align="right"><h5>兌換率</h5></td>
						<td><s:textfield name="" cssClass="input-small" disabled="true" /></td>
						<td align="right"><input type="checkbox" name="checkbox" value="checkbox"></td>
						<td><h5>合併支票</h5></td>
					</tr>
					<tr>
						<td align="right"><input name="radiobutton" type="radio" value="radiobutton"></td>
						<td><h5>外幣匯款</h5></td>
						<td></td>
						<td align="right"><h5>金額</h5></td>
						<td><s:select name="entity.cashCurrency" cssClass="input-small" list="@org.iii.core.enums.Currency@values()" listKey="name()" listValue="currency" /></td>
						<td colspan="2"><s:textfield name="" cssClass="input-small" /></td>
						<td align="right"><h5>兌換率</h5></td>
						<td><s:textfield name="" cssClass="input-small" disabled="true" /></td>
					</tr>
					<tr>
						<td colspan="5" align="right"><h5>銀行帳號資料序號</h5></td>
						<td colspan="2"><s:textfield name="" cssClass="input-small" /></td>
						<td align="right"><h5>帳號</h5></td>
						<td><s:textfield name="" cssClass="input-small" /></td>
						<td align="right"><input type="checkbox" name="checkbox" value="checkbox"></td>
						<td colspan="3"><h5>匯費由受款人負擔</h5></td>
					</tr>
					<tr>
						<td colspan="5" align="right"><h5>銀行代號</h5></td>
						<td colspan="2"><s:textfield name="" cssClass="input-small" /></td>
						<td align="right"><h5>銀行名稱</h5></td>
						<td><s:textfield name="" cssClass="input-small" /></td>
					</tr>
					<tr>
						<td colspan="5" align="right"><h5>swift code</h5></td>
						<td colspan="2"><s:textfield name="" cssClass="input-small" /></td>
						<td align="right"><h5>IBAN code</h5></td>
						<td><s:textfield name="" cssClass="input-small" /></td>
						<td colspan="4"><h5><s:text name="(歐元必要欄位)"/></h5></td>
					</tr>
					<tr>
						<td colspan="5" align="right"><h5>國別</h5></td>
						<td colspan="2"><s:textfield name="" cssClass="input-small" /></td>
						<td align="right"><h5>城市</h5></td>
						<td><s:textfield name="" cssClass="input-small" /></td>
					</tr> --%>
				</table>
			</div>
			
			<c:choose>
				<c:when test="${not empty entity.id}">
					<%-- 創建者資訊 --%>
					<jsp:include page="/WEB-INF/jsp/layout/creatorInfo.jsp" />
				</c:when>
			</c:choose>
			<div class="btn-group" style="float: right">
				<c:choose>
					<c:when test="${empty entity.id}">
						<s:submit cssClass="btn btn-success" action="claim.payments.save" value="儲存" />
					</c:when>
					<c:otherwise>
						<s:submit cssClass="btn btn-success"
							action="claim.payments.update" value="修改" />
					</c:otherwise>
				</c:choose>
				<input type="button" class="btn btn-inverse return" value="關閉"
					onclick="parent.jQuery.fancybox.close();" />
			</div>
		</s:form>
	</fieldset>
			
</body>

</html>