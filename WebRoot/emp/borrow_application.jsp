<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta http-equiv="content-type" content="text/html; charset=utf-8"> 
  <head>
    <base href="<%=basePath%>">
    
    <title>出差管理系统</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta charset="utf-8">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
		String result = (String)request.getAttribute("result");//获取得到的结果
	 %>
	 
	 <script type="text/javascript">
	 
	    var rowsData='{"rows":<%=result %>}';
		rowsData = JSON.parse(rowsData);
    
	 </script>

  </head>
  
  <body>
  <div class="easyui-panel" title="填写申请单" style="width:100%">
		<div style="padding:10px 60px 20px 60px">
	    <form id="ff" class="easyui-form" method="post" data-options="novalidate:true">
	    	<table cellpadding="5">
	    	<tr>
	    		<td>借款出差编号:</td>
	 			<td> <input id = "bh" name = "trapp_no" /></td>
	    		
	    	</tr>
	    	
	    		<tr>
	    			<td>借款费用:</td>
	    			<td><input class="easyui-textbox" type="text" name="cost" data-options="required:true"></input></td>
                </tr>
                
	    		<tr>
	    			<td>借款理由:</td>
	    			<td><input class="easyui-textbox" type="text" name="reason" data-options="multiline:true" require style="height:100px;width:400px"></input></td>
	    		</tr>
	    			
	    	</table>
	    </form>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">取消</a>
	    </div>
	    </div>
   </div>
   <script>
		function submitForm(){
			$('#ff').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				url:'BorrowAppServlet?action=saveApp',//把表单提交到它
				
				success:function(){
		        	$.messager.alert("提示","保存成功");
		        	setTimeout("window.location.reload()",2000);
		        	//clearForm();
		    	}
			});
		}
		function clearForm(){
			$('#ff').form('clear');
		}//申请时要填写的表单
		
	</script>

<script>

$(function(){
    	
	$('#bh').combogrid
	({
	panelWidth:500,
	idField:'no',
	textField:'no',
	mode:'remote',
	fitColumns:true,
	editable:false,
	columns:
	[[
		
		{field:'no',align:'left',title:'借款出差编号',width:80},
		{field:'dept',align:'left',title:'所属部门',width:80},
		{field:'name',align:'left',title:'出差申请人',width:80},
		{field:'state',align:'left',title:'状态',width:60}
	]]
	
	});
	
	$("#bh").combogrid("grid").datagrid("loadData", rowsData);
	
    });

</script>	
  </body>
</html>	