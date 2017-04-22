package our.dxz.trip.dao.depmgr;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import our.dxz.trip.bean.ExpenseApp;
import our.dxz.trip.db.DBConn;

public class ApproveExpenseDao extends DBConn
{
	private static final String TAG = ApproveExpenseDao.class.getSimpleName();
	
	public List<ExpenseApp> getExpenseApps(String sql,Object[] params)
	{
		List<ExpenseApp> expenseApps = new ArrayList<ExpenseApp>();
		
		List<ExpenseApp> apps = new ArrayList<ExpenseApp>();
		
		try
		{
			pstm = super.getConnection().prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next()) 
			{
				/*
				private int id;
			    private String trapp_no;
			    private float expense;
			    private String card_no;
			    private String state;*/
				
				ExpenseApp expenseApp = new ExpenseApp();
				expenseApp.setId(rs.getInt("id"));
				expenseApp.setTrapp_no(rs.getString("trapp_no"));
				expenseApp.setExpense(rs.getFloat("expense"));
				expenseApp.setCard_no(rs.getString("card_no"));
				
//				报销申请状态(0:新建，1:部门经理拒绝2：部门经理打回，3：部门经理通过，4：总经理拒绝(>5000)，5.总经理打回(>5000)，6.总经理通过(>5000))
				String state = rs.getString("state");
				if("0".equals(state))
				{
					state = "新建";
				}else if("1".equals(state))
				{
					state = "部门经理拒绝";
				}else if("2".equals(state))
				{
					state = "部门经理打回";
				}
				else if("3".equals(state))
				{
					state = "部门经理通过";
				}
				else if("4".equals(state))
				{
					state = "总经理拒绝";
				}
				else if("5".equals(state))
				{
					state = "总经理打回";
				}
				else if("6".equals(state))
				{
					state = "总经理通过";
				}
				expenseApp.setState(state);
				
				apps.add(expenseApp);
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			super.closeAll(); 
		}
		
		return apps;
		
	}
	
	public int updateExpenseAndApprove(String sql)
	{
		try
		{
			return super.executeSQL(sql, null);
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return 0;
	}
	
	
}
