package our.dxz.trip.servlet.chart;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import our.dxz.trip.bean.ChartBean;
import our.dxz.trip.bean.TravelApp;
import our.dxz.trip.service.chart.ChartService;
import our.dxz.trip.utils.Mog;

public class ChartServlet extends HttpServlet
{

	private static final String TAG = ChartServlet.class.getSimpleName();
	
	private ChartService service = new ChartService();
	
	public ChartServlet()
	{
		super();
	}

	public void destroy()
	{
		super.destroy();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		if (action.equals("cbs"))
		{
			String cbs = getChartBeansJsonString();
			
			request.setAttribute("result", cbs);
			request.getRequestDispatcher("/chart.jsp").forward(request, response);
		}
		
	}
	
	public void init() throws ServletException
	{
		
	}
	
	private String getChartBeansJsonString()
	{
		List<ChartBean> chartBeans = this.TravelApp2ChartBean();
		
		Gson gson = new Gson();
		
		Mog.d(TAG, "ChartBeansJsonString:"+gson.toJson(chartBeans));
		
		return gson.toJson(chartBeans);
		
	}
	
	private List<ChartBean> TravelApp2ChartBean()
	{
		List<ChartBean> chartBeans = new ArrayList<ChartBean>();
		List<TravelApp> apps = service.getTravelAppList();
		
		Mog.d(TAG, "apps length:"+apps.size());
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("1", "财务部");
		map.put("2", "策划部");
		map.put("3", "行政部");
		
		for(int i = 0 ; i < 3; i++)
		{
			ChartBean chartBean = new ChartBean();
			chartBean.setName(map.get(String.valueOf(i+1)));
			chartBeans.add(chartBean);
		}
		
		int[] dep1 = new int[12];
		int[] dep2 = new int[12];
		int[] dep3 = new int[12];
		
		for (TravelApp app : apps)
		{
			if (app.getDept().equals("1"))
			{
				dep1[month(app.getLeave_time())] ++;
				
			}else if (app.getDept().equals("2")) 
			{
				dep2[month(app.getLeave_time())] ++;
				
			}else if (app.getDept().equals("3")) 
			{
				dep3[month(app.getLeave_time())] ++;
				
			}else 
			{
				dep3[month(app.getLeave_time())] ++;
			}
		}
		
		chartBeans.get(0).setData(dep1);
		chartBeans.get(1).setData(dep2);
		chartBeans.get(2).setData(dep3);
		
		return chartBeans;
	}
	
	private int month(Date date)
	{
		Calendar calendar = Calendar.getInstance(); 
        calendar.setTime(date); 

        return  calendar.get(Calendar.MONTH);
        
	}
	

}
