<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript"
	src='<c:url value="/resources/js/jquery.fancybox.js" />'></script>
<script type="text/javascript"
	src='<c:url value="/resources/js/jquery.fancybox.pack.js" />'></script>
<link href='<c:url value="/resources/css/ui-fancybox/jquery.fancybox.css"/>'
	rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>


<s:url namespace="/crud" action="claim.estimate.update"	var="updateTrue" escapeAmp="false">
			<s:param name="entity.id">${entity.id}</s:param>
			<s:param name="correct">true</s:param>
</s:url>

<s:url namespace="/crud" action="claim.estimate.update"	var="updateFalse" escapeAmp="false">
			<s:param name="entity.id">${entity.id}</s:param>
			<s:param name="correct">false</s:param>
</s:url>


<script type="text/javascript">
	
	
	$(function(){		
		//TOPMENU ACTIVE設定給理算賠款選項
		$("li").removeAttr("class");
		$("ul.nav.nav-tabs li:nth-child(2)").addClass("active");	
		
		var ccaseCode='${entity.caseCode}';
		//取得保單資料
		$.ajax({
			  url: '<s:url namespace="/json" action="claim.estimate.findEstimateCheckAjax?caseCode='+ccaseCode+'" />',
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
		
		
		//取得保單號碼
		var policyCode=$("input[name='entity.policyCode']").val();		
		//取得保單資料
		$.ajax({
			  url: '<s:url namespace="/json" action="claim.register.findPolicyByPlateAjax?policyCode='+policyCode+'" />',
			  type: "GET",
			  dataType: "json",
			  async: false,
			  success: function(Jdata) {
				$('input[name="entity.feeStatus"]').val(Jdata["data"][0]["payStatu"]);
				$('input[name="entity.insuredPeriod"]').val(Jdata["data"][0]["insuranceDate"]);	
				$('input[name="entity.processUserCode"]').val(Jdata["data"][0]["processUserCode"]);
				$('input[name="entity.currency"]').val(Jdata["data"][0]["moneyType"]);
				$('input[name="entity.insuredAmount"]').val(Jdata["data"][0]["insuranceValue"]);			
			  },
			  error: function() {
			    alert("ERROR!!!");
			  }
			});
		//取得保單項目資料
		$.ajax({
			  url: '<s:url namespace="/json" action="claim.register.findPolicyItemAjax?policyCode='+policyCode+'" />',
			  type: "GET",
			  dataType: "json",
			  async: false,
			  success: function(Jdata) {

					  $(Jdata["data"]).each(function(){
						  $("#claimItem tbody").append(createTr(this));		
					  });				
					  //產生的元素綁事件 
					  	//ADD圖片 增加賠償受損人與金額
						$("img[name='add']").on('click',function(){
							var insuranceItem=$(this).parent().text();
							var caseCode=$("input[name='entity.caseCode']").val();		
							if(insuranceItem=='51.乘客責任險'){
								$.fancybox.open({
									href : '<s:url namespace="/edcrud" action="claim.estimateDetail.query.51?caseCode='+caseCode+'" />',
									type : 'iframe',
									padding : 20,
									scrolling : 'auto'
								});
							}else if(insuranceItem=='01.甲式車體險'){
								$.fancybox.open({
									href : '<s:url namespace="/edcrud" action="claim.estimateDetail.query.01?caseCode='+caseCode+'" />',
									type : 'iframe',
									padding : 20,
									scrolling : 'auto'
								});
							}
						});		  

			  },
			  error: function() {
			    alert("ERROR!!!");
			  }
			});	
		
		var caseCode=$("input[name='entity.caseCode']").val();
		
		//取得理算賠款項目資料
		$.ajax({
			  url: '<s:url namespace="/json" action="claim.estimateDetail.findEstimateDetailAjax?caseCode='+caseCode+'" />',
			  type: "GET",
			  dataType: "json",
			  async: false,
			  success: function(Jdata) {
				  $(Jdata["data"]).each(function(){
					var insuredType = $(this)[0]['insuredType'];
					var name = $(this)[0]['victimsName'];
					var price = $(this)[0]['estimatedLossAmount'];
					var ownerName = $(this)[0]['ownerName'];
					var lossAmount = $(this)[0]['lossAmount'];
					var idd = $(this)[0]['id'];
					if(insuredType=="51.乘客責任險"){
						saveDamageObjectOwner(name,price,idd);
					}					
					if(insuredType=="01.甲式車體險"){
						saveOwnerName(ownerName,lossAmount,idd);
					}										
				  });			
				  calAmount();
			  },
			  error: function() {
			    alert("ERROR!!!");
			  }
			});	
		
		
	});
	
	//動態產生保單項目列
	function createTr(d) {
    	return  '<tr>'+
    				'<td name="id">'+'</td>'+
           			'<td name="code">'+d['code']+'</td>'+
           			'<td name="insuranceItem" rowspan="0">'+d['insuranceItem']+'<img name="add" src="<c:url value="/resources/media/images/add.png"/>" width="16px"></td>'+
        			'<td name="name1">'+'</td>'+
        			'<td name="moneyType">'+'</td>'+
        			'<td name="price">'+'</td>'+
    	 	    '</tr>';
	}
	
	//01車險資料回填
	function saveOwnerName(name,price,idd){
		$("#claimItem tbody tr").find("td[name='insuranceItem']").each(function(){					  
			  if($(this).text()=='01.甲式車體險'){
			      var row= $(this).attr("rowspan");
			 	  var tr=$(this).parent();
				  if(row==0){	
					 tr.find("td[name='insuranceItem']").attr("rowspan",(row*1+1));
				 	 tr.find("td[name='name1']").append('<img name="del" src="<c:url value="/resources/media/images/delete.png" />"width="16px">'+'<font color="#7878FF">&nbsp;&nbsp;&nbsp;'+name+'</font>');
				  	 tr.find("td[name='moneyType']").append($('select[name="tempCurrency"]').eq(0).clone().attr("name","entity.amoutCurrency"));
				  	 tr.find("td[name='price']").append('<input name="estimatedLossAmount" type="text" class="input-small" readonly="true" value="'+price+'">');
				  	 tr.find("td[name='id']").append('<s:hidden class="id2" id="iiid" value="'+idd+'" />');
					 $("#selectedCurrency").val($("select[name='entity.amoutCurrency']").val());
				  }else{
					 tr.find("td[name='code']").attr("rowspan",(row*1+1));
					 tr.find("td[name='insuranceItem']").attr("rowspan",(row*1+1));
					 tr.find("td[name='moneyType']").attr("rowspan",(row*1+1));
					 var a01=tr;
					 if(tr.parent().find(".a01").length>0){
						 tr.parent().find(".a01").each(function(){
							 a01=$(this);
						 });
					 }
					 a01.after('<tr class="a01">'+
						'<td>'+
							'<s:hidden class="id2" id="iiid" value="'+idd+'" />'+
						'</td>'+
						'<td>'+
							'<img name="del" src="<c:url value="/resources/media/images/delete.png"/>"width="16px"><font color="#7878FF">&nbsp;&nbsp;&nbsp;'+name+'</font>'+
						'</td>'+
						'<td>'+
							'<input name="estimatedLossAmount" type="text" class="input-small" readonly="true" value="'+price+'">'+
						'</td>'+
					'</tr>');
				  }
	
				
				//DELETE圖片 刪除項目
			 	 tr.parent().find("img[name='del']:last").on('click',function(){
				 	 	 var tr=$(this).parent().parent();
				 	 	 var rowTd=tr.find("td[name='insuranceItem']");	
				 	 	 var row= rowTd.attr("rowspan");
				 	 	 
						var id1=$(this).parent().parent().find("td:first").find("input").val();
						if($(this).parent().length==0){
							id1=$("#claimItem tbody").find("td:first").find("input").val();
						}
				 	 	 if(row==1){
				 	 		rowTd.attr("rowspan",(row*1-1));
				 	 		$(this).parent().parent().find("td[name='moneyType']").text("");
				 	 		$(this).parent().parent().find("td[name='price']").text("");
				 	 		$(this).parent().parent().find("td[name='name1']").text("");
				 	 	 }else{
							if($(this).parent().parent().find("td[name='insuranceItem']").length>0){
					 	 		$(this).parent().parent().find("td[name='price']").text("");
					 	 		$(this).parent().parent().find("td[name='name1']").text("");
							}else{
								tr.find("td[name='code']").attr("rowspan",(row*1-1));
								tr.find("td[name='insuranceItem']").attr("rowspan",(row*1-1));
								tr.find("td[name='moneyType']").attr("rowspan",(row*1-1));
								$(this).parent().parent().remove();
							}							
				 	 	 }
				 	 	calAmount();
				 	 	
				 	 	//ajax刪除

							$.ajax({
								  url: '<s:url namespace="/json" action="claim.estimateDetail.deleteAjax?id='+id1+'" />',
								  type: "GET",
								  dataType: "json",
								  async: false,
								  success: function() {
									
								  },
								  error: function() {
								    alert("ERROR!!!");
								  }
							});
				 	 	 
				  	 });
				
			      amountCurrencySync();
				  calAmount();
			  }
		  });
	}
	
	
	//受損標的所有權人回填
	function saveDamageObjectOwner(name,price,idd) {
		  $("#claimItem tbody tr").find("td[name='insuranceItem']").each(function(){					  
			  if($(this).text()=='51.乘客責任險'){
			      var row= $(this).attr("rowspan");
			 	  var tr=$(this).parent();
				  if(row==0){	
					 tr.find("td[name='insuranceItem']").attr("rowspan",(row*1+1));
				 	 tr.find("td[name='name1']").append('<img name="del" src="<c:url value="/resources/media/images/delete.png" />"width="16px">'+'<font color="#7878FF">&nbsp;&nbsp;&nbsp;'+name+'</font>');
				  	 tr.find("td[name='moneyType']").append($('select[name="tempCurrency"]').eq(0).clone().attr("name","entity.amoutCurrency"));
				  	 tr.find("td[name='price']").append('<input name="estimatedLossAmount" type="text" class="input-small" readonly="true" value="'+price+'">');
				  	 tr.find("td[name='id']").append('<s:hidden class="id2" id="iiid" value="'+idd+'" />');
					 $("#selectedCurrency").val($("select[name='entity.amoutCurrency']").val());
				  }else{
					 tr.find("td[name='code']").attr("rowspan",(row*1+1));
					 tr.find("td[name='insuranceItem']").attr("rowspan",(row*1+1));
					 tr.find("td[name='moneyType']").attr("rowspan",(row*1+1));
					 var a51=tr;
					 if(tr.parent().find(".a51").length>0){
						 tr.parent().find(".a51").each(function(){
							 a51=$(this);
						 });
					 }
 					 a51.after('<tr class="a51">'+
 						'<td>'+
							'<s:hidden class="id2" id="iiid" value="'+idd+'" />'+
						'</td>'+
						'<td>'+
							'<img name="del" src="<c:url value="/resources/media/images/delete.png"/>"width="16px"><font color="#7878FF">&nbsp;&nbsp;&nbsp;'+name+'</font>'+
						'</td>'+
						'<td>'+
							'<input name="estimatedLossAmount" type="text" class="input-small" readonly="true" value="'+price+'">'+
						'</td>'+
					'</tr>');
				  }
				
				//DELETE圖片 刪除項目
			 	 tr.parent().find("img[name='del']:last").on('click',function(){
				 	 	 var tr=$(this).parent().parent().parent();
				 	 	 var rowTd=tr.find("td[name='insuranceItem']");	
				 	 	 var row= rowTd.attr("rowspan");
				 	 	 if(row==1){
				 	 		rowTd.attr("rowspan",(row*1-1));
				 	 		$(this).parent().parent().find("td[name='moneyType']").text("");
				 	 		$(this).parent().parent().find("td[name='price']").text("");
				 	 		$(this).parent().parent().find("td[name='name1']").text("");
				 	 	 }else{
							if($(this).parent().parent().find("td[name='insuranceItem']").length>0){
					 	 		$(this).parent().parent().find("td[name='price']").text("");
					 	 		$(this).parent().parent().find("td[name='name1']").text("");
							}else{
								tr.find("td[name='code']:first").attr("rowspan",(row*1-1));
								tr.find("td[name='insuranceItem']:first").attr("rowspan",(row*1-1));
								tr.find("td[name='moneyType']:first").attr("rowspan",(row*1-1));
								$(this).parent().parent().remove();
							}							
				 	 	 }
				 	 	calAmount();
				 	 	
				 	 	//ajax刪除
							var id1=$(this).parent().parent().find("td:first").find("input").val();
							if($(this).parent().length==0){
								id1=$("#claimItem tbody").find("td:first").find("input").val();
							}
				 	 		
							$.ajax({
								  url: '<s:url namespace="/json" action="claim.estimateDetail.deleteAjax?id='+id1+'" />',
								  type: "GET",
								  dataType: "json",
								  async: false,
								  success: function() {
									
								  },
								  error: function() {
								    alert("ERROR!!!");
								  }
							});
				 	 	 
				  	 });

			 	  amountCurrencySync();
				  calAmount();
			  }
		  });
	}
	
	//計算總預估金額
	function calAmount(){
		var num=0;
		 $("#claimItem tbody").find("input[name='estimatedLossAmount']").each(function(){
			 num=num*1+($(this).val())*1;			 			
		 });		
		 $("input[name='entity.totalEstimatedAmount']").val(num);
	}
	
	//理算確認
	function estimateConfirm(){
		 var total=$("input[name='entity.totalEstimatedAmount']").val();
		 if(total==0){
			 alert("無受損標的所有權人與預估金額");
		 }else{
				document.location="<s:property value='#updateTrue' escape='false'/>";
		 }
	} 
	
	//理算確認取消
	function cancelEstimateConfirm(){
		document.location="<s:property value='#updateFalse' escape='false'/>";
	} 
	
	//幣值 連動
	function amountCurrencySync(){
		var moneyType="";
			 $("select[name='entity.amoutCurrency']").on("change",function(){
				 if($(this).val().length>0){
				 	moneyType=$(this).val();
					$('select[name="entity.amoutCurrency"]').each(function(){
						$(this).val(moneyType);
					});	
				 }	
					$("#selectedCurrency").val(moneyType);
			 });	

	};


	
	
</script>

<style type="text/css">

.divBorder {
	border: 1px solid #dddddd;
	width: 95%;
}

.registerTable tr td {
	border: 1px solid #dddddd;
}

.registerTable tr th {
	border: 1px solid #dddddd;
}
</style>


<title>車險系統</title>
</head>
<body class="page-padding">
	<fieldset>
		<legend>理算賠款</legend>

		<%-- 選單 --%>
		<jsp:include
			page="/WEB-INF/jsp/module/claim/case/layout/caseTopMenu.jsp" />

		<s:form namespace="/crud" method="post" cssClass="form-horizontal">
			<s:hidden name="entity.id" />
			<s:hidden name="dsEstimateDetail.results" />
			<table>
				<tr>
					<td><h5>賠案號碼</h5></td>
					<td><s:textfield name="entity.caseCode"
							cssClass="input-medium" readonly="true" /></td>
					<td><h5>被保險人ID</h5></td>
					<td><s:textfield name="entity.insuredId"
							cssClass="input-medium" readonly="true" /></td>
					<td><h5>出險日期</h5></td>
					<td><s:textfield name="entity.accidcirDate"
							cssClass="input-small" disabled="true" /></td>
					<s:hidden name="entity.accidcirDate" />
					<td><h5>被保險人姓名</h5></td>
					<td><s:textfield name="entity.insuredName"
							cssClass="input-small" readonly="true" /></td>

				</tr>
				<tr>
					<td><h5>車牌號碼</h5></td>
					<td><s:textfield name="entity.plate" cssClass="input-small"
							readonly="true" /></td>
					<td><h5>申請次數</h5></td>
					<td><s:textfield name="entity.applyBout"
							cssClass="input-small" readonly="true" /></td>
					<td><h5>事故原因</h5></td>
					<td><s:textfield name="entity.accident" cssClass="input-medium"
							readonly="true" /></td>
				</tr>
				<tr>
					<td><h5>總預估金額</h5></td>
					<td><s:textfield id="selectedCurrency" cssClass="input-mini"
							readonly="true" /><s:textfield name="entity.totalEstimatedAmount"
							cssClass="input-small" readonly="true" /></td>
				</tr>
			</table>
			<div class="divBorder">
				<h5>保單資料</h5>
				<table class="registerTable">
					<thead>
						<tr>
							<th><h5>保單號碼</h5></th>
							<th><h5>收費情形</h5></th>
							<th><h5>投保期間</h5></th>
							<th><h5>營業經辦</h5></th>
							<th><h5>幣別</h5></th>
							<th><h5>保額</h5></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><s:textfield name="entity.policyCode" readonly="true" cssClass="input-medium"/></td>
							<td><s:textfield name="entity.feeStatus" readonly="true" cssClass="input-small"/></td>
							<td><s:textfield name="entity.insuredPeriod" readonly="true" cssClass="input-large"/></td>
							<td><s:textfield name="entity.processUserCode" readonly="true" cssClass="input-small"/></td>
							<td><s:textfield name="entity.currency" readonly="true" cssClass="input-mini"/></td>
							<td><s:textfield name="entity.insuredAmount" readonly="true" cssClass="input-medium"/></td>
						</tr>
					</tbody>
				</table>
				</div>
				<hr>
				<div class="divBorder">
				<h5>理賠項目</h5>
				<table class="registerTable" id="claimItem">
					<thead>
						<tr>
							<th><h5></h5></th>
							<th><h5>保單號碼</h5></th>
							<th><h5>險種</h5></th>
							<th><h5>受損標的所有權人</h5></th>
							<th><h5>幣別</h5></th>
							<th><h5>預估金額</h5></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div style="display:none">
				<table>
					<tr id="template">
   				  	 	<td class="delRow"><img name="delRow" src="<c:url value="/resources/media/images/delete.png"/>" width="16px"></td>
           				<td class="code"></td>
           				<td class="insuranceItem"><img name="add" src="<c:url value="/resources/media/images/add.png"/>" width="16px"></td>
        				<td class="name1"></td>
        				<td class="moneyType"></td>
        				<td class="price"></td>
    	 		    </tr>
				</table>
			</div>			
			<div id="select"  style="display:none"><s:select label="Currency" name="tempCurrency" listValue="%{currency}"
									list="currencyArray" cssClass="input-small" /></div>
			<div id="amount"  style="display:none">
			<s:textfield name="entity.estimatedAmount"
									cssClass="input-small" readonly="true" /></div>			
									
			<s:hidden name="entity.damageObjectOwner"></s:hidden>	


			<c:if test="${not empty entity.id}">
			<input type="button" class="btn btn-success" onclick="estimateConfirm()" value="理算確認" />
			<input type="button" class="btn btn-inverse return" onclick="cancelEstimateConfirm()" value="取消理算確認" />
			</c:if>
		
			<div class="btn-group" style="float: right">
			
			<c:choose>
				<c:when test="${empty entity.id}">
					<s:submit cssClass="btn btn-success" action="claim.estimate.save"
						value="儲存" />
				</c:when>
			    <c:otherwise>
					<s:submit cssClass="btn btn-success"
							action="claim.estimate.update" value="修改" />
				</c:otherwise>
			</c:choose>
			<s:url namespace="/crud" action="claim.case.query" var="#close">
				<s:param name="entity.caseCode">${entity.caseCode}</s:param>
			</s:url>
				<input type="button" class="btn btn-inverse return" value="關閉"
					onclick="document.location='<s:property value="#close" />'" />
			</div>
		</s:form>
	</fieldset>
	
</body>

</html>