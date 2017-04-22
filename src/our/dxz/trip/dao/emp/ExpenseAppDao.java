package our.dxz.trip.dao.emp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import our.dxz.trip.bean.ExpenseApp;
import our.dxz.trip.bean.ExpenseItem;
import our.dxz.trip.bean.TravelApp;
import our.dxz.trip.bean.TravelSum;
import our.dxz.trip.db.DBConn;
import our.dxz.trip.service.emp.ExpenseAppService;

public class ExpenseAppDao extends DBConn
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
			
				/*private int id;
				private String no;
				private String dept;
				private String name;
				private String follow_name;
				private String borrow_money;
				private String cost_type;
				private String travel_reason;
				private String travel_place;
				private String plan_time;
				private Date back_time;
				private Date leave_time;
				private Date create_time;
				private String travel_plan;
				private String state;*/
				
				/*TravelApp travelApp = new TravelApp();
				travelApp.setId(rs.getInt("id"));
				travelApp.setNo(rs.getString("no"));
				travelApp.setDept(rs.getString("dept"));
				travelApp.setName(rs.getString("name"));
				travelApp.setBorrow_money(rs.getString("borrow_money"));
				travelApp.setCost_type(rs.getString("cost_type"));
				travelApp.setTravel_plan(rs.getString("travel_reason"));
				travelApp.setTravel_place(rs.getString("travel_place"));
				travelApp.setPlan_time(rs.getString("plan_time"));
				travelApp.setBack_time(rs.getDate("back_time"));
				travelApp.setLeave_time(rs.getDate("leave_time"));	
				travelApp.setLeave_time(rs.getDate("create_time"));	
				travelApp.setTravel_plan(rs.getString("travel_plan"));
				travelApp.setTravel_plan(rs.getString("state"));*/
				
				
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
				
				expenseApp.setImgpath(rs.getString("imgpath"));
				
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
	
	public List<ExpenseItem> getExpenseItems(String sql,Object[] params)
	{
		List<ExpenseItem> items = new ArrayList<ExpenseItem>();
		try
		{
			pstm = super.getConnection().prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next())
			{
//				private int id;
//			    private String trapp_no;
//			    private String cost;
//			    private String type;
//			    private String desc;
				
				ExpenseItem item = new ExpenseItem();
				item.setId(rs.getInt("id"));
				item.setTrapp_no(rs.getString("trapp_no"));
				item.setCost(rs.getString("cost"));
				item.setType(rs.getString("type"));
				item.setDesc(rs.getString("desc"));
				
				items.add(item);
				
			}
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return items;
	
	}
	
	public List<TravelSum> getTravelSums(String sql,Object[] params)
	{
		
		List<TravelSum> apps = new ArrayList<TravelSum>();
		
		try
		{
			pstm = super.getConnection().prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while (rs.next()) 
			{
				
//				private int id;
//				private String title;
//				private String trapp_no;
//				private String content;
//				private String state;
//				private String name;
				
				TravelSum sum = new TravelSum();
				sum.setId(rs.getInt("id"));
				sum.setTitle(rs.getString("title"));
				sum.setTrapp_no(rs.getString("trapp_no"));
				sum.setContent(rs.getString("content"));
				sum.setName(rs.getString("name"));
				
				String st = rs.getString("state");
				String state = "新建";
				if("0".equals(st))
				{
					state = "新建";
				}else if("1".equals(st))
				{
					state = "部门经理打回";
				}else if("2".equals(st))
				{
					state = "部门经理通过";
				}
				
				sum.setState(state);
				
				apps.add(sum);
				
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
	
	public int insertExpenseApp(String sql,Object[] params)
	{
		int effectrow = 0;
		
		try
		{
			effectrow = super.executeSQL(sql, params);
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return effectrow;
	}
	
	public int insertExpenseItemApp(String sql,Object[] params)
	{
		int effectrow = 0;
		
		try
		{
			effectrow = super.executeSQL(sql, params);
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return effectrow;
	}
	
	public int updateExpenseApp(String sql,Object[] params)
	{
		int effectrow = 0;
		
		try
		{
			effectrow = super.executeSQL(sql, params);
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return effectrow;
	}
	
}
