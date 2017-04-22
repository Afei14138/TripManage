<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'newuser.jsp' starting page</title>
    
    <link rel="stylesheet" type="text/css"
			href="<%=path%>/static/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/static/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="<%=path%>/static/themes/icon.css">
		<script type="text/javascript" src="<%=path%>/static/js/jquery.min.js"></script>
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
								员工姓名:
							</td>
							<td>
								<input id="cg" style="width: 250px" name="username"></select>
							</td>
						</tr>

						<tr>
							<td>
								员工编号:
							</td>
							<td>
								<input type="text" name="no"
									data-options="required:true" style="width: 250px"></input>
							</td>
						</tr>

						<tr>
							<td>
								用户密码:
							</td>
							<td>
								<input type="text" name="password" style="width: 250px"></input>
							</td>
						</tr>
						
						<tr>
							<td>
								用户类别:
							</td>
							
							<td>
								<select name="type" style="width: 250px">
								<option value="0">
									员工
								</option>
								<option value="1">
									部门经理
								</option>
								<option value="2">
									成本控制员
								</option>
								<option value="3">
									总经理
								</option>
								<option value="4">
									财务
								</option>
								<option value="5">
									管理员
								</option>
							</select>
							</td>
							
						</tr>

					</table>

					<div style="text-align: left; padding: 5px">
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">添加</a>
					</div>

				</form>
				
			</div>
			
		</div>
		
		<script type="text/javascript">
		
		function submitForm()
	{
			$('#ff').form('submit',
			{
				onSubmit:function()
				{
					return $(this).form('enableValidation').form('validate');
				},
				url:'AdministratorServlet?action=adduser',
				
				success:function()
				{
		        	$.messager.alert("提示","保存成功");
		        	setTimeout("window.location.reload()",2000);
		        	//clearForm();
		    	}
			});
	}
		
		</script>
		
  </body>
</html>
