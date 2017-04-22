package our.dxz.trip.service.mgr;

import java.util.List;

import our.dxz.trip.bean.Approve;
import our.dxz.trip.bean.BorrowApp;
import our.dxz.trip.dao.mgr.MApproveBorrowDao;

public class MApproveBorrowService {
	MApproveBorrowDao taDao = new MApproveBorrowDao();
	public List<BorrowApp> getBorrowAppList(){
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from borrow_app");
		
		List<BorrowApp> list = taDao.getBorrowAppList(sql.toString());//去Dao中获得表单列表
		return list;
	}


public void agreeBorrowApp(Approve approve){
	StringBuffer sqlborr = new StringBuffer();
	sqlborr.append("update borrow_app ta set ta.state = 4 where ta.trapp_no = ?");
	Object[] param = { 
			approve.getTrapp_no(),
		 };
	taDao.approve(sqlborr.toString(),param);
	StringBuffer sqlappr = new StringBuffer();
    sqlappr.append("update approve set approve_date_dapmanager=? where trapp_no=?");
    Object[] appparam = { 
			//approve.getApprove_depmanager(),
			approve.getApprove_date_manager(),
			approve.getTrapp_no(),
			
			 };
	//在Dao中执行approve，得到表中的数据
	taDao.approve(sqlappr.toString(),appparam);//在Dao中执行approve，得到表中的数据
}

/**
 * 打回出差申请
 * */
public void disagreeBorrowApp(Approve approve){
	StringBuffer sqlborr = new StringBuffer();
	sqlborr.append("update borrow_app ta set ta.state = 3 where ta.trapp_no = ?");
	
	Object[] param = {approve.getTrapp_no()};
	
	taDao.approve(sqlborr.toString(),param);
	
	
	StringBuffer sqlappr=new StringBuffer();
	sqlappr.append("update approve set approve_depmanager=?,return_reason_depmanager=? where trapp_no=?");
	
	 Object[] appparam = { 
				approve.getApprove_manager(),
				approve.getRefuse_reason_manager(),
				approve.getTrapp_no(),
				
				 };
	//taDao.approve(sqlborr.toString(),param);//在Dao中执行approve，得到表中的数据
	taDao.approve(sqlappr.toString(),appparam);//在Dao中执行approve，得到表中的数据
	
}

}

