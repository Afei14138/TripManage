<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>User List</title>

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
	 %>

	<script type="text/javascript">
	 
	    var rowsData='{"rows":<%=result %>}';
		rowsData = JSON.parse(rowsData);
		console.log(rowsData);
		
	 </script>

  </head>
  
  <body>
  
  <table id="tt" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:true,footer:'#ft'"></table>
  
  <script type="text/javascript">
  
  $(function()
       {
       	$('#tt').datagrid(
       	{
		    columns:
		    [[
		    	{field:'id',width:100,title:'用户名'},
				{field:'username',width:100,title:'用户名'},
				{field:'no',width:60,title:'编号'},
				{field:'type',width:120,title:'类别'},
				{field:'option',title:'操作',width:65,
					formatter:function(value,row,index)
					{
						var a = "<a href=\"javascript:deluser(\'"+row.id+"\');\">删除</a>  "; 	 
						return a ;
					}
				}
		    ]]
		});
		
		//加载数据
		$("#tt").datagrid('loadData', rowsData);
		
       });
       
       function deluser(id)
       {
       console.log("deluser:id="+id);
       $.ajax({
		        type: "post",
		        url: "AdministratorServlet?action=deluser&id="+id,
		        success: function()
		        		 {
							$.messager.alert("提示","已删除");
		                 }
		    });
		    
		    setTimeout("window.location.reload()",2000);
       }
  
  </script>
  
   
  
  </body>
</html>
