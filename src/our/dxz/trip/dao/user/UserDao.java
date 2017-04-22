package our.dxz.trip.dao.user;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import our.dxz.trip.bean.User;
import our.dxz.trip.db.DBConn;

public class UserDao extends DBConn {
	public List<User> login(String sql){
		ResultSet rs = null;
		int i=0;
		List<User> list = new ArrayList<User>();
		try {
			pstm = super.getConnection().prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				i++;
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setNo(rs.getString("no"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setDepid(rs.getInt("depid"));
				user.setType(rs.getString("type"));
				
				list.add(user);
			}
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return list;
	}

}
