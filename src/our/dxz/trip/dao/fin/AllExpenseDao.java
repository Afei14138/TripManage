package our.dxz.trip.dao.fin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import our.dxz.trip.bean.ExpenseApp;
import our.dxz.trip.db.DBConn;
import our.dxz.trip.service.fin.AllExpenseService;

public class AllExpenseDao extends DBConn
{
	public List<ExpenseApp> getTrExpenseApps(String sql,Object[] params)
	{
		
		List<ExpenseApp> apps = new ArrayList<ExpenseApp>();
		
		try
		{
			pstm = super.getConnection().prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next()) 
			{

				ExpenseApp expenseApp = new ExpenseApp();
				expenseApp.setId(rs.getInt("id"));
				expenseApp.setTrapp_no(rs.getString("trapp_no"));
				expenseApp.setExpense(rs.getFloat("expense"));
				expenseApp.setCard_no(rs.getString("card_no"));
				
//				报销申请状态(0:新建，1:部门经理拒绝2：部门经理打回，3：部门经理通过，4：总经理拒绝(>5000)，5.总经理打回(>5000)，6.总经理通过(>5000))
				String state = rs.getString("state");
				if(("3".equals(state)&&rs.getFloat("expense")<5000)||"6".equals(state))
				{
					expenseApp.setState(state);
				}
				
				
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
}
