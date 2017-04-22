package our.dxz.trip.service.emp;

import java.util.List;

import our.dxz.trip.bean.ExpenseApp;
import our.dxz.trip.bean.ExpenseItem;
import our.dxz.trip.bean.TravelApp;
import our.dxz.trip.bean.TravelSum;
import our.dxz.trip.dao.emp.ExpenseAppDao;

public class ExpenseAppService
{
	private ExpenseAppDao eaDao = new ExpenseAppDao();
	
	public List<ExpenseApp> getExpenseApps()
	{
		
		String sqlString = "select * from expense_app";
		
		List<ExpenseApp> apps = eaDao.getTrExpenseApps(sqlString, null);
		
		return apps;
	}
	
	public List<ExpenseItem> getExpenseItems()
	{
		
		String sqlString = "select * from expense_item";
		
		List<ExpenseItem> apps = eaDao.getExpenseItems(sqlString, null);
		
		return apps;
	}
	
	public List<TravelSum> getTravelSums()
	{
		
		String sqlString = "select * from travel_sum where state = \'2\' and trapp_no not in(select trapp_no from expense_app)";
		
		List<TravelSum> apps = eaDao.getTravelSums(sqlString, null);
		
		return apps;
	}
	
	public int insertExpenseApp(ExpenseApp item)
	{
		int effectrow = 0;
		
		String sql = "insert into expense_app (trapp_no,expense,card_no,state,imgpath) values (?,?,?,?,?)";
		
		Object[] params = 
		{
				item.getTrapp_no(),
				item.getExpense(),
				item.getCard_no(),
				item.getState(),
				item.getImgpath()
		};
		
		effectrow = eaDao.insertExpenseApp(sql, params);
		
		return effectrow;
	}
	
	public int insertExpenseItem(ExpenseItem item)
	{
		int effectrow = 0;
		
		String sql = "insert into expense_item (`trapp_no`,`cost`,`type`,`desc`) values (?,?,?,?)";
		
		Object[] params = 
		{
				item.getTrapp_no(),
				item.getCost(),
				item.getType(),
				item.getDesc()
		};
		
		effectrow = eaDao.insertExpenseItemApp(sql, params);
		
		return effectrow;
	}
	
	public int reEditExpenseApp(ExpenseApp item)
	{
		int effectrow = 0;
		
		String sql = "update expense_app set expense = \'"+item.getExpense()+"\' , card_no = \'"+item.getCard_no()+"\' , state = \'"+item.getState()+"\'"
		+" where trapp_no = \'"+item.getTrapp_no()+"\'";
		
		effectrow = eaDao.insertExpenseApp(sql, null);
		
		return effectrow;
	}
	
}
