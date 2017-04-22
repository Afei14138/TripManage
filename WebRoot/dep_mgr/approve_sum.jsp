<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'approve_sum.jsp' starting page</title>
    
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
		String result = (String)request.getAttribute("result");
		System.out.println(result);
	 %>
	 <script type="text/javascript">
	 	var rowsData='{"rows":<%=result %>}';
		rowsData = JSON.parse(rowsData);
		console.log(rowsData);
    </script>
  </head>
  
  <body>
	<div style="margin:20px 0;"></div>
	<table id="tt" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:false,footer:'#ft'"></table>
	<div id="ft" style="padding:2px 5px;">
		<font face="ADMUI3Lg"></font>
	</div>
	<font face="ADMUI3Lg">
	<div id="dlg" class="easyui-dialog" title="审批出差总结" data-options="iconCls:'icon-save'" closed = "true" style="width:440px;height:350px;padding:40px">
		<form id="reform" method="post" class="easyui-form" data-options="novalidate:false">
			<table cellpadding="5">
				<tr>
					<td>
						出差申请编号:
					</td>
					<td>
						<input id = "trapp_no" type="text" name="trapp_no" data-options="required:true" style="width: 250px" ></input>
					</td>
				</tr>
	

				<tr>
					<td>
						点评:
					</td>
					<td>
						<input id = "return_reason_depmanager" class="easyui-textbox" type="text" name="return_reason_depmanager" style="width: 250px"></input>
					</td>
				</tr>
				<tr>
					<td>
						审批人:
					</td>
					<td>
						<input id = "approve_depmanager " class="easyui-textbox" type="text" name="approve_depmanager" style="width: 250px"></input>
					</td>
				</tr>
			</table>

			<div>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="repulseSum()">提交</a>	
			</div>
		</form>
 	</div>
  </body>
  <script type="text/javascript">
	$(function(){
       	$('#tt').datagrid({
			columns:
		    [[
		        {field:'title',width:100,align:'left',title:'标题 '},
				{field:'trapp_no',width:100,align:'left',title:'出差编号'},
				{field:'content',width:700,align:'left',title:'内容'},
				{field:'state',width:100,align:'left',title:'状态'},
				{field:'name',width:100,align:'left',title:'填表人姓名'},
				{field:'option',align:'left',title:'操作',width:65,
				 formatter:function(value,row,index){
					if(row.state =="新建"){
						var a = "<a href=\"javascript:pass(\'"+row.trapp_no+"\');\">通过   </a>"; 
							a += "<a href=\"javascript:repulse(\'"+row.trapp_no+"\');\">打回</a>  "; 
						return a ;
						}
					}
				}					
					
		    ]]
		});
		
		//加载数据
		$("#tt").datagrid('loadData', rowsData);
		
       });
       	function repulse(no){
			$("#dlg" ).dialog("open");
			document.getElementById("trapp_no").value=no;
		};
		function pass(no){
			$.ajax({
		        type: "post",
		        url: "ApproveSumServlet?action=pass&no="+no,
		        success: function(){
					$.messager.alert("提示","处理成功");
					setTimeout("window.location.reload()",2000);
					
		        }
		    });
		};
		
		function repulseSum(no){
			$('#reform').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				url:"ApproveSumServlet?action=repulse&no"+no,
				success:function(){
		        	$.messager.alert("提示","保存成功");
		        	setTimeout("window.location.reload()",2000);
		        	
		    	}
			});
		};
		function clearForm(){
			$('#reform').form('clear');
		};
		

 
    </script></font><!--datagrid基本设置-->
    <br><br><br><br>
  
  
  
  
  
  
  
  
</html>
