<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<script type="text/javascript"
	src='<c:url value="/resources/media/js/jquery.js" />'></script>
<script type="text/javascript"
	src='<c:url value="/resources/media/js/jquery.dataTables.js" />'></script>
<link href='<c:url value="/resources/media/css/jquery.dataTables.css"/>'
	rel="stylesheet" type="text/css" media="screen" />

<script type="text/javascript">
$(function(){
	var url= '<s:url namespace="/json" action="claim.register.findPlateNoSameAjax" />';
		var table =$('#example').dataTable( {
		  "ajax": url,
	        "columns": [
	            { "data": "plate" },
	            { "data": "insuredId" },
	            { "data": "insuredName" },
	        ],
	        "fnDrawCallback": function( oSettings ) {
	    		$('#example tbody').find('tr').each(function () {
	    			var plate=$('td', this).eq(0).text();
	    			var result=parent.checkPlate(plate);
	    			if(result){
	    				$(this).remove();
	    			}
	               
	            });	
	        }
	    } );	
		


	 var clicked =null;
	 
	 $('#example tbody').on('click', 'tr', function () {	 
		   var code = $('td', this).eq(0).text();
		   clicked=this;
	       $('#selected').text(code);
	       
            table.$('tr.selected').removeClass('selected');
	        $(this).addClass('selected');	       
	    } );	 
	 
	 $('#button1').on('click',function(){
		 if(clicked != null){
			 var plate = $('td',clicked).eq(0).text();
			 parent.setPlate(plate);	
		 }
		 parent.setRegisterTable();
		 parent.jQuery.fancybox.close();
	 });
	 
	 
});

</script>

</head>
<body>
<div align="center">
<table>
<tr>
	<td><h3 id="selected" style="color: red">點擊選取</h3></td>
	<td><input type="button" class="btn btn-inverse return" value="確認"
					id="button1" /></td>
</tr>
</table>
</div>
<table id="example" class="display" >
    <thead>
        <tr>
            <th>車牌號碼</th>
            <th>車主身分證字號</th>
            <th>車主姓名</th>
        </tr>
    </thead>
</table>
</body>
</html>