<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>出差管理系统</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script src="<%=path %>/static/js/jquery.min.js"></script>
		<script src="<%=path %>/static/js/jquery.easyui.min.js"></script>
		<script src="<%=path %>/static/js/easyui-lang-zh_CN.js"></script>
		<link type="text/css" rel="stylesheet"
			href="<%=path %>/static/themes/default/easyui.css" />
		<link type="text/css" rel="stylesheet"
			href="<%=path %>/static/themes/icon.css" />

		<%
		String result = (String)request.getAttribute("result");//获取得到的结果
	 %>

		<script type="text/javascript">
	 
	    var rowsData='{"rows":<%=result %>}';
		rowsData = JSON.parse(rowsData);
    
	 </script>

	</head>

	<body>
		<div style="margin: 20px 0;"></div>
		<table id="tt" class="easyui-datagrid"
			data-options="rownumbers:true,singleSelect:false"></table>


	
		<!--datagrid基本设置-->
		<script type="text/javascript">
       
       $(function()
       {
       	$('#tt').datagrid 
		(
       	{
		    columns:[[
		        {field:'trapp_no',width:150,title:'申请单编号'},
				{field:'cost',width:100,title:'借款费用'},
				{field:'reason',width:500,title:'借款理由'},
				
		    ]]
		});
		
		$("#tt").datagrid('loadData', rowsData);
	
		
       });
       
    </script>
	</body>
</html>
