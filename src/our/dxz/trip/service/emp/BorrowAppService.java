package our.dxz.trip.service.emp;

import java.text.SimpleDateFormat;
import java.util.List;

import our.dxz.trip.dao.emp.BorrowAppDao;
import our.dxz.trip.bean.BorrowApp;
import our.dxz.trip.bean.TravelApp;
//import com.jereh.entity.TravelApp;

//import our.dxz.trip.bean.BorrowApp;

//import our.dxz.trip.dao.emp.BorrowAppDao;

public class BorrowAppService {
	BorrowAppDao taDao = new BorrowAppDao();
	public int saveBorrowApp(BorrowApp borrowApp){
		int row=0;
		StringBuffer sql = new StringBuffer();//给sql一个默认空间，只要在这个空间内都是一个地址
		sql.append("insert into borrow_app(trapp_no,cost,reason,state) values (?,?,?,?)");
		Object[] param = { 
				borrowApp.getTrapp_no(),
				borrowApp.getCost(),
				borrowApp.getReason(),
				borrowApp.getState(),
			
				 };
		//得到参数值
		row = taDao.saveBorrowApp(sql.toString(),param);
		return row;
	}
	public List<BorrowApp> getBorrowAppList(){
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from borrow_app");
		
		List<BorrowApp> list = taDao.getBorrowAppList(sql.toString());//去Dao中获得表单列表
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	//	for (BorrowApp borrowApp : list) {
			//borrowApp.setLeaveTime(borrowApp.getLeaveTime());
			
		//}
		return list;
	}//在出差申请中获得页面
	public List<TravelApp> getTravelAppList(){
		StringBuffer sql = new StringBuffer();
		
		sql.append("select no,dept,name,state from travel_app");
		
		List<TravelApp> list = taDao.getTravelAppList(sql.toString());
		//去Dao中获得表单列表
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	//	for (BorrowApp borrowApp : list) {
			//borrowApp.setLeaveTime(borrowApp.getLeaveTime());
			
		//}
		return list;
	}
	public int updateBorrowApp(BorrowApp borrowApp){
		int row=0;
		StringBuffer sql = new StringBuffer();//给sql一个默认空间，只要在这个空间内都是一个地址
		sql.append("update borrow_app set cost=?,reason=?,state=? where trapp_no=?");
		Object[] param = { 
				borrowApp.getCost(),
				borrowApp.getReason(),
				borrowApp.getState(),
				borrowApp.getTrapp_no(),
				
				 };
		//得到参数值
		row = taDao.updateBorrowApp(sql.toString(),param);
		return row;
	}
	
}
