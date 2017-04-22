package our.dxz.trip.service.depmgr;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import our.dxz.trip.bean.ExpenseApp;
import our.dxz.trip.dao.depmgr.ApproveExpenseDao;

public class ApproveExpenseService
{
	private static final String TAG = ApproveExpenseService.class.getSimpleName();
	
	private ApproveExpenseDao approveExpenseDao;
	
	public ApproveExpenseService()
	{
		approveExpenseDao = new ApproveExpenseDao();
	}
	
	public List<ExpenseApp> getExpenseApps()
	{
		String sql = "select * from expense_app where state = \'0\'";
		
		return approveExpenseDao.getExpenseApps(sql, null);
	}
	
	public int updateExpenseAndApprove(String trapp_no,String newState,String iReason)
	{
		String upExpSql = "update expense_app set state = \'"+newState+"\'"
		+" where trapp_no = \'"+trapp_no+"\'";
		
		String upAppSql = "";
		
		if (newState.equals("1"))//部门经理拒绝
		{
			upAppSql = "update approve set refuse_reason_depmanager = \'"+iReason+"\'"
			+" where trapp_no = \'"+trapp_no+"\'";
			
		}else if (newState.equals("2"))//部门经理打回
		{
			upAppSql = "update approve set return_reason_depmanager = \'"+iReason+"\'"
			+" where trapp_no = \'"+trapp_no+"\'";
		}
		
		int efr = 0;
		
		efr += approveExpenseDao.updateExpenseAndApprove(upExpSql);
		efr += approveExpenseDao.updateExpenseAndApprove(upAppSql);
		
		return efr;
		
	}
	
	public int agreeExp(String trapp_no)
	{
		Date currentTime = new Date();
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime);
		
		Timestamp sTimestamp = Timestamp.valueOf(time);
		String sql0 = "update approve set approve_date_dapmanager = \'"+sTimestamp+"\' where trapp_no = "+"\'"+trapp_no+"\'";
		String sql = "update expense_app set state = \'3\' where trapp_no = "+"\'"+trapp_no+"\'";
		int efr = 0;
		 try
		{
			efr += approveExpenseDao.executeSQL(sql, null);
			efr += approveExpenseDao.executeSQL(sql0, null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		 
		 
		 return efr;
	}
	
	
}
