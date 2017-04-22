package our.dxz.trip.service;

import java.util.ArrayList;
import java.util.List;

import our.dxz.trip.bean.*;
import our.dxz.trip.dao.user.UserDao;

public class LoginService {
	
    UserDao userDao = new UserDao();
	public List<User> login(User user ) 
	
	{
		List<User> list = new ArrayList<User>();
		System.out.println("service receive"+user.getUsername());
		System.out.println("service receive"+user.getPassword());
		String sql = "select * from user where username="+"'"+user.getUsername()+"'"+" and password="+"'"+user.getPassword()+"'";
		list = userDao.login(sql);
		
		return list;
	}

}
