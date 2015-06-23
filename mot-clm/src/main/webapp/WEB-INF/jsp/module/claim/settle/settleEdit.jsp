<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<style>
.divBorder {
	border: 1px solid #dddddd;
	width: 100%;
}

.clmtable tr td {
	border: 1px solid #dddddd;
}

.clmtable tr th {
	border: 1px solid #dddddd;
}

.clmtable {
	width: 100%;
	margin-top: auto;
	margin-bottom: auto;
	margin-left: auto;
	margin-right: auto;
	border: 1px solid #dddddd;
	border-collapse: separate;
	*border-collapse: collapse;
	border-left: 0;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	border-radius: 4px;
	
}
</style>

<script type="text/javascript">
//init
$(function(){
	$('#topMenu').find("li").eq(0).removeAttr('class');
	$('#topMenu').find("li").eq(2).attr('class','active');	
	
	var caseCode='${entity.caseCode}';
	//理算簽結MENU列設定
	$.ajax({
		  url: '<s:url namespace="/json" action="claim.estimate.findEstimateCheckAjax?caseCode='+caseCode+'" />',
		  type: "GET",
		  dataType: "json",
		  async: false,
		  success: function(Jdata) {
			var checked=Jdata["data"];
			if(checked=="true"){
				$("#topMenu").find("li").eq(2).removeAttr("style");
			}
		  },
		  error: function() {
		    alert("ERROR!!!");
		  }
		});
	
});

//總已決金額 榜定事件 加總
$(function(){
	calAmount();
	
	 $("#clmtbody").find("input[name$='].settleAmount']").each(function(){
			//總已決金額
			$(this).on("change",function(){				
				//金額必須是數字
				var lossAmount=$(this).val();
				if(isNaN(lossAmount)){
					$(this).val("0");
					alert("必須是數字");
				}			
				calAmount();
			});	 			
	 });	
});


//賠付資料列回填
function paymentAdd(id,name,price){
	$("#buttoo").removeAttr("disabled");
	$("#payTable").find("tbody").append(createTr(id,name,price));
}


//產生賠付資料列
function createTr(id,name,price) {
	$("#clmtbody").find("input[name='checkbox2']").each(function(){
		if($(this).prop("checked")){
			$(this).parent().parent().find("td").eq(8).text(id);
		}
	});
		
	return  '<tr>'+
				'<td><input type="checkbox" name="checkbox3" />'+'</td>'+
       			'<td>'+id+'</td>'+
       			'<td><a>賠付內容</a></td>'+
    			'<td>'+name+'</td>'+
    			'<td>TWD</td>'+
    			'<td>'+price+'</td>'+
	 	    '</tr>';
}


//計算總預估金額
function calAmount(){
	var num=0;
	 $("#clmtbody").find("input[name$='].settleAmount']").each(function(){
		 num=num*1+($(this).val())*1;			 			
	 });	
	 $("input[name='entity.settledAmount']").val(num);
}

//增加賠款列
function addClm(){
	var count=0;
	$("#clmtbody").find("input[name='checkbox2']").each(function(){
		count=count+1;
	});
	$("#clmtbody").find("input[name='checkbox']").each(function(){
		if($(this).prop("checked")){
			var idd=$(this).next().val();
			var insuredType=$(this).parent().parent().find("td").eq(3).text();
	
			var tr=$("#template").clone().removeAttr("id");
			tr.find("td").eq(0).attr("name","child");
			tr.find("td").eq(1).html('<input type="text" name="liSettleDetail['+count+'].id" style="display:none"/>');
			tr.find("td").eq(2).html('<input type="text" name="liSettleDetail['+count+'].estimateDetailId" style="display:none" value="'+idd+'"/>');
			tr.find("td").eq(3).html('<input type="text" name="liSettleDetail['+count+'].insuredType" style="display:none" value="'+insuredType+'" />');
			tr.find("td").eq(6).html('<input type="checkbox" name="checkbox2" />');
			tr.find("td").eq(7).html('<input type="text" class="input-small" name="liSettleDetail['+count+'].settleAmount" value="0" />');
			$(this).parent().parent().after(tr);
			count=count+1;
	 	 	//ajax settleDetail.save
/* 			$.ajax({				
				  url: '<s:url namespace="/crud" action="claim.settleDetail.save?id='+idd+'&insuredType='+insuredType+'&insuredType='+idd+'&insuredType='+idd+'" />',
				  type: "GET",
				  dataType: "json",
				  async: false,
				  success: function() {
					
				  },
				  error: function() {
				    alert("ERROR!!!");
				  }
			}); */
		}
		
		//總已決金額
		$("#clmtbody").find("input[name$='].settleAmount']").on("change",function(){
			
			//金額必須是數字
			var lossAmount=$(this).val();
			if(isNaN(lossAmount)){
				$(this).val("0");
				alert("必須是數字");
			}			
			calAmount();
		});
		
	});
}


//刪除賠款列
function delClm(){
	$("#clmtbody").find("input[name='checkbox2']").each(function(){
		if($(this).prop("checked")){
			$(this).parent().parent().remove();
		}
	});
}


//增加賠付資料
function addPayment(){
	var checked=false;
	var sum=0;
	var num;
	$("#clmtbody").find("input[name='checkbox2']").each(function(){
		if($(this).prop("checked")){
			checked=true;
			var a=$(this).parent().next().children().val();
			var b=$(this).parent().parent().find("input").eq(0).val();
			var c=$(this).parent().parent().find("td").eq(8).text();
			if(c!=null && c!=""){
				checked=false;
				alert("此賠付金額已有賠付對象");
			}
			if(num==null || num.trim()==""){
				num=b;
			}else{
				num=num+","+b;
			}
			if(a==0){
				checked=false;
				alert("已決金額不得為0");
				return false;
			}else{
				sum=sum*1+a*1;
			}
			
		}
	});

	if(checked){
		var caseCode=$("input[name='entity.caseCode']").val();
		$.fancybox.open({
			href : '<s:url namespace="/crud" action="claim.payments.query?caseCode='+caseCode+'&entity.cash='+sum+'&num='+num+' " />',
			type : 'iframe',
			padding : 20,
			scrolling : 'auto'
		});
	}else{
		alert("未選取已決金額");
	}	
}

function delPayment(){
	
}

</script>


<title>車險系統</title>
</head>
<body class="page-padding">
	<fieldset>
		<legend>理算簽結設定</legend>
			<jsp:include page="/WEB-INF/jsp/module/claim/case/layout/caseTopMenu.jsp" />

		<%-- 				<s:url namespace="/crud" action="claim.settle.list" var="settle"> --%>
		<%-- 					<s:param name="registId">${entity.id}</s:param> --%>
		<%-- 				</s:url> --%>

		<s:form namespace="/crud" method="post" action="claim.settle.query" cssClass="form-horizontal">
			<s:hidden name="entity.id" />
			<s:hidden name="entity.policyCode" />
			<table width="100%">
				<tr>
					<td class="form-label" ><h5>賠案號碼</h5></td>
					<td><s:textfield name="entity.caseCode" cssClass="input-medium" readonly="true" /></td>
					<td class="form-label" ><h5>出險日期</h5></td>
					<td><s:textfield name="entity.accidcirDate" cssClass="input-medium" disabled="true" /></td>
						<s:hidden name="entity.accidcirDate" />
					<td class="form-label" ><h5>被保險人ID</h5></td>
					<td><s:textfield name="entity.insuredId" cssClass="input-medium" readonly="true" /></td>
					<td class="form-label" ><h5>被保險人姓名</h5></td>
					<td><s:textfield name="entity.insuredName" cssClass="input-small" readonly="true" /></td>
					
				</tr>
			</table>
			<table>
				<tr>
					<td class="form-label" ><h5>車牌號碼</h5></td>
					<td><s:textfield name="entity.plate" cssClass="input-small" readonly="true" /></td>
					<td class="form-label" ><h5>申請次數</h5></td>
					<td><s:textfield name="entity.applyBout" cssClass="input-small" readonly="true" /></td>
					<td class="form-label" ><h5>事故原因</h5></td>
					<td><s:textfield name="entity.accident" cssClass="input-xlarge" readonly="true" /></td>
					
				</tr>
			</table>
			<table>
				<tr>
					<td class="form-label" ><h5>總預估金額</h5></td>
					<td><s:textfield name="entity.totalAmountCur" cssClass="input-mini" readonly="true" /></td>
					<td><s:textfield name="entity.totalAmount" cssClass="input-small" readonly="true" /></td>
					<td class="form-label" ><h5>總已決金額</h5></td>
					<td><s:textfield name="entity.settledAmountCur" cssClass="input-mini" readonly="true" /></td>
					<td><s:textfield name="entity.settledAmount" cssClass="input-small" readonly="true" /></td>
					
				</tr>
			</table>
			<table>
				<tr>
					<td class="form-label" ><h5>審核狀態</h5></td>
					<td><s:select name="entity.auditState" cssClass="input-medium" list="@org.iii.core.enums.AuditState@values()" listKey="name()" listValue="auditState"></s:select></td>
					<td class="form-label"><h5>已決日期</h5></td>
					<td><s:textfield name="entity.settledDate" cssClass="input_medium" disabled="true"/></td>
					<s:hidden name="entity.settledDate" />
				</tr>
			</table>
			<table>
				<tr>
					<td class="form-label"><h5>結案別</h5></td>
					<td><s:select name="entity.closedType" cssClass="input-medium" list="@org.iii.module.claim.settle.enums.ClosedType@values()" listKey="name()" listValue="closedType"></s:select></td>
					<td class="form-label"><h5>是否追償</h5></td>
					<td><s:select name="entity.recovery" cssClass="input-small" list="@org.iii.core.enums.SystemStatus@values()" listKey="name()" listValue="localName3"></s:select></td>
					<td class="form-label"><h5>追償對象</h5></td>
					<td><s:select name="entity.recoveryObject" cssClass="input-small" list="@org.iii.module.claim.settle.enums.RecoveryObject@values()" listKey="name()" listValue="recoveryObject"></s:select></td>
					<td class="form-label"><h5>追償人員</h5></td>
					<td><s:select name="entity.recoveryPerson" cssClass="input-small" list="@org.iii.module.claim.settle.enums.RecoveryPerson@values()" listKey="name()" listValue="recoveryPerson"></s:select></td>
				
				</tr>
			</table>
			
			<div class="divBorder">
				<h5>理賠項目</h5>
				<table class="clmtable">
					<thead>
						<tr>
							<th></th>
							<th><h5>簽賠次數</h5></th>
							<th><h5>保單號碼</h5></th>
							<th><h5>險種</h5></th>
							<th><h5>受損標的所有權人</h5></th>
							<th><h5>幣別</h5></th>
							<th><h5>預估金額</h5></th>
							<th><h5>已決金額 </h5></th>
							<th><h5>賠付序號</h5></th>
						</tr>
					</thead>
					<tbody id="clmtbody">
					<c:forEach var="item" items="${dsEstimateDetail.results}" varStatus="status">
					<tr>
						<td><input type="checkbox" name="checkbox" /><input type="text" value="${item.id}" style="display:none" /> </td>
						<td>W</td>
						<td>${entity.policyCode}</td>			
						<td>${item.insuredType}</td>
						<td>${item.ownerName}${item.victimsName}</td>
						<td>${entity.currency}</td>
						<td>${item.estimatedLossAmount}${item.lossAmount}</td>
						<td></td>
						<td></td>
					</tr>
						<c:forEach var="detail" items="${liSettleDetail}" varStatus="detailstat">
							<c:if test="${item.id == detail.estimateDetailId}">
							<tr>
								<td></td>
								<td><input type="text" name="liSettleDetail[${detailstat.index}].id" style="display:none" value="${detail.id}"/></td>
								<td><input type="text" name="liSettleDetail[${detailstat.index}].estimateDetailId" style="display:none" value="${detail.estimateDetailId}"/></td>			
								<td><input type="text" name="liSettleDetail[${detailstat.index}].insuredType" style="display:none" value="${detail.insuredType}"/></td>
								<td></td>
								<td></td>
								<td><input type="checkbox" name="checkbox2" /></td>
								<td><input type="text" class="input-small" name="liSettleDetail[${detailstat.index}].settleAmount" value="${detail.settleAmount}"/></td>
								<td>${detail.payId}</td>
							</tr>						
							</c:if>					
						</c:forEach>					
					</c:forEach>
					</tbody>			
				</table>
				<div style="display:none">
					<table>
						<tr id="template" >
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
				</div>
			
			</div>
			<div>
				<table  width="100%">
					<tr>
						<td>
							<button type="button" onclick="addClm()">增加賠款</button>
							<button type="button" onclick="delClm()">刪除賠款</button>
									</td>
						<td align="right">
							<button type="button" onclick="addPayment()">增加賠付資料</button>
							<button type="button" onclick="delPayment()">刪除賠付資料</button>
						</td>
					</tr>
				</table>
			</div>
			<div class="divBorder">
				<h5>賠付資料</h5>
				<table class="clmtable" id="payTable">
					<thead>
						<tr>
							<th></th>
							<th><h5>賠付序號</h5></th>
							<th><h5>賠付內容</h5></th>
							<th><h5>對象</h5></th>
							<th><h5>幣別</h5></th>
							<th><h5>金額 </h5></th>
						</tr>							
					</thead>
					<tbody>
					<c:forEach var="item" items="${dsPayments.results}" varStatus="status">
						<tr>
							<td><input type="checkbox" id="checkbox3" /></td>
							<td>${item.payNumber}</td>
							<td><a>賠付內容</a></td>
							<td>${item.paymentObject}</td>
							<td>TWD</td>
							<td>${item.cash}</td>						
						</tr>					
					</c:forEach>					
					</tbody>				
				</table>
			
			</div>
		<table class="form-table">
			<tr>
				<td colspan="2" class="form-button">
					<div class="btn-group">
						<c:choose>
							<c:when test="${empty entity.id}">
								<s:submit cssClass="btn btn-success" action="claim.settle.save"
									value="儲存" />
							</c:when>
							<c:otherwise>
							<s:submit cssClass="btn btn-success"
									action="claim.settle.update" value="修改" />	
							<c:if test="${not empty dsPayments.results}">
							<s:submit cssClass="btn btn-warning"
							        action='claim.settle.confirmUpdate' value="送審" />
							</c:if>
							<c:if test="${empty dsPayments.results}">
							<s:submit cssClass="btn btn-warning"
							        action='claim.settle.confirmUpdate' value="送審" disabled="true" id="buttoo"/>
							</c:if>
								<c:if test="${not empty check}">
							<s:url namespace="/crud" action="claim.settle.cancelUpdate" var="#cancelUpdate">
								<s:param name="entity.id">${entity.id}</s:param>
							</s:url>
							<input type="button" class="btn btn-inverse return" value="取消送審"
							onclick="document.location='<s:property value="#cancelUpdate" escape="false" />'" />
								</c:if>
							</c:otherwise>
						</c:choose>
						
						<s:url namespace="/crud" action="claim.case.query" var="#close">
							<s:param name="entity.caseCode">${entity.caseCode}</s:param>
						</s:url>
						<input type="button" class="btn btn-inverse return" value="關閉"
							onclick="document.location='<s:property value="#close" escape="false" />'" />
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