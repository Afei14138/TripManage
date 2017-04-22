package our.dxz.trip.service.depmgr;

import java.text.SimpleDateFormat;
import java.util.Date;

import our.dxz.trip.bean.Approve;
import our.dxz.trip.dao.depmgr.ApproveTravelSumDao;

public class ApproveSumService {
	ApproveTravelSumDao approveTravelSumDao = new ApproveTravelSumDao();
	//通过出差总结
	public void pass(String no){
		//如何更新两个表
		Date currentTime = new Date();
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
	
		StringBuffer sql = new StringBuffer();
		sql.append("update approve,travel_sum set approve.approve_date_dapmanager=?,travel_sum.state='2' where approve.trapp_no=? and approve.trapp_no=travel_sum.trapp_no;");
		Object[] param = 
		{
			java.sql.Timestamp.valueOf(time),
			no
		};
		
		approveTravelSumDao.approve(sql.toString(),param);
	}
	//打回出差总结
	public int repulse(Approve approve){
		int row = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("Update travel_sum,approve set travel_sum.state='1',approve.approve_depmanager=?,approve.return_reason_depmanager=? where travel_sum.trapp_no=? and travel_sum.trapp_no=approve.trapp_no;");
		Object[] param = {
			approve.getApprove_manager(),
			approve.getReturn_reason_depmanager(),
			approve.getTrapp_no()
				
		};
		approveTravelSumDao.approve(sql.toString(), param);
		return row;
	}
}
