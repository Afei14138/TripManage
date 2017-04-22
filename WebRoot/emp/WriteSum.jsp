<%@ page  language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<meta http-equiv="content-type" content="text/html; charset=utf-8"> 

<head>
    <base href="<%=basePath%>"/>
    
		<link rel="stylesheet" type="text/css" href="<%=path%>/static/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="<%=path%>/static/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="<%=path%>/static/themes/icon.css">
		<script type="text/javascript" src="<%=path%>/static/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path%>/static/js/jquery.easyui.min.js"></script>
    
    <title>My JSP 'WriteSum.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%
		request.setCharacterEncoding("utf-8");
		String result = (String)request.getAttribute("result");
		System.out.println(result);
	 %>
	 
	 <script type="text/javascript">
		var rowsData=<%=result%>;
	    rowsData = JSON.parse(rowsData);
	
    </script>

  </head>
  
  <body>

  <form action="TravelSumServlet" method="post" name="saveTravelSum" id="ff" >
  <div style="margin:20px 0;"></div>
	<div class="easyui-panel" title="新建出差总结" style="width:1200px">
		<div style="padding:10px 60px 20px 60px">
	    
	    	<table cellpadding="5">
	    		<tr>
	    			<td>题目:</td>
	    			<td><input class="easyui-textbox" type="text" name="title" data-options="required:true" style="width:900px"></input></td>
	    		</tr>
	    		<tr>
	    			<td>出差单号:</td>
					<td><input id="cg" style="width:900px" name="no"></td>
				</tr>
	    		<tr>
	    			<td>填表人:</td>
	    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true" style="width:900px"></input></td>
	    		</tr>
	    		<tr>
	    			<td>总结内容:</td>
	    			<td><input class="easyui-textbox" name="content" data-options="multiline:true" style="height:300px;width:900px;" ></input></td>
	    		</tr>
	    	</table>
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">清空</a>
	    </div>
	    </div>
	</div>
	</form>
		<script>
		function submitForm(){
			$('#ff').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				url:'TravelSumServlet?action=submitSum',
				success:function(){
		        	$.messager.alert("提示","保存成功");
		        	setTimeout("window.location.reload()",1500);
		    	}
			});
		}
		function clearForm(){
			$('#ff').form('clear');
		}
		
	</script>
	<script type="text/javascript">
	$(function () {
		$('#cg').combogrid({
				panelWidth:500,
				url: '',
				idField:'no',
				textField:'no',
				mode:'remote',
				fitColumns:true,
				columns:[[
					{field:'id',align:'left',title:'ID',width:60},
					{field:'no',align:'left',title:'出差单号',width:60},
					{field:'name',align:'left',title:'出差人姓名',align:'right',width:80},
					{field:'borrow_money',align:'left',title:'借款',align:'right',width:60},
					{field:'travel_place',align:'left',title:'出差地',align:'right',width:60},
					{field:'state',align:'left',title:'状态',align:'center',width:80}

				]]
			});
		  $("#cg").combogrid("grid").datagrid("loadData",rowsData);
	});
	</script>
  	
	
  </body>
</html>
