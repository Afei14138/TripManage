<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Add News</title>
	
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
	
  </head>
  
  <body>
  	<div class="easyui-panel" title="添加用户" style="width: 100%">
			<div style="padding: 10px 60px 20px 60px">
				<form id="ff" class="easyui-form" method="post" data-options="novalidate:true">
					<table cellpadding="5">

						<tr>
							<td>
								标题
							</td>
							<td>
								<input type="text" id="title" name="title" style="width: 250px" ></select>
							</td>
						</tr>

						<tr>
							<td>
								内容:
							</td>
							<td>
								<textarea id = "content" name="content" rows="4" style="width: 250px"></textarea>
							</td>
						</tr>
						
						<tr>
							<td>
								编辑:
							</td>
							<td>
								<input type="text" id="editor" name="editor" style="width: 250px"></input>
							</td>
						</tr>
						
					</table>

					<div style="text-align: left; padding: 5px">
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addNews()">添加</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="uploadDlg()">上传图片</a>
					</div>

				</form>
				
			</div>
			
		</div>
		
		
		<div id="dlg" class="easyui-dialog" title="上传图片" closed = "true" style="width:440px;height:350px;padding:40px">
			<form id="imgff" class="easyui-form"  method="post"
				enctype="multipart/form-data">

				<input type="file" id="img" name="img" style="width: 250px"></input>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="uploadImg()">添加</a>

			</form>
		</div>
		
		<script type="text/javascript">
		
		function addNews()
		{
			$('#ff').form('submit',
			{
				onSubmit:function()
				{
					return $(this).form('enableValidation').form('validate');
				},
				url:'NewsServlet?action=addnews',
				
				success:function()
				{
		        	$.messager.alert("提示","保存成功");
		        	setTimeout("window.location.reload()",2000);
		    	}
			});
		}
		
		function uploadDlg()
		{
			$('#dlg').dialog('open');
		}
		
		function uploadImg()
		{	
			$('#imgff').form('submit',
			{
				onSubmit:function()
				{
					return $(this).form('enableValidation').form('validate');
				},
				url:'NewsServlet?action=uploadimg',
				
				success:function()
				{
		        	$.messager.alert("提示","上传成功");
		        	$('#dlg').dialog('close');
		        	//setTimeout("window.location.reload()",1500);
		    	}
			});
		}
		
		</script>
  </body>
</html>
