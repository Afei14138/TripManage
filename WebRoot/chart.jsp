<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'chart.jsp' starting page</title>

	<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	%>
	
	<%
		String result = (String)request.getAttribute("result");
		System.out.println("chart.jsp result:"+result);
	
	 %>
	 
	 <script type="text/javascript">
	 
	  	var rowsData = '<%= result%>';
	    rowsData = JSON.parse(rowsData);
		console.log(rowsData);
		
	 </script>

	<script type="text/javascript" src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
    <script src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
    <script src="http://cdn.hcharts.cn/highstock/highstock.js"></script>

  </head>
  
  <body>
    
    
    <div id="gr"></div>
  
  <script type="text/javascript">
  
  $(function () {
    $('#gr').highcharts({
        title: {
            text: '出差申请统计表',
            x: -20 //center
        },
        subtitle: {
            text: '出差申请日期-人数统计表',
            x: -20
        },
        xAxis: {
            categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun','Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        },
        yAxis: {
            title: {
                text: '人数'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: '人'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: rowsData
    });
});
</script>
    
    
  </body>
</html>
