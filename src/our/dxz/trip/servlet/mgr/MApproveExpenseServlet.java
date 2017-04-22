package our.dxz.trip.servlet.mgr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import our.dxz.trip.service.mgr.MApproveExpenseService;
import our.dxz.trip.service.emp.ExpenseAppService;
import our.dxz.trip.servlet.depmgr.ApproveExpenseServlet;
import our.dxz.trip.servlet.emp.ExpenseAppServlet;
import our.dxz.trip.utils.Mog;

public class MApproveExpenseServlet extends HttpServlet
{private static final String TAG = ApproveExpenseServlet.class.getSimpleName()+":";

private MApproveExpenseService approveExpenseService;
	/**
	 * Constructor of the object.
	 */
	public MApproveExpenseServlet()
	{
		super();
		approveExpenseService = new MApproveExpenseService();
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

	this.doPost(request, response);
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
		response.setContentType("text/html;charset=utf-8"); 
		
		String action = request.getParameter("action");
		Mog.d(action, "action:" +action);
		
		if (action.equals("getExpenseAppList"))
		{
			String result = this.getExpenseAppsString(request,response);
			request.setAttribute("result", result);
						
			request.getRequestDispatcher("/mgr/Mlist_expenseapp.jsp").forward(request, response);
		}
		
		else if (action.equals("updateExpense"))
		{
			this.updateExpense(request, response);
		}
		
		else if (action.equals("upExOnly"))
		{
			this.updateExpenseOnly(request, response);
		}
		
		
		
	}
	private String getExpenseAppsString(HttpServletRequest request, HttpServletResponse response)
	{
		return new Gson().toJson(approveExpenseService.getExpenseApps());
	}
	
	private void updateExpense(HttpServletRequest request, HttpServletResponse response)
	{
		String trapp_no = request.getParameter("trapp_no");
		String newState = request.getParameter("newstate");
		String iReason = request.getParameter("ireason");
		
		Mog.d(TAG, "updateExpense():"+" trapp_no:"+trapp_no+" newState:"+newState+" ireason:"+iReason);
		
		approveExpenseService.updateExpenseAndApprove(trapp_no, newState, iReason);
		
	}
	
	private void updateExpenseOnly(HttpServletRequest request, HttpServletResponse response)
	{
		String trapp_no = request.getParameter("trapp_no");
		approveExpenseService.agreeExp(trapp_no);
		
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
	

}
