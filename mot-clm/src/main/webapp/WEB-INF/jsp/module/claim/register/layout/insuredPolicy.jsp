<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src='<c:url value="/resources/media/js/jquery.dataTables.js" />'></script>
<link href='<c:url value="/resources/media/css/jquery.dataTables.css"/>'
	rel="stylesheet" type="text/css" media="screen" />
	<script type="text/javascript">
	var policyTable;
	$(function(){
		setRegisterTable();		 
	});		
	function setRegisterTable(){
		var plate=$('input[name="entity.plate"]');
		var policyCode=$('input[name="entity.policyCode"]');
		var ajaxUrl;
		if(plate.val().length){	
			
			if(policyCode.val().length){
				ajaxUrl='<s:url namespace="/json" action="claim.register.findPolicyItemAjax?plate='+plate.val()+'&policyCode='+policyCode.val()+'" />';
			}else{
				ajaxUrl='<s:url namespace="/json" action="claim.register.findPolicyItemAjax?plate='+plate.val()+'" />';
			}
			
			if (policyTable != undefined) {
				policyTable.fnClearTable();
			};
			
			policyTable =$('#registerTable').dataTable( {
			  "ajax": ajaxUrl,
	   	      "columns": [
	            { "data": "code" },
	            { "data": "insuranceDate" },
	            { "data": "payStatu" },
	            { "data": "insuranceItem" },
	            { "data": "eachAccident" },
	            { "data": "eachHurt" },
	            { "data": "eachDead" },
	            { "data": "paySelf" },
	       	 ], "bDestroy": true,
		        "fnDrawCallback": function( oSettings ) {
		    		var policyCode= $('input[name="entity.policyCode"]').val();
             		if(policyCode!=''){
             			$('#policy').text('');
		    			$('#registerTable tbody').find('tr').each(function () {
			    			var code=$('td', this).eq(0).text();			    			
			    			if(policyCode==code){
			    				 $(this).addClass('selected');	
			    			}			               
			            });
		    		}	
		   		   $('#registerTable tbody').on('click', 'tr', function () {	
				        policyTable.$('tr.selected').removeClass('selected');
				     	$(this).addClass('selected');					     	
				        var code = $('td', this).eq(0).text();
				        
				     	$('#registerTable tbody').find('tr').each(function () {
		    				var policyCode=$('td', this).eq(0).text();
		    	   			if(policyCode==code){
			    				 $(this).addClass('selected');	
			    			}			               
			            });
				        
				        
				        $('input[name="entity.policyCode"]').val(code);
				        $('#policy').text('');
				    } );	
		        }
	  	  });		
		}		
	}	
	</script>
	<div class="divBorder">
				<h5 class="heading">保單資料</h5><h4 id="policy" style="color: red">請點擊選取保單</h4>
					<table id="registerTable" class="display" >
   					 <thead>
						<tr>
							<th><h5>保單號碼</h5></th>
							<th><h5>保險起迄日</h5></th>
							<th><h5>繳費情形</h5></th>
							<th><h5>承保項目</h5></th>
							<th><h5>每一事故</h5></th>
							<th><h5>每一體傷</h5></th>
							<th><h5>每一死亡</h5></th>
							<th><h5>自負額</h5></th>
						</tr>
					 </thead>
				</table>
			</div>