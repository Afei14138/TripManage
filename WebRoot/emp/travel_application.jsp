<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<head>
	<base href="<%=basePath%>">
    
    <title>出差管理系统</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script src="http://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
	
	<!-- 引入easyUI所需要的资源文件(绝对路径) -->	
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
			System.out.println("travel_application.jsp result:"+result);
	 	%>
	 	
	 	<script type="text/javascript">
	 	
	 	var res = "\'<%= result%>\'";
	 	if(res!='\'null\'')
	 	{
	 		console.log(res);
	 		alert(res);
	 	}
	 	
	 	</script>
	
</head>
<body>
	<div style="margin:20px 0;"></div>
	
	<div class="easyui-panel" title="填写申请单" style="width:100%">
		<div style="padding:10px 60px 20px 60px">
		
	    <form id="ff" class="easyui-form" method="post" data-options="novalidate:true">
	    	<table cellpadding="5">
	    		<tr>
	    			<td>出差人:</td>
	    			<td><input class="easyui-textbox" type="text" name="name" data-options="required:true"></input></td>
	    			<td>部门:</td>
	    			<td>
	    		         <select class="easyui-combobox" type="text" name="dept" data-options="required:true" style="width:173px;">
							<option value="1">财务部</option>
							<option value="2">策划部</option>
							<option value="3">行政部</option>
						</select>
				    </td>
	    		</tr>
	    		<tr>
	    			<td>预借差旅费:</td>
	    			<td><input class="easyui-textbox" type="text" name="borrow_money" data-options="required:true"></input></td>
	    			<td>费用承担方:</td>
	    			<td>
		    			<select class="easyui-combobox" name="cost_type" style="width:173px;">
							<option value="1">公司</option>
							<option value="2">客户</option>
						</select>
	    			
					</td>
					
	    		<tr>
	    			<td>出差目的:</td>
	    			<td><input class="easyui-textbox" type="text" name="travel_reason" data-options="required:true"></input></td>
	    			<td>出差地点:</td>
	    			<td><input class="easyui-textbox" type="text" name="travel_place" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>随行人员:</td>
	    			<td><input class="easyui-textbox" type="text" name="follow_name"></input></td>
	    			<td>计划用时(天):</td>
	    			<td><input class="easyui-textbox" type="text" name="plan_time" data-options="required:true"></input></td>
	    		</tr>
	    		
	    		<tr>
	    			<td>离开时间:</td>
	    			<td><input type="date" name="leave_time" id="leave_time"></input></td>
	    			<td>返程时间:</td>
	    			<td><input type="date" name="back_time" id="back_time"></input></td>
	    		</tr>

				<tr>
	    			<td>出差计划:</td>
	    			<td><input class="easyui-textbox" name="travel_plan" data-options="multiline:true" style="height:100px;width: 400px"></input></td>
	    		</tr>
	    	</table>
	    </form>
	    
	    <div style="text-align:center;padding:5px">
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
	    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">取消</a>
	    </div>
	    
	    </div>
	</div>
	
	<script>
		function submitForm()
		{
			
			$('#ff').form('submit',
			{
				onSubmit:function()
				{
					return $(this).form('enableValidation').form('validate');
				},
				url:'TravelAppServlet?action=saveApp',
				
				success:function()
				{
		        	//$.messager.alert("提示","保存成功");
		        	setTimeout("window.location.reload()",1500);
		    	}
			});
			
		}
		
		
		function clearForm(){
			$('#ff').form('clear');
		}
		
	</script>
	
	<script type="text/javascript">
			var $start = $("#leave_time"),
				$end = $("#back_time");
			$start.change(function(event) {
				/* Act on the event */
				var end = $end.val();
				if (!end){
					return;
				}
				var start = this.value;
				if (start > end ){
					alert("开始日期不能比结束日期晚");
					this.value = "";
				}

			});
			$end.change(function (event){
				var start = $start.val();
				if (!start){
					return;
				}
				var end = this.value;
				if (start > end ){
					alert("开始日期不能比结束日期晚");
					this.value = "";
				}
			})

	</script>
	
	
</body>
</html>