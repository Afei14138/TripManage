<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	System.out.println("Access Control page");
	
	Cookie[] cookies = request.getCookies();
	
	boolean can = false;
	
	if (cookies != null)
	{
		for (int i = 0; i < cookies.length; i++)
		{
			System.out.println("cookie:"+i+":"+cookies[i].getName()+":"+cookies[i].getValue());
			if(cookies[i].getName().equals("login")  && !cookies[i].getValue().equals(""))
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
    <title>Access Control</title>
    
  </head>
  
  <body>
  </body>
</html>
