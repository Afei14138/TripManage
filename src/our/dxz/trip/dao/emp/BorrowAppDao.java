package our.dxz.trip.dao.emp;

import java.util.ArrayList;
import java.util.List;

import our.dxz.trip.bean.BorrowApp;
import our.dxz.trip.bean.TravelApp;

import our.dxz.trip.db.DBConn;

public class BorrowAppDao extends DBConn{

	/**
	 * 执行保存操作
	 * @param sql
	 * @param params
	 * @return
	 */
	public int saveBorrowApp(String sql,Object[] params){
		int result = 0;
		try {
			result = super.executeSQL(sql,params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}//获得查询结果
	public int updateBorrowApp(String sql,Object[] params){
		int result = 0;
		try {
			result = super.executeSQL(sql,params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}//获得查询结果
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
				}else if("1".equals(state)){
					state = "部门经理打回";
				}else if(("2".equals(state))){
					state = "部门经理通过";
				}else if("3".equals(state)){
					state = "总经理拒绝";
				}
				else if("4".equals(state)){
					state = "总经理通过";
				}
				borrowApp.setState(state);
				
				list.add(borrowApp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.closeAll(); 
		}
		
		return list;
	}
	public List<TravelApp> getTravelAppList(String sql){
		List<TravelApp> list = new ArrayList<TravelApp>(); 
		try {
			pstm = super.getConnection().prepareStatement(sql);// 创建预编译的SQL语句对象
			rs = pstm.executeQuery();
			while (rs.next()) {
				
				TravelApp travelApp = new TravelApp();
				  String no = rs.getString("no");
			      travelApp.setNo(no);
			      String dept = rs.getString("dept");
			      travelApp.setDept(dept);
			      String name=rs.getString("name");
			      travelApp.setName(name);
			     // System.out.println("no is:"+no);
				//travelApp.setName("name");
				String state = rs.getString("state");
				if("6".equals(state)){
					travelApp.setState("同意");
					list.add(travelApp);
				}
					
			//	System.out.println("state is:"+state);
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.closeAll(); 
		}
		
		return list;
	}
}
