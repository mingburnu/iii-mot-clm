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
	var caseCode =parent.getCaseCode();
	console.log(caseCode);
	var url= '<s:url namespace="/json" action="claim.contact.findContactAjax?caseCode='+caseCode+'" />';
		var table =$('#example').dataTable( {
		  "ajax": url,
	        "columns": [
	            { "data": "contact" },
	            { "data": "homeTel" },
	            { "data": "mobile" },
	            { "data": "mail" },
	            { "data": "address" }
	        ],
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
			 var contact = $('td',clicked).eq(0).text();
			 var homeTel = $('td',clicked).eq(1).text();
			 var mobile = $('td',clicked).eq(2).text();
			 var mail = $('td',clicked).eq(3).text();
			 var address = $('td',clicked).eq(4).text();
			 parent.setData(contact,homeTel,mobile,mail,address);	
		 }
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
            <th>聯絡人</th>
            <th>住家電話</th>
            <th>手機</th>
            <th>電子郵件</th>
            <th>地址</th>
        </tr>
    </thead>
</table>
</body>
</html>