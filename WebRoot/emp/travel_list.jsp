<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    
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
  </head>
  
  	<%
		String result = (String)request.getAttribute("result");
	 %>
	 
	 <script type="text/javascript">
	 
	    var rowsData='{"rows":<%=result %>}';
		rowsData = JSON.parse(rowsData);
    
	 </script>
  <body>
    
    
  
	<div style="margin:20px 0;"></div>
	<table id="tt" class="easyui-datagrid" data-options="rownumbers:true,singleSelect:false,footer:'#ft'" style="width:100%; height:100%">
    </table>
	   <div id="dlg" class="easyui-dialog" title="重写出差申请" data-options="iconCls:'icon-save'" closed = "true" style="width:1000px;height:400px;padding:40px">
	<!--datagrid基本设置-->

		    <form id="ff" class="easyui-form" method="post" data-options="novalidate:true">
	    	<table cellpadding="5">
	    	
	    		<tr>
	    			<td>申请编号</td>
	    			<td><input type="text" id = "no" name="no" data-options="required:true"></input></td>
	    		</tr>
	    	
	    		<tr>
	    			<td>出差人:</td>
	    			<td><input  type="text" id= "name" name="name" data-options="required:true"></input></td>
	    			<td>部门:</td>
	    			<td><input  type="text" id = "dept" name="dept" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>预借差旅费:</td>
	    			<td><input type="text" id ="borrow_money" name="borrow_money" data-options="required:true"></input></td>
	    			<td>费用承担方:</td>
	    			<td>
		    			<select class="easyui-combobox" name="cost_type" style="width:173px;">
							<option value="1">公司</option>
							<option value="2">客户</option>
						</select>
	    			
					</td>
	    		</tr>
	    		<tr>
	    			<td>出差目的:</td>
	    			<td><input type="text" id ="travel_reason" name="travel_reason" data-options="required:true"></input></td>
	    			<td>出差地点:</td>
	    			<td><input type="text" id = "travel_place" name="travel_place" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>随行人员:</td>
	    			<td><input  type="text" id = "follow_name" name="follow_name"></input></td>
	    			<td>计划用时(天):</td>
	    			<td><input type="text" id ="plan_time" name="plan_time" data-options="required:true"></input></td>
	    		</tr>
	    		<tr>
	    			<td>离开时间:</td>
	    			<td><input class="easyui-datebox" type="text" id = "leave_time" name="leave_time" data-options="required:true"></input></td>
	    			<td>返程时间:</td>
	    			<td><input class="easyui-datebox" type="text" id = "back_time" name="back_time"></input></td>
	    		</tr>
	    		<tr>
	    			<td>出差计划:</td>
	    			<td><input  type="text" id = "travel_plan" name="travel_plan" data-options="multiline:true" style="height:100px;width: 400px"></input></td>
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
		       //{field:'id',width:100,title:'申请单编号'},
		        {field:'no',width:100,align:'left',title:'申请单编号'},
				{field:'name',width:50,align:'left',title:'出差人'},
				{field:'dept',width:50,align:'left',title:'部门'},
				{field:'borrow_money',width:80,align:'left',title:'预借差旅费'},
				{field:'travel_reason',width:100,align:'left',title:'出差目的'},
				{field:'travel_place',width:100,align:'left',title:'出差地点'},
				{field:'follow_name',width:100,align:'left',title:'随行人员'},
				{field:'plan_time',width:100,align:'left',title:'计划用时(天)'},
				{field:'leave_time',width:100,align:'left',title:'离开时间'},
				{field:'back_time',width:100,align:'left',title:'返回时间'},
				{field:'travel_plan',width:100,align:'left',title:'出差计划'},
				{field:'option2',title:'状态',align:'left',width:100,
						formatter:function(value,row,index)
						{
							if(row.state == "部门经理打回" || row.state == "成本控制打回")
							{
								var a ="<a href=\"javascript:approve(\'"+row.no+"\');\">"+row.state+" </a>"; 
								return a ;
							}else
							{
								var a = row.state;
								return a;
							}
						}
					},
				{field:'option',title:'操作',align:'left',width:65,
                   formatter:function(value,row,index)
                   {
							if(row.state =="成本控制打回" || row.state == "部门经理打回")
							{
								var a ="<a href=\"javascript:edit(\'"+row.no+"\',\'"+row.name+"\',\'"+row.dept+"\',\'"+row.borrow_money+"\',\'"+row.travel_reason+"\',\'"+row.travel_place+"\',\'"+row.follow_name+"\',\'"+row.plan_time+"\',\'"+row.travel_plan+"\');\">编辑 </a>"; 
								return a ;
							}
						}
				}	
		    ]]
		});
		
		//加载数据
		$("#tt").datagrid('loadData', rowsData);
		
       });
       
       function edit(no,name,dept,borrow_money,travel_reason,travel_place,follow_name,plan_time,travel_plan){
            
			$("#dlg" ).dialog("open");
			document.getElementById("no").value=no;
			document.getElementById("name").value=name;
			if(dept=="1")
			document.getElementById("dept").value="财务部";
			else if(dept=="2")
			document.getElementById("dept").value="策划部";
			else if(dept=="3")
			document.getElementById("dept").value="行政部";
			document.getElementById("borrow_money").value=borrow_money;
			document.getElementById("travel_reason").value=travel_reason;
			document.getElementById("travel_place").value=travel_place;
			document.getElementById("follow_name").value=follow_name;
			plan_time[plan_time.length-1]="";
			document.getElementById("plan_time").value=plan_time;
			//document.getElementById("leave_time").value=leave_time;
			//document.getElementById("back_time").value=back_time;
			document.getElementById("travel_plan").value=travel_plan;

		};
				function submitForm(){
			$('#ff').form('submit',{
				onSubmit:function(){
					return $(this).form('enableValidation').form('validate');
				},
				url:'TravelAppServlet?action=updateApp',
				
				success:function(){
		        	$.messager.alert("提示","保存成功");
		        	setTimeout("window.location.reload()",1500);
		        	//clearForm();
		    	}
			});
			
			$("#dlg" ).dialog("close");
			clearForm();
		}
		function clearForm(){
			$('#ff').form('clear');
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
