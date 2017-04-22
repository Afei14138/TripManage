package our.dxz.trip.service.emp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.sun.org.apache.regexp.internal.recompile;

import our.*;
import our.dxz.trip.bean.TravelApp;
import our.dxz.trip.bean.TravelSum;
import our.dxz.trip.dao.emp.TravelSumDao;

public class TravelSumService {
	TravelSumDao travelSumDao = new TravelSumDao();
	//从数据库中查询正确的数据！！！！！！
	public List<TravelSum> getSumList(){
		String sql = "select * from travel_sum;";
		List<TravelSum> list = travelSumDao.getTravelSums(sql);
		return list;
	}
	//从数据库查询已经通过的单号
	public List<TravelApp> getTrappNo(){
		String sql = "select id,no,name,borrow_money,travel_place,state from travel_app where state='6';";
		List<TravelApp> list = travelSumDao.getTrappNo(sql);
		return list;		
		
	}
	//将总结表存储到数据库
	public int saveSums(TravelSum travelSum){
		int row=0;
		StringBuffer sql = new StringBuffer();
		sql.append("insert into travel_sum(title,trapp_no,content,name,state) values (?,?,?,?,?)");
		Object[] param = { 
				travelSum.getTitle(),
				travelSum.getTrapp_no(),
				travelSum.getContent(),
				travelSum.getName(),
				travelSum.getState()
		};
		row = travelSumDao.saveTravelSums(sql.toString(),param);
		return row;
		
		
	}
	public int updateSum(TravelSum travelSum){
		int row = 0;
		StringBuffer sql = new StringBuffer();
		sql.append("update travel_sum set title=?,content=?,name=?,state= 0 where trapp_no=?");
		Object[] param = {
				travelSum.getTitle(),
				travelSum.getContent(),
				travelSum.getName(),
				travelSum.getTrapp_no()
		};
		row = travelSumDao.updateTravelSums(sql.toString(), param);
		return row;
		
	}

}
