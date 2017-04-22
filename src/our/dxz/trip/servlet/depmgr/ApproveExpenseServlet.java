package our.dxz.trip.servlet.depmgr;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import our.dxz.trip.service.depmgr.ApproveExpenseService;
import our.dxz.trip.utils.Mog;

public class ApproveExpenseServlet extends HttpServlet
{

	private static final String TAG = ApproveExpenseServlet.class.getSimpleName()+":";
	
	private ApproveExpenseService approveExpenseService;
	
	public ApproveExpenseServlet()
	{
		super();
		approveExpenseService = new ApproveExpenseService();
		Mog.d(TAG, "constructed function");
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
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8"); 
		
		String action = request.getParameter("action");
		Mog.d(action, "action:" +action);
		
		if (action.equals("getExpenseAppList"))
		{
			String result = this.getExpenseAppsString(request,response);
			request.setAttribute("result", result);
						
			request.getRequestDispatcher("/dep_mgr/list_expenseapp.jsp").forward(request, response);
		}
		
		if (action.equals("updateExpense"))
		{
			this.updateExpense(request, response);
		}
		
		if (action.equals("upExOnly"))
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
	
	public void init() throws ServletException
	{
	}

}
