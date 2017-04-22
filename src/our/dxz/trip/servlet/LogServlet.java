package our.dxz.trip.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import our.dxz.trip.service.LoginService;
import our.dxz.trip.utils.Mog;
import our.dxz.trip.bean.*;
import our.dxz.trip.config.StorgeUser;
public class LogServlet extends HttpServlet 
{
	
	private static final String TAG = LogServlet.class.getSimpleName();
	
	private static final long serialVersionUID = 1L;
	LoginService loginService = new LoginService();
	/**
	 * Constructor of the object.
	 */
	public LogServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
			{
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8"); 
		
		User user = new User();
		String username=request.getParameter("uname");
		String pwd=request.getParameter("upwd");
		System.out.println(username);
		System.out.println(pwd);
		
		user.setUsername(username);
		user.setPassword(pwd);
		
		request.setAttribute("loginUser", user);
		List<User> list= loginService.login(user);
		if (list.size() > 0)
		{
			StorgeUser.setCacheUser(list.get(0));
		}
		
		
		String type = null;
		for (User user2 : list) 
		{
			Mog.d(TAG, "user:"+"username:"+user2.getUsername());
			type = user2.getType();
		}
		if( list.size() != 0)
		{
			 request.setAttribute("result","success");
			
			 Cookie cookie_type = new Cookie("login", type);
			 cookie_type.setMaxAge(-1);
			 response.addCookie(cookie_type);
			 Mog.d(TAG, "cookie:");
			 Cookie[] cookies = request.getCookies();
				
			if (cookies != null)
			{
				for (int i = 0; i < cookies.length; i++)
				{
					System.out.println(TAG + "cookie:" + i + ":"
							+ cookies[i].getName() + ":"
							+ cookies[i].getValue());
				}
			}
				
			 
			if(type.equals("0"))
			{
				request.getRequestDispatcher("/index_emp.jsp").forward(request,response);
			}else if(type.equals("1")){
				request.getRequestDispatcher("/index_depmgr.jsp").forward(request,response);				
			}else if(type.equals("2")){
				request.getRequestDispatcher("/index_costctrl.jsp").forward(request,response);				
			}else if(type.equals("3")){
				request.getRequestDispatcher("/index_mgr.jsp").forward(request,response);				
			}else if(type.equals("4")){
				request.getRequestDispatcher("/index_fin.jsp").forward(request,response);			
			}else if(type.equals("5")){
				request.getRequestDispatcher("/index_admin.jsp").forward(request,response);			
			}
			
		}
		else 
		{
			request.setAttribute("result","用户名或密码错误");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	public void init() throws ServletException
	{
	}

}
