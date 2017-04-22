package our.dxz.trip.service.cost_ctrl;

import java.text.SimpleDateFormat;
import java.util.List;

import our.dxz.trip.bean.Approve;
import our.dxz.trip.bean.TravelApp;
import our.dxz.trip.dao.cost_ctrl.CostControlDao;
import our.dxz.trip.utils.Mog;

public class CostControlService {
	CostControlDao taDao = new CostControlDao();
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
	public void agree(String no)
	{
		StringBuffer sql = new StringBuffer();
		sql.append("update travel_app ta set ta.state = 6 where ta.no = ?");
		Object[] param = {no};
	    	
		taDao.approve(sql.toString(),param);
		
	}
	/**
	 * 打回出差申请
	 * */
	public void disagree(String no){
		StringBuffer sql = new StringBuffer();
		sql.append("update travel_app ta set ta.state = 5 where ta.no = ?");
		Object[] param = {no};
		
		taDao.approve(sql.toString(),param);
	}
	public void refuse(String no){
		StringBuffer sql = new StringBuffer();
		sql.append("update travel_app ta set ta.state = 4 where ta.no = ?");
		Object[] param = {no};
		
		taDao.approve(sql.toString(),param);
	}
	public int updateApproveRf(Approve approve){
		int row=0;
		StringBuffer sql = new StringBuffer();
		sql.append("update approve set approve_costcontroller= ?,refuse_reson_costcontroller=? where trapp_no = ?");
		Object[] param = {
				approve.getApprove_costcontroller(),
				approve.getRefuse_reson_costcontroller(),
				approve.getTrapp_no()
		};
		Mog.d("SQL里面的trappe_no:",approve.getTrapp_no());
		Mog.d("SQL里面的trappe_no:",approve.getApprove_costcontroller());
		Mog.d("SQL里面的refuse_no:",approve.getRefuse_reson_costcontroller());
		row=taDao.saveTravelApp(sql.toString(),param);
		return row;
		
	}
	public int updateApproveDis(Approve approve){
		int row=0;
		StringBuffer sql = new StringBuffer();
		sql.append("update approve set approve_costcontroller = ?,return_reason_costcontroller=? where trapp_no = ?");
		Object[] param = {
				approve.getApprove_costcontroller(),
				approve.getReturn_reason_costcontroller(),
				approve.getTrapp_no()
		};
		
		row=taDao.saveTravelApp(sql.toString(),param);
		Mog.d("SQL里面的trappe_no:",approve.getTrapp_no());
		Mog.d("SQL里面的trappe_no:",approve.getApprove_costcontroller());
		Mog.d("SQL里面的trappe_no:",approve.getReturn_reason_costcontroller());

		return row;
	}
	public int updateApprove(Approve approve){
		int row=0;
		StringBuffer sql = new StringBuffer();
		sql.append("update approve set approve_date_costcontroller= ? where trapp_no = ?");
		Object[] param = {
				approve.getApprove_date_costcontroller(),
				approve.getTrapp_no()
		};
		
		row=taDao.saveTravelApp(sql.toString(),param);
		Mog.d("SQL里面的trappe_no:",approve.getTrapp_no());
		Mog.d("SQL里面的通过时间:",approve.getApprove_date_costcontroller().toString());

		return row;
	}
}
