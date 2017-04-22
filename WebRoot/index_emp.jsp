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
			if(cookies[i].getName().equals("login")  && cookies[i].getValue().equals("0"))
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


<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>My JSP 'index_emp.jsp' starting page</title>

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

	<style>
	a {
	text-decoration: none;
	}
	</style>

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

	<body>
	
	<div class="easyui-layout" data-options="fit:true">

			<div data-options="region:'north'" style="height: 60px;">
				<iframe src="head.html" width="100%" height="100%" frameborder="0"
					scrolling="no">
				</iframe>
				
			</div>

			<div data-options="region:'west',title:'菜单',split:true" style="width: 150px;">
			
				<div class="easyui-accordion" data-options="fit:true">
				
					<div data-options="title:'出差申请',iconCls:'icon-search'">
						<div>
							<img src="static/img/admin.gif" />
							<a
								href="javascript:addTabs('我的出差申请','TravelAppServlet?action=getTravelAppList')">我的出差申请</a>
						</div>
						<div>
							<img src="static/img/admin.gif" />
							<a href="javascript:addTabs('新建出差申请','emp/travel_application.jsp')">新建出差申请</a>
						</div>
					</div>
					
					<div data-options="title:'借款申请',iconCls:'icon-search'">
						<div>
							<img src="static/img/admin.gif" />
							<a
								href="javascript:addTabs('我的借款申请','BorrowAppServlet?action=getBorrowAppList')">我的借款申请</a>
						</div>
						<div>
							<img src="static/img/admin.gif" />
							<a href="javascript:addTabs('新建借款申请','BorrowAppServlet?action=addBorrowApp')">新建借款申请</a>
						</div>
					</div>
					
					<div data-options="title:'出差总结',iconCls:'icon-search'">
						<div>
							<img src="static/img/admin.gif" />
							<a
								href="javascript:addTabs('我的出差总结','TravelSumServlet?action=getTravelSum')">我的出差总结</a>
						</div>
						<div>
							<img src="static/img/admin.gif" />
							<a href="javascript:addTabs('新建出差总结','TravelSumServlet?action=writeSum')">新建出差总结</a>
						</div>
					</div>
					
					<div data-options="title:'报销申请',iconCls:'icon-search'">
						<div>
							<img src="static/img/admin.gif" />
							<a
								href="javascript:addTabs('我的报销申请','ExpenseAppServlet?action=getExpenseAppList')">我的报销申请</a>
						</div>
						<div>
							<img src="static/img/admin.gif" />
							<a href="javascript:addTabs('新建报销申请','ExpenseAppServlet?action=newExpenseApp')">新建报销申请</a>
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
