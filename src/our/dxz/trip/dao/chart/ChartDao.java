package our.dxz.trip.dao.chart;

import java.util.ArrayList;
import java.util.List;

import our.dxz.trip.bean.TravelApp;
import our.dxz.trip.db.DBConn;

public class ChartDao extends DBConn
{
	
	public List<TravelApp> getTravelAppList(String sql)
	{
		List<TravelApp> list = new ArrayList<TravelApp>(); 
		try 
		{
			pstm = super.getConnection().prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) 
			{
				TravelApp travelApp = new TravelApp();
				travelApp.setNo(rs.getString("no"));
				travelApp.setName(rs.getString("name"));
				travelApp.setDept(rs.getString("dept"));
				travelApp.setBorrow_money(rs.getString("borrow_money"));
				travelApp.setTravel_reason(rs.getString("travel_reason"));
				travelApp.setTravel_place(rs.getString("travel_place"));
				travelApp.setPlan_time(rs.getString("plan_time"));
				travelApp.setLeave_time(rs.getDate("leave_time"));
				travelApp.setBack_time(rs.getDate("back_time"));
				travelApp.setTravel_plan(rs.getString("travel_plan"));
				
				String state = rs.getString("state");
				if("0".equals(state))
				{
					state = "新建";
				}else if("1".equals(state))
				{
					state = "部门经理拒绝";
				}else if("2".equals(state))
				{
					state = "部门经理打回";
				}else if("3".equals(state))
				{
					state = "部门经理通过";
				}else if("4".equals(state))
				{
					state = "成本控制拒绝";
				}else if("5".equals(state))
				{
					state = "成本控制打回";
				}else
				{
					state = "成本控制通过";
				}
				
				travelApp.setState(state);
				
				list.add(travelApp);
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
		} finally 
		{
			super.closeAll(); 
		}
		
		return list;
	}
	
}
