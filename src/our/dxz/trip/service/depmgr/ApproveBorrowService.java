package our.dxz.trip.service.depmgr;

import java.text.SimpleDateFormat;
import java.util.List;

import our.dxz.trip.bean.Approve;
import our.dxz.trip.bean.BorrowApp;
import our.dxz.trip.dao.depmgr.ApproveBorrowDao;

public class ApproveBorrowService {
	ApproveBorrowDao taDao = new ApproveBorrowDao();
	public List<BorrowApp> getBorrowAppList(){
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from borrow_app");
		
		List<BorrowApp> list = taDao.getBorrowAppList(sql.toString());//去Dao中获得表单列表
		return list;
	}//在出差申请中获得页面
	/*public void agree(String trapp_no)
	{StringBuffer sqlborr = new StringBuffer();
	sqlborr.append("update borrow_app ta set ta.state = 2 where ta.trapp_no = ?");
	//Object[] param = {trapp_no};
			
		
	Object[] param = {trapp_no};
	taDao.approve(sqlborr.toString(),param);
		
	}*/
	public void agreeBorrowApp(Approve approve){
		StringBuffer sqlborr = new StringBuffer();
		sqlborr.append("update borrow_app ta set ta.state = 2 where ta.trapp_no = ?");
		Object[] param = { 
				approve.getTrapp_no(),
			 };
		taDao.approve(sqlborr.toString(),param);
		StringBuffer sqlappr = new StringBuffer();
        sqlappr.append("update approve set approve_date_dapmanager=? where trapp_no=?");
        Object[] appparam = { 
				//approve.getApprove_depmanager(),
				approve.getApprove_date_dapmanager(),
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
		sqlborr.append("update borrow_app ta set ta.state = 1 where ta.trapp_no = ?");
		
		Object[] param = {approve.getTrapp_no()};
		
		taDao.approve(sqlborr.toString(),param);
		
		
		StringBuffer sqlappr=new StringBuffer();
		sqlappr.append("update approve set approve_depmanager=?,return_reason_depmanager=? where trapp_no=?");
		
		 Object[] appparam = { 
					approve.getApprove_depmanager(),
					approve.getReturn_reason_depmanager(),
					approve.getTrapp_no(),
					
					 };
		//taDao.approve(sqlborr.toString(),param);//在Dao中执行approve，得到表中的数据
		taDao.approve(sqlappr.toString(),appparam);//在Dao中执行approve，得到表中的数据
		
	}

}
