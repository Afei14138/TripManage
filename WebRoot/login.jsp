<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String result = (String) request.getAttribute("result");
	System.out.println("res" + result);
	if(null != result && result.equals("success"))
	{
	out.println("<script type='text/javascript'>window.location.reload(true);</script>");
	}
%>
<%
	Cookie[] cookies = request.getCookies();
	
	if (cookies != null)
	{
		for (int i = 0; i < cookies.length; i++)
		{
			System.out.println("login.jsp:" + "cookie:" + i + ":"
					+ cookies[i].getName() + ":"
					+ cookies[i].getValue());
					
		}
	}
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>登陆</title>
		<title>My JSP 'index_emp.jsp' starting page</title>

		<link rel="stylesheet" type="text/css" href="<%=path%>/static/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="<%=path%>/static/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="<%=path%>/static/themes/icon.css">
		<script type="text/javascript" src="<%=path%>/static/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path%>/static/js/jquery.easyui.min.js"></script>

</head>

<body>
<div id="login"  style="padding-top:10px">
<div class="easyui_panel" data-options="noheader:true,fit:true" style="padding-left:12px"> 
    	<table>	<form action="LogServlet" target="_self" id="loginForm" method="post">

        <tr>
	        <td>
		    	<table>
		        	<tr><td>用户名：</td><td><input type="text"  id="uname" name="uname"/></td><td></td></tr>
		            <tr><td>密码：</td><td><input type="password"  id="upwd" name="upwd"/></td><td></td></tr>
		        </table>
	        </td>
	        <td>
	        	<img src="./static/img/head.png"/>
	        </td>
        </tr>
        </table>
    </form>
</div>
</div>
</body>


<script>

$(function(){
	
	$("#login").dialog({
		title:'系统登录',
		iconCls:'icon-cus-lock',
		width: 420,
    	height: 230,
		minimizable:false,//是否最小化
		maximizable:false,//是否最大化
		collapsible:true,//是否可折叠
		resizable:false,//是否可拖动
		modal: true,//模态窗口
		buttons:[{
				text:'登录',
				handler:function(){
					doLogin();
				}
			}]
		
	});
	//提示用户名密码错误
	if(result != null && result != "")
	{
		$.messager.alert("提示",result);
	}
});
function doLogin(){
		$("#login").dialog("close");
		$("#loginForm").get(0).submit();
		
}
</script>

</html>
