package our.dxz.trip.dao.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import our.dxz.trip.bean.User;
import our.dxz.trip.db.DBConn;

public class AdministratorDao extends DBConn
{
	public int addUser(String sql,Object[] params)
	{
		int efr = 0;
		
		try
		{
			efr = super.executeSQL(sql, params);
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return efr;
		
	}
	
	public List<User> getUsers(String sql)
	{
		List<User> users = new ArrayList<User>();
		
		try
		{
			pstm = super.getConnection().prepareStatement(sql);
			rs = pstm.executeQuery();
			
//			private int id;
//		    private String no;
//		    private String username;
//		    private String password;
//		    private int depid;
//		    private String type;
			
			while (rs.next()) 
			{
				User user = new User();
				
				user.setId(rs.getInt("id"));
				user.setNo(rs.getString("no"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setDepid(rs.getInt("depid"));
				
				Map<String, String> map = new HashMap<String, String>();
				//0:员工，1:部门经理，2：成本控制员，3：总经理，4.财务
				map.put("0", "员工");
				map.put("1", "部门经理");
				map.put("2", "成本控制员");
				map.put("3", "总经理");
				map.put("4", "财务");
				map.put("5", "管理员");
				
				
				user.setType(map.get(rs.getString("type")));
				
				users.add(user);
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		return users;
	}
	
	public int delUser(String sql)
	{
		int e = 0 ;
		try
		{
			e = super.executeSQL(sql, null);
		} catch (Exception e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return e;
	}
	
}
