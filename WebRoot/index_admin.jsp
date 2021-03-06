<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<%
	System.out.println("Access Control page");
	
	Cookie[] cookies = request.getCookies();
	
	boolean can = false;
	
	if (cookies != null)
	{
		for (int i = 0; i < cookies.length; i++)
		{
			System.out.println("cookie:"+i+":"+cookies[i].getName()+":"+cookies[i].getValue());
			if(cookies[i].getName().equals("login")  && cookies[i].getValue().equals("5"))
			{
				System.out.println("can:"+"true");
				can = true;
			}
		}
	}
	
	System.out.println("can:"+can);
	
	if(!can)
	{
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Administrator Site Page</title>
	
	<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	%>
	
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
			
		<script>
		function addTabs(title,url){
			var content="<iframe src=\""+url+"\" width=\"100%\" height=\"100%\" frameborder=\"0\" scrolling=\"no\"></iframe>";
			if($("#tt").tabs("getTab",title)!=null){
				$("#tt").tabs("select",title);
			}else{
				$("#tt").tabs('add',{
					title:title,
					content:content,
					closable:true
				});
			}
		}
	</script>
			
  </head>
  
  <body>
  
  <div class="easyui-layout" data-options="fit:true">

			<div data-options="region:'north'" style="height: 60px;">
				<iframe src="head.html" width="100%" height="100%" frameborder="0"
					scrolling="no">
				</iframe>
				
			</div>

			<div data-options="region:'west',title:'菜单',split:true" style="width: 150px;">
			
				<div class="easyui-accordion" data-options="fit:true">
				
					<div data-options="title:'用户管理',iconCls:'icon-search'">
						<div>
							<img src="static/img/admin.gif" />
							<a
								href="javascript:addTabs('查看用户','AdministratorServlet?action=getUserList')">查看用户</a>
						</div>
						<div>
							<img src="static/img/admin.gif" />
							<a href="javascript:addTabs('添加用户','admin/newuser.jsp')">添加用户</a>
						</div>
					</div>
					
					<div data-options="title:'新闻管理',iconCls:'icon-search'">
				
						<div>
							<img src="static/img/admin.gif" />
							<a href="javascript:addTabs('添加新闻','admin/addnews.jsp')">添加新闻</a>
						</div>
					</div>
					
					<div data-options="title:'统计',iconCls:'icon-search'">
				
						<div>
							<img src="static/img/admin.gif" />
							<a href="javascript:addTabs('出差统计','ChartServlet?action=cbs')">出差统计</a>
						</div>
					</div>
									
					
				</div>

			</div>
			<div data-options="region:'center'">
				<div id="tt" class="easyui-tabs" data-options="fit:true">

					<div data-options="title:'首页'">
						<iframe src="welcome.jsp" width="100%" height="100%"
							frameborder="0" scrolling="no"></iframe>
					</div>
				</div>
			</div>
		</div>
  
  </body>
</html>
