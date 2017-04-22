package our.dxz.trip.dao.emp;

import java.util.ArrayList;
import java.util.List;

import our.dxz.trip.bean.TravelApp;
import our.dxz.trip.db.DBConn;

public class TravelAppDao extends DBConn{

	/**
	 * 执行保存操作
	 * @param sql
	 * @param params
	 * @return
	 */
	public int saveTravelApp(String sql,Object[] params){
		int result = 0;
		try {
			result = super.executeSQL(sql,params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public int updateTravelApp(String sql,Object[] params){
		int result = 0;
		try {
			result = super.executeSQL(sql,params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 获取申请单列表
	 * */
	public List<TravelApp> getTravelAppList(String sql){
		List<TravelApp> list = new ArrayList<TravelApp>(); 
		try {
			pstm = super.getConnection().prepareStatement(sql);// 创建预编译的SQL语句对象
			rs = pstm.executeQuery();
			while (rs.next()) {
				TravelApp travelApp = new TravelApp();
				travelApp.setNo(rs.getString("no"));
				travelApp.setName(rs.getString("name"));
				travelApp.setDept(rs.getString("dept"));
				travelApp.setBorrow_money(rs.getString("borrow_money"));
				travelApp.setTravel_reason(rs.getString("travel_reason"));
				travelApp.setTravel_place(rs.getString("travel_place"));
				travelApp.setFollow_name(rs.getString("follow_name"));
				travelApp.setPlan_time(rs.getString("plan_time"));
				travelApp.setLeave_time(rs.getDate("leave_time"));

				
				travelApp.setBack_time(rs.getDate("back_time"));
//				travelApp.setBackTime(rs.getDate("back_time"));
				travelApp.setTravel_plan(rs.getString("travel_plan"));
				
				String state = rs.getString("state");
				if("0".equals(state)){
					state = "新建";
				}else if("1".equals(state)){
					state = "部门经理拒绝";
				}else if("2".equals(state)){
					state = "部门经理打回";
				}else if("3".equals(state)){
					state = "部门经理通过";
				}else if("4".equals(state)){
					state = "成本控制拒绝";
				}else if("5".equals(state)){
					state = "成本控制打回";
				}else{
					state = "成本控制通过";
				}
				
				travelApp.setState(state);
				
				list.add(travelApp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.closeAll(); 
		}
		
		return list;
	}
	
	/**
	 * 处理出差申请
	 * @param sql
	 * @param params
	 */
	public void approve(String sql,Object[] params){
		try {
			super.executeSQL(sql,params);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
