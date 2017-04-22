package our.dxz.trip.service.admin;

import java.util.List;

import our.dxz.trip.bean.User;
import our.dxz.trip.dao.admin.AdministratorDao;

public class AdministratorService
{
	private AdministratorDao dao = new AdministratorDao();
	
	public int addUser(User user)
	{
		String sql = "insert into user(no,username,password,depid,type) values (?,?,?,?,?)";
		Object [] params = 
		{
			user.getNo(),
			user.getUsername(),
			user.getPassword(),
			user.getDepid(),
			user.getType()
		};
		
		return dao.addUser(sql, params);
	}
	
	public List<User> getUsers()
	{
		String sql = "select * from user";
		return dao.getUsers(sql);
	}
	
	public int delUser(String id)
	{
		String sql = "delete from user where id = "+id;
		return dao.delUser(sql);
	}
	
}
