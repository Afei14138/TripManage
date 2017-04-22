package our.dxz.trip.config;

import our.dxz.trip.bean.User;

public class StorgeUser
{
	public static User cacheUser;

	public static User getCacheUser()
	{
		return cacheUser;
	}

	public static void setCacheUser(User cacheUser)
	{
		StorgeUser.cacheUser = cacheUser;
	}
	
}
