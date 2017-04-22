package our.dxz.trip.service.fin;

import java.util.List;

import our.dxz.trip.bean.ExpenseApp;
import our.dxz.trip.bean.ExpenseItem;
import our.dxz.trip.bean.TravelSum;
import our.dxz.trip.dao.emp.ExpenseAppDao;
import our.dxz.trip.dao.fin.AllExpenseDao;

public class AllExpenseService
{
	private AllExpenseDao eaDao = new AllExpenseDao();
	
	public List<ExpenseApp> getExpenseApps()
	{
		
		String sqlString = "select * from expense_app";
		
		List<ExpenseApp> apps = eaDao.getTrExpenseApps(sqlString, null);
		
		return apps;
	}
	
	
}
