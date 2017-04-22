package our.dxz.trip.service.chart;

import java.text.SimpleDateFormat;
import java.util.List;

import our.dxz.trip.bean.TravelApp;
import our.dxz.trip.dao.chart.ChartDao;

public class ChartService
{
	private ChartDao dao = new ChartDao();
	
	public List<TravelApp> getTravelAppList()
	{
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from travel_app order by create_time desc");
		List<TravelApp> list = dao.getTravelAppList(sql.toString());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (TravelApp travelApp : list) 
		{
			travelApp.setLeave_time(travelApp.getLeave_time());
			
		}
		return list;

	}
}
