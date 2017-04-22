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
    
    <title>My JSP 'travelApp_approve.jsp' starting page</title>
    
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
	 %>
	 
	 <script type="text/javascript">
	 
	    var rowsData='{"rows":<%=result %>}';
		rowsData = JSON.parse(rowsData);
    
	 </script>
	 <style type="text/css">
	 	a{text-decoration: none;}
	 </style>
  </head>
  <body>
	<div style="margin:20px 0;"></div>
	<table id="tt"></table>
	
	
	 <div id="dlg" class="easyui-dialog" title="打回理由" data-options="iconCls:'icon-save'" closed = "true" style="width:1000px;height:400px;padding:40px">
                 <form id="ff" class="easyui-form" method="post" data-options="novalidate:true">
	    	    <table cellpadding="5">
	    	    <tr>
	    			<td>申请单编号:</td>
	    			<td><input type="text" id = "no" name="trapp_no" data-options="required:true"></input></td>
	    		</tr>
	    		 <tr>
	    			<td>审批成本控制员:</td>
	    			<td><input class="easyui-textbox" name="approve_costcontroller" type="text" data-options="required:true"></input></td>
	    		</tr>
                 <tr>
	    			<td>打回理由:</td>
	    			<td><input class="easyui-textbox" name="return_reason_costcontroller" data-options="multiline:true" style="height:100px;width: 400px"></input></td>
	    		</tr>
	    		</table>
	    		<div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submit()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clear()">取消</a>
	    </div>
	    		</form>
	    		
	   </div>
	   <div id="rfdlg" class="easyui-dialog" title="拒绝理由" data-options="iconCls:'icon-save'" closed = "true" style="width:1000px;height:400px;padding:40px">
                 <form id="fff" class="easyui-form" method="post" data-options="novalidate:true">
	    	    <table cellpadding="5">
	    	       <tr>
	    			<td>申请编号</td>
	    			<td><input type="text" id = "newno" name="trapp_no" data-options="required:true"></input></td>
	    		</tr>
	    		 <tr>
	    			<td>审批成本控制员:</td>
	    			<td><input class="easyui-textbox" name="approve_costcontroller" type="text" data-options="required:true"></input></td>
	    		</tr>
                 <tr>
	    			<td>拒绝理由:</td>
	    			<td><input class="easyui-textbox" name="refuse_reson_costcontroller" data-options="multiline:true" style="height:100px;width: 400px"></input></td>
	    		</tr>
	    		</table>
	    		<div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">取消</a>
	    </div>
	    		</form>
	    		
	    	</div>
    <script type="text/javascript">
       $(function(){
       	
       	$('#tt').datagrid({
		    columns:[[
		        {field:'no',width:100,align:'left',title:'申请单编号'},
				{field:'name',width:50,align:'left',title:'出差人'},
				{field:'borrow_money',width:80,align:'left',title:'预借差旅费'},
				{field:'travel_reason',width:150,align:'left',title:'出差目的'},
				{field:'travel_place',width:100,align:'left',title:'出差地点'},
				{field:'plan_time',width:100,align:'left',title:'计划用时'},
				{field:'leave_time',width:100,align:'left',title:'离开时间'},
				{field:'back_time',width:100,align:'left',title:'返回时间'},
				{field:'travel_plan',width:180,align:'left',title:'出差计划'},
				{field:'state',width:100,align:'left',title:'申请状态'},
				{field:'option',align:'left',title:'操作',width:140,align:'center',
					formatter:function(value,row,index){
						if(row.state !="成本控制通过" && row.state !="成本控制打回"&&row.state !="成本控制拒绝"){
							var a = "<a href=\"javascript:agree(\'"+row.no+"\');\">通过</a>  "; 
								a += "<a href=\"javascript:disagree(\'"+row.no+"\');\">打回</a>  ";
								 a += "<a href=\"javascript:refuse(\'"+row.no+"\');\">拒绝</a>  "; 
								 
							return a ;
						}
					}
				}

		    ]]
		});
		
		//加载数据
		$("#tt").datagrid('loadData', rowsData);
		});
		
		
		function agree(no){
			$.ajax({
		        type: "post",
		        url: "CostControlServlet?action=agree&no="+no,
		        success: function(){
						$.messager.alert("提示","处理成功");	
						setTimeout("window.location.reload()",2000);
						
		                 }
		    });
		};
       function disagree(no){
			$("#dlg" ).dialog("open");
            document.getElementById("no").value=no;
            $.ajax({
		        type: "post",
		        url: "CostControlServlet?action=disagree&no="+no,
		    });
		 };
		                  
		function refuse(no){
		  $("#rfdlg" ).dialog("open");
		   document.getElementById("newno").value=no;
			$.ajax({
		        type: "post",
		        url: "CostControlServlet?action=refuse&no="+no,
		    });
		};
		function submitForm(){
			$('#fff').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				url:'CostControlServlet?action=updateApproveRf',
				
				success:function(){
		        	$.messager.alert("提示","拒绝成功");
		        	setTimeout("window.location.reload()",2000);
		        	
		        	//clearForm();
		    	}
			});
			
			$("#rfdlg" ).dialog("close");
			clearForm();
		}
		function clearForm(){
			$('#ff').form('clear');
		}
		function submit(){
			$('#ff').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				url:'CostControlServlet?action=updateApproveDis',
				
				success:function(){
		        	$.messager.alert("提示","打回成功");
		        	setTimeout("window.location.reload()",2000);
		        	
		        	//clearForm();
		    	}
			});
			
			$("#dlg" ).dialog("close");
			clearForm();
		}
		function clear(){
			$('#ff').form('clear');
		}
		
    </script>
  </body>
</html>
