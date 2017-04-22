<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Travel Application about expense list</title>
    
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	%>
    
    <link rel="stylesheet" type="text/css"
			href="<%=path%>/static/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/static/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/static/themes/icon.css">
		<script type="text/javascript"
			src="<%=path%>/static/js/jquery.min.js"></script>
		<script type="text/javascript"
			src="<%=path%>/static/js/jquery.easyui.min.js"></script>

	<%
		String result = (String)request.getAttribute("result");
		System.out.println("list_expense_travel.jsp result:"+result);
		
		String result2 = (String)request.getAttribute("result2");
		System.out.println("list_expense_travel.jsp result2:"+result2);
	 %>

	<script type="text/javascript">
	 
	    var rowsData='{"rows":<%=result %>}';
		rowsData = JSON.parse(rowsData);
		console.log(rowsData);
		
		var rowsData2='{"rows":<%=result2 %>}';
		rowsData2 = JSON.parse(rowsData2);
		
		console.log(rowsData2);
		
	 </script>

  </head>
  
  <body>
  
    <div style="margin:20px 0;"></div>
	
	<table id="tt" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:true,footer:'#ft'"></table>

    <script type="text/javascript">
    
       $(function()
       {
       	$('#tt').datagrid(
       	{
		    columns:
		    [[
				{field:'trapp_no',width:200,title:'出差申请编号'},
				{field:'expense',width:200,title:'报销费用'},
				{field:'card_no',width:800,title:'报销银行卡号'},
				
		    ]],
		    onClickRow:function(rowIndex,rowData)
		    {
   				onRowClick(rowIndex,rowData);
  			}
  			
		});
		
		//加载数据
		$("#tt").datagrid('loadData', rowsData);
		

		
       });
       

       

       

       
       
    </script>
  
  </body>
</html>
