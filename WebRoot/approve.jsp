<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@page import="our.dxz.trip.dao.emp.ApproveDao"%>
<%@page import="our.dxz.trip.bean.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Approve Detail</title>
	 
  </head>
  
  <body>
  
  <div style="width:100%; height:100%; overflow:scroll; padding: 50px">
    
    <table border="0" width="100%" align="center">
  
     <%
     	ApproveDao handler=new ApproveDao();
     	String trapp_no = request.getParameter("trapp_no");
     	String sql = "select * from approve where trapp_no = \'"+trapp_no+"\'";
        List<Approve> list=handler.getApproves(sql);
        
        if(list.size()>0)
        {
        	Approve approve = list.get(0);
        	
        	%>
         <tr>
          	<td>审批ID</td>
          	<td><%= approve.getId() %></td>
         </tr>
                
         <tr>
         	<td>出差申请编号</td>
          	<td><%= approve.getTrapp_no() %></td>
         </tr>
         
         <tr>
          	<td>审批通过的部门经理</td>
          	<td><%= approve.getApprove_depmanager() %></td>
         </tr>
                
         <tr>
         	<td>部门经理审批通过的时间</td>
          	<td><%= approve.getApprove_date_dapmanager() %></td>
         </tr>
         
         <tr>
          	<td>部门经理拒绝理由</td>
          	<td><%= approve.getRefuse_reason_depmanager() %></td>
         </tr>
                
         <tr>
         	<td>部门经理打回理由</td>
          	<td><%= approve.getReturn_reason_depmanager() %></td>
         </tr>
         
         <tr>
          	<td>审批通过的成本控制员</td>
          	<td><%= approve.getApprove_costcontroller() %></td>
         </tr>
                
         <tr>
         	<td>成本控制员审批通过的时间</td>
          	<td><%= approve.getApprove_date_costcontroller() %></td>
         </tr>
         
         <tr>
          	<td>成本控制员拒绝理由</td>
          	<td><%= approve.getRefuse_reson_costcontroller() %></td>
         </tr>
                
         <tr>
         	<td>成本控制员打回理由</td>
          	<td><%= approve.getReturn_reason_costcontroller() %></td>
         </tr>
         
         <tr>
          	<td>审批通过的经理</td>
          	<td><%= approve.getApprove_manager() %></td>
         </tr>
                
         <tr>
         	<td>经理审批通过的时间</td>
          	<td><%= approve.getApprove_date_manager() %></td>
         </tr>
         
         <tr>
          	<td>经理拒绝理由</td>
          	<td><%= approve.getRefuse_reason_manager() %></td>
         </tr>
                
         <tr>
         	<td>经理打回理由</td>
          	<td><%= approve.getReturn_reason_manager() %></td>
         </tr>
         
          
         <%
        	
        }
        
     
      %>
       </table>
  
	</div>
  
  </body>
</html>
