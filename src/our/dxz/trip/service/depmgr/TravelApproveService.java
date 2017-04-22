package our.dxz.trip.service.depmgr;

import java.text.SimpleDateFormat;
import java.util.List;

import our.dxz.trip.bean.TravelApp;
import our.dxz.trip.bean.Approve;

import our.dxz.trip.dao.depmgr.TravelApproveDao;
import our.dxz.trip.utils.Mog;

public class TravelApproveService {
	TravelApproveDao taDao = new TravelApproveDao();
	public List<TravelApp> getTravelAppList(){
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from travel_app ta order by ta.create_time desc");
		
		List<TravelApp> list = taDao.getTravelAppList(sql.toString());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (TravelApp travelApp : list) {
			travelApp.setLeave_time(travelApp.getLeave_time());
			
		}
		return list;
	}
	
	public void agree(String no){
		StringBuffer sql = new StringBuffer();
		sql.append("update travel_app ta set ta.state = 3 where ta.no = ?");
		Object[] param = {no};
	    	
		taDao.approve(sql.toString(),param);
		
	}
	/**
	 * 打回出差申请
	 * */
	public void disagree(String no){
		StringBuffer sql = new StringBuffer();
		sql.append("update travel_app ta set ta.state = 2 where ta.no = ?");
		Object[] param = {no};
		
		taDao.approve(sql.toString(),param);
	}
	public void refuse(String no){
		StringBuffer sql = new StringBuffer();
		sql.append("update travel_app ta set ta.state = 1 where ta.no = ?");
		Object[] param = {no};
		
		taDao.approve(sql.toString(),param);
	}
	public int updateApproveRf(Approve approve){
		int row=0;
		StringBuffer sql = new StringBuffer();
		sql.append("update approve set approve_depmanager = ?,refuse_reason_depmanager=? where trapp_no = ?");
		Object[] param = {
				approve.getApprove_depmanager(),
				approve.getRefuse_reason_depmanager(),
				approve.getTrapp_no()
		};
		Mog.d("SQL里面的trappe_no:",approve.getTrapp_no());
		Mog.d("SQL里面的trappe_no:",approve.getApprove_depmanager());
		Mog.d("SQL里面的refuse_no:",approve.getRefuse_reason_depmanager());
		row=taDao.saveTravelApp(sql.toString(),param);
		return row;
		
	}
	public int updateApproveDis(Approve approve){
		int row=0;
		StringBuffer sql = new StringBuffer();
		sql.append("update approve set approve_depmanager = ?,return_reason_depmanager=? where trapp_no = ?");
		Object[] param = {
				approve.getApprove_depmanager(),
				approve.getReturn_reason_depmanager(),
				approve.getTrapp_no()
		};
		
		row=taDao.saveTravelApp(sql.toString(),param);
		Mog.d("SQL里面的trappe_no:",approve.getTrapp_no());
		Mog.d("SQL里面的trappe_no:",approve.getApprove_depmanager());
		Mog.d("SQL里面的trappe_no:",approve.getReturn_reason_depmanager());

		return row;
	}
	public int updateApprove(Approve approve){
		int row=0;
		StringBuffer sql = new StringBuffer();
		sql.append("update approve set approve_date_dapmanager = ? where trapp_no = ?");
		Object[] param = {
				approve.getApprove_date_dapmanager(),
				approve.getTrapp_no()
		};
		
		row=taDao.saveTravelApp(sql.toString(),param);
		Mog.d("SQL里面的trappe_no:",approve.getTrapp_no());
		Mog.d("SQL里面的通过时间:",approve.getApprove_date_dapmanager().toString());

		return row;
	}
}
