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
	
	<table id="tt" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:true" style="width:100%;" ></table>
	
	<div id="dlg" class="easyui-dialog" title="重新编辑报销申请" data-options="iconCls:'icon-save'" closed = "true" style="width:500px;height:350px;padding:40px">
	<form id="reform" method="post" class="easyui-form" data-options="novalidate:false">

				<table cellpadding="5">

					<tr>
						<td>
							出差申请编号:
						</td>
						<td>
							<input id = "re_trapp_no" class="easyui-validatebox" type="text" name="trapp_no" style="width: 250px" ></input>
						</td>
					</tr>

					<tr>
						<td>
							报销的总费用:
						</td>
						<td>
							<input id = "expense_cost"  type="text" name="expense"
								data-options="required:true" style="width: 250px"></input>
						</td>
					</tr>

					<tr>
						<td>
							报销银行卡号:
						</td>
						<td>
							<input id = "expense_cardno"  type="text" name="card_no"
								style="width: 250px"></input>
						</td>
					</tr>

					<tr>
						<td>
							<input id = "restate" type="hidden" name="state" style="width: 250px" ></input>
						</td>
					</tr>
					
				</table>

				<div>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="updateExpenseApp()">提交</a>	
				</div>
		
	</form>
</div>

<div id="dlg2" class="easyui-dialog" title="报销条目列表" data-options="iconCls:'icon-save'" closed = "true" style="width:570px;height:360px;padding:0px">
	<table id="items" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:true" ></table>
</div>
	
    <script type="text/javascript">
    
       $(function()
       {
       	$('#tt').datagrid(
       	{
		    columns:
		    [[
				{field:'trapp_no',width:100,align:'left',title:'出差申请编号'},
				{field:'expense',width:60,align:'left',title:'报销费用'},
				{field:'card_no',width:120,align:'left',title:'报销银行卡号'},
				{field:'state',width:110,align:'left',title:'状态'},
				{field:'option',title:'附件',align:'left',width:65,
					formatter:function(value,row,index)
					{
					console.log(row.imgpath);
						if(row.imgpath != "" && row.imgpath != null)
						{
							var a = "<a href=\"ExpenseAppServlet?action=download&url="+row.imgpath+"\">下载</a>  "; 
							$('#dlg2').dialog('close');
							return a ;
						}
					}
				},
				{field:'option2',title:'操作',align:'left',width:65,
					formatter:function(value,row,index){
						if(row.state == "部门经理打回" || row.state =="总经理打回")
						{
							var a = "<a href=\"javascript:reEditExpenseApp(\'"+row.trapp_no+"\',\'"+row.expense+"\',\'"+row.card_no+"\',\'"+row.state+"\');\">重新编辑</a>  "; 
							 
							return a ;
						}
					}
				}
		    ]],
		    onClickRow:function(rowIndex,rowData)
		    {
   				onRowClick(rowIndex,rowData);
  			}
		    
  			
		});
		
		//加载数据
		$("#tt").datagrid('loadData', rowsData);
		
       	$('#items').datagrid(
       	{
		    columns:
		    [[
				{field:'trapp_no',width:100,title:'出差申请编号'},
				{field:'cost',width:60,title:'条目费用'},
				{field:'type',width:120,title:'条目类别'},
				{field:'desc',width:220,title:'条目描述'},
		    ]]
		});
		
       });
       
       function reEditExpenseApp(trapp_no,expense,card_no,state)
       {
       		console.log("trapp_no:"+trapp_no+" state:"+state+" expense:"+expense+" card_no:"+card_no);
       		$('#dlg').dialog('open');
       		$('#dlg2').dialog('close');
       		
    		$("#re_trapp_no").val(trapp_no);
    		$("#expense_cost").val(expense);
    		$("#expense_cardno").val(card_no);
    		
    		console.log(document.getElementById("re_trapp_no").value);
       		
       }
       
       function updateExpenseApp()
       {
       
       console.log("updateExpenseApp()");
       
       	$('#reform').form('submit',
			{
				onSubmit:function()
				{
					return $(this).form('enableValidation').form('validate');
				},
				url:'ExpenseAppServlet?action=reEditExpenseApp',
				
				success:function()
				{
		        	$.messager.alert("提示","保存成功");
		        	setTimeout("window.location.reload()",1500);
		    	}
			});
			
			$('#reform').form('clear');
			$('#dlg').dialog('close');
						
       }
       
       function onRowClick(rowIndex,rowData)
       {
       		console.log(rowIndex);
   			console.log(rowData);
   			
   			var trapp_no = rowData.trapp_no;
   			console.log(trapp_no);
   			
   			$('#dlg2').dialog('open');
   			
   			var newJson = [];
   			for(var i = 0 ; i <rowsData2.rows.length ; i++)
   			{
   				if(rowsData2.rows[i].trapp_no == trapp_no)
   				{
   					newJson.push(rowsData2.rows[i]);
   				}
   			}
   			var fuck = 
   			{
    			rows : newJson
    		};
   			
   			console.log(fuck);
   			
   			$("#items").datagrid('loadData', fuck);
   			
       }
       
       
    </script>
  
  </body>
</html>
