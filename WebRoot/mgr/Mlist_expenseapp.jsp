<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Mlist_expenseapp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
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
		String result = (String)request.getAttribute("result");
		//System.out.println("list_expense_travel.jsp result:"+result);
	 %>

	<script type="text/javascript">
	 
	    var rowsData='{"rows":<%=result %>}';
		rowsData = JSON.parse(rowsData);
		console.log(rowsData);
		
	 </script>
	

  </head>
  
  <body>
     <div style="margin:20px 0;"></div>
	
	 <table id="tt" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:true"></table>
	 
	 <div id="dd" closed = "true" class="easyui-dialog" style="width:400px;height:500px;padding:20px">
	 	
			<form id="reasonff" method="post" class="easyui-form"
				data-options="novalidate:false">

				<table cellpadding="5">
				
					<tr>
						
						<td>
							<p id="hint"></p>
						</td>
					</tr>

					<tr>
						<td>
							<input id="ireason" class="easyui-textbox" type="text"
								name="ireason" style="width: 300px"></input>
						</td>
					</tr>
					
					<tr>
					<td>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						onclick="updateExpenseApp()">提交</a>
					</td>
					</tr>
					
					<tr>
						<td>
							<input type="hidden" id="newstate" name ="newstate" />
						</td>
						<td>
							<input type="hidden" name="trapp_no" id = "trapp_no">
						</td>
					</tr>

				</table>

			</form>
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
				{field:'option',align:'left',title:'操作',width:100,
					formatter:function(value,row,index)
					{
						if(row.state =="部门经理通过"&&row.state!="总经理通过"&&row.state!="总经理打回"&&row.state!="总经理拒绝")
						{
							var a = "<a href=\"javascript:agree(\'"+row.trapp_no+"\');\">通过</a>  "; 
								a += "<a href=\"javascript:reject(\'"+row.trapp_no+"\');\">打回</a>  ";
								a += "<a href=\"javascript:refuse(\'"+row.trapp_no+"\');\">拒绝</a>  ";  
							return a ;
						}
					}
				}
		    ]]
  			
		});
		
		$("#tt").datagrid('loadData', rowsData);

       });
       
	function agree(trapp_no) {
		console.log("agree" + trapp_no);
		upExonly(trapp_no);
	}

	function reject(trapp_no) {
		console.log("reject" + trapp_no);
		showDlg(5,"打回理由","你将打回出差申请编号为"+trapp_no+"的报销申请</br>输入打回理由:",trapp_no);
	}

	function refuse(trapp_no) {
		console.log("refuse" + trapp_no);
		showDlg(4,"拒绝理由","你将拒绝出差申请编号为"+trapp_no+"的报销申请</br>输入拒绝理由:",trapp_no);
	}

	function showDlg(type,title,hint,trapp_no) 
	{
	
	$("p").html(hint);
	document.getElementById("newstate").value=type;
	document.getElementById("trapp_no").value=trapp_no;
	
	console.log("type:" + type+"title:"+title+"hint:"+hint);
	$('#dd').dialog
	({
    title: title,
    width: 400,
    height: 200,
    closed: false,
    cache: false,
    modal: true
	});
	
	}
	
	function updateExpenseApp()
       {
       
       console.log("updateExpenseApp()");
       
       	$('#reasonff').form('submit',
			{
				onSubmit:function()
				{
					return $(this).form('enableValidation').form('validate');
				},
				url:'MApproveExpenseServlet?action=updateExpense',
				
				success:function()
				{
		        	$.messager.alert("提示","保存成功");
		    	}
			});
			
			$('#reasonff').form('clear');
			$('#dd').dialog('close');
			
			setTimeout("window.location.reload()",2000);
			
       }
       
       function upExonly(trapp_no)
       {
       $.ajax({
		        type: "post",
		        url: "MApproveExpenseServlet?action=upExOnly&trapp_no="+trapp_no,
		        success: function()
		        		 {
							$.messager.alert("提示","已通过");
		                 }
		    });
		    
		    setTimeout("window.location.reload()",2000);
       }
	
</script>
	
  </body>
</html>
