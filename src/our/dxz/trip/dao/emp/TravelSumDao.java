package our.dxz.trip.dao.emp;

import java.util.ArrayList;
import java.util.List;

import our.dxz.trip.bean.TravelApp;
import our.dxz.trip.bean.TravelSum;
import our.dxz.trip.db.DBConn;

public class TravelSumDao extends DBConn {
	public  List<TravelSum> getTravelSums(String sql){
		List<TravelSum> list = new ArrayList<TravelSum>();
		try {
			pstm = super.getConnection().prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				TravelSum travelSum = new TravelSum();
				travelSum.setId(rs.getInt("id"));
				travelSum.setTrapp_no(rs.getString("trapp_no"));
				travelSum.setTitle(rs.getString("title"));
				travelSum.setContent(rs.getString("content"));
				travelSum.setName(rs.getString("name"));
				String tempstate=rs.getString("state");
				if("0".equals(tempstate)){
					tempstate="新建";
				}else if("1".equals(tempstate)) {
					tempstate="部门经理打回";
					
				}else if("2".equals(tempstate)){
					tempstate="通过";
					
				}
				travelSum.setState(tempstate);
				list.add(travelSum);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
	public List<TravelApp> getTrappNo(String sql){
		List<TravelApp> list = new ArrayList<TravelApp>();
		try {
			pstm = super.getConnection().prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()){
				TravelApp tempApp = new TravelApp();
				tempApp.setId(rs.getInt("id"));
				tempApp.setNo(rs.getString("no"));
				tempApp.setName(rs.getString("name"));
				tempApp.setBorrow_money(rs.getString("borrow_money"));
				tempApp.setTravel_place(rs.getString("travel_place"));
				String tempstate  = rs.getString("state");
				if("0".equals(tempstate)){
					tempstate="新建";
				}else if ("1".equals(tempstate)) {
					tempstate="部门经理打回";
				}else if ("2".equals(tempstate)) {
					tempstate="通过";
				}
				tempApp.setState(tempstate);
				list.add(tempApp);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	public int saveTravelSums(String sql,Object[] params){
		int result = 0;
		try {
			result = super.executeSQL(sql,params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	public int updateTravelSums(String sql,Object[] params){
		int result = 0;
		try {
			result=super.executeSQL(sql, params);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

}
