package our.dxz.trip.servlet.fin;

import java.io.IOException;
import our.dxz.trip.service.emp.ExpenseAppService;
import our.dxz.trip.service.fin.AllExpenseService;
import our.dxz.trip.utils.Mog;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import our.dxz.trip.bean.ExpenseApp;

public class AllExpenseServlet extends HttpServlet
{
	AllExpenseService eaService = new AllExpenseService();
	/**
	 * Constructor of the object.
	 */
	public AllExpenseServlet()
	{
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy()
	{
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		doPost(request,response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String action = (String)request.getParameter("action");
		
		Mog.d("财务查看报销申请:",action);
		
		if (action.equals("getExpenseAppList"))
		{
			String resString = this.getExpenseAppListString();
		//	String resString2 = this.getExpenseItemString();
			request.setAttribute("result", resString);
			//request.setAttribute("result2", resString2);
						
			request.getRequestDispatcher("/fin/list_expenseapp.jsp").forward(request, response);
			
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException
	{
		// Put your code here
	}
	private String getExpenseAppListString()
	{
		
		List<ExpenseApp> apps = eaService.getExpenseApps();
		
		Gson gson = new Gson();
		String resultJson = gson.toJson(apps);
		
	
		
		return resultJson;
	}
	
}
