package our.dxz.trip.service.emp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import our.dxz.trip.config.StorgeUser;
import our.dxz.trip.dao.emp.TravelAppDao;
import our.dxz.trip.utils.Mog;
import our.dxz.trip.bean.TravelApp;
import our.dxz.trip.bean.Approve;
import our.dxz.trip.bean.User;

public class TravelAppService {

	private static final String TAG = TravelAppService.class.getSimpleName();
	
	TravelAppDao taDao = new TravelAppDao();
	
	//保存申请单
	public int saveApprove(Approve approve){
		
		int row=0;
		StringBuffer sql = new StringBuffer();
		sql.append("insert into approve(trapp_no) values(?)");
		Object[] param = {approve.getTrapp_no()};
		row = taDao.saveTravelApp(sql.toString(),param);
		return row;
	}
	public int saveTravelApp(TravelApp travelApp){
		int row=0;
		StringBuffer sql = new StringBuffer();
		//System.out.println(travelApp);
		sql.append("insert into travel_app(no,dept,name,follow_name,borrow_money,cost_type," +
				"travel_reason,travel_place,plan_time,back_time,leave_time,create_time," +
				"travel_plan,state) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		Object[] param = 
		{       
				travelApp.getNo(),
				travelApp.getDept(),
				travelApp.getName(),
				travelApp.getFollow_name(),
				travelApp.getBorrow_money(),
				travelApp.getCost_type(),
				travelApp.getTravel_reason(),
				travelApp.getTravel_place(),
				travelApp.getPlan_time(),
			    travelApp.getBack_time(),
				travelApp.getLeave_time(),
				travelApp.getCreate_time(),
				travelApp.getTravel_plan(),
				travelApp.getState()
		};
	row = taDao.saveTravelApp(sql.toString(),param);
		return row;
	}
	public int updateTravelApp(TravelApp travelApp){
		int row=0;
		StringBuffer sql = new StringBuffer();
		//System.out.println(travelApp);
		/*sql.append("insert into travel_app(no,dept,name,follow_name,borrow_money,cost_type," +
				"travel_reason,travel_place,plan_time,back_time,leave_time,create_time," +
				"travel_plan,state) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");*/
		sql.append("update travel_app set dept=?,name=?,follow_name=?,borrow_money=?,cost_type=?,travel_reason=?,travel_place=?,plan_time=?,back_time=?," +
				"leave_time=?,create_time=?,travel_plan=?,state=? where no=?");
		Object[] param = 
		{       
				
				travelApp.getDept(),
				travelApp.getName(),
				travelApp.getFollow_name(),
				travelApp.getBorrow_money(),
				travelApp.getCost_type(),
				travelApp.getTravel_reason(),
				travelApp.getTravel_place(),
				travelApp.getPlan_time(),
			    travelApp.getBack_time(),
				travelApp.getLeave_time(),
				travelApp.getCreate_time(),
				travelApp.getTravel_plan(),
				travelApp.getState(),
				travelApp.getNo()
		};
	     row = taDao.updateTravelApp(sql.toString(),param);
			Mog.d("TR:","row"+row);
			
		return row;
	}
	/**
	 * 获取列表
	 * */
	public List<TravelApp> getTravelAppList()
	{
		
		User user = StorgeUser.getCacheUser();
		
		String sql = "select * from travel_app order by create_time desc";
		
		if (null != user)
		{
			String username = StorgeUser.getCacheUser().getUsername();
			String type = StorgeUser.getCacheUser().getType();
			
			Mog.d(TAG, "get cache user != null :"+"usertype:"+type+"name:"+username);
			
			if (type.equals("0") && username != null)
			{
				sql = "select * from travel_app where name = \'"+username+"\'"+"order by create_time";
			}
			
		}
		
		
		List<TravelApp> list = taDao.getTravelAppList(sql.toString());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (TravelApp travelApp : list) {
			travelApp.setLeave_time(travelApp.getLeave_time());
			
		}
		return list;

	}
}