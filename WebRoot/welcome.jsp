<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@page import="our.dxz.trip.servlet.admin.*"%>
<%@page import="our.dxz.trip.bean.*"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    <title>Welcome to the TripManager Site</title>
    
  </head>
  
  <body>
    
    
    <div style="width:100%; height:100%; overflow:scroll; padding: 50px">
    
    <table border="0" width="100%" align="center">
  
     <%
     	NewsHandler handler=new NewsHandler();
        List<News> list=handler.getNewsList();
        for(News item:list)
        {%>
         <tr>
          	<td width="60%">
          	<span style="font-size:20px;color:blue;"><%= item.getTitle()%></span>
          	<span style="font-size:12px;color:red;  text-align:center;"><%= item.getEditor()%></span>
          	</br>
          	</br>
          	<span style="font-size:12px;color:black;"> <%= item.getContent().length()>100?item.getContent().substring(0, 100).toString()+"......":item.getContent()%></span>
          	</td>
          	<td width="40%"> <img alt="" width="100px" height="100px" src="<%= item.getImgpath()%>"> </td>
         </tr>
                
         <tr>
         <td><DIV style="BORDER-TOP: #00686b 1px dashed; OVERFLOW: hidden; HEIGHT: 1px"></DIV></td>
         </tr>
          
         <%}
     
      %>
       </table>
  
	</div>
   
  </body>
  
</html>
