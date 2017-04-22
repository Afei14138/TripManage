package our.dxz.trip.dao.depmgr;

import java.util.ArrayList;
import java.util.List;

import our.dxz.trip.bean.Approve;
import our.dxz.trip.bean.BorrowApp;
import our.dxz.trip.db.DBConn;

public class ApproveBorrowDao extends DBConn{
	public List<BorrowApp> getBorrowAppList(String sql){
		List<BorrowApp> list = new ArrayList<BorrowApp>(); 
		try {
			pstm = super.getConnection().prepareStatement(sql);// 创建预编译的SQL语句对象
			rs = pstm.executeQuery();
			while (rs.next()) {
				

				BorrowApp borrowApp = new BorrowApp();
				borrowApp.setTrapp_no(rs.getString("trapp_no"));
				borrowApp.setCost(rs.getFloat("cost"));
				borrowApp.setReason(rs.getString("reason"));
				String state = rs.getString("state");
			    float cost = borrowApp.getCost();
				if("0".equals(state)){
					state = "新建";
					borrowApp.setState(state);
					
					list.add(borrowApp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.closeAll(); 
		}
		
		return list;
	}
	public void approve(String sql,Object[] params){
		try {
			super.executeSQL(sql,params);//参数sql是查询数据的SQL语句，查询参数，最后获得表中的数据
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
