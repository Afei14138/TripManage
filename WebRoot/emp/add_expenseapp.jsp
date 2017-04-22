<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Add new expense app</title>

		<link rel="stylesheet" type="text/css"
			href="<%=path%>/static/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/static/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/static/themes/icon.css">
		<script type="text/javascript" src="<%=path%>/static/js/jquery.min.js"></script>
		<script type="text/javascript"
			src="<%=path%>/static/js/jquery.easyui.min.js"></script>

		<%
			String result = (String) request.getAttribute("appsResult");
			System.out.println("list_expense_travel.jsp result:" + result);
		%>

	<script type="text/javascript">
	 
	    var rowsData='{"rows":<%=result%>}';
		rowsData = JSON.parse(rowsData);
		
		console.log(rowsData);
		
	 </script>

	</head>

<body>

		<div style="margin: 20px 0;"></div>
		<div class="easyui-panel" title="填写申请单" style="width: 100%">
			<div style="padding: 10px 60px 20px 60px">
				<form id="ff" class="easyui-form" method="post"
					data-options="novalidate:true">
					<table cellpadding="5">

						<tr>
							<td>
								报销的出差申请:
							</td>
							<td>
								<select id="cg" style="width: 250px" name="expense_trappno"></select>
							</td>
						</tr>

						<tr>
							<td>
								报销的总费用:
							</td>
							<td>
								<input class="easyui-textbox" type="text" name="expense_cost"
									data-options="required:true" style="width: 250px"></input>
							</td>
						</tr>

						<tr>
							<td>
								报销银行卡号:
							</td>
							<td>
								<input class="easyui-textbox" type="text" name="expense_cardno"
									style="width: 250px"></input>
							</td>
						</tr>

					</table>

					<div style="text-align: left; padding: 5px">
						<a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="submitForm()">提交</a>
						<a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="clearForm()">清空</a>
							
						<a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="addExpenseItem()">添加报销条目</a>
							
						<a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="uploadDlg()">上传附件</a>
							
					</div>

				</form>
				
			</div>
			
		</div>
		
		

<div id="dlg" class="easyui-dialog" title="添加报销条目" data-options="iconCls:'icon-save'" closed = "true" style="width:500px;height:250px;padding:40px">
	<form id="itemform" method="post" class="easyui-form" data-options="novalidate:true">
		
		<table cellpadding="5">

						<tr>
							<td>
								报销条目的费用:
							</td>
							<td>
								<input id ="item_cost" class="easyui-textbox" type="text" name="item_cost"
									data-options="required:true" style="width: 250px"></input>
							</td>
						</tr>

						<tr>
							<td>
								报销条目的类别:
							</td>
							<td>
							<select id = "item_type" name="item_type" style="width: 250px;">
								
								<option selected = "selected">
									车费
								</option>
								<option>
									旅费
								</option>
								<option>
									住宿费
								</option>
								<option>
									食费
								</option>
								<option>
									其他
								</option>
								
							</select>
						</td>
						</tr>

						<tr>
							<td>
								报销条目描述:
							</td>
							<td>
								<input id = "item_desc" class="easyui-textbox" type="text" name="item_desc"
									style="width: 250px"></input>
							</td>
						</tr>

					</table>
		
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitExpenseItem()">提交</a>	
		</div>
		
	</form>
</div>

<div id="dlg2" class="easyui-dialog" title="上传图片" closed = "true" style="width:440px;height:350px;padding:40px">
			<form id="imgff" class="easyui-form"  method="post" enctype="multipart/form-data">

				<input type="file" id="img" name="img" style="width: 250px"></input>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="uploadImg()">添加</a>

			</form>
</div>

<div class="easyui-panel" title="报销条目列表" style="width: 100%">
<table id="eil" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:true,collapsible:true"></table>
</div>


<script type="text/javascript">
	var globalData = [];
	
	$(function(){
    	
	$('#cg').combogrid
	({
	panelWidth:500,
	idField:'trapp_no',
	textField:'trapp_no',
	mode:'remote',
	fitColumns:true,
	editable:false,
	columns:
	[[
		{field:'trapp_no',title:'出差申请编号',width:60},
		{field:'name',title:'总结人',width:40},
		{field:'state',title:'状态',width:60}
	]]
	
	});
	
	$("#cg").combogrid("grid").datagrid("loadData", rowsData);
	
	$('#dd').dialog({
    title: 'My Dialog',
    width: 400,
    height: 200,
    closed: false,
    cache: false,
    modal: true
	});
	
    });
    
    ////////////Expense item listview//////////////
    $(function()
       {
       	$('#eil').datagrid(
       	{
		    columns:
		    [[
				{field:'cost',width:60,title:'条目费用'},
				{field:'type',width:120,title:'条目类别'},
				{field:'desc',width:110,title:'条目描述'}
		    ]]
		});
		
		//加载数据
		//$("#eil").datagrid('loadData', rowsData);
		
       });
    
    var jsonData;
    
    function updateItemLv()
    {
    	var cost = document.getElementById("item_cost").value ;
    	var type = document.getElementById("item_type").value ;
    	var desc = document.getElementById("item_desc").value ;
    	var data = {
    		cost : cost,
    		type : type,
    		desc : desc
    	};
    	globalData.push(data);
    	var fuck = {
    		rows : globalData
    	};
    	
    	console.log(fuck);
    	
    	$("#eil").datagrid('loadData', fuck);
    	
    }
    
	function getValue()
	{   		
  		 var grid=$("#cg").combogrid("grid");//获取表格对象 
  		 var row = grid.datagrid('getSelected');//获取行数据 
  		 alert(row.no); 
 	} 
	
	function submitForm()
	{
			$('#ff').form('submit',
			{
				onSubmit:function()
				{
					return $(this).form('enableValidation').form('validate');
				},
				url:'ExpenseAppServlet?action=saveExpenseApp',
				
				success:function()
				{
		        	$.messager.alert("提示","保存成功");
		        	setTimeout("window.location.reload()",2000);
		        	//clearForm();
		    	}
			});
	}
	
	function clearForm()
	{
		$('#ff').form('clear');
	}
	
	function addExpenseItem()
	{
		$('#dlg').dialog('open');
	}
	
	function submitExpenseItem()
	{	
	
		updateItemLv();
		$('#itemform').form('submit',
			{
				onSubmit:function()
				{
					return $(this).form('enableValidation').form('validate');
				},
				url:'ExpenseAppServlet?action=newExpenseAppItem',
				
				success:function()
				{
		        	$.messager.alert("提示","保存成功");
		        	//clearForm();
		    	}
			});
		$('#itemform').form('clear');
		$('#dlg').dialog('close');
	}
	
	
	function uploadDlg() 
	{
		$('#dlg2').dialog('open');
	}

	function uploadImg() 
	{
		$('#imgff').form('submit',
			{
				onSubmit:function()
				{
					return $(this).form('enableValidation').form('validate');
				},
				url:'ExpenseAppServlet?action=uploadimg',
				
				success:function()
				{
		        	$.messager.alert("提示","上传成功");
		        	$('#dlg2').dialog('close');
		        	//setTimeout("window.location.reload()",1500);
		    	}
			});
	}
</script>

</body>
</html>
