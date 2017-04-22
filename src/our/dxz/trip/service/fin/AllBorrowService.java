package our.dxz.trip.service.fin;

import java.util.List;

import our.dxz.trip.bean.BorrowApp;
import our.dxz.trip.dao.emp.BorrowAppDao;

public class AllBorrowService {
	BorrowAppDao taDao = new BorrowAppDao();
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
}
