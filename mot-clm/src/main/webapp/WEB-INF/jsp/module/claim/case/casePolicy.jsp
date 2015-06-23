<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src='<c:url value="/resources/media/js/jquery.dataTables.js" />'></script>
<link href='<c:url value="/resources/media/css/jquery.dataTables.css"/>'
	rel="stylesheet" type="text/css" media="screen" />
<style type="text/css">
td.details-control {
    background: url('/mot-clm/resources/media/images/myAdd.png') no-repeat center center;
    cursor: pointer;
}
tr.shown td.details-control {
    background: url('/mot-clm/resources/media/images/myClose.png') no-repeat center center;
}
</style>
	
<script type="text/javascript">
$(function(){	

	$('#topMenu').find("li").eq(0).removeAttr('class');
	$('#topMenu').find("li").eq(3).attr('class','active');	
	
	var caseCode='${entity.caseCode}';
	//取得保單資料
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

	
	var pla=$('#plate').val();
	var api=null;
	$('#registerTable').dataTable( {
	  "ajax": '<s:url namespace="/json" action="claim.register.findPlateAjax?plate='+pla+'" />',
	  "columns": [
		{
			  "class":         'details-control',
			  "orderable":      false,
			  "data":           null,
			  "defaultContent": ''
		},
        { "data": "code" },
        { "data": "statu" },
        { "data": "insuranceDate" },
        { "data": "insuranceType" },
        { "data": "projectType" },
        { "data": "payType" },
        { "data": "payStatu" },
        { "data": "moneyType" },
        { "data": "policyNotPaid" }, 
        { "data": "policyPaid" },       
   		 ], 
   		"drawCallback": function() {
   	       api = this.api();
   	     }
	  });			
	
	$('#registerTable tbody').on('click', 'td.details-control', function () {
		  var tr = $(this).parents('tr');
	        var row = api.row( tr );
	 
	        if ( row.child.isShown() ) {
	            // This row is already open - close it
	            row.child.hide();
	            tr.removeClass('shown');
	        }
	        else {
	            // Open this row
	            row.child( format(row.data()) ).show();
	            tr.addClass('shown');
	        }
	} );
	
	
});

function format ( d ) {
    // `d` is the original data object for the row
    return '<table cellpadding="5" cellspacing="0" border="0" style="padding-left:50px;">'+
        '<tr>'+
	   		'<td>營業經辦:</td>'+
    	 	'<td>'+d.processUserCode+'</td>'+
	
	   		'<td>強制證號:</td>'+
	 		'<td>'+d.forcedCode+'</td>'+
	
	   		'<td>車輛種類:</td>'+
	 		'<td>'+d.carTypeId+'</td>'+
		'</tr>'+   
        '<tr>'+
	   		'<td>引擎號碼:</td>'+
	 		'<td>'+d.engineCode+'</td>'+
	
	   		'<td>車輛年份:</td>'+
		 	'<td>'+d.carYear+'</td>'+
	
	   		'<td>廠牌型式:</td>'+
	 		'<td>'+d.brandType+'</td>'+
		'</tr>'+   
        '<tr>'+
	   		'<td>排氣量:</td>'+
	 		'<td>'+d.displacement+'</td>'+
	
	   		'<td>載客人數:</td>'+
	 		'<td>'+d.passengers+'</td>'+
	
            '<td>特殊註記:</td>'+
            '<td>'+d.specialNote+'</td>'+
        '</tr>'+
    '</table>';
}
</script>
<title></title>
</head>
<body class="page-padding">
	<fieldset>
		<legend id="haha"> 保單資料 </legend>
				<%-- 選單 --%>
				<jsp:include
					page="/WEB-INF/jsp/module/claim/case/layout/caseTopMenu.jsp" />
				<s:hidden name="plate" id="plate"/>
				<s:hidden name="entity.id" />
				<s:hidden name="entity.caseCode" />
			<table>
				<tr>
					<td><h5>被保險人ID</h5></td>
					<td><s:textfield name="entity.insuredId" cssClass="input-medium"
							readonly="true" /></td>
					<td><h5>被保險人姓名</h5></td>
					<td><s:textfield name="entity.insuredName" cssClass="input-medium"
							readonly="true" /></td>
					<td><h5>車牌號碼</h5></td>
					<td><s:textfield name="entity.plate"
							cssClass="input-medium" readonly="true" /></td>
				</tr>
			</table>
				
				
				<div class="divBorder">
					<table id="registerTable" class="display" >
   					 <thead>
						<tr>
							<th></th>
							<th><h5>保單號碼</h5></th>
							<th><h5>狀態</h5></th>
							<th><h5>保期</h5></th>
							<th><h5>險別</h5></th>
							<th><h5>專案別</h5></th>
							<th><h5>繳費別</h5></th>
							<th><h5>收費情形</h5></th>
							<th><h5>幣別</h5></th>
							<th><h5>未收保費</h5></th>
							<th><h5>總保費</h5></th>
						</tr>
					 </thead>
				</table>
			</div>
		
		
	</fieldset>
</body>
</html>