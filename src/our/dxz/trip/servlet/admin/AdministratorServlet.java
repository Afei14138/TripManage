package our.dxz.trip.servlet.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import our.dxz.trip.bean.User;
import our.dxz.trip.service.admin.AdministratorService;
import our.dxz.trip.utils.Mog;

public class AdministratorServlet extends HttpServlet
{

private static final String TAG = AdministratorServlet.class.getSimpleName();
	
	private AdministratorService service = new AdministratorService();
	
	public AdministratorServlet()
	{
		super();
	}

	public void destroy()
	{
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		response.setContentType("text/html;charset=utf-8"); 
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		
		Mog.d(TAG, "action:"+action);
		
		if (action.equals("adduser"))
		{
			this.addUser(request, response);
		}
		
		if (action.equals("getUserList"))
		{
			String result = usersString();
			request.setAttribute("result", result);
			request.getRequestDispatcher("/admin/listuser.jsp").forward(request, response);
		}
		if (action.equals("deluser"))
		{
			this.delUser(request, response);
		}
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response)
	{
		String username = request.getParameter("username");
		String no = request.getParameter("no");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		
		Mog.d(TAG, "requse:"+" username:"+username+" no:"+no+" password:"+password+" type:"+type);
		
		User user = new User();
		user.setUsername(username);
		user.setNo(no);
		user.setPassword(password);
		user.setType(type);
		
		service.addUser(user);
		
	}
	
	private String usersString()
	{
		List<User> users = service.getUsers();
		
		return new Gson().toJson(users);
		
	}
	
	private void delUser(HttpServletRequest request, HttpServletResponse response)
	{
		String id = request.getParameter("id");
		service.delUser(id);
	}
	
	public void init() throws ServletException
	{
		
	}

}
