<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>出差总结表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- 引入easyUI所需要的资源文件(绝对路径) -->	
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
		//console.log(rowsData);
    </script>
  </head>
  
  <body>
  	<div style="margin:20px 0;"></div>
	<table id="tt" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:false,footer:'#ft'">
	</table>
		<div id="ft" style="padding:2px 5px;">
			<font face="ADMUI3Lg"></font>
		</div><font face="ADMUI3Lg">
		
	<div id="dlg" class="easyui-dialog" title="重写出差总结" data-options="iconCls:'icon-save'" closed = "true" style="width:440px;height:350px;padding:40px">
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
					标题:
				</td>
				<td>
					<input id = "title" type="text" name="title" data-options="required:true" style="width: 250px"></input>
				</td>
			</tr>

			<tr>
				<td>
					内容:
				</td>
				<td>
					<input id = "content"  type="text" name="content" style="width: 250px"></input>
				</td>
			</tr>
			<tr>
				<td>
					填表人:
				</td>
				<td>
					<input id = "name"  type="text" name="name" style="width: 250px"></input>
				</td>
			</tr>

		</table>

		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton" onclick="updateSumApp()">提交</a>	
		</div>
		
	</form>
	</div>
		<script type="text/javascript">
		
    	$(function(){
       		$('#tt').datagrid({
		    	columns:
		    	[[
		        	{field:'title',width:100,align:'left',title:'标题 '},
					{field:'trapp_no',width:100,align:'left',title:'出差编号'},
					{field:'content',width:700,align:'left',title:'内容'},
					{field:'name',width:100,align:'left',title:'填表人姓名'},
					{field:'option',title:'操作',align:'left',width:65,
						formatter:function(value,row,index){
							if(row.state == "部门经理打回"){
								var a ="<a href=\"javascript:edit(\'"+row.trapp_no+"\',\'"+row.title+"\',\'"+row.content+"\',\'"+row.name+"\');\">编辑 </a>"; 
		
								return a ;
							}
						}
					},
					{field:'option2',title:'状态',align:'left',width:100,
						formatter:function(value,row,index)
						{
							if(row.state == "部门经理打回")
							{
								var a ="<a href=\"javascript:approve(\'"+row.trapp_no+"\');\">"+row.state+" </a>"; 
								return a ;
							}else
							{
								var a = row.state;
								return a;
							}
						}
					}					
					
		    	]]
			});
		
		//加载数据
		$("#tt").datagrid('loadData', rowsData);
		
       });
       
       function deleteList()
       {
       		var ids = [];
			var rows = $('#tt').datagrid('getSelections');
			for(var i=0; i<rows.length; i++){
				ids.push("'"+rows[i].no+"'");
			}
			$.ajax({
		        type: "post",
		        url: "SystemManageServlet?action=deleteList&ids="+ids,
		        success: function(){
							$.messager.alert("提示","删除成功");
							$("#tt").datagrid('loadData', rowsData);
							$('#tt').datagrid('reload'); 
							setTimeout("window.location.reload()",1500);
		                 }
		    });
       }
       	function edit(trapp_no,title,content,name){
			$("#dlg" ).dialog("open");
			$("#trapp_no").val(trapp_no);
			$("#title").val(title);
			$("#content").val(content);
			$("#name").val(name);
		};
		
		function approve(trapp_no)
		{
			openWin(trapp_no);
			//window.open ("approve.jsp?trapp_no="+trapp_no, "Approve Detail", "height=500, width=600, toolbar= no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
		}
		
		function openWin(trapp_no) 
		{ 
           var url="approve.jsp?trapp_no="+trapp_no;
           var name= "Approve Detail";
           var iWidth=500;
           var iHeight=600;
           var iTop = (window.screen.availHeight - 30 - iHeight) / 2; 
           var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; 
           window.open(url, name, 'height=' + iHeight + ',,innerHeight=' + iHeight + ',width=' + iWidth + ',innerWidth=' + iWidth + ',top=' + iTop + ',left=' + iLeft + ',status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=0,titlebar=no'); 
       }
		
		function updateSumApp(){
			$('#reform').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				url:'TravelSumServlet?action=edit',
				success:function(){
		        	$.messager.alert("提示","保存成功");
		        	setTimeout("window.location.reload()",1500);
		    	}
			});
		}
		function clearForm(){
			$('#reform').form('clear');
		}
		

 
    </script></font><!--datagrid基本设置-->
    <br><br><br><br>
  </body>
</html>
