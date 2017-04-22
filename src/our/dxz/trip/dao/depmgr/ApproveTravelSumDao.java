package our.dxz.trip.dao.depmgr;

import our.dxz.trip.db.DBConn;

public class ApproveTravelSumDao extends DBConn {
	//执行审批功能
	public void approve(String sql,Object[] params){
		try {
			super.executeSQL(sql,params);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
}
