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
	
	
	<div id="dlg" class="easyui-dialog" title="重写出差借款申请" data-options="iconCls:'icon-save'" closed = "true" style="width:800px;height:350px;padding:40px">
	
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
							借款费用:
						</td>
						<td>
							<input type="text" id = "cost" name="cost"
								data-options="required:true"></input>
						</td>
					</tr>

					<tr>
						<td>
							借款理由:
						</td>
						<td>
							<input type="text" id ="reason" name="reason"
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
       
       $(function()
       {
       	$('#tt').datagrid 
		(
       	{
		    columns:[[
		        {field:'trapp_no',width:150,align:'left',title:'申请单编号'},
				{field:'cost',width:100,align:'left',title:'借款费用'},
				{field:'reason',width:500,align:'left',title:'借款理由'},
				{field:'option',align:'left',title:'操作',width:65,
					formatter:function(value,row,index)
					{
						if(row.state =="部门经理打回")
						{
							var a = "<a href=\"javascript:reEdit(\'"+row.trapp_no+"\',\'"+row.cost+"\',\'"+row.reason+"\');\">重新编辑</a>  "; 
							 
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
		
		$("#tt").datagrid('loadData', rowsData);
	
		
       });
       
       
       function deleteList()
       {
       		var ids = [];
			var rows = $('#tt').datagrid('getSelections');//所有操作必须先获得datagrid
			for(var i=0; i<rows.length; i++){
				ids.push("'"+rows[i].no+"'");//每循环一条就把数据的ID选出来，“‘”的用途
			}
			$.ajax({
		        type: "post",//传递形式
		        //data:{ids:ids},data这句
		        url: "TravelAppApproveServlet?action=deleteList&ids="+ids,//根据ID来删除，提交表单，保存申请单，申请等都单独建一个servelt
		        success: function(){
							$.messager.alert("提示","删除成功");
							$("#tt").datagrid('loadData', rowsData);
							$('#tt').datagrid('reload'); 
		                 }//删除成功会有提示
		    });
       }
       
       function reEdit(trapp_no,cost,reason)
       {
       $('#dlg').dialog('open');
       $("#trapp_no").val(trapp_no);
       $("#cost").val(cost);
       $("#reason").val(reason);
       }
       
       function submitForm()
       {
       $('#ff').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				url:'BorrowAppServlet?action=updateApp',//把表单提交到它
				
				success:function(){
		        	$.messager.alert("提示","保存成功");
		        	setTimeout("window.location.reload()",1500);
		        	//clearForm();
		    	}
			});
			$('#dlg').dialog('close');
			$('#reform').form('clear');
       }
       
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
       
    </script>
  </body>
</html>
