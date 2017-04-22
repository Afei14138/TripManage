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
	<link  type="text/css" rel="stylesheet"  href="<%=path %>/static/themes/default/easyui.css"/>
	<link type="text/css" rel="stylesheet" href="<%=path %>/static/themes/icon.css" />
	<%
		String result = (String)request.getAttribute("result");//获取得到的结果
	 %>
	 
	 <script type="text/javascript">
	 
	    var rowsData='{"rows":<%=result %>}';
		rowsData = JSON.parse(rowsData);
    
	 </script>
	

  </head>
  
  <body>
   <div style="margin:20px 0;"></div>
	<table id="tt" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:false"></table>
	<div id="dlg" class="easyui-dialog" title="拒绝理由" data-options="iconCls:'icon-save'" closed = "true" style="width:800px;height:350px;padding:40px">
	<form id="ff" class="easyui-form" method="post" data-options="novalidate:true">
	    	<table cellpadding="5">

					<tr>
						<td>
							借款出差编号:
						</td>
						<td>
							<input id="trapp_no" name="trapp_no" />
						</td>
					</tr>

					<tr>
						<td>
							审批人：
						</td>
						<td>
							<input class="easyui-textbox" type="text" name="approve_manager"
								data-options="required:true"></input>
						</td>
					</tr>

					<tr>
						<td>
							打回理由:
						</td>
						<td>
							<input class="easyui-textbox" type="text" name="refuse_reason_manager"
								data-options="multiline:true" require
								style="height: 100px; width: 400px"></input>
						</td>
					</tr>

					<tr>
					<td>
					
					<a href="javascript:void(0)" class="easyui-linkbutton"
							onclick="submitForm()">提交</a>
					</td>
						
					</tr>

				</table>
	    	
	    </form>
	    </div>
	<!--datagrid基本设置-->
    <script type="text/javascript">
       $(function(){
       
       	$('#tt').datagrid({
		    columns:[[
		        {field:'trapp_no',width:150,align:'left',title:'申请单编号'},
				{field:'cost',width:100,align:'left',title:'借款费用'},
				{field:'reason',width:500,align:'left',title:'借款理由'},
				{field:'state',width:100,align:'left',title:'申请状态'},
				{field:'option',align:'left',title:'操作',width:65,
					formatter:function(value,row,index){
						if(row.state =="部门经理通过" && row.state !="总经理通过"&& row.state !="总经理拒绝" ){
							var a = "<a href=\"javascript:agree(\'"+row.trapp_no+"\');\">通过</a>  "; 
								a += "<a href=\"javascript:reason(\'"+row.trapp_no+"\');\">拒绝</a>  "; 
							return a ;
						}
					}
				}

		    ]]
		    
		});
		
		//加载数据
		$("#tt").datagrid('loadData', rowsData);
		});
		
		
		function agree(trapp_no){
		console.log("trapp_no:"+trapp_no);
			$.ajax({
		        type: "post",
		        url: "MApproveBorrowServlet?action=agree&trapp_no="+trapp_no,
		        success: function()
		        {
							$.messager.alert("提示","已通过");
							setTimeout("window.location.reload()",2000);
		                 }
		    });
		     
		}; 
		function reason(trapp_no)
       {
       $('#dlg').dialog('open');
       $("#trapp_no").val(trapp_no);
       }
       
       function submitForm()
       {
       $('#ff').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				url:'MApproveBorrowServlet?action=updateApp',//把表单提交到它
				
				success:function(){
		        	$.messager.alert("提示","保存成功");
		    	}
			});
			$('#dlg').dialog('close');
			$('#reform').form('clear');
			
			setTimeout("window.location.reload()",2000);
       }
       
	
		
		
    </script>
  </body>
</html>
