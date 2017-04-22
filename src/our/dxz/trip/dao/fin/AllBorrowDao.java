package our.dxz.trip.dao.fin;

import java.util.ArrayList;
import java.util.List;

import our.dxz.trip.bean.BorrowApp;

import our.dxz.trip.db.DBConn;

public class AllBorrowDao extends DBConn{

	/**
	 * 执行保存操作
	 * @param sql
	 * @param params
	 * @return
	 */

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
		        if((("2".equals(state))&&rs.getFloat("cost")<5000)||("4".equals(state))){
					state = "通过";
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

}
